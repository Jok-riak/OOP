package vu_fst;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ExhibitionRegistrationApp extends JFrame {

    JTextField regIDField, nameField, facultyField, titleField, contactField, emailField;

    public ExhibitionRegistrationApp() {
        setTitle("Exhibition Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 2, 10, 10)); // 10 rows for extra buttons

        // Text Fields
        regIDField = new JTextField();
        nameField = new JTextField();
        facultyField = new JTextField();
        titleField = new JTextField();
        contactField = new JTextField();
        emailField = new JTextField();

        // Labels and Fields
        add(new JLabel("Registration ID:")); add(regIDField);
        add(new JLabel("Student Name:"));    add(nameField);
        add(new JLabel("Faculty:"));         add(facultyField);
        add(new JLabel("Project Title:"));   add(titleField);
        add(new JLabel("Contact Number:"));  add(contactField);
        add(new JLabel("Email Address:"));   add(emailField);

        // Buttons
        JButton registerButton = new JButton("Register");
        JButton clearButton    = new JButton("Clear");
        JButton exitButton     = new JButton("Exit");
        JButton searchButton   = new JButton("Search");

        // Add buttons to layout
        add(registerButton); add(clearButton);
        add(searchButton);   add(exitButton);

        // Button Actions
        registerButton.addActionListener(e -> handleRegistration());
        clearButton.addActionListener(e -> clearFields());
        exitButton.addActionListener(e -> System.exit(0));
        searchButton.addActionListener(e -> searchStudent());

        setSize(500, 400);
        setVisible(true);
    }

    private void clearFields() {
        regIDField.setText("");
        nameField.setText("");
        facultyField.setText("");
        titleField.setText("");
        contactField.setText("");
        emailField.setText("");
    }

    private void handleRegistration() {
        try {
            String regID = regIDField.getText();
            String name = nameField.getText();
            String faculty = facultyField.getText();
            String title = titleField.getText();
            String contact = contactField.getText();
            String email = emailField.getText();

            if (regID.isEmpty() || name.isEmpty() || faculty.isEmpty() ||
                title.isEmpty() || contact.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://VUE_Exhibition.accdb");
            String query = "INSERT INTO Participants (RegistrationID, Name, Faculty, ProjectTitle, Contact, Email) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, regID);
            stmt.setString(2, name);
            stmt.setString(3, faculty);
            stmt.setString(4, title);
            stmt.setString(5, contact);
            stmt.setString(6, email);
            stmt.executeUpdate();
            conn.close();

            JOptionPane.showMessageDialog(this, "Registration successful!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void searchStudent() {
        String regID = JOptionPane.showInputDialog(this, "Enter Registration ID to search:");

        if (regID != null && !regID.trim().isEmpty()) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:ucanaccess://VUE_Exhibition.accdb");
                String query = "SELECT * FROM Participants WHERE RegistrationID = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, regID);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String result = "Name: " + rs.getString("Name") + "\n" +
                                    "Faculty: " + rs.getString("Faculty") + "\n" +
                                    "Project Title: " + rs.getString("ProjectTitle") + "\n" +
                                    "Contact: " + rs.getString("Contact") + "\n" +
                                    "Email: " + rs.getString("Email");

                    JOptionPane.showMessageDialog(this, result, "Student Found", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No student found with Registration ID " + regID);
                }

                conn.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExhibitionRegistrationApp());
    }
}
