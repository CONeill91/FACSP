package view;

import java.awt.*;
import javax.swing.*;

public class SplitPane extends JFrame {
    private JSplitPane splitPaneV;
    private JSplitPane splitPaneH;
    private MainPanel mainPanel;
    private EditorPanel editorPanel;
    private VisPanel visPanel;
    final static int X_DIM = 1800;
    final static int Y_DIM = 1000;


    public SplitPane() {
        setTitle("Formal Analysis of Cryptographic Security Protocols (FACSP)");
        setIconImage(new ImageIcon("C:/Users/Conor/IdeaProjects/Facsp/src/res/facsp.png").getImage());
        setBackground(Color.gray);
        //setSize(X_DIM, Y_DIM);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Create the panels
        initPanels();

        // Create a splitter pane
        splitPaneV = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        topPanel.add(splitPaneV, BorderLayout.CENTER);
        splitPaneH = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneH.setLeftComponent(mainPanel);
        splitPaneH.setRightComponent(visPanel);
        splitPaneH.setDividerLocation(Y_DIM / 2);

        splitPaneV.setDividerLocation(X_DIM / 2);
        splitPaneV.setLeftComponent(splitPaneH);
        splitPaneV.setRightComponent(editorPanel);

    }

    public void initPanels(){
        mainPanel = new MainPanel();
        editorPanel = new EditorPanel();
        visPanel = new VisPanel();
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public EditorPanel getEditorPanel() {
        return editorPanel;
    }

    public VisPanel getVisPanel() {
        return visPanel;
    }


}
