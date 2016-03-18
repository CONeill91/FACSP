package controllers;

import javaCC.CasperParser;
import javaCC.ParseException;
import model.msg.Message;
import model.Protocol;
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

    public PanelController(MainPanel mainPanel, EditorPanel editorPanel, VisPanel visPanel, Protocol protocol) {
        this.mainPanel = mainPanel;
        this.editorPanel = editorPanel;
        this.visPanel = visPanel;
        this.protocol = protocol;
        initEditorFunc();
        initMainFunc();
        this.filePath = "";
    }

    // Initialises Editor panel functionality
    public void initEditorFunc() {
        editorPanel.add(initMenuBar(), BorderLayout.NORTH);
    }

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
                    Protocol protocol = parseProtocol();
                    initVisualiser(protocol);


                } catch (ParseException p) {
                    highlightLine(getLineNumberFromParseException(p) - 1);
                    mainPanel.getErrorLabel().setText("Error: Line Number " + getLineNumberFromParseException(p) + ". See editor.");
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

    // Returns functioning menubar to be added to the editor panel.
    public JMenuBar initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");


        JMenuItem open = new JMenuItem(new AbstractAction("Open") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Casper Scripts (.spl)", "spl", "spl~");
                chooser.setFileFilter(filter);
                int option = chooser.showOpenDialog(editorPanel);
                if (option == JFileChooser.APPROVE_OPTION) {
                    Reader reader = new Reader();
                    File file = chooser.getSelectedFile();
                    editorPanel.getEditor().setText(reader.readFile(file));
                }
            }
        });

        JMenuItem save = new JMenuItem(new AbstractAction("Save & select this script") {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveScript();
            }
        });


        JMenuItem reset = new JMenuItem(new AbstractAction("Reset editor") {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetEditor();
            }
        });

        JMenuItem exit = new JMenuItem(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file.add(open);
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

    // Parser the file whose path is set in filepath
    public Protocol parseProtocol() throws ParseException, FileNotFoundException {
        CasperParser parser = new CasperParser(new java.io.FileInputStream(filePath));
        Protocol protocol = parser.script();
        return protocol;
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

    // Take the line number of the error from the ParseException
    public int getLineNumberFromParseException(ParseException p) {
        return Character.getNumericValue(p.toString().charAt(p.toString().indexOf("line") + 5));
    }

    public void initVisualiser(Protocol protocol){
        // TODO finish
        Visualiser visualiser = new Visualiser(protocol);
        visPanel.getVisualiserPanel().add(visualiser,BorderLayout.CENTER);
        visPanel.getVisualiserPanel().revalidate();
        visualiser.startTimer();

    }
    // Reset the Application
    public void resetUI(){
        resetLabels();
        resetEditor();
    }
    // Reset the UI labels
    public void resetLabels(){
        mainPanel.getErrorLabel().setText("Error: None");
        mainPanel.getFileLabel().setText("Casper Script Selected: None");
        visPanel.getStepLabel().setText("Current Protocol Step: N/A");

    }

    // Reset the editor to sample casper outline / reset filepath
    public void resetEditor() {
        editorPanel.getEditor().setText(EditorPanel.CASPER_LAYOUT_GUIDE);
        setFilePath("");
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

//    public void incCurrentProtocolStep(){
//        if (currentProtocolStep == protocol.getMessages().size() - 1){
//            return;
//        }
//        currentProtocolStep++;
//    }
//
//    public void decCurrentProtocolStep(){
//        if (currentProtocolStep == 0){
//            return;
//        }
//        currentProtocolStep--;
//    }

}




