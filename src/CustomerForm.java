import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerForm extends GuiBase implements Form {

    private final Map<String, JButton> buttons = new HashMap<>();

    private final JLabel promptName = new JLabel("Name");
    private final JLabel promptPhone = new JLabel("Telephone");
    private final JLabel promptType = new JLabel("Type");
    private final JLabel promptContactPerson = new JLabel("Contact person");
    private final JLabel promptSeats = new JLabel();


    private final JTextField valueId = new JTextField();
    private final JTextField valueName = new JTextField();
    private final JTextField valuePhone = new JTextField();
    private final JTextField valueContact = new JTextField();
    private JComboBox ddCustomerTypes = new JComboBox();
    private final JTextArea valueSeats = new JTextArea();
    private Customer.EType saveCustomerType;

    //String[] ddChoices = new String[]{"Random", "Private", "Company"};
    String[] ddChoices = Customer.ddChoices;

    JPanel jFields = new JPanel(); // All field prompts and values - 2 columns, 5 rows


    private static class C { // Constants
        static class size {
            static final Dimension dialog = new Dimension(380, 250);
        }

        static class pos {
            static final Point dialog = new Point(560, 250);
        }

        static class height {
            static final int fields = 190;
            static final int buttons = 36;
        }

        static class color {
            static final Color seatsBackground = Color.white; // valueSeats: Reset to inputBackgroundColor
            static final Color highlightColor = new Color(0, 255, 27); // valueSeats
            static final Color changedField = Color.yellow;
        }

        static class background {
            static final Color baseBackground = Color.white;
            static final Color customerForm = Color.red; // customerForm (should be hidden by formFields panel)
            static final Color formFields = new Color(45, 150, 255);
            static final Color seats = Color.white; // seats
            static final Color formButtons = new Color(228, 228, 228); // formButtons
        }

        static class border {
            static Border field; // Set by constructor
        }
    }

    public CustomerForm(CustomerTable table, Form.FORM_ACTION action) {
        Debug.console("CustomerForm, action: " + action);
        String selectedId = table.getSelectedId();

        ddCustomerTypes = new JComboBox(ddChoices);
        ddCustomerTypes.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                String selectedItem = (String) ((JComboBox) e.getSource()).getSelectedItem();
                saveCustomerType = Customer.Type.getDdEType(selectedItem);
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                // Check if customerType has been changed
                JComboBox ddChoices = (JComboBox) e.getSource();
                Customer.EType selectedEtype = Customer.Type.getDdEType((String) ddChoices.getSelectedItem());
                if (!selectedEtype.equals(saveCustomerType)) {
                    setVisible(selectedEtype);
                }
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });
        Customer customer = Customers.find(Integer.valueOf(selectedId));
        if (action == FORM_ACTION.INSERT) {
            customer = Customer.factory(customer.getEType());
        } else {
            customer = customer.copy();
        }
        buttons.put("pickUp", new JButton("Get tickets"));
        buttons.put("ok", new JButton("OK"));
        buttons.put("cancel", new JButton("Cancel"));

        // Create dialog
        JDialog dialog = new JDialog();
        dialog.setTitle(ACTION.get(action) + " Customer");

        JPanel form = new JPanel(); // Outer form: All but window-title - 1 column, 2 rows
        JPanel jFields = new JPanel(); // All field prompts and values - 2 columns, 5 rows
        JPanel buttonsPanel = new JPanel(); // All buttons - no grid

        // Informative only
        form.setName("form");
        jFields.setName("jFields");
        buttonsPanel.setName("buttonsPanel");

        // Outer form: 1 column, 2 rows (All but window-title)
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{C.size.dialog.width};
        gbl.columnWeights = new double[]{0.0};
        gbl.rowHeights = new int[]{C.height.fields, C.height.buttons};
        gbl.rowWeights = new double[]{0.0, 0.0};
        form.setLayout(gbl);

        // Fields panel: 2 columns, 5 rows
        GridBagLayout gblF = new GridBagLayout();
        gblF.columnWidths = new int[]{100, 200};
        gblF.columnWeights = new double[]{0.0, 0.0};
        gblF.rowHeights = new int[]{30, 30, 30, 30, 30};
        gblF.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        jFields.setLayout(gblF);

        // Add all prompts to fields-panel
        jFields.add(promptName, makeConstraints(0, 0));
        jFields.add(promptPhone, makeConstraints(0, 1));
        jFields.add(promptType, makeConstraints(0, 2));
        jFields.add(promptContactPerson, makeConstraints(0, 3));

        jFields.add(promptSeats, makeConstraints(0, 4));
        // Add all values to fields-panel
        jFields.add(valueId);
        jFields.add(valueName, makeConstraints(1, 0));
        jFields.add(valuePhone, makeConstraints(1, 1));
        jFields.add(ddCustomerTypes, makeConstraints(1, 2));
        jFields.add(valueContact, makeConstraints(1, 3));

        saveCustomerType = customer.getEType();
        ddCustomerTypes.setSelectedItem(Customer.Type.getDdText(saveCustomerType));
        jFields.add(new JScrollPane(valueSeats), makeConstraints(1, 4));

        // All field- prompts and values added to jFields

        // Add all buttons to buttons panel
        buttonsPanel.add(buttons.get("pickUp"));
        buttonsPanel.add(buttons.get("ok"));
        buttonsPanel.add(buttons.get("cancel"));

        ActionListener actionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final String cmd = e.getActionCommand();
                if (cmd.matches("Cancel")) {
                    dialog.dispose();
                }
            }
        };

        buttons.get("cancel").addActionListener(actionListener);


        // jFields and buttonsPanel completely populated

        // Set all field values
        valueId.setVisible(false);
        valueId.setText(String.valueOf(customer.getId()));
        valueName.setText(customer.getName());
        valuePhone.setText(customer.getPhoneNumber());
        if (customer.getEType() == Customer.EType.COMPANY) {
            valueContact.setText(((Customer.Company) customer).getContactPerson());
        }

        Seat[] seats = customer.getSeats();
        String[] seatsTxt = new String[seats.length];
        int i = 0;
        for (Seat seat : seats) {
            seatsTxt[i++] = "Section " + (seat.getSectionNdx() + 1) + ", row " + (seat.getRowNdx() + 1) + ", seat " + (seat.getSeatNdx() + 1);
        }
        valueSeats.setText(Arrays.stream(seatsTxt).collect(Collectors.joining("\n")));
        valueSeats.setEditable(false);


        if (action == FORM_ACTION.DELETE) {
            valueName.setEditable(false);
            valuePhone.setEditable(false);
            valueContact.setEditable(false);
            ddCustomerTypes.setEditable(false);
            valueSeats.setEditable(false);
        }


        // Add jFields to form row 0
        form.add(jFields, makeConstraints(0, 0));

        // Add buttons to form row 1
        form.add(buttonsPanel, makeConstraints(0, 1));

        // All fields and buttons added to form
        // Add form to dialog
        dialog.add(form);
        dialog.setLocation(C.pos.dialog.x, C.pos.dialog.y);

        // TODO: Not required?
        dialog.setSize(C.size.dialog.width, C.size.dialog.height);

        // Debug!
        jFields.setBackground(C.background.formFields);
        setVisible(customer.getEType());
        dialog.setVisible(true);
        dialog.pack();
    }

    private void setVisible(Customer.EType eType) {
        if (eType == Customer.EType.COMPANY) {
            valueContact.setVisible(true);
            promptContactPerson.setVisible(true);
        } else {
            valueContact.setVisible(false);
            promptContactPerson.setVisible(false);
        }
        if (eType == Customer.EType.PRIVATE) {
            valueSeats.setRows(1);
            promptSeats.setText("Seat");
        } else {
            valueSeats.setRows(5);
            promptSeats.setText("Seats");
        }

    }
}