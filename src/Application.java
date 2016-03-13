import controllers.PanelController;
import model.Protocol;
import view.SplitPane;

/**
 * Created by Conor on 19/02/2016.
 * Main class to launch the overall application
 */
public class Application {
    public static void main(String [] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SplitPane mainScreen = new SplitPane();
                Protocol protocol = new Protocol();
                PanelController controller = new PanelController(mainScreen.getMainPanel(),mainScreen.getEditorPanel(),mainScreen.getVisPanel(),protocol);

            }
        });
    }
}

