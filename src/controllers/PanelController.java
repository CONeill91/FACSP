package controllers;

import analysis.Analyser;
import javaCC.CasperParser;
import javaCC.ParseException;
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
 * Created by Conor on 11/03/2016.
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

    public PanelController(MainPanel mainPanel, EditorPanel editorPanel, VisPanel visPanel, Protocol protocol) {
        this.mainPanel = mainPanel;
        this.editorPanel = editorPanel;
        this.visPanel = visPanel;
        this.protocol = protocol;
        initEditorFunc();
        initMainFunc();
        this.filePath = "";
    }

    /*
    * EditorPanel functions
    *
    * */
    // Initialises Editor panel functionality
    public void initEditorFunc() {
        editorPanel.add(initMenuBar(), BorderLayout.NORTH);
    }

    // Returns functioning menubar to be added to the editor panel.
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

    // Save & select a script for analysis
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

    // Highlight the given line in the editor panel
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

    // Take the line number of the error from the ParseException -- TODO Unit test you ensure this is ok.
    public int getLineNumberFromParseException(ParseException p) {
        int offset = p.toString().indexOf("line") + 5;
        return Integer.parseInt(p.toString().substring(offset,p.toString().indexOf(",")));
    }

    /*
    *  Main panel functions
    *
    * */

    // Initialises Main panel functionality
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
                    setSpecLabel(protocol);
                    initVisualiser(protocol);
                    protocol.setInitAndRespInfo();
                    Analyser analyser = new Analyser(protocol);
                    analyser.generateInitiatorImpersonation();
                    analyser.generateResponderImpersonation();




                } catch (ParseException p) {
                    System.out.println(p);
                    highlightLine(getLineNumberFromParseException(p) - 1);
                    mainPanel.getErrorLabel().setText("Error: Line " + getLineNumberFromParseException(p) + ". See editor.");
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


    // Parser the file whose path is set in filepath
    public void parseProtocol() throws ParseException, FileNotFoundException {
        CasperParser parser = new CasperParser(new java.io.FileInputStream(filePath));
        protocol = parser.script();

    }

    /*
    * VisPanel functions
    *
    * */


    public void initVisualiser(Protocol protocol) {
        Visualiser visualiser = new Visualiser(protocol);
        visPanel.setVisualiser(visualiser);
        updateCurrentStepLabel();
        visPanel.getVisualiserPanel().add(visualiser, BorderLayout.CENTER);
        visPanel.getVisualiserPanel().revalidate();
        visualiser.startTimer();
        initVisualiserButtons();
    }

    // Init the inc/dec buttons in Vispanel functionality.
    public void initVisualiserButtons() {
        // Prevents adding more than one action listener to each button
        if (visPanel.getInc().getActionListeners().length == 0 & visPanel.getDec().getActionListeners().length == 0) {
            visPanel.getInc().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (visPanel.getVisualiser() != null) {
                        incCurrentProtocolStep();
                        updateCurrentStepLabel();
                    }

                }
            });

            visPanel.getDec().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (visPanel.getVisualiser() != null) {
                        decCurrentProtocolStep();
                        updateCurrentStepLabel();
                    }
                }
            });
        }
    }

    public void incCurrentProtocolStep() {
        if (visPanel.getVisualiser().getCurrentProtocolStep() == protocol.getMessages().size() - 1) {
            return;
        }
        visPanel.getVisualiser().setCurrentProtocolStep(visPanel.getVisualiser().getCurrentProtocolStep() + 1);
        visPanel.getVisualiser().resetXPosition();
    }

    public void decCurrentProtocolStep() {
        if (visPanel.getVisualiser().getCurrentProtocolStep() == 0) {
            return;
        }
        visPanel.getVisualiser().setCurrentProtocolStep(visPanel.getVisualiser().getCurrentProtocolStep() - 1);
        visPanel.getVisualiser().resetXPosition();
    }

    public void updateCurrentStepLabel() {
        visPanel.getStepLabel().setText("Current Protocol Step: " + visPanel.getVisualiser().getCurrentProtocolStep());
    }

    /*
    * UI resetting functions
    * */

    // Reset the Application
    public void resetUI() {
        resetLabels();
        resetEditor();
        setProtocol(new Protocol());
        resetVisPanel();
    }

    // Reset the UI labels
    public void resetLabels() {
        mainPanel.getErrorLabel().setText("Error: None");
        mainPanel.getFileLabel().setText("Casper Script Selected: None");
        mainPanel.getInfoLabel().setText("Specifications: None");
        visPanel.getStepLabel().setText("Current Protocol Step: N/A");
    }

    // Reset the editor to sample casper outline / reset filepath
    public void resetEditor() {
        editorPanel.getEditor().setText(EditorPanel.CASPER_LAYOUT_GUIDE);
        setFilePath("");

    }

    public void resetVisPanel() {
        if (visPanel.getVisualiser() != null) {
            visPanel.getVisualiser().stopTimer();
            visPanel.getVisualiserPanel().remove(visPanel.getVisualiser());
            visPanel.setVisualiser(null);
            visPanel.revalidate();
            visPanel.repaint();
        }
    }

    public void resetHighlighter(){
        Highlighter highlighter = editorPanel.getEditor().getHighlighter();
        highlighter.removeAllHighlights();
    }
    
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

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


}




