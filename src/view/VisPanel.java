package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Conor on 11/03/2016.
 */
public class VisPanel extends JPanel {
    private JLabel headerLabel;

    public VisPanel(){
        super();
        setBackground(Color.LIGHT_GRAY);
        setLayout(new FlowLayout());
        headerLabel = new JLabel("Protocol Visualisation");
        headerLabel.setFont(new Font("Serif", Font.BOLD, 20));
        add(headerLabel);

    }
}
