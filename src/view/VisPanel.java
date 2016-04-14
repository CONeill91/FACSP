package view;

import model.Protocol;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Vis panel component class
 * @author Conor
 */
public class VisPanel extends JPanel {
    private JLabel headerLabel;
    private JPanel visPanel;
    private Visualiser visualiser;

    public VisPanel(){
        super();
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());
        headerLabel = new JLabel("Protocol Analysis Visualiser",SwingConstants.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 30));

        visPanel = new JPanel();
        visPanel.setBackground(Color.LIGHT_GRAY);
        visPanel.setLayout(new BorderLayout());

        add(headerLabel, BorderLayout.NORTH);
        add(visPanel, BorderLayout.CENTER);

    }

    /**
     * Returns the visualiser panel
     * @return  JPanel
     * @see     JPanel
     */

    public JPanel getVisualiserPanel() {
        return visPanel;
    }

    /**
     * Returns the visualiser
     * @return  JPanel
     * @see     JPanel
     */

    public Visualiser getVisualiser() {
        return visualiser;
    }

    /**
     * Sets the visualiser
     * @param visualiser Visualiser to be set
     * @see     JPanel
     */

    public void setVisualiser(Visualiser visualiser) {
        this.visualiser = visualiser;
    }
}
