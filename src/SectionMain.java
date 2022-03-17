import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Locale;

/**
 * Singleton class<br/>
 * Methods:<br/>
 *      render(Theatre theatre, Section section)<br/>
 *          Called for all sections by Gui.showSeats()<br/>
 * Usage: SectionMain.getInstance().render(theatre, section);
 */
public class SectionMain extends GuiBase implements SectionInterface {
    private static SectionMain instance; // Singleton
    //
    private SectionMain() {
        instance = this;
    }
    static SectionMain getInstance() {
        return instance==null ? new SectionMain() : instance;
    }
    @Override
    public JPanel render(Theatre theatre, Section section) {
        int rowCount = section.getRowCount();
        int seatCount = section.getSeatCount(0);

        section.seatsPanel = new JPanel();
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

        section.seatsPanel.add(new JLabel("Hei på deg"));

        Section.Row[] rows = section.getRows();
        for(Section.Row row : rows) {
            for (Seat seat : row.seats) {
                final int rowNdx = seat.getRowNdx();
                final int seatNdx = seat.getSeatNdx();
                final int sectionNdx = seat.getSection();
                final Seat.STATE seatState = seat.getSeatState();
                //final int color = seat.getColor();
                final Customer customer = seat.getCustomer();
                final Customer.EType eType = customer.getEType();
                if (eType==Customer.EType.RANDOM) {
                    //farger inne her

                }
                else {
                    if (eType==Customer.EType.PRIVATE){
                        //Farger inne her 

                    }
                    else if(eType==Customer.EType.COMPANY) {
                        //FARGER INNI HER

                    }
                }

                // Section section, int rowNdx, int seatNdx, Color color
                //new Gui.CircleButton(sectionNdx, rowNdx, seatNdx, color);

            }
        }
        return section.seatsPanel;
    }
}
