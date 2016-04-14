package view;

import util.Reader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Editor panel component class
 * @author Conor
 */
public class EditorPanel extends JPanel {
    private JTextArea editor;
    public final static String CASPER_LAYOUT_GUIDE = "-- Sample Comment Line\n\n" +
            "Casper Script Sections: (Optional sections in square brackets) \n\n " +
            "Free Variables Section\n\n" + "Processes Section\n*Should contain Initiator / Responder Processes*\n\n" + "Protocol Description Section\n\n" + "Specification Section\n\n" + "[Equivalences Section]\n\n" +
            "Actual Variables Section\n\n" + "[Functions Section]\n\n" + "System Section\n\n" + "[Channels Section]";

    public EditorPanel(){
        super();
        editor = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(editor);
        TextLineNumber tln = new TextLineNumber(editor);
        scrollPane.setRowHeaderView(tln);
        editor.setText(CASPER_LAYOUT_GUIDE);
        editor.setVisible(true);
        editor.setEditable(true);
        setLayout(new BorderLayout());
        add(scrollPane);
    }

    /**
     * Returns the editor panel JTextArea.
     * @return  JTextArea.
     * @see     JTextArea
     */
    public JTextArea getEditor() {
        return editor;
    }


}
