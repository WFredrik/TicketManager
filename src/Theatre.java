import javax.swing.*;
import java.util.List;

public class Theatre {
    private final String name;
    private Section[] sections = new Section[1];
    List<Customer> list;
    Gui gui;
    private int[] rowSeatCount;


    public Theatre(String name) {
        this.name = name;

/*        for (Customer customer : list) {
            int sectionNdx;
            int rowNdx;
            int seatNdx;
            Seat[] seats = customer.getSeats();
            for (Seat customerSeat : seats) {
                sectionNdx = customerSeat.getSectionNdx();
                rowNdx = customerSeat.getRowNdx();
                seatNdx = customerSeat.getSeatNdx();
                Section.Row r[] = sections[sectionNdx].rows;
                r[rowNdx].seats[seatNdx] = customerSeat;

            }
        }  */
        load(true);

        Debug.console(this.toString());
    }

    public String getName() {
        return name;
    }

    void load(boolean loadData) {
        StringBuilder str = new StringBuilder("Theatre.load(loadData): ").append(loadData);
        if (loadData) {
            list = Customers.getDummyData();
            rowSeatCount = new int[]{15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15};
            sections[0] = new Section(0, "Hoved", rowSeatCount) {
                @Override
                public JPanel render(Theatre theatre, Section section) {
                    return SectionMain.getInstance().render(theatre, section);
                }
            };
            str.append(", section[0] recreated");
            // All section.seats have state.free and no customer at this point
        }
        if (gui == null) {
            str.append(", gui does not exist, creating");
            gui = new Gui(this);
        }
        gui.setSections(sections);
        Debug.console(str.toString());

        gui.showSeats();
        gui.frame.pack();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Theatre: ").append(name).append(Debug.NL);

        for (Section section : sections) {
            str.append(section.toString());
        }
        return str.toString();
    }
}