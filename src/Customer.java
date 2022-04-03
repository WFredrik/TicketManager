import java.util.Arrays;

class Customer {
    enum EType {FREE, PRIVATE, RANDOM, COMPANY,}


    static String[] ddChoices = new String[]{"Random", "Private", "Company"};

    static class Type {

        static int getIndex(String ddType) {
            return Arrays.asList(ddChoices).indexOf(ddType);
        }

        static EType getEtype(String strType) {
            switch (strType) {
                case "Private":
                    return EType.PRIVATE;
                case "Company":
                    return EType.COMPANY;
                default:
                    return EType.RANDOM; // "Random"
            }
        }

        static String getDdText(EType eType) {
            switch (eType) {
                case PRIVATE:
                    return "Private";
                case COMPANY:
                    return "Company";
                default:
                    return "Random";    // Random:
            }
        }

        static EType getDdEType(String ddType) {
            switch (ddType) {
                case "Private":
                    return EType.PRIVATE;
                case "Company":
                    return EType.COMPANY;
                default:
                    return EType.RANDOM; // "Tilfeldig"
            }
        }

        static String get(EType eType) {
            switch (eType) {
                case PRIVATE:
                    return "Private";
                case COMPANY:
                    return "Company";
                default:
                    return "Random"; // Random
            }
        }
    }

    private int id;
    private String name;
    private String phoneNumber;
    private Seat seats[] = new Seat[0]; // NB! Avoid null
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

    Customer copy() {
        return copy(eType);
    }

    Customer copy(EType eType) {
        Customer customer = Customer.factory(eType);
        customer.id = id;
        customer.name = name;
        customer.phoneNumber = phoneNumber;
        customer.copySeats(seats);
        if (customer.eType == EType.COMPANY && customer.eType.equals(this.eType)) {
            ((Company) customer).contactPerson = ((Company) this).contactPerson;
        }
        return customer;
    }

    void copySeats(Seat[] seats) {
        this.seats = new Seat[seats.length];
        for (int i = 0; i < seats.length; i++) {
            this.seats[i] = seats[i].copy(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getContact() {
        return getContact();
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Seat[] getSeats() {
        return seats;
    }

    public EType getEType() {
        return eType;
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

    public void setEType(EType eType) {
        this.eType = eType;
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