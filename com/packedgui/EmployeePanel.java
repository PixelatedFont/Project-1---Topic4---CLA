package com.packedgui;
import com.packedatabase.*;
import com.packedatabase.SQLConnect;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;

public class EmployeePanel {
    private JPanel employeePanel, tablePanel, picturePanel, buttonPanel, detailPanel;
    private JButton b1,b2,b3,b4;
    private JScrollPane scrollPane;
    private JTable table;
    private JLabel firstName, lastName, age, departmentID, employeeID, departmentName;

    public EmployeePanel()
    {

    }

    public void Init(MainFrame mainFrame)
    {
        String[] cols = {"Please select a table","CURRENT_TABLE", "Project Cheerio", "Fire Department"};
        String[][] data = {{"", ""},{"", ""}};

        DefaultTableModel model = new DefaultTableModel(data, cols);

        b4 = new JButton("Check Attendance");

        employeeID = new JLabel();
        firstName = new JLabel();
        lastName = new JLabel();
        age = new JLabel();
        departmentID = new JLabel();
        departmentName = new JLabel();

        //Picture Panel
        picturePanel = new JPanel();
        picturePanel.setLayout(new BoxLayout(picturePanel,BoxLayout.Y_AXIS));
        picturePanel.setBorder(BorderFactory.createEtchedBorder());
        picturePanel.add(Box.createVerticalStrut(150));
        picturePanel.add(Box.createHorizontalStrut(150));
        picturePanel.add(b4);
        b4.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Table
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel,BoxLayout.Y_AXIS));
        tablePanel.setBorder(BorderFactory.createEtchedBorder());
        //tablePanel.setPreferredSize(new Dimension(500,500));
        tablePanel.add(Box.createVerticalStrut(100));
        tablePanel.add(Box.createHorizontalStrut(500));

        //Detail Panel
        detailPanel = new JPanel();
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        detailPanel.add(employeeID);
        detailPanel.add(Box.createVerticalStrut(10));
        detailPanel.add(firstName);
        detailPanel.add(Box.createVerticalStrut(10));
        detailPanel.add(lastName);
        detailPanel.add(Box.createVerticalStrut(10));
        detailPanel.add(age);
        detailPanel.add(Box.createVerticalStrut(10));
        detailPanel.add(departmentID);
        detailPanel.add(Box.createVerticalStrut(10));
        detailPanel.add(departmentName);
        detailPanel.add(Box.createVerticalStrut(10));
        detailPanel.setBorder(BorderFactory.createEmptyBorder());

        //Employee Panel
        employeePanel = new JPanel();
        employeePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Employee View"));
        employeePanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,10,10,10);

        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;
        employeePanel.add(picturePanel,constraints);

        constraints.gridy = 0;
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        employeePanel.add(detailPanel,constraints);

        constraints.gridy = 1;
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.SOUTH;
    }

    public void openSelf(boolean val)
    {
        employeePanel.setVisible(val);
    }

    public JPanel getEmployeePanel(){
        return  employeePanel;
    }

    public void setData()
    {
        if (SQLConnect.login == true){
            employeeID.setText("Employee ID: " + SQLConnect.Data.get(0));
            firstName.setText("First Name: " + SQLConnect.Data.get(1));
            lastName.setText("Last Name: " + SQLConnect.Data.get(2));
            age.setText("Age: " + SQLConnect.Data.get(3));
            departmentID.setText("Department ID: " + SQLConnect.Data.get(4));
            departmentName.setText("Department Name: " + SQLConnect.Data.get(5));
        }
    }
}
