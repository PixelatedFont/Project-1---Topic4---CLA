package com.packedgui;

import com.packedatabase.SQLConnect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataFrame extends JFrame
{
    protected JFrame dataFrame;
    private  JPanel editPanel, buttonPanel;
    private static JTable table;
    private static JScrollPane scrollPane;
    private static TableRowSorter<TableModel> rowSorter;
    private JButton addButton, deleteButton;
    static DefaultTableModel model;
    protected static int tableNo;

    public DataFrame(int var)
    {
        super();
        model = new DefaultTableModel();

        setLocationRelativeTo(null);
        setTitle("EDIT FRAME");
        setBackground(Color.lightGray);
        setDefaultCloseOperation(dataFrame.DISPOSE_ON_CLOSE);

        addButton = new JButton("ADD");
        addButton.setBackground(Color.darkGray);
        addButton.setForeground(Color.white);
        addButton.setBorder(BorderFactory.createEtchedBorder());

        deleteButton = new JButton("DELETE");
        deleteButton.setBackground(Color.darkGray);
        deleteButton.setForeground(Color.white);
        deleteButton.setBorder(BorderFactory.createEtchedBorder());


        ActionListener addToEmployee = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLConnect.getConnectionInsert(1);

            }
        };

        ActionListener addToDepartment = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLConnect.getConnectionInsert(3);


            }
        };

        ActionListener addToProject = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLConnect.getConnectionInsert(2);
            }
        };

        ActionListener deleteToEmployee = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLConnect.getConnectionDelete(1);
            }
        };

        ActionListener deleteToProject = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLConnect.getConnectionDelete(2);
            }
        };

        ActionListener deleteToDepartment = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLConnect.getConnectionDelete(3);
            }
        };

        if(var == 1)
        {
            table = new JTable();
            scrollPane = new JScrollPane(table);
            rowSorter = new TableRowSorter<>();
            table.setRowSorter(rowSorter);
            table.setPreferredScrollableViewportSize(new Dimension(500,500));
            SQLConnect.getConnectionTable(1,table,model);
            rowSorter.setModel(table.getModel());
            addButton.addActionListener(addToEmployee);
            deleteButton.addActionListener(deleteToEmployee);
        }

        else if(var == 2)
        {
            table = new JTable();
            scrollPane = new JScrollPane(table);
            rowSorter = new TableRowSorter<>();
            table.setRowSorter(rowSorter);
            table.setPreferredScrollableViewportSize(new Dimension(500,500));
            SQLConnect.getConnectionTable(2,table,model);
            rowSorter.setModel(table.getModel());
            addButton.addActionListener(addToProject);
            deleteButton.addActionListener(deleteToProject);

        }

        else if(var == 3)
        {
            table = new JTable();
            scrollPane = new JScrollPane(table);
            rowSorter = new TableRowSorter<>();
            table.setRowSorter(rowSorter);
            table.setPreferredScrollableViewportSize(new Dimension(500,500));
            SQLConnect.getConnectionTable(3,table,model);
            rowSorter.setModel(table.getModel());
            addButton.addActionListener(addToDepartment);
            deleteButton.addActionListener(deleteToDepartment);
        }

        editPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,10,10,10);

        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        editPanel.add(addButton,constraints);

        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        editPanel.add(deleteButton,constraints);

        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.gridx = 0;
        constraints.gridy = 1;
        editPanel.add(scrollPane,constraints);

        add(editPanel);
        pack();
        setResizable(false);
        setVisible(false);
    }
}
