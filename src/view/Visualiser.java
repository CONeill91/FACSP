package view;

import model.Protocol;
import model.msg.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Conor on 16/03/2016.
 */
public class Visualiser extends JPanel implements ActionListener {
    private int xPosition;
    private int xVelocity;
    private Timer timer;
    private ArrayList<Message> messages;
    private int currentProtocolStep;


    public Visualiser(Protocol protocol) {
        this.currentProtocolStep = 0;
        this.messages = protocol.getMessages();
        this.setVisible(true);
        this.xPosition = 105;
        this.xVelocity = 1;
        timer = new Timer(10,this);
        setBackground(Color.LIGHT_GRAY);
    }

    public void paintComponent(Graphics g){
        int sender = 50;
        int receiver = getWidth() - 75;
        super.paintComponent(g);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        // Draw Sender String
        g.drawString("Sender", sender , this.getHeight() / 2 - 20);
        // Draw Sender ID String
        g.drawString(messages.get(currentProtocolStep).getSenderId(), sender , this.getHeight() / 2);

        // Draw Msg String
        g.drawString(messages.get(currentProtocolStep).toString(), xPosition, this.getHeight() / 2 + 20);
        // Draw Receiver ID String
        g.drawString(messages.get(currentProtocolStep).getReceiverId(), receiver + 20, this.getHeight() / 2);
        // Draw Receiver String
        g.drawString("Receiver", receiver , this.getHeight() / 2 - 20);;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(xPosition >= this.getWidth() - 200) {
            xPosition = 105;
            timer.restart();

        }
        xPosition += xVelocity;
        repaint();
    }

    public void startTimer(){
        timer.start();
    }

    public void restartTimer(){
        timer.restart();
    }

    public void stopTimer(){
        timer.stop();
    }

    public int getCurrentProtocolStep() {
        return currentProtocolStep;
    }

    public void setCurrentProtocolStep(int currentProtocolStep) {
        this.currentProtocolStep = currentProtocolStep;
    }

    public void resetXPosition(){
        this.xPosition = 105;
    }
}
