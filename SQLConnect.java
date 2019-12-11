package com.packedatabase;
import com.packedgui.LoginPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class SQLConnect
{
    public static Connection connection = null;
    public static Statement stmt = null;
    public static PreparedStatement prepStmt = null;
    public static ResultSet rs = null;
    public static boolean login = false;
    public static boolean Admin = false;
    public static ArrayList<String> Data = new ArrayList<String>();
    private static String QUERYSEARCH;
    private static String QUERYTABLE;
    private static String QUERYINSERT;
    private static String QUERYDELETE;


    public  static Connection getConnectionLogin(JTextField textField, JPasswordField passwordField){
        try{
            String username = textField.getText();
            String password = String.valueOf(passwordField.getPassword());
            String QUERYLOGIN = "SELECT  E.*, D.* FROM employee_table E LEFT JOIN department_table D ON E.Department_ID = D.Department_ID  WHERE First_Name=? and CAST(Pw AS BINARY) =?";

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","MasterJoker1");

            prepStmt = (PreparedStatement) connection.prepareStatement(QUERYLOGIN);
            prepStmt.setString(1,username);
            prepStmt.setString(2,password);
            rs = prepStmt.executeQuery();
            if (rs.next())
            {
                login = true;
                Data.add(rs.getString("Employee_ID"));
                Data.add(rs.getString("First_Name"));
                Data.add(rs.getString("Last_Name"));
                Data.add(rs.getString("Age"));
                Data.add(rs.getString("Department_ID"));
                Data.add(rs.getString("Department_Name"));
                if (rs.getInt("EmployeeType_ID") == 1 )
                {
                    Admin = false;
                }
                else if (rs.getInt("EmployeeType_ID") == 99)
                {
                    Admin = true;
                }

            }

            else
            {
                login = false;
            }

        }catch (SQLException e) {
            e.printStackTrace();

        }
        return connection;
    }

    public static Connection getConnectionTable(int tableNo, JTable table, DefaultTableModel model)
    {
        try{
            connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","MasterJoker1");
            stmt = connection.createStatement();
            switch (tableNo)
            {
                case 1:
                {
                    QUERYTABLE = "SELECT * from employee_table;";
                    ResultSet rs = stmt.executeQuery(QUERYTABLE);
                    model = new DefaultTableModel(new String[]{"Employee ID", "First Name", "Last Name","Age", "Login Name","Password", "Department ID"},0 );
                    while(rs.next())
                    {
                        String EmployeeID = rs.getString("Employee_ID");
                        String FirstName = rs.getString("First_Name");
                        String LastName = rs.getString("Last_Name");
                        String Age = rs.getString("Age");
                        String LoginName = rs.getString("First_Name");
                        String Password = rs.getString("Pw");
                        String DepartmentID = rs.getString("Department_ID");
                        model.addRow(new Object[]{EmployeeID, FirstName, LastName, Age, LoginName, Password, DepartmentID});

                    }
                    break;

                }
                case 2:
                {
                    QUERYTABLE = "SELECT * FROM project_table";
                    ResultSet rs = stmt.executeQuery(QUERYTABLE);
                    model = new DefaultTableModel(new String[]{"Project ID", "Project Name", "Project Begin Date", "Project End Date", "Project Status"},0);
                    while (rs.next())
                    {
                        String ProjectID = rs.getString("Project_ID");
                        String ProjectName = rs.getString("Project_Name");
                        String ProjectBeginDate = rs.getString("Project_BeginDate");
                        String ProjectEndDate = rs.getString("Project_EndDate");
                        String ProjectStatus = rs.getString("Project_Status");
                        model.addRow(new Object[]{ProjectID,ProjectName,ProjectBeginDate,ProjectEndDate,ProjectStatus});
                    }
                    break;
                }
                case 3:
                {
                    QUERYTABLE = "SELECT * FROM department_table ";
                    ResultSet rs = stmt.executeQuery(QUERYTABLE);
                    model = new DefaultTableModel(new String[]{"Department ID", "Department Name"},0);
                    while (rs.next())
                    {
                        String DepartmentID = rs.getString("Department_ID");
                        String DepartmentName = rs.getString("Department_Name");
                        model.addRow(new Object[]{DepartmentID, DepartmentName});
                    }
                    break;
                }

                case 4:
                {
                    QUERYTABLE = "SELECT E.*, EP.Project_ID, D.* FROM employee_table E LEFT JOIN emp_project_table EP ON E.Employee_ID = EP.Employee_ID LEFT JOIN department_table D ON E.Department_ID = D.Department_ID GROUP BY E.Employee_ID;";
                    ResultSet rs = stmt.executeQuery(QUERYTABLE);
                    model = new DefaultTableModel(new String[]{"Employee ID", "First Name", "Last Name","Age", "Login Name","Password", "Department ID",  "Department Name", "Project ID" },0 );
                    while(rs.next())
                    {
                        String EmployeeID = rs.getString("Employee_ID");
                        String FirstName = rs.getString("First_Name");
                        String LastName = rs.getString("Last_Name");
                        String Age = rs.getString("Age");
                        String LoginName = rs.getString("First_Name");
                        String Password = rs.getString("Pw");
                        String DepartmentID = rs.getString("Department_ID");
                        String DepartmentName = rs.getString("Department_Name");
                        String ProjectID = rs.getString("Project_ID");
                        model.addRow(new Object[]{EmployeeID, FirstName, LastName, Age, LoginName, Password, DepartmentID, DepartmentName, ProjectID});
                    }
                    break;
                }

            }

            table.setModel(model);
            table.setEnabled(false);

        }
        catch(SQLException e)
        {
            e.printStackTrace();

        }
        return connection;
    }

    public static Connection getConnectionInsert(int tableNo)
    {
        String temp;

        try{
            switch (tableNo)
            {
                case 1:
                {
                    QUERYINSERT = "INSERT INTO employee_table (Employee_ID, First_Name, Last_Name, Pw, Age, Attendance, Salary_Multiplier, EmployeeType_ID) VALUES (?, ?, ?, ?, ?, ?, ? ,? )" ;
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","MasterJoker1");

                    PreparedStatement preparedStatement = connection.prepareStatement(QUERYINSERT);


                    temp = JOptionPane.showInputDialog("Enter ID");
                    if (temp != null)
                    {
                        preparedStatement.setInt(1,Integer.parseInt(temp));
                    }
                    else { break; }

                    temp = JOptionPane.showInputDialog("Enter First Name");
                    if (temp != null)
                    {
                        preparedStatement.setString(2, temp);
                    }
                    else { break; }


                    temp = JOptionPane.showInputDialog("Enter Last Name");
                    if (temp != null)
                    {
                        preparedStatement.setString(3, temp);
                    }
                    else { break; }


                    temp = JOptionPane.showInputDialog("Enter Password");
                    if (temp != null)
                    {
                        preparedStatement.setString(4, temp);
                    }
                    else { break; }


                    temp = JOptionPane.showInputDialog("Enter Age");
                    if (temp != null)
                    {
                        preparedStatement.setInt(5,Integer.parseInt(temp));
                    }
                    else { break; }

                    //Attendance
                    preparedStatement.setBoolean(6, false);
                    //Salary Multiplier
                    preparedStatement.setFloat(7, Float.valueOf("5"));
                    //Type ID
                    preparedStatement.setInt(8,1);

                    if (temp != null)
                    {
                        preparedStatement.executeUpdate();
                    }
                    else { break; }
                }


                case 2:
                {
                    QUERYINSERT = "INSERT INTO project_table (Project_ID, Project_Name, Project_BeginDate, Project_EndDate, Project_Status) VALUES (?, ?, ?, ?, ?)" ;
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","MasterJoker1");

                    PreparedStatement preparedStatement = connection.prepareStatement(QUERYINSERT);


                    temp = JOptionPane.showInputDialog("Enter ID");
                    if (temp != null)
                    {
                        preparedStatement.setInt(1, Integer.parseInt(temp));
                    }
                    else { break; }


                    temp = JOptionPane.showInputDialog("Enter Project Name");
                    if (temp != null)
                    {
                        preparedStatement.setString(2, temp);
                    }
                    else { break; }


                    //IMPLEMENT PATTERN CHECKER FOR THE DATE

                    temp = JOptionPane.showInputDialog("Enter Project Begin Date");
                    if (temp != null)
                    {
                        preparedStatement.setDate(3, Date.valueOf(temp));
                    }
                    else { break; }

                    temp = JOptionPane.showInputDialog("Enter Project End Date");
                    if (temp != null)
                    {
                        preparedStatement.setDate(4, Date.valueOf(temp));
                    }
                    else { break; }

                    temp = JOptionPane.showInputDialog("Enter Project Status");
                    if (temp != null)
                    {
                        preparedStatement.setInt(5,Integer.parseInt(temp));
                    }
                    else { break; }

                    if (temp != null)
                    {
                        preparedStatement.executeUpdate();
                    }
                    else { break; }

                }

                case 3:
                {
                    QUERYINSERT = "INSERT INTO department_table (Department_ID, Department_Name) VALUES (?, ?)" ;
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","MasterJoker1");

                    PreparedStatement preparedStatement = connection.prepareStatement(QUERYINSERT);


                    temp = JOptionPane.showInputDialog("Enter Department ID");
                    if (temp != null)
                    {
                        preparedStatement.setInt(1,Integer.parseInt(temp));
                    }
                    else { break; }

                    temp = JOptionPane.showInputDialog("Enter Department Name");
                    if (temp != null)
                    {
                        preparedStatement.setString(2, temp);
                    }
                    else { break; }

                    if (temp != null)
                    {
                        preparedStatement.executeUpdate();

                    }
                    else { break; }

                }
            }


        }catch (SQLException e) {
            e.printStackTrace();

        }
        return connection;
    }

    public static Connection getConnectionDelete(int tableNo)
    {
        String temp;

        try{
            switch (tableNo)
            {
                case 1:
                {
                    QUERYDELETE = "DELETE FROM employee_table WHERE Employee_ID=?";
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","MasterJoker1");

                    PreparedStatement preparedStatement = connection.prepareStatement(QUERYDELETE);

                    temp = JOptionPane.showInputDialog("Enter Employee's ID to be Deleted");
                    if (temp != null)
                    {
                        preparedStatement.setInt(1,Integer.parseInt(temp));
                    }
                    else { break; }

                    if (temp != null)
                    {
                        int row = preparedStatement.executeUpdate();
                    }
                    else { break; }
                }

                case 2:
                {
                    QUERYDELETE = "DELETE FROM project_table WHERE Project_ID=?";
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","MasterJoker1");

                    PreparedStatement preparedStatement = connection.prepareStatement(QUERYDELETE);


                    temp = JOptionPane.showInputDialog("Enter Projects ID to be Deleted");
                    if (temp != null)
                    {
                        preparedStatement.setInt(1,Integer.parseInt(temp));
                    }
                    else { break; }

                    if (temp != null)
                    {
                        int row = preparedStatement.executeUpdate();
                    }
                    else { break; }

                }

                case 3:
                {
                    QUERYDELETE = "DELETE FROM department_table WHERE Department_ID=?";
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","MasterJoker1");

                    PreparedStatement preparedStatement = connection.prepareStatement(QUERYDELETE);


                    temp = JOptionPane.showInputDialog("Enter Departments ID to be Deleted");
                    if (temp != null)
                    {
                        preparedStatement.setInt(1,Integer.parseInt(temp));

                    }
                    else { break; }

                    if (temp != null)
                    {
                        int row = preparedStatement.executeUpdate();
                    }
                    else { break; }
                }


            }



        }catch (SQLException e) {
            e.printStackTrace();

        }
        return connection;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }




}
