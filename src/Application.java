import controllers.PanelController;
import model.Protocol;
import view.SplitPane;


import javax.swing.*;

/**
 *
 * Main class to launch the Application
 * @author Conor
 */
public class Application {

    private SplitPane mainScreen;

    /**
     * Method to start application / Set L&F
     * @param args - Unused
     *
     */

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                SplitPane mainScreen = new SplitPane();
                new PanelController(mainScreen.getMainPanel(), mainScreen.getEditorPanel(), mainScreen.getVisPanel(), new Protocol());
                SwingUtilities.updateComponentTreeUI(mainScreen);
            }
        });

    }

}

