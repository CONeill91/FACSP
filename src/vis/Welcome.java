package vis;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * Created by Conor on 26/02/2016.
 */
public class Welcome {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private String filePath = "";



    public Welcome(){
        prepareGUI();
    }

    public static void main(String[] args){
        Welcome welcome = new Welcome();
        welcome.showButtonDemo();
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
        statusLabel = new JLabel("",JLabel.CENTER);

        statusLabel.setSize(350,100);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }



    private void showButtonDemo(){
        headerLabel.setText("Welcome to FACSP");
        

        JButton chooseScriptButton = new JButton("Choose Protocol");
        JButton createScriptButton = new JButton("Submit Protocol");
        JButton chooseSecPropButton = new JButton("Choose Security Property");
        JButton createSecPropButton = new JButton("Submit Security Property");
        chooseSecPropButton.setHorizontalTextPosition(SwingConstants.LEFT);

        chooseScriptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Casper Scripts", "spl", "spl~");
                chooser.setFileFilter(filter);
                int option = chooser.showOpenDialog(mainFrame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    statusLabel.setText(file.getAbsolutePath());
                    setFilePath(file.getAbsolutePath());

            }
            }
        });

        createScriptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Submit Button clicked.");
            }
        });

        chooseSecPropButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Cancel Button clicked.");
            }
        });

        createSecPropButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Cancel Button clicked.");
            }
        });
        
        controlPanel.add(chooseScriptButton);
        controlPanel.add(createScriptButton);
        controlPanel.add(chooseSecPropButton);
        controlPanel.add(createSecPropButton);
        mainFrame.setVisible(true);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
