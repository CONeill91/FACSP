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
        setLayout(new BorderLayout());
        headerLabel = new JLabel("Protocol Visualiser",SwingConstants.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 30));
        add(headerLabel,BorderLayout.NORTH);

    }
}
