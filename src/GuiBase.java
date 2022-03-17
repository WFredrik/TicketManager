import java.awt.*;

public class GuiBase {
    static GridBagConstraints makeConstraints(int xNdx, int yNdx) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.gridx = xNdx;
        gbc.gridy = yNdx;

        return gbc;
    }
}
