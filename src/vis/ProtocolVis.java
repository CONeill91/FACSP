package vis;

import model.Message;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Conor on 18/02/2016.
 * Class to visualize the data being passed in a protocol
 */
public class ProtocolVis extends JFrame {



    public ProtocolVis() {
        super("Protocol Title");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        setContentPane(new ProtocolPanel());
        setVisible(true);
        setSize(500,500);

    }

    class ProtocolPanel extends JPanel {
        private ArrayList<Message> messageList;

        public void paintComponent(Graphics g){
            float f=25.0f; // font size.
            g.setFont(g.getFont().deriveFont(f));

            g.setColor(Color.RED);
            g.drawString("A", 50, 50);
            g.drawString("B",450,50);


        }


    }


}
