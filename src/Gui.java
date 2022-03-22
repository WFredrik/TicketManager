import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Gui extends GuiBase {
    JFrame frame;
    Theatre theatre;
    JPanel leftPanel;

    private Section[] sections;

    static class CircleButton extends JPanel {
        private final int square = 13;
        private final Section section;
        private final int rowNdx;
        private final int seatNdx;
        private final Color color;
        private final Color backgroundColor;

        CircleButton(Section section, int rowNdx, int seatNdx, Color color) {
            this.section = section;
            this.rowNdx = rowNdx;
            this.seatNdx = seatNdx;
            this.color = color;
            this.backgroundColor = color;
            JLabel jLabel = new JLabel();
        }
    }

    public void setSections(Section[] sections) {
        this.sections = sections;
    }

    private static final Map<Color, ShapeIcon> Icons = new HashMap<>();


    static class C {
        static class height {
            static int frame = 658;
            static int topPanel = 30;
            static int leftPanel = 540;
            static int rightPanel = 540;
            static int informPanel = 30;
            static int bottomPanel = 30;
            static int seat = 19;      // Height for seat-panels
        }

        static class width {
            static int frame = 1100;
            static int topPanel = 1100;
            static int leftPanel = 550;
            static int rightPanel = 550;
            static int informPanel = 1100;
            static int bottomPanel = 1100;
            static int seat = 19;
        }

        static class color {
            static Color topPanel = Color.lightGray;
            static Color leftPanel = Color.blue;
            static Color rightPanel = Color.orange;
            static Color informPanel = Color.cyan;
            static Color bottomPanel = Color.red;

            static Color free = Color.white;
            static Color reserved = Color.black;
            static Color randomReserved = Color.red;
            static final Color randomCollected = Color.blue;
            static final Color selected = Color.green;
        }

        static class background {
            static Color seatsPanel = Color.white;
            static Color seats = new Color(0, 200, 100);
        }

        static class pos {
            static Point frame = new Point(40, 40);
            static Point topPanel = new Point(40, 60);
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

    public Gui(Theatre theatre) {
        //CircleButton cb = new CircleButton();
        frame = new JFrame("Ticket Manager");
        this.theatre = theatre;
        Icons.put(C.color.free, new ShapeIcon(C.color.free));
        Icons.put(C.color.reserved, new ShapeIcon(C.color.reserved));
        Icons.put(C.color.randomReserved, new ShapeIcon(C.color.randomReserved));
        Icons.put(C.color.randomCollected, new ShapeIcon(C.color.randomCollected));
        Icons.put(C.color.selected, new ShapeIcon(C.color.selected));

        JPanel topPanel = new JPanel();
        topPanel.setName(theatre.getName());
        topPanel.setBounds(C.pos.topPanel.x, C.pos.topPanel.y, C.width.topPanel, C.height.topPanel);
        topPanel.setBackground(C.color.topPanel);
        topPanel.add(new JLabel(theatre.getName()));

        informPanel = new JPanel();
        informPanel.setName("Inform Panel");
        informPanel.setBounds(C.pos.informPanel.x, C.pos.informPanel.y, C.width.informPanel, C.height.informPanel);
        informPanel.setBackground(C.color.informPanel);
        inform("Kim the Controller makes a comeback");

        leftPanel = new JPanel();
        leftPanel.setName("Left Panel");
        leftPanel.setBounds(C.pos.leftPanel.x, C.pos.leftPanel.y, C.width.leftPanel, C.height.leftPanel);
        leftPanel.setBackground(C.color.leftPanel);
        //leftPanel.add(new JLabel("Sander Jevnaker"));

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
        frame.setLocation(160, 120);

        frame.setContentPane(makeBase());

        frame.add(topPanel, makeConstraints(0, 0));

        informPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.add(informPanel, makeConstraints(0, 1));


        frame.add(makeContent(leftPanel, rightPanel), makeConstraints(0, 2));

        frame.add(bottomPanel, makeConstraints(0, 3));

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }

    private JPanel makeBase() {
        GridBagLayout gblFrame = new GridBagLayout();
        gblFrame.rowHeights = new int[]{C.height.topPanel, C.height.informPanel, C.height.leftPanel, C.height.bottomPanel};
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
        makeConstraints(0, 0);
        GridBagLayout gbl = new GridBagLayout();
        gbl.rowHeights = new int[]{C.height.leftPanel};
        gbl.rowWeights = new double[]{0.0};
        gbl.columnWidths = new int[]{C.width.leftPanel, C.width.rightPanel};
        gbl.columnWeights = new double[]{0.0, 0.0};
        JPanel panel = new JPanel();
        panel.setLayout(gbl);
        panel.add(leftPanel, makeConstraints(0, 0));
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(rightPanel, makeConstraints(1, 0));
        return panel;
    }

    void showSeats() {
        Debug.console("Gui.showSeats");

        leftPanel.removeAll();
        for (Section section : sections) {
            section.render(theatre, section);
            leftPanel.add(section.seatsPanel);
        }
    }
}