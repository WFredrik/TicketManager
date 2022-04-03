import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerTable extends GuiBase {
    private final Theatre theatre;
    private final int selectedRowNdx;
    private final JPanel container;
    private final Map<String, JButton> buttons = new HashMap<>();
    List<Customer> customers;
    JTable table;

    private static class C {
        static class height {
            static final int header = 30;
            static final int buttons = 32;
        }

        static class background {
            static final Color title = new Color(215, 235, 255);
            static final Color buttonsPanel = new Color(228, 228, 228);
            static final Color scrollPanel = Color.white;
        }
    }

    CustomerTable(Theatre theatre, int selectedRowNdx) {
        this.theatre = theatre;
        this.selectedRowNdx = selectedRowNdx;
        customers = theatre.getCustomers();
        container = theatre.gui.rightPanel;
        if (container.getComponentCount() > 0) {
            container.removeAll();
        }

        // Grid: 1 x 3
        JPanel title = new JPanel();
        JPanel jPanelButtons = new JPanel();

        title.add(new JLabel("Customers"));
        container.add(title, makeConstraints(0, 0));

        GridBagLayout gbl = new GridBagLayout();
        gbl.rowHeights = new int[]{C.height.header, C.height.buttons, Gui.C.height.rightPanel - C.height.header - C.height.buttons};
        gbl.rowWeights = new double[]{0.0, 0.0, 0.0};
        gbl.columnWidths = new int[]{Gui.C.width.rightPanel};
        gbl.columnWeights = new double[]{0.0};
        container.setLayout(gbl);

        buttons.put("insert", new JButton("New"));
        buttons.put("edit", new JButton("Edit"));
        buttons.put("delete", new JButton("Delete"));

        jPanelButtons.add(buttons.get("insert"));
        jPanelButtons.add(buttons.get("edit"));
        jPanelButtons.add(buttons.get("delete"));

        CustomerTable customerTable = this;

        ActionListener actionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final String cmd = e.getActionCommand();
                Form.FORM_ACTION action = cmd.matches("Edit") ? Form.FORM_ACTION.EDIT :
                        cmd.matches("New") ? Form.FORM_ACTION.INSERT : Form.FORM_ACTION.DELETE;
                Debug.console("CustomerTable.actionPerformed." + action);

                Debug.console("actionlistener: " + e);
                //String action = ((JButton) e.getSource()).getName();
                new CustomerForm(customerTable, action);

            }
        };

        buttons.get("insert").addActionListener(actionListener);
        buttons.get("edit").addActionListener(actionListener);
        buttons.get("delete").addActionListener(actionListener);

        buttons.get("insert").setName("insert");
        buttons.get("edit").setName("edit");
        buttons.get("delete").setName("delete");


        // Build and show customerList
        showList(selectedRowNdx);


        container.add(jPanelButtons, makeConstraints(0, 1));
        GuiBase.getFrame().pack();
    }

    void showList(int selectedRowNdx) {
        JScrollPane jScrollPane = makeList(selectedRowNdx);
        container.add(jScrollPane, makeConstraints(0, 2));
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    private String getId(int rowNdx) {
        return String.valueOf(table.getModel().getValueAt(rowNdx, 0));
    }

    String getSelectedId() {
        final int selectedNdx = table.getSelectedRow();
        return selectedNdx == -1 ? null : getId(table.getSelectedRow());
    }

    JScrollPane makeList(int selectedRowNdx) {
        final String[][] data = new String[customers.size()][4];
        Customer customer;
        for (int i = 0; i < customers.size(); i++) {
            customer = customers.get(i);
            data[i][0] = String.valueOf(customer.getId());
            data[i][1] = customer.getName();
            data[i][2] = Customer.Type.get(customer.getEType());
            data[i][3] = customer.getPhoneNumber();
        }
        String[] columnNames = {"Id", "Name", "Type", "Phone"};

        table = new JTable(data, columnNames);
        table.setModel(new DefaultTableModel(data, columnNames) {

            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        });
        if (selectedRowNdx > -1) {
            table.setRowSelectionInterval(selectedRowNdx, selectedRowNdx);
            table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        }

        // Remove id column from screen (kept in customerTable.getModel().getValueAt(selectedRow, 0))
        TableColumnModel tcm = table.getColumnModel();
        tcm.removeColumn(tcm.getColumn(0));

        final JScrollPane jScrollPane = new JScrollPane(table);
        return jScrollPane;

    }
}
