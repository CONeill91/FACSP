package view;

import util.Reader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Conor on 11/03/2016.
 */
public class EditorPanel extends JPanel {
    private JTextArea editor;
    public final static String CASPER_LAYOUT_GUIDE = "-- Comment protocol name \n\n" +
            "Casper Script Sections: (Optional sections in square brackets) \n\n " +
            "Free Variables\n\n" + "Processes\n\n" + "Protocol Description\n\n" + "Specification\n\n" + "[Equivalences]\n\n" +
            "Actual Variables\n\n" + "[Functions]\n\n" + "System\n\n" + "[Channels]";

    public EditorPanel(){
        super();
        //setBackground(Color.LIGHT_GRAY);
        editor = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(editor);
        TextLineNumber tln = new TextLineNumber(editor);
       // tln.setBackground(Color.LIGHT_GRAY);
        scrollPane.setRowHeaderView(tln);
        //editor.setBackground(Color.LIGHT_GRAY);
        editor.setText(CASPER_LAYOUT_GUIDE);
        editor.setVisible(true);
        editor.setEditable(true);
        setLayout(new BorderLayout());
        add(scrollPane);
    }

    public JTextArea getEditor() {
        return editor;
    }


}
