;

public class Seat {
    enum STATE {free, reserved, randomReserved, randomCollected, selected}
    private Customer customer;
    private int rowNdx;
    private int seatNdx;
    private STATE seatState;
    private int section;

    Seat(int section, int rowNdx, int seatNdx, Customer customer) {
        this.customer = customer;
        this.rowNdx = rowNdx;
        this.seatNdx = seatNdx;
        this.seatState = STATE.free;
        this.section = section;
    }
    Seat(int section, int rowNdx, int seatNdx) {
        this.customer = null;
        this.rowNdx = rowNdx;
        this.seatNdx = seatNdx;
        this.seatState = STATE.free;
        this.section = section;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getRowNdx() {
        return rowNdx;
    }

    public void setRowNdx(int rowNdx) {
        this.rowNdx = rowNdx;
    }

    public int getSeatNdx() {
        return seatNdx;
    }

    public void setSeatNdx(int seatNdx) {
        this.seatNdx = seatNdx;
    }

    public STATE getSeatState() {
        return seatState;
    }

    public void setSeatState(STATE seatState) {
        this.seatState = seatState;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }


}
