;

public class Seat {
    enum STATE {free, reserved, randomReserved, randomCollected, selected}

    private Customer customer;
    private int rowNdx;
    private int seatNdx;
    private STATE seatState;
    private int sectionNdx;

    Seat(int section, int rowNdx, int seatNdx, STATE seatState, Customer customer) {
        this.customer = customer;
        this.rowNdx = rowNdx;
        this.seatNdx = seatNdx;
        this.seatState = seatState;
        this.sectionNdx = section;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getRowNdx() {
        return rowNdx;
    }

    public int getSeatNdx() {
        return seatNdx;
    }

    public STATE getState() {
        return seatState;
    }

    public int getSectionNdx() {
        return sectionNdx;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setRowNdx(int rowNdx) {
        this.rowNdx = rowNdx;
    }

    public void setSeatNdx(int seatNdx) {
        this.seatNdx = seatNdx;
    }

    public void setState(STATE seatState) {
        this.seatState = seatState;
    }

    public void setSection(int section) {
        this.sectionNdx = section;
    }

    static Seat[] copy(Seat[] seats) {
        Seat[] seatS = new Seat[seats.length];
        for (int i = 0; i < seats.length; i++) {
            seatS[i] = seats[i];
            seatS[i].seatState = seats[i].seatState;
            seatS[i].customer = seats[i].customer;
            seatS[i].sectionNdx = seats[i].sectionNdx;
            seatS[i].rowNdx = seats[i].rowNdx;
            seatS[i].seatNdx = seats[i].seatNdx;
        }
        return seatS;
    }

    Seat copy(Customer customer) {
        return new Seat(sectionNdx, rowNdx, seatNdx, seatState, customer);
    }

}
