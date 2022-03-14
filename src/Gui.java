import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Gui extends GuiBase {
    JFrame frame;

    static class C {
        static class height {
            static int frame = 628;
            static int leftPanel = 540;
            static int rightPanel = 540;
            static int informPanel = 30;
            static int bottomPanel = 30;
        }

        static class width {
            static int frame = 1100;
            static int leftPanel = 550;
            static int rightPanel = 550;
            static int informPanel = 1100;
            static int bottomPanel = 1100;
        }

        static class color {
            static Color leftPanel = Color.blue;
            static Color rightPanel = Color.orange;
            static Color informPanel = Color.cyan;
            static Color bottomPanel = Color.red;
        }

        static class pos {
            static Point frame = new Point(40, 40);
            static Point leftPanel = new Point(0, 30);
            static Point rightPanel = new Point(550, 30);
            static Point informPanel = new Point(0, 0);
            static Point bottomPanel = new Point(0, 570);
        }
    }

    static class B {
        static Color color = Color.black;
        static int width = 1;
    }

    private JPanel informPanel;

    public Gui() {
        frame = new JFrame("Hjertnes Kino");

        informPanel = new JPanel();
        informPanel.setName("Inform Panel");
        informPanel.setBounds(C.pos.informPanel.x, C.pos.informPanel.y, C.width.informPanel, C.height.informPanel);
        informPanel.setBackground(C.color.informPanel);
        inform("Gutta liker djåms");

        JPanel leftPanel = new JPanel();
        leftPanel.setName("Left Panel");
        leftPanel.setBounds(C.pos.leftPanel.x, C.pos.leftPanel.y, C.width.leftPanel, C.height.leftPanel);
        leftPanel.setBackground(C.color.leftPanel);
        leftPanel.add(new JLabel("Tuva Christine Andersen"));

        JPanel rightPanel = new JPanel();
        rightPanel.setName("Right Panel");
        rightPanel.setBounds(C.pos.rightPanel.x, C.pos.rightPanel.y, C.width.rightPanel, C.height.rightPanel);
        rightPanel.setBackground(C.color.rightPanel);

        JButton button1 = new JButton();
        button1.setText("TEST!");

        JPanel bottomPanel = new JPanel();
        bottomPanel.setName("Bottom Panel");
        bottomPanel.setBounds(C.pos.bottomPanel.x, C.pos.bottomPanel.y, C.width.bottomPanel, C.height.bottomPanel);
        bottomPanel.setBackground(C.color.bottomPanel);
        bottomPanel.add(button1);


        frame.setPreferredSize(new Dimension(C.width.frame, C.height.frame));

        frame.setContentPane(makeBase());

        informPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.add(informPanel, getGridBagConstraints(0));


        frame.add(makeContent(leftPanel, rightPanel), getGridBagConstraints(1));

        frame.add(bottomPanel, getGridBagConstraints(2));

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }

    private JPanel makeBase() {
        GridBagLayout gblFrame = new GridBagLayout();
        gblFrame.rowHeights = new int[]{C.height.informPanel, C.height.leftPanel, C.height.bottomPanel};
        gblFrame.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
        gblFrame.columnWidths = new int[]{C.width.frame};
        gblFrame.columnWeights = new double[]{0.0};

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.pink);
        jPanel.setBorder(new LineBorder(B.color, B.width));
        jPanel.setLayout(gblFrame);

        return jPanel;
    }

    private void inform(String msg) {
        JLabel label = new JLabel(msg);
        informPanel.removeAll();
        informPanel.add(label);
    }


    private JPanel makeContent(JPanel leftPanel, JPanel rightPanel) {
        getGridBagConstraints(0, 0);
        GridBagLayout gbl = new GridBagLayout();
        gbl.rowHeights = new int[]{C.height.leftPanel};
        gbl.rowWeights = new double[]{0.0};
        gbl.columnWidths = new int[]{C.width.leftPanel, C.width.rightPanel};
        gbl.columnWeights = new double[]{0.0, 0.0};
        JPanel panel = new JPanel();
        panel.setLayout(gbl);
        panel.add(leftPanel, getGridBagConstraints(0, 0));
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(rightPanel, getGridBagConstraints(1, 0));
        return panel;
    }
}