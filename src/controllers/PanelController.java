package controllers;

import analysis.Analyser;
import javaCC.CasperParser;
import javaCC.ParseException;
import model.ErrorLocation;
import model.Process_;
import model.msg.Message;
import model.Protocol;
import model.spec.*;
import util.PrettyPrinter;
import util.Reader;
import view.EditorPanel;
import view.MainPanel;
import view.VisPanel;
import view.Visualiser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Controller for the Application.
 * @author Conor
 */

public class PanelController {
    private MainPanel mainPanel;
    private EditorPanel editorPanel;
    private VisPanel visPanel;
    private Protocol protocol;
    private String filePath;

    final String SAVE_PIC_FILEPATH = "C:/Users/Conor/IdeaProjects/Facsp/src/res/save.png";
    final String RESET_PIC_FILEPATH = "C:/Users/Conor/IdeaProjects/Facsp/src/res/reset.png";
    final String EXIT_PIC_FILEPATH = "C:/Users/Conor/IdeaProjects/Facsp/src/res/exit.png";

    /**
     * @param mainPanel Main panel to be set
     * @param editorPanel Editor panel to be set
     * @param visPanel Vis panel to be set
     * @param protocol Protocol object to contain the analysed Protocol
     */

    public PanelController(MainPanel mainPanel, EditorPanel editorPanel, VisPanel visPanel, Protocol protocol) {
        this.mainPanel = mainPanel;
        this.editorPanel = editorPanel;
        this.visPanel = visPanel;
        this.protocol = protocol;
        initEditorFunc();
        initMainFunc();
        this.filePath = "";
    }

    /**
     * Initialises the editor panel functionality
     */

    public void initEditorFunc() {
        editorPanel.add(initMenuBar(), BorderLayout.NORTH);
    }

    /**
     * Returns functional JMenuBar for use in the Editor panel.
     * @return JMenuBar
     *
     */

    public JMenuBar initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");

