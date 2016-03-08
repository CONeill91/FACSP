package vis;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


/**
 * Created by Conor on 26/02/2016.
 */
public class OpeningScreen {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JLabel errorLabel;
    private JPanel controlPanel;
    private String filePath = "";
    private String errorMessage = "";


    public OpeningScreen(){
        prepareGUI();
    }

    public static void main(String[] args){
        new OpeningScreen();

    }

    private void prepareGUI(){
        mainFrame = new JFrame("Formal Analysis of Cryptographic Security Protocols ");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.setLocationRelativeTo(null);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("File Selected: None",JLabel.CENTER);
        errorLabel = new JLabel("", JLabel.CENTER);

        statusLabel.setSize(350,100);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.add(errorLabel);
        mainFrame.setVisible(true);
        initButtons();
    }



    private void initButtons(){
        headerLabel.setText("Welcome to FACSP");

        JButton startButton = new JButton(new AbstractAction("Start") {
            @Override
            public void actionPerformed(ActionEvent e) {
                return;
            }
        });
        
        JButton chooseScriptButton = new JButton(new AbstractAction("Choose Protocol") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Casper Scripts (.spl)", "spl", "spl~");
                chooser.setFileFilter(filter);
                int option = chooser.showOpenDialog(mainFrame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    statusLabel.setText(file.getAbsolutePath());
                    setFilePath(file.getAbsolutePath());
                    statusLabel.setText("File Selected: " + file.getName());
            }
        }});

        JButton createScriptButton = new JButton(new AbstractAction("Submit Protocol") {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTextEditor();
            }
        });

        controlPanel.add(startButton);
        controlPanel.add(chooseScriptButton);
        controlPanel.add(createScriptButton);
        mainFrame.setVisible(true);
    }

    public void openTextEditor(){
        mainFrame.setVisible(false);
        TextEditor editor = new TextEditor();

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public class TextEditor {

        public final String CASPER_LAYOUT_GUIDE = "-- Comment protocol name \n\n" +
                "Casper Script Sections: (Optional sections in square brackets) \n\n " +
                "Free Variables\n\n" + "Processes\n\n" + "Protocol Description\n\n" + "Specification\n\n" + "[Equivalences]\n\n" +
                "Actual Variables\n\n" + "[Functions]\n\n" + "System\n\n" + "[Channels]";


        public TextEditor() {
            JFrame editorFrame = new JFrame("Create your own Casper Script");
            editorFrame.setLocationRelativeTo(null);
            JTextArea editor = new JTextArea(50, 50);
            editor.setAutoscrolls(true);
            JPanel editorPanel = new JPanel();
            editorPanel.add(editor);
            editor.setEditable(true);
            editor.setVisible(true);
            editorFrame.add(editorPanel);
            editorFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent windowEvent) {
                    mainFrame.setVisible(true);
                }
            });

            editorFrame.pack();
            editorFrame.setVisible(true);
            editor.setText(CASPER_LAYOUT_GUIDE);
        }
    }




}
