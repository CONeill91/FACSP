package view;

import model.Protocol;
import model.msg.Message;
import util.PrettyPrinter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Class to model the visualiser components
 *
 */
public class Visualiser extends JPanel {
    private ArrayList<Message> messages;
    private ArrayList<Message> initiator;
    private ArrayList<Message> responder;
    private PrettyPrinter prettyPrinter;
    private int x = 10;
    private int y = this.getHeight() / 2 ;

    public Visualiser(ArrayList<Message> messages, ArrayList<Message> initiator, ArrayList<Message> responder ) {
        this.messages = messages;
        this.initiator = initiator;
        this.responder = responder;
        this.prettyPrinter = new PrettyPrinter();
        this.setVisible(true);
        setBackground(Color.LIGHT_GRAY);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setFont(new Font("Serif", Font.BOLD, 16));
        for(int i = 0; i < messages.size(); i++){
            g.drawString(i + ". " + messages.get(i).getSenderId() + "-->" + messages.get(i).getReceiverId() + ": " +  prettyPrinter.createPrettyMessage(messages.get(i)),x,y);
            g.drawString(i + ". " + initiator.get(i).getSenderId() + "-->" + initiator.get(i).getReceiverId() + ": " + prettyPrinter.createPrettyMessage(initiator.get(i)),300,y);
            g.drawString(i + ". " + responder.get(i).getSenderId() + "-->" + responder.get(i).getReceiverId() + ": " + prettyPrinter.createPrettyMessage(responder.get(i)),600,y);
            y += 20;
        }
    }



}
