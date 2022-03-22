class Customer {

    enum EType {FREE, PRIVATE, RANDOM, COMPANY,}

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
            sb.append("\tSeat in section: " + seat.getSectionNdx()).
                    append(", rowNdx: ").append(seat.getRowNdx()).
                    append(", seatNdx: ").append(seat.getSeatNdx()).append(Debug.NL);
        }

        return String.valueOf(sb);
    }

    static Customer factory(EType eType) {
        switch (eType) {
            case PRIVATE:
                return new Customer.Private();
            case COMPANY:
                return new Customer.Company();
            default:
                return new Customer.Random();
        }
    }

    Customer copy(EType eType) {
        // TODO: Explain and create factory method (static)
        Customer customer = Customer.factory(eType);
        if (eType == EType.PRIVATE) {
            customer = new Customer.Private();
        } else if (eType == EType.COMPANY) {
            customer = new Customer.Company();
            ((Company) customer).contactPerson = ((Company) this).contactPerson;
        } else if (eType == EType.RANDOM) {
            customer = new Customer.Random();
        }

        customer.id = id;
        customer.name = name;
        customer.phoneNumber = phoneNumber;
        customer.copySeats(seats);
        if (customer.eType == EType.COMPANY && customer.eType.equals(this.eType)) {
            ((Company) customer).contactPerson = ((Company) this).contactPerson;
        }
        return customer;
    }

    Seat[] copySeats(Seat[] seats) {
        Seat[] newSeats = new Seat[seats.length];
        for (int i = 0; i < seats.length; i++) {
            newSeats[i] = seats[i].copy(this);
        }
        return newSeats;
    }

    public int getId() {
        return id;
    }

    public Seat[] getSeats() {
        return seats;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public void setSeats(Seat[] seats) {
        assert this.eType == EType.PRIVATE && seats.length > 1;
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