        JMenuItem save = new JMenuItem(new AbstractAction("Save & select this script") {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveScript();
            }
        });
        save.setIcon(new ImageIcon(SAVE_PIC_FILEPATH));

        JMenuItem reset = new JMenuItem(new AbstractAction("Reset editor") {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetEditor();
            }
        });
        reset.setIcon(new ImageIcon(RESET_PIC_FILEPATH));

        JMenuItem exit = new JMenuItem(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exit.setIcon(new ImageIcon(EXIT_PIC_FILEPATH));

        file.add(save);
        file.add(reset);
        file.add(exit);
        menuBar.setVisible(true);
        menuBar.add(file);
        menuBar.add(edit);
        return menuBar;
    }

    /**
     * Saves a file to disk. For use in the editorPanel.
     */

    public void saveScript() {
        final JFileChooser SaveAs = new JFileChooser();
        SaveAs.setApproveButtonText("Save & select script");
        int actionDialog = SaveAs.showOpenDialog(editorPanel);
        if (actionDialog != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File fileName = new File(SaveAs.getSelectedFile() + ".spl");
        BufferedWriter outFile = null;
        try {
            outFile = new BufferedWriter(new FileWriter(fileName));


            editorPanel.getEditor().write(outFile);   // *** here: ***
            filePath = fileName.getPath();
            mainPanel.getFileLabel().setText("File Selected: " + fileName.getName());

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (outFile != null) {
                try {
                    outFile.close();
                } catch (IOException e) {

                }
            }
        }
    }

    /**
     * Highlights a line in the editor panel JTextArea
     * @param lineNo Line to be highlighted
     */


    public void highlightLine(int lineNo) {
        Highlighter highlighter = editorPanel.getEditor().getHighlighter();
        highlighter.removeAllHighlights();

        try {
            int offset = editorPanel.getEditor().getLineStartOffset(lineNo);
            highlighter.addHighlight(offset, editorPanel.getEditor().getLineEndOffset(lineNo), new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW));
        } catch (BadLocationException b) { // Will never be thrown as line number comes from CasperParser
            mainPanel.getErrorLabel().setText("Highlighting error");
        }
    }

    /**
     * Returns the line number of an error in a Casper Script.
     * @param p ParseException
     *
     */

    public ErrorLocation getLineNumberFromParseException(ParseException p) {
        int lineNumberOffset = p.toString().indexOf("line") + 5;
        int columnNumberOffset = p.toString().indexOf("column") + 7;
        return new ErrorLocation(Integer.parseInt(p.toString().substring(lineNumberOffset,p.toString().indexOf(","))),Integer.parseInt(p.toString().substring(columnNumberOffset,p.toString().indexOf("\n") - 2)));
    }

    /**
     * Initialises main panel functionality
     */

    public void initMainFunc() {
        mainPanel.getStart().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (filePath.equals("")) {
                    mainPanel.getErrorLabel().setText("Error : No script selected.");
                    return;
                }
                try {
                    // Sets protocol private var.
                    resetHighlighter();
                    parseProtocol();
                    protocol.setInitAndRespInfo();
                    setSpecLabel(protocol);

                    Analyser analyser = new Analyser(protocol);
                    analyser.generateImpersonators();
                    initVisualiser(analyser.getInitiatorImpersonation(),analyser.getResponderImpersonation());
                } catch (ParseException p) {
                    System.out.println(p);
                    ErrorLocation errorLocation = getLineNumberFromParseException(p);
                    highlightLine(errorLocation.getLineNumber() - 1);
                    mainPanel.getErrorLabel().setText("Error: Line " + errorLocation.getLineNumber() + " Column: " + errorLocation.getColumnNumber() + ". See editor.");
                } catch (FileNotFoundException f) {
                    mainPanel.getErrorLabel().setText("File path incorrect");
                }
            }
        });

        mainPanel.getChooseProtocol().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Casper Scripts (.spl)", "spl", "spl~");
                chooser.setFileFilter(filter);
                int option = chooser.showOpenDialog(mainPanel);
                if (option == JFileChooser.APPROVE_OPTION) {
                    Reader reader = new Reader();
                    File file = chooser.getSelectedFile();
                    editorPanel.getEditor().setText(reader.readFile(file));
                    setFilePath(file.getPath());
                    mainPanel.getFileLabel().setText("Casper Script Selected: " + file.getName());
                    mainPanel.getErrorLabel().setText("<html>Error: None</html>");
                }
            }
        });

        mainPanel.getReset().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetUI();
            }
        });
    }

    /**
     * Sets the specification label in the mainpanel
     * @param protocol Protocol which contains the specifications
     *
     */

    public void setSpecLabel(Protocol protocol){
        StringBuilder builder = new StringBuilder();
        int count = 1;
        builder.append("Specifications: <br>");
        for(Specification spec : protocol.getSpecifications()){
            builder.append(count + ". ");
            builder.append(specToStringLabel(spec));
            builder.append("<br>");
            count++;
        }
        mainPanel.getInfoLabel().setText("<html>" + builder.toString() + "</html>");
    }


    /**
     * Parses the file, specified by the filepath
     * @exception ParseException - JavaCC error
     * @exception FileNotFoundException if the filepath is null or incorrect
     */
    public void parseProtocol() throws ParseException, FileNotFoundException {
        CasperParser parser = new CasperParser(new java.io.FileInputStream(getFilePath()));
        protocol = parser.script();

    }

    /**
     * Initialises visualiser
     * @param init Initiator messageList
     * @param resp Responder messageList
     */

    public void initVisualiser(ArrayList<Message> init, ArrayList<Message> resp ) {
        Visualiser visualiser = new Visualiser(protocol.getMessages(), init, resp);
        visPanel.setVisualiser(visualiser);
        visPanel.getVisualiserPanel().add(visualiser, BorderLayout.CENTER);
        visPanel.getVisualiserPanel().revalidate();

    }

    /**
     * Resets the UI
     */

    public void resetUI() {
        resetLabels();
        resetEditor();
        setProtocol(new Protocol());
        resetVisPanel();
    }

    /**
     * Resets all labels
     */

    public void resetLabels() {
        mainPanel.getErrorLabel().setText("Error: None");
        mainPanel.getFileLabel().setText("Casper Script Selected: None");
        mainPanel.getInfoLabel().setText("Specifications: None");
    }

    /**
     * Resets the editor panel and filepath.
     */

    public void resetEditor() {
        editorPanel.getEditor().setText(EditorPanel.CASPER_LAYOUT_GUIDE);
        setFilePath("");
    }

    /**
     * Resets the Vis panel
     */

    public void resetVisPanel() {
        if (visPanel.getVisualiser() != null) {
            visPanel.getVisualiserPanel().remove(visPanel.getVisualiser());
            visPanel.setVisualiser(null);
            visPanel.revalidate();
            visPanel.repaint();
        }
    }

    /**
     * Resets the Highlighter
     */

    public void resetHighlighter(){
        Highlighter highlighter = editorPanel.getEditor().getHighlighter();
        highlighter.removeAllHighlights();
    }

    /**
     * Resets the UI
     * @param specification Specification to be prettied
     * @return String prettied for the view
     */

    public String specToStringLabel(Specification specification){
        if (specification instanceof Secret){
            return ("Secret. Value: " + ((Secret) specification).getSecret() + ". Participants: " + ((Secret) specification).getProposerId() + " and " + ((Secret) specification).getPermitted());
        }
        if(specification instanceof StrongSecret){
            return ("StrongSecret. Value:  " + ((StrongSecret) specification).getSecret() + ". Participants: " + ((StrongSecret) specification).getProposerId() + " and " + ((StrongSecret) specification).getPermitted());
        }
        if(specification instanceof Agreement){
            return ("Agreement. Values: " + ((Agreement) specification).getAgreedUpon() + ". Participants: " + ((Agreement) specification).getParticipant1() + " and " + ((Agreement) specification).getParticipant2());
        }

        if(specification instanceof Aliveness){
            return ("Aliveness. Participants: " + ((Aliveness) specification).getParticipant1() + " and " + ((Aliveness) specification).getParticipant2());
        }
        return "";
    }

    /**
     * Returns the protocol
     * @return Protocol
     */

    public Protocol getProtocol() {
        return protocol;
    }

    /**
     * Sets the protocol
     * @param protocol protocol to be set
     */

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    /**
     * Returns the filepath
     * @return String - Filepath of file to be parsed.
     *
     */

    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets the filepath
     * @param filePath String to be set
     */

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


}




