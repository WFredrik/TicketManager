class Customer {

    enum EType {PRIVATE, RANDOM, COMPANY,}

    private int id;
    private String name;
    private String phoneNumber;
    private Seat seats[];
    private EType eType;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append(Debug.NL).append("Phone: ").append(phoneNumber).append(Debug.NL);

        for (Seat seat : seats) {
            sb.append("\tSeat in section: " + seat.getSection()).
                    append(", rowNdx: ").append(seat.getRowNdx()).
                    append(", seatNdx: ").append(seat.getSeatNdx()).append(Debug.NL);
        }

        return String.valueOf(sb);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Seat[] getSeats() {
        return seats;
    }
    public void setSeats(Seat[] seats, EType eType) {
        this.seats = seats;
        for (Seat seat : seats) {
            seat.setSeatState(eType == EType.RANDOM ? Seat.STATE.randomReserved : Seat.STATE.reserved);
        }
    }
    public void setSeats(Seat[] seats) {
        if (this.eType == EType.PRIVATE && seats.length > 1) {
            throw new IllegalArgumentException("Invalid parameter passed to Customer.setSeats: seats should have a length of 1");
        }
        this.seats = seats;
    }

    public EType getEType() {
        return eType;
    }

    public void setEType(EType eType) {
        this.eType = eType;
    }

    Customer() {
        this.setSeats(new Seat[0]);
    }
    public static class Private extends Customer {
        Private() {
            super();
            setEType(EType.PRIVATE);
        }
    }

    public static class Random extends Customer {
        Random() {
            super();
            setEType(EType.RANDOM);
        }
    }

    public static class Company extends Customer {
        private String contactPerson;

        Company() {
            super();
            setEType(EType.COMPANY);
        }

        public String getContactPerson() {
            return contactPerson;
        }

        public void setContactPerson(String contactPerson) {
            this.contactPerson = contactPerson;
        }
    }

}