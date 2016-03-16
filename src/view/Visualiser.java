package view;

import model.Message;
import model.Protocol;

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


    public Visualiser() {
        this.xPosition = 105;
        this.xVelocity = 1;
        timer = new Timer(10,this);
        setBackground(Color.LIGHT_GRAY);
    }

    public void paintComponent(Graphics g){
        int sender = 100;
        int receiver = getWidth() - 100;
        super.paintComponent(g);
        g.setFont(new Font("Serif", Font.BOLD, 30));
        g.drawString("A", sender , this.getHeight() / 2);
        g.drawString("Message", xPosition, this.getHeight() / 2);
        g.drawString("B", receiver, this.getHeight() / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(xPosition >= this.getWidth() - 200) {
            timer.stop();
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


}
