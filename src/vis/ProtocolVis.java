package vis;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Conor on 18/02/2016.
 * Class to visualize the data being passed in a protocol
 */
public class ProtocolVis extends JFrame {
    private Color color = (Color.BLACK);
    private JPanel jPanel;

    public ProtocolVis() {
        super("Protocol Title");
        jPanel = new JPanel();
        jPanel.setBackground(color);
        setVisible(true);
        add(jPanel,BorderLayout.CENTER);
        setSize(425,120);
    }



    public static void main(String [] args){
        ProtocolVis vis = new ProtocolVis();
        vis.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
