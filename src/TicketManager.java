/**
 * Oppgave 1a
 * <p>
 * • Create repo TicketManager on GitHub
 * • Clone repo to local machine
 * • Move this project to your new local repo
 * <p>
 * • Create code in class Customer with all data described in README.md
 * • Create subclasses for all customer types
 * • Create code in class Seat with all required data
 * • Create code in class Customers.getDummyData()
 * <p>
 * Ref. oppgave_1a.MD for sample output
 */
public class TicketManager {
    public static void main(String[] args) {
        Debug.on();
        Customers.getDummyData();
        oppgave_1a();
    }

    private static void printCustomers() {
        Debug.console("Customers:\n----------");
        for (Customer c : Customers.list) {
            Debug.console(c.toString());
        }
    }

    private static void oppgave_1a() {
        Debug.console("oppgave_1a:\n----------");
        printCustomers();
    }
}
