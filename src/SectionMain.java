import javax.swing.*;
import java.awt.*;
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

    Seat getSeat(Gui.CircleButton b, Section section) {
        return section.getRows()[b.getRowNdx()].seats[b.getSeatNdx()];
    }

    @Override
    public JPanel render(Theatre theatre, Section section) {
        Debug.console("SectionMain.render " + section.getName());
        //int rowCount = section.getRowCount();
        //int seatCount = section.getSeatCount(0);
        int rowCount = section.getRowCount() + 1; // + 1 for row numbers
        int seatCount = section.getSeatCount(0) + 1; // + 1 for seat numbers

        GridBagLayout gblMatrix = new GridBagLayout();
        gblMatrix.columnWidths = new int[seatCount];
        gblMatrix.columnWeights = new double[seatCount];
        gblMatrix.rowHeights = new int[rowCount];
        gblMatrix.rowWeights = new double[rowCount];

        Arrays.fill(gblMatrix.columnWidths, Gui.C.width.seat);
        Arrays.fill(gblMatrix.rowHeights, Gui.C.height.seat);
        Arrays.fill(gblMatrix.columnWeights, 0.0);
        Arrays.fill(gblMatrix.rowWeights, 0.0);

        section.seatsPanel = new JPanel();
        section.seatsPanel.setName("section.seatsPane");
        section.seatsPanel.setLayout(gblMatrix);
        section.seatsPanel.setBackground(Gui.C.background.seatsPanel);
        // TR, 21.02.2022: Commented out next line (moved label down)
        //section.seatsPanel.add(label, GuiBase.makeConstraints(0, 0));

        JLabel label;
        int rowNdx;
        int seatNdx;
        // Top header: Seat numbers
        for (seatNdx = 1; seatNdx < seatCount; seatNdx++) {
            label = new JLabel();
            label.setHorizontalAlignment(0);
            label.setText(Integer.toString(seatNdx));
            section.seatsPanel.add(label, makeConstraints(seatNdx, 0));
        }
        // Left header: Row numbers
        for (rowNdx = 1; rowNdx < rowCount; rowNdx++) {
            label = new JLabel();
            label.setHorizontalAlignment(0);
            label.setText(Integer.toString(rowNdx));
            section.seatsPanel.add(label, makeConstraints(0, rowNdx));
        }
        // TR, 21.03, added next section
        // All seats in all rows
        final Section.Row[] rows = section.getRows();
        // Set section.row.seat.state to customer.seat.state and section.row.seat.customer to customer
        for (Customer c : theatre.getCustomers()) {
            for (Seat customerSeat : c.getSeats()) {
                rowNdx = customerSeat.getRowNdx();
                seatNdx = customerSeat.getSeatNdx();
                rows[rowNdx].seats[seatNdx].setState(customerSeat.getState());
                rows[rowNdx].seats[seatNdx].setCustomer(c.copy());
            }
        }
        // Build seats
        for (Section.Row row : rows) {
            for (Seat seat : row.seats) {
                rowNdx = seat.getRowNdx();
                seatNdx = seat.getSeatNdx();
                final Seat.STATE seatState = seat.getState();
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
                circleButton.setName("C" + section + "R" + rowNdx + "S" + seatNdx);
                circleButton.setBackground(Gui.C.background.seats);

                circleButton.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //TODO: implement Selected seats.
                        Gui.CircleButton b = (Gui.CircleButton) e.getSource();
                        Seat seat = getSeat(b, section);
                        StringBuilder str = new StringBuilder("Over seat on section " + section.getName());
                        str.append(", row " + (b.getRowNdx() + 1));
                        str.append(", seat " + (b.getSeatNdx() + 1));
                        Customer customer = seat.getCustomer();
                        if (customer != null) {
                            str.append(", " + customer.getEType() + ", customer " + customer.getName());
                        }
                        Debug.console(str.toString());
                    }


                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        Gui.CircleButton b = (Gui.CircleButton) e.getSource();
                        Seat seat = getSeat(b, section);
                        StringBuilder str = new StringBuilder("Over seat on section " + section.getName());
                        str.append(", row " + (b.getRowNdx() + 1));
                        str.append(", seat " + (b.getSeatNdx() + 1));
                        Customer customer = seat.getCustomer();
                        if (customer != null) {
                            str.append(", " + customer.getEType() + ", customer " + customer.getName());
                        }
                        theatre.gui.inform(str.toString());
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        theatre.gui.inform("");
                    }
                });
                section.seatsPanel.add(circleButton, makeConstraints(seatNdx + 1, rowNdx + 1));
            }
        }
        return section.seatsPanel;
    }
}
