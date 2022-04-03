import javax.swing.*;
import java.util.List;

public class Theatre {
    private final String name;
    public Section[] sections;
    private List<Customer> customers;
    public Gui gui;
    private int[] rowSeatCount;

    public Theatre(String name) {
        this.name = name;
        gui = new Gui(this);
        makeSections();
        load(true);
        //Debug.console(this.toString());
    }


    String getName() {
        return name;
    }

    private void makeSections() {
        rowSeatCount = new int[]{15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15};
        sections = new Section[1]; // Single section
        sections[0] = new Section(0, "Hoved", rowSeatCount) {
            @Override
            public JPanel render(Theatre theatre, Section section) {
                Debug.console("section.render() for section '" + section.getName() + "' calling SectionMain.render()");
                return SectionMain.getInstance().render(theatre, section);
                // All section.seats have state.free and no customer at this point
            }
        };
    }

    void load(boolean loadData) {
        Debug.console("Theatre.load(loadData), loadData: " + loadData);
        if (loadData) {
            customers = Customers.getDummyData();
        }
        gui.setSections(sections);
        gui.showSeats();
        gui.loadCustomerTable(0);
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

    // TR, 21.03 added next method
    List<Customer> getCustomers() {
        return customers;
    }
}