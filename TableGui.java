import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Spykexx on 8/5/2017.
 */
public class TableGui extends JFrame implements ActionListener{
    DecimalFormat df = new DecimalFormat("#0.00");
        private JTable table1;
        private JLabel instructions = new JLabel("Right click to delete selected row. This will delete that entry in \"stars.txt\" as well. Sort using column header.");
        private String[] colName = {"Rep ID", "First Name", "Last Name", "Supplies Sold", "Books Sold", "Paper Sold", "District", "Contact Pref"};
        private Object[] repData = new Object[9];
        private DefaultTableModel tableModel;
        private JPopupMenu menuRightClick;
        private JMenuItem removeSelection;
        private JMenuItem removeAllEntries;
        List<SalesRep> listCopy = new ArrayList<SalesRep>();
    public TableGui(List<SalesRep> l){
        listCopy.addAll(l);
        tableModel = new DefaultTableModel(){
            Class[] types = { Integer.class, String.class, String.class, Double.class, Double.class, Double.class, String.class, String.class };

            @Override
            public Class getColumnClass(int columnIndex) {
                return this.types[columnIndex];
            }
            @Override
            public int getColumnCount(){
                return colName.length;
            }

            @Override
            public String getColumnName(int column) {
                return colName[column];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1 = new JTable(tableModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table1.getModel());
        table1.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);


        JScrollPane JSP = new JScrollPane(table1);
        JSP.setPreferredSize(new Dimension(770, 375));

        menuRightClick = new JPopupMenu();

        removeSelection = new JMenuItem("Remove Current Row");
        removeAllEntries = new JMenuItem("Remove All Rows");


        removeSelection.addActionListener(this);
        removeAllEntries.addActionListener(this);


        menuRightClick.add(removeSelection);
        menuRightClick.add(removeAllEntries);

        table1.setComponentPopupMenu(menuRightClick);

        table1.addMouseListener(new TableMouseListener(table1));


        for(int i = 0; i < l.size(); i++){
            repData[0] = l.get(i).getSalesID();
            repData[1] = l.get(i).getFName();
            repData[2] = l.get(i).getLName();
            repData[3] = l.get(i).getSuppliesTotal();
            repData[4] = l.get(i).getBooksTotal();
            repData[5] = l.get(i).getPaperTotal();
            repData[6] = l.get(i).getDistrict();
            repData[7] = l.get(i).getContact();
            tableModel.addRow(repData);

        }

        TableColumnModel tcm = table1.getColumnModel();
        tcm.getColumn(3).setCellRenderer(NumRender.getCurrencyFormat());
        tcm.getColumn(4).setCellRenderer(NumRender.getCurrencyFormat());
        tcm.getColumn(5).setCellRenderer(NumRender.getCurrencyFormat());
        JPanel Master = new JPanel(new BorderLayout());
        Master.add(JSP, BorderLayout.CENTER);
        Master.add(instructions, BorderLayout.PAGE_END);
        this.getContentPane().add(Master);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menu = (JMenuItem) e.getSource();
        if (menu == removeSelection) {
            removeSelectedRow();
        } else if (menu == removeAllEntries) {
            removeAllEntries();
        }
    }

    private void removeSelectedRow() {
        int selectedRow = table1.getSelectedRow();
        selectedRow = table1.convertRowIndexToModel(selectedRow);
        listCopy.remove(selectedRow);
        WriteFile wf = new WriteFile(listCopy, "stars.txt");
        tableModel.removeRow(selectedRow);
    }

    private void removeAllEntries() {
        int rowCount = tableModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            tableModel.removeRow(0);
        }
        listCopy.clear();
        WriteFile wf = new WriteFile(listCopy, "stars.txt");
    }
    }

