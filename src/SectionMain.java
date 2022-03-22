import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

/**
 * Singleton class<br/>
 * Methods:<br/>
 * render(Theatre theatre, Section section)<br/>
 * Called for all sections by Gui.showSeats()<br/>
 * Usage: SectionMain.getInstance().render(theatre, section);
 */
public class SectionMain extends GuiBase implements SectionInterface {
    private static SectionMain instance; // Singleton

    private SectionMain() {
        instance = this;
    }

    static SectionMain getInstance() {
        return instance == null ? new SectionMain() : instance;
    }

    @Override
    public JPanel render(Theatre theatre, Section section) {
        Debug.console("SectionMain.render " + section.getName());
        int rowCount = section.getRowCount();
        int seatCount = section.getSeatCount(0);

        section.seatsPanel = new JPanel();
        section.seatsPanel.setName("section.seatsPane");
        GridBagLayout gblMatrix = new GridBagLayout();

        gblMatrix.columnWidths = new int[seatCount];
        gblMatrix.columnWeights = new double[seatCount];
        gblMatrix.rowHeights = new int[rowCount];
        gblMatrix.rowWeights = new double[rowCount];

        Arrays.fill(gblMatrix.columnWidths, Gui.C.width.seat);
        Arrays.fill(gblMatrix.rowHeights, Gui.C.height.seat);
        Arrays.fill(gblMatrix.columnWeights, 0.0);
        Arrays.fill(gblMatrix.rowWeights, 0.0);

        section.seatsPanel.setLayout(gblMatrix);
        section.seatsPanel.setBackground(Gui.C.background.seatsPanel);
        JLabel label = new JLabel("");
        section.seatsPanel.add(label, GuiBase.makeConstraints(0, 0));

        //section.seatsPanel.add(new JLabel("Hei på deg"));

        Section.Row[] rows = section.getRows();
        // Top header: Seat numbers
        for (int seatNdx = 0; seatNdx < seatCount; seatNdx++) {
            label = new JLabel();
            label.setHorizontalAlignment(0);
            label.setText(Integer.toString(seatNdx + 1));
            section.seatsPanel.add(label, makeConstraints(seatNdx + 1, 0));
        }
        // Left header: Row numbers

        for (int rowNdx = 0; rowNdx < rowCount; rowNdx++) {
            label = new JLabel();
            label.setHorizontalAlignment(0);
            label.setText(Integer.toString(rowNdx + 1));
            section.seatsPanel.add(label, makeConstraints(0, rowNdx + 1));
        }

        //seatsS
        for (Section.Row row : rows) {
            for (Seat seat : row.seats) {
                final int rowNdx = seat.getRowNdx();
                final int seatNdx = seat.getSeatNdx();
                final Seat.STATE seatState = seat.getSeatState();
                Color color;
                final Customer customer = seat.getCustomer();
                final Customer.EType eType = customer == null ? Customer.EType.FREE : customer.getEType();
                if (eType == Customer.EType.RANDOM) {
                    color = seatState == Seat.STATE.randomCollected ?
                            Gui.C.color.randomCollected :
                            Gui.C.color.randomReserved;
                } else {
                    color = eType == Customer.EType.PRIVATE ?
                            Gui.C.color.reserved :
                            eType == Customer.EType.COMPANY ? Gui.C.color.reserved :
                                    Gui.C.color.free;
                }
                final Gui.CircleButton circleButton = new Gui.CircleButton(section, rowNdx, seatNdx, color);
                circleButton.setName("R" + rowNdx + "S" + seatNdx);
                circleButton.setBackground(Gui.C.background.seats);
                circleButton.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
                section.seatsPanel.add(circleButton, makeConstraints(seatNdx + 1, rowNdx + 1));
            }
        }
        return section.seatsPanel;
    }
}
