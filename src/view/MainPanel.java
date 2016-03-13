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

    public MainPanel() {
        super();
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());
        Font headerFont = new Font("Serif", Font.BOLD, 30);

        headerLabel = new JLabel("Welcome to FACSP",SwingConstants.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 30));
        infoLabel = new JLabel("<html><div style='text-align: center;'>FACSP is a tool to formally analyse network security protocols & verify them with respect to a given security property.</html>");
        infoLabel.setFont(new Font("Serif", Font.BOLD, 20));

        // Panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(new JButton("Start"));
        buttonPanel.add(new JButton("Choose Protocol"));

        add(headerLabel,BorderLayout.NORTH);
        add(infoLabel,BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);


    }

    public JLabel getHeaderLabel() {
        return headerLabel;
    }

    public void setHeaderLabel(String text) {
        this.headerLabel.setText(text);
    }

    public JLabel getInfoLabel() {
        return infoLabel;
    }

    public void setInfoLabel(String text) {
        this.infoLabel.setText(text);
    }
}
