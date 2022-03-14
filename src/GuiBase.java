import java.awt.*;

public class GuiBase {
    GridBagConstraints getGridBagConstraints(int yNdx) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.gridx = 0;
        gbc.gridy = yNdx;

        return gbc;
    }

    GridBagConstraints getGridBagConstraints(int xNdx, int yNdx) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.gridx = xNdx;
        gbc.gridy = yNdx;

        return gbc;
    }
}