package view;

import model.Protocol;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Conor on 11/03/2016.
 */
public class VisPanel extends JPanel {
    private JLabel headerLabel;
    private JLabel stepLabel;
    private JButton inc;
    private JButton dec;
    private JPanel visPanel;



    public VisPanel(){
        super();
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());
        headerLabel = new JLabel("Protocol Visualiser",SwingConstants.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 30));
        stepLabel = new JLabel("Current Protocol Step: N/A",SwingConstants.CENTER);
        stepLabel.setFont(new Font("Serif", Font.BOLD, 25));

        visPanel = new JPanel();
        visPanel.setBackground(Color.LIGHT_GRAY);
        visPanel.setLayout(new BorderLayout());
        visPanel.add(stepLabel,BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel();
        inc = new JButton("Increment protocol step");
        dec = new JButton("Decrement protocol step");
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(inc);
        buttonPanel.add(dec);

        add(headerLabel, BorderLayout.NORTH);
        add(visPanel, BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);
    }



    public JLabel getStepLabel() {
        return stepLabel;
    }

    public JPanel getVisualiserPanel() {
        return visPanel;
    }

    public JButton getInc() {
        return inc;
    }

    public JButton getDec() {
        return dec;
    }


}
