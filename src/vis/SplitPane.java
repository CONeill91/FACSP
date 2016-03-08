package vis;


import javaCC.CasperParser;
import util.Reader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class SplitPane extends JFrame implements ActionListener {
    private JSplitPane splitPaneV;
    private JSplitPane splitPaneH;

    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel headerLabel = new JLabel("FACSP is a tool for analysing & verifying security protocols with respect to security properties.");

    private JTextArea editor;
    final static int X_DIM = 1800;
    final static int Y_DIM = 1000;
    public final static String CASPER_LAYOUT_GUIDE = "-- Comment protocol name \n\n" +
            "Casper Script Sections: (Optional sections in square brackets) \n\n " +
            "Free Variables\n\n" + "Processes\n\n" + "Protocol Description\n\n" + "Specification\n\n" + "[Equivalences]\n\n" +
            "Actual Variables\n\n" + "[Functions]\n\n" + "System\n\n" + "[Channels]";


    public SplitPane() {
        setTitle("Formal Analyzer for Cryptographic Security Protocols");
        setBackground(Color.gray);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);

        // Create the panels
        createPanel1();
        createPanel2();
        createPanel3();

        // Create a splitter pane
        splitPaneV = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        topPanel.add(splitPaneV, BorderLayout.CENTER);

        splitPaneH = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneH.setLeftComponent(panel1);
        splitPaneH.setRightComponent(panel2);
        splitPaneH.setDividerLocation(Y_DIM / 2);
        splitPaneV.setDividerLocation(X_DIM / 2);

        splitPaneV.setLeftComponent(splitPaneH);
        splitPaneV.setRightComponent(panel3);
    }

    public void createPanel1() {
        panel1 = new JPanel();
        panel1.setBackground(Color.GRAY);
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel1.add(headerLabel);
        // Add some buttons

        panel1.add(new JButton(new AbstractAction("Start") {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }));
        panel1.add(new JButton("Choose Protocol"));


    }

    public void createPanel2() {
        panel2 = new JPanel();
        panel2.setBackground(Color.GRAY);
        panel2.setLayout(new FlowLayout());

        panel2.add(new JButton("Button 1"));
        panel2.add(new JButton("Button 2"));
        panel2.add(new JButton("Button 3"));
    }

    public void createPanel3() {
        JMenuBar menuBar = prepareMenuBar();
        editor = new JTextArea();
        editor.setText(CASPER_LAYOUT_GUIDE);
        menuBar.setVisible(true);



        panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.setPreferredSize(new Dimension(400, 100));
        panel3.setMinimumSize(new Dimension(100, 50));
        panel3.add(menuBar,BorderLayout.NORTH);


        panel3.add(editor);
    }

    public JMenuBar prepareMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenuItem open = new JMenuItem(new AbstractAction("Open") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Casper Scripts (.spl)", "spl", "spl~");
                chooser.setFileFilter(filter);
                int option = chooser.showOpenDialog(panel3);
                if (option == JFileChooser.APPROVE_OPTION) {
                    Reader reader = new Reader();
                    editor.setText(reader.readFile(chooser.getSelectedFile()));
                }
        }});
        JMenuItem exit = new JMenuItem(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        file.add(open);
        file.add(exit);
        menuBar.add(file);
        menuBar.add(edit);
        return menuBar;
    }

    public static void main(String args[]) {
        // Create an instance of the test application
        SplitPane mainFrame = new SplitPane();
        mainFrame.setSize(X_DIM,Y_DIM);
        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        editor.setText("Exit");

    }
}
