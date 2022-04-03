import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GuiBase {
    static GridBagConstraints makeConstraints(int xNdx, int yNdx, double weight) {
        GridBagConstraints gbc = makeConstraints(xNdx, yNdx);
        gbc.weightx = weight;
        gbc.weighty = weight;

        return gbc;
    }

    static GridBagConstraints makeConstraints(int xNdx, int yNdx) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.gridx = xNdx;
        gbc.gridy = yNdx;

        return gbc;
    }

    static JFrame getFrame() { // Frame is created by Gui constructor, ie. before it is possible to call getFrame()
        return (JFrame) Frame.getFrames()[0];
    }

    // ACTION: Language dependent text for form actions
    static final Map<Form.FORM_ACTION, String> ACTION;

    static {
        ACTION = new HashMap<>();
        ACTION.put(Form.FORM_ACTION.INSERT, "New");
        ACTION.put(Form.FORM_ACTION.EDIT, "Edit");
        ACTION.put(Form.FORM_ACTION.DELETE, "Delete");

    }

}

