package com.packedgui;
import com.packedatabase.SQLConnect;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginPanel
{
    private JPanel loginPanel;
    private JLabel userLabel, passwordLabel, message;
    private JButton loginButton, backButton;
    private JTextField userField;
    private JPasswordField passwordField;
    private JRadioButton adminRadio;
    private JRadioButton employeeRadio;

    public LoginPanel(){}

    public void Init(MainFrame mainFrame)
    {

        adminRadio = new JRadioButton("Admin");
        employeeRadio = new JRadioButton("Employee");
        employeeRadio.setSelected(true);
        userLabel = new JLabel("Username");
        userField = new JTextField(15);

        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField(15);

        message = new JLabel();
        loginButton = new JButton("Login");
        backButton = new JButton("Guest");
        backButton.setVisible(false);

        //Layout
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10,10,10,10);
        loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Login Panel"));

        constraints.gridx = 0;
        constraints.gridy = 0;
        loginPanel.add(userLabel,constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        loginPanel.add(userField,constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        loginPanel.add(passwordLabel,constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        loginPanel.add(passwordField,constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.EAST;
        loginPanel.add(employeeRadio,constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.WEST;
        loginPanel.add(adminRadio,constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.EAST;
        loginPanel.add(loginButton,constraints);

        loginButton.setEnabled(false);

        userField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changed();

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changed();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changed();

            }

            public void changed(){
                int a = userField.getText().length();

                if( a > 1  )
                {
                    loginButton.setEnabled(true);
                }
                else if(a <= 1) {
                    loginButton.setEnabled(false);
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLConnect.getConnectionLogin(userField, passwordField);
                if (SQLConnect.login==true && SQLConnect.Admin == false && employeeRadio.isSelected() )
                {

                    JOptionPane.showMessageDialog(loginButton,"Welcome " + SQLConnect.Data.get(1));
                    mainFrame.employeePanel.setData();
                    mainFrame.openPanel(4);
                    mainFrame.mainFrame.pack();
                }
                else if (SQLConnect.login==true && SQLConnect.Admin == true && adminRadio.isSelected())
                {
                    JOptionPane.showMessageDialog(loginButton,"Welcome administrator " + SQLConnect.Data.get(1));
                    mainFrame.openPanel(3);
                    mainFrame.mainFrame.pack();
                }
                else
                {
                    JOptionPane.showMessageDialog(loginButton,"FAILED");
                }

            }
        });
        employeeRadio.getAccessibleContext().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(employeeRadio.isSelected() == true)
                {
                   adminRadio.setSelected(false);
                }
            }
        });
        adminRadio.getAccessibleContext().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(adminRadio.isSelected() == true)
                {
                    employeeRadio.setSelected(false);
                }
            }
        });
    }

    public  void openSelf(boolean val)
    {
        loginPanel.setVisible(val);
    }

    public JPanel getLoginPanel()
    {
        return loginPanel;
    }
}
