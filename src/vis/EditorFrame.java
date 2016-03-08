package vis;

import javax.swing.*;

/**
 * Created by Conor on 04/03/2016.
 */
public class EditorFrame extends JFrame {
    private JFrame editorFrame;
    public final String CASPER_LAYOUT_GUIDE = "-- Comment protocol name \n\n" +
            "Casper Script Sections: (Optional sections in square brackets) \n\n " +
            "Free Variables\n\n" + "Processes\n\n" + "Protocol Description\n\n" + "Specification\n\n" + "[Equivalences]\n\n" +
            "Actual Variables\n\n" + "[Functions]\n\n" + "System\n\n" + "[Channels]";


    public EditorFrame() {
        editorFrame = new JFrame("Create your own Casper Script");
        editorFrame.setLocationRelativeTo(null);
        JTextArea editor = new JTextArea(50, 50);
        editor.setAutoscrolls(true);
        JPanel editorPanel = new JPanel();
        editorPanel.add(editor);
        editor.setEditable(true);
        editor.setVisible(true);
        editorFrame.add(editorPanel);

        editorFrame.pack();
        editor.setText(CASPER_LAYOUT_GUIDE);
    }
}
