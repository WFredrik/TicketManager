import javax.swing.*;

public abstract class Section implements SectionInterface {
    private int id;
    private final String name;
    private final Row[] rows;
    private final int rowCount;
    private final int[] rowSeatCount;
    JPanel seatsPanel;


    // TODO: Implement SEATCATEGORY
    Section(int id, String name, int[] rowSeatCount) {
        this.id = id;
        this.name = name;
        this.rowCount = rowSeatCount.length;
        this.rowSeatCount = rowSeatCount;
        rows = new Row[this.rowCount];
        for (int i = 0; i < rowCount; i++) {
            int seatCount = rowSeatCount[i];
            rows[i] = new Row(id, i, seatCount);
        }
    }

    static class Row {
        int rowNdx;
        Seat[] seats;

        Row(int section, int rowNdx, int seatCount) {
            this.rowNdx = rowNdx;
            seats = new Seat[seatCount];
            for (int seatNdx = 0; seatNdx < seatCount; seatNdx++) {
                seats[seatNdx] = new Seat(section, rowNdx, seatNdx, Seat.STATE.free, null);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\tSection: ").append(name).append(Debug.NL).
                append("\t\tRows: ").append(rows.length).append(Debug.NL);
        for (int i = 0; i < rows.length; i++) {
            Row row = rows[i];
            sb.append("\t\t\tRow number: ").append(i).append(", seats: ").append(row.seats.length).append(Debug.NL);

            for (int j = 0; j < row.seats.length; j++) {
                Seat seat = row.seats[j];
                Customer customer = seat.getCustomer();
                if (customer != null) {
//          Customer id: 2, Type: Private, Name: Donald Duck, Phone: 777 77 777, Section: Hoved, Row: 1, Seat: 14

                    sb.append("\t\t\t\tCustomer id: ").append(customer.getId()).
                            append(", Type: ").append(customer.getEType()).
                            append(", Name: ").append(customer.getName()).
                            append(", Phone: ").append(customer.getPhoneNumber()).
                            append(", Section: ").append(name).
                            append(", Row: ").append(i).
                            append(", Seat: ").append(j).
                            append(Debug.NL);
                }
            }

            //return String.valueOf(sb);
        }
        return String.valueOf(sb);
    }


    public Row[] getRows() {
        return rows;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public JPanel getSeatsPanel() {
        return seatsPanel;
    }

    int getRowCount() {
        return rowCount;
    }

    int getSeatCount(int rowNdx) {
        return rowSeatCount[rowNdx];
    }
}