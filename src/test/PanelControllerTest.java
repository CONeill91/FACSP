package test;

import controllers.PanelController;
import model.Protocol;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import view.SplitPane;

/**
 *
 * Class to test the Panel Controller
 * @author Conor
 */
public class PanelControllerTest {
    SplitPane splitPane;
    PanelController controller;

    @Before
    public void init(){
        splitPane = new SplitPane();
        controller = new PanelController(splitPane.getMainPanel(),splitPane.getEditorPanel(),splitPane.getVisPanel(),new Protocol());
    }

    // Reset Functions

   //
}
