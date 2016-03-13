package view;

import model.Message;
import model.Protocol;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Conor on 18/02/2016.
 * Class to visualize the data being passed in a protocol
 */
public class ProtocolVis extends JFrame {
    public ProtocolVis(Protocol protocol) {
        super(protocol.getTitle() + " Protocol Steps Visualization");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        add(new ProtocolPanel(protocol.getMessages()));
        setBackground(Color.BLACK);
        pack();
        // setLocationRelativeTo(ProtocolPanel.CENTER_ALIGNMENT);


    }

    class ProtocolPanel extends JPanel {
        private ArrayList<Message> messageList;

        public ProtocolPanel(ArrayList<Message> msgs) {
            super();
            this.messageList = msgs;
            setPreferredSize(new Dimension(500, 55 * messageList.size()));
            setBackground(Color.DARK_GRAY);
        }

        public void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            int lineNo = 1;
            int lineNoX = 10;
            int headingY = 20;
            int y = 50;
            int senderX  = 50;
            int messageX = 175;
            int receiverX = 450;
            float f = 20.0f; // font size.

            g.setFont(g.getFont().deriveFont(f));
            y = drawMessage(g2D, messageList,y,lineNo,lineNoX,senderX,messageX,receiverX,headingY);
            //  System.out.println(y);
        }

        private int drawMessage(Graphics2D g, ArrayList<Message> messageList, int y, int lineNo, int lineNoX, int senderX, int messageX, int receiverX, int headingY){
            g.drawString("Step",5,headingY);
            g.drawString("Sender",senderX,headingY);
            g.drawString("Message",messageX,headingY);
            g.drawString("Receiver",410,headingY);

            for(Message message : messageList) {
                g.drawString(lineNo + ".", lineNoX, y);
//                g.drawString(message.getSenderId(), senderX, y);
//                g.drawString(message.getMsgString(), messageX, y);
//                g.drawString(message.getReceiverId(), receiverX, y);
//                y += 50;
                lineNo++;
            }
            return y;
        }






    }
}


