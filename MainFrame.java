package com.packedgui;
import com.packedatabase.*;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainFrame {
    protected final int UI_INITIAL_PANEL = 1;
    protected final int UI_LOGIN_PANEL = 2;
    protected final int UI_ADMIN_PANEL = 3;
    protected final int UI_EMPLOYEE_PANEL = 4;

    JFrame mainFrame;
    JPanel mainPanel;

    public MainFrame() {
        startGUI();
    }

    public LoginPanel loginPanel = new LoginPanel();
    public AdminPanel adminPanel = new AdminPanel();
    public EmployeePanel employeePanel = new EmployeePanel();

    public void startGUI() {
        //Setup JFrame

        mainFrame = new JFrame();
        //mainFrame.setSize(400,400);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle("HR Management App 0.01");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Add to JFrame

        loginPanel.Init(this);
        adminPanel.Init(this);
        employeePanel.Init(this);
        mainPanel = new JPanel();
        mainPanel.add(loginPanel.getLoginPanel());
        mainPanel.add(adminPanel.getAdminPanel());
        mainPanel.add(employeePanel.getEmployeePanel());
        mainFrame.add(mainPanel);
        openPanel(2);
        mainFrame.pack();
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

    public void openPanel(int panelID) {
        loginPanel.openSelf(panelID == UI_LOGIN_PANEL);
        adminPanel.openSelf(panelID == UI_ADMIN_PANEL);
        employeePanel.openSelf(panelID == UI_EMPLOYEE_PANEL);
    }
}



