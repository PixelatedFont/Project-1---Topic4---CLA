package com.packedgui;

import com.packedatabase.SQLConnect;

import javax.swing.*;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;

public class AdminPanel {
    private JButton searchButton, addButton, deleteButton, editButton;
    private JTable table;
    private JPanel adminPanel, boxPanel, searchPanel, buttonPanel, tablePanel, selectPanel;
    private JScrollPane scrollPane;
    private JTextField searchBox;
    private JLabel currentTableLabel, searchLabel, companyLabel;
    private JComboBox<String> tableBox , someBox;
    private JDialog dialog;
    private TableRowSorter<TableModel> rowSorter;

    public AdminPanel(){

    }

    public void Init(MainFrame mainFrame){
        String[] cols = {"Please select a table","Employee List", "Project List", "Department List", "Mixed Table"};
        DefaultTableModel model = new DefaultTableModel();
        ImageIcon icon = new ImageIcon("D:\\Capture.png");

        currentTableLabel = new JLabel("CURRENT_TABLE");
        searchLabel = new JLabel("SEARCH BOX");

        companyLabel = new JLabel(icon);

        searchBox = new JTextField();
        searchBox.setBorder(BorderFactory.createEtchedBorder());

        tableBox = new JComboBox<String>(cols);
        tableBox.setBorder(BorderFactory.createEtchedBorder());
        tableBox.setUI(new BasicComboBoxUI(){
            @Override
            protected JButton createArrowButton()
            {
                JButton arrowButton = super.createArrowButton();
                arrowButton.setForeground(Color.white);
                arrowButton.setBackground(Color.darkGray);
                return arrowButton;
            }
        });

        //tableBox.setSelectedIndex(2);

        searchButton = new JButton("SEARCH");
        searchButton.setBackground(Color.darkGray);
        searchButton.setForeground(Color.white);
        searchButton.setMaximumSize(searchBox.getMaximumSize());
        searchButton.setBorder(BorderFactory.createEtchedBorder());
        searchButton.setFocusable(false);

        editButton = new JButton("EDIT");
        editButton.setBackground(Color.darkGray);
        editButton.setForeground(Color.WHITE);
        editButton.setMaximumSize(searchButton.getMaximumSize());
        editButton.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()));
        editButton.setEnabled(false);

        table = new JTable();
        scrollPane = new JScrollPane(table);
        rowSorter = new TableRowSorter<>();
        table.setRowSorter(rowSorter);
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setReorderingAllowed(false);
        table.setDragEnabled(false);


        //SearchPanel
        searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel,BoxLayout.X_AXIS));
        searchPanel.add(searchBox);
        searchBox.setPreferredSize(new Dimension(100,30));
        searchPanel.add(searchButton);
        searchPanel.setBackground(Color.lightGray);


        //BoxPanel
        boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel,BoxLayout.Y_AXIS));
        boxPanel.add(searchPanel);
        boxPanel.setBackground(Color.lightGray);

        //Select Panel
        selectPanel = new JPanel();
        selectPanel.setLayout(new BoxLayout(selectPanel,BoxLayout.X_AXIS));
        selectPanel.add(tableBox);
        selectPanel.setBackground(Color.lightGray);
        selectPanel.add(Box.createHorizontalStrut(10));
        selectPanel.add(editButton);

        //TablePanel
        tablePanel = new JPanel();
        tablePanel.add(scrollPane);
        tablePanel.setBorder(BorderFactory.createEtchedBorder());
        tablePanel.setBackground(Color.lightGray);
        scrollPane.setBackground(Color.lightGray);
        scrollPane.getViewport().setBackground(Color.lightGray);
        scrollPane.setBorder(BorderFactory.createEtchedBorder());
        table.setPreferredScrollableViewportSize(new Dimension(1000,300));
        //table.setBackground(Color.lightGray);
        table.setBorder(BorderFactory.createEtchedBorder());



        //AdminPanel
        adminPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        adminPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Admin View"));
        searchPanel.setBorder(BorderFactory.createEtchedBorder());

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        adminPanel.add(selectPanel,constraints);

        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        adminPanel.add(boxPanel,constraints);

        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        adminPanel.add(companyLabel,constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.SOUTH;
        adminPanel.add(tablePanel, constraints);
        adminPanel.setBackground(Color.lightGray);


        //Action

        searchBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = searchBox.getText();
                searchBoxFunction(text);


            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchBox.getText();
                searchBoxFunction(text);
            }
        });
        tableBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableBox.getSelectedItem().equals("Employee List"))
                {
                    SQLConnect.getConnectionTable(1,table,model);
                    DataFrame.tableNo = 1;
                    rowSorter.setModel(table.getModel());
                    editButton.setEnabled(true);
                }
                else if (tableBox.getSelectedItem().equals("Project List")){
                    SQLConnect.getConnectionTable(2,table,model);
                    DataFrame.tableNo = 2;
                    rowSorter.setModel(table.getModel());
                    editButton.setEnabled(true);
                }
                else if (tableBox.getSelectedItem().equals("Department List")){
                    SQLConnect.getConnectionTable(3,table,model);
                    DataFrame.tableNo = 3;
                    rowSorter.setModel(table.getModel());
                    editButton.setEnabled(true);
                }
                else if (tableBox.getSelectedItem().equals("Mixed Table"))
                {
                    SQLConnect.getConnectionTable(4,table,model);
                    rowSorter.setModel(table.getModel());
                    DataFrame.tableNo = 4;
                    editButton.setEnabled(false);
                }
                else if (tableBox.getSelectedItem().equals("Please select a table")){
                    table.setModel(model);
                    editButton.setEnabled(false);
                }

            }
        });
        tableBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                searchBox.setText("");
            }
        });
        WindowListener windowListener = new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                setComponentState(adminPanel,true);
                table.setEnabled(false);
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        };
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DataFrame.tableNo == 1)
                {
                    DataFrame dataFrame = new DataFrame(1);
                    dataFrame.addWindowListener(windowListener);
                    dataFrame.setVisible(true);
                }
                else if (DataFrame.tableNo == 2)
                {
                    DataFrame dataFrame = new DataFrame(2);
                    dataFrame.addWindowListener(windowListener);
                    dataFrame.setVisible(true);
                }

                else if (DataFrame.tableNo == 3)
                {
                    DataFrame dataFrame = new DataFrame(3);
                    dataFrame.addWindowListener(windowListener);
                    dataFrame.setVisible(true);
                }
                else if (DataFrame.tableNo == 4)
                {

                }
                setComponentState(adminPanel,false);
            }
        });
    }

    public JPanel getAdminPanel(){
        return adminPanel;

    }

    void setComponentState(Component component, boolean enabled) {
        component.setEnabled(enabled);
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                setComponentState(child, enabled);
            }
        }
    }

    void searchBoxFunction(String text)
    {
        if (text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }


    public void openSelf(boolean val)
    {
        adminPanel.setVisible(val);
    }

}
