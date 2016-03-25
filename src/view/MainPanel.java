package view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Created by Conor on 11/03/2016.
 */
public class MainPanel extends JPanel {
    private JLabel headerLabel;
    private JLabel infoLabel;
    private JLabel errorLabel;
    private JLabel fileLabel;
    private JButton start;
    private JButton chooseProtocol;
    private JButton reset;


    public MainPanel() {
        super();
        Font font = new Font("Serif", Font.BOLD, 20);
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());

        headerLabel = new JLabel("Formal Analysis of Cryptographic Security Protocols (FACSP)",SwingConstants.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 30));
        // Panel for info, error & filepath labels
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BorderLayout());
        labelPanel.setBackground(Color.LIGHT_GRAY);


        infoLabel = new JLabel("<html><div style='text-align: center;'>FACSP is a research tool to formally analyse network security protocols. " +
                "The protocol you wish to analyse should be specified using Casper Syntax. A detailed description of Casper can be found here: http://www.cs.ox.ac.uk/gavin.lowe/Security/Casper/. " +
                "To ensure the script adheres to Casper's grammar it may be edited using the editor (A script outline is provided on startup). " +
                "A visualisation of the protocol steps is generated in the bottom left after the protocol has been analysed. " +
                "The protocol will be analysed with respect to the security properties in the specifications section and verified. " +
                "The location of any vulnerabilities are also highlighted.</html>");
        infoLabel.setFont(font);
        errorLabel = new JLabel("<html>Error: None</html>",SwingConstants.CENTER);
        errorLabel.setFont(font);
        fileLabel = new JLabel("Casper Script Selected: None",SwingConstants.CENTER);
        fileLabel.setFont(font);
        labelPanel.add(infoLabel,BorderLayout.NORTH);
        labelPanel.add(errorLabel,BorderLayout.CENTER);
        labelPanel.add(fileLabel,BorderLayout.SOUTH);

        // Panel for the buttons
        JPanel buttonPanel = new JPanel();
        start = new JButton("Start");
        chooseProtocol = new JButton("Choose Protocol");
        reset = new JButton("Reset");
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(start);
        buttonPanel.add(chooseProtocol);
        buttonPanel.add(reset);

        add(headerLabel, BorderLayout.NORTH);
        add(labelPanel,BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);


    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public JLabel getFileLabel() {
        return fileLabel;
    }

    public JButton getStart() {
        return start;
    }

    public JButton getChooseProtocol() {
        return chooseProtocol;
    }

    public JButton getReset() {
        return reset;
    }
}
