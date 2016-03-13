package controllers;

import javaCC.CasperParser;
import javaCC.ParseException;
import model.Protocol;
import util.Reader;
import view.EditorPanel;
import view.MainPanel;
import view.VisPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by Conor on 11/03/2016.
 */
public class PanelController {
    private MainPanel mainPanel;
    private EditorPanel editorPanel;
    private VisPanel visPanel;
    private Protocol protocol;
    private String filePath = "";



    public PanelController(MainPanel mainPanel, EditorPanel editorPanel, VisPanel visPanel, Protocol protocol) {
        this.mainPanel = mainPanel;
        this.editorPanel = editorPanel;
        this.visPanel = visPanel;
        this.protocol = protocol;
        initEditorFunc();
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
    // Initialises Editor panel functionality
    public void initEditorFunc(){
        editorPanel.add(initMenuBar(), BorderLayout.NORTH);
    }

    // Initialises Main panel functionality
    public void initMainFunc(){
        mainPanel.getStart().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(filePath.equals("")){
                    mainPanel.getErrorLabel().setText("Error : No script selected.");
                    return;
                }
                try{
                    Protocol protocol = parseProtocol();
                }
                catch(ParseException p){
                    mainPanel.getErrorLabel().setText(p.toString());
                }
                catch (FileNotFoundException f){
                    mainPanel.getErrorLabel().setText("File path incorrect");
                }
            }
        });
    }

    public JMenuBar initMenuBar(){
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
        }});

        JMenuItem save = new JMenuItem(new AbstractAction("Save") {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveScript();
        }});

        JMenuItem exit = new JMenuItem(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file.add(open);
        file.add(save);
        file.add(exit);
        menuBar.setVisible(true);
        menuBar.add(file);
        menuBar.add(edit);
        return menuBar;
    }

    public void saveScript() {
        final JFileChooser SaveAs = new JFileChooser();
        SaveAs.setApproveButtonText("Save");
        int actionDialog = SaveAs.showOpenDialog(editorPanel);
        if (actionDialog != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File fileName = new File(SaveAs.getSelectedFile() + ".spl");
        BufferedWriter outFile = null;
        try {
            outFile = new BufferedWriter(new FileWriter(fileName));

            editorPanel.getEditor().write(outFile);   // *** here: ***

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

    public Protocol parseProtocol() throws ParseException,FileNotFoundException{
        CasperParser parser = new CasperParser(new java.io.FileInputStream(filePath));
        Protocol protocol = parser.script();
        return protocol;
    }
}




