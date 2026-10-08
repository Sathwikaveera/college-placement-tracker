package dbms;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

// ================= LOGIN PAGE =================

class LoginPage extends JFrame {

    JTextField userField;
    JPasswordField passField;
    JButton loginBtn;

    public LoginPage() {

        setTitle("Login Page");

        setSize(350,250);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel userLabel =
        new JLabel("Username:");

        userLabel.setBounds(50,50,100,30);

        add(userLabel);

        userField =
        new JTextField();

        userField.setBounds(150,50,120,30);

        add(userField);

        JLabel passLabel =
        new JLabel("Password:");

        passLabel.setBounds(50,100,100,30);

        add(passLabel);

        passField =
        new JPasswordField();

        passField.setBounds(150,100,120,30);

        add(passField);

        loginBtn =
        new JButton("Login");

        loginBtn.setBounds(120,160,100,30);

        add(loginBtn);

        loginBtn.addActionListener(
            new ActionListener() {

            public void actionPerformed(
                ActionEvent e
            ) {

                String username =
                userField.getText();

                String password =
                passField.getText();

                if(
                    username.equals("admin")
                    &&
                    password.equals("admin123")
                ) {

                    JOptionPane.showMessageDialog(
                        null,
                        "Login Successful"
                    );

                    new PlacementGUI();

                    dispose();

                } else {

                    JOptionPane.showMessageDialog(
                        null,
                        "Invalid Username or Password"
                    );
                }
            }
        });

        setVisible(true);
    }
}

// ================= MAIN GUI =================

public class PlacementGUI extends JFrame {

    JTable table;

    DefaultTableModel model;

    JTextField searchField;
    JTextField companyField;
    JTextField minField;
    JTextField maxField;

    public PlacementGUI() {

        setTitle("College Placement Management");

        setSize(1400,700);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();

        JButton viewBtn =
        new JButton("View Placements");

        JButton selectedBtn =
        new JButton("Selected");

        JButton rejectedBtn =
        new JButton("Rejected");

        JButton shortlistedBtn =
        new JButton("Shortlisted");

        JButton appliedBtn =
        new JButton("Applied");

        JButton highestBtn =
        new JButton("Highest Package");

        JButton avgBtn =
        new JButton("Average Package");

        JButton companyViewBtn =
        new JButton("View Companies");

        searchField =
        new JTextField(10);

        JButton searchBtn =
        new JButton("Search Student");

        companyField =
        new JTextField(10);

        JButton companyBtn =
        new JButton("Search Company");

        minField =
        new JTextField(5);

        maxField =
        new JTextField(5);

        JButton rangeBtn =
        new JButton("Package Range");

        // ADD BUTTONS
        topPanel.add(viewBtn);
        topPanel.add(selectedBtn);
        topPanel.add(rejectedBtn);
        topPanel.add(shortlistedBtn);
        topPanel.add(appliedBtn);

        topPanel.add(searchField);
        topPanel.add(searchBtn);

        topPanel.add(companyField);
        topPanel.add(companyBtn);

        topPanel.add(new JLabel("Min"));
        topPanel.add(minField);

        topPanel.add(new JLabel("Max"));
        topPanel.add(maxField);

        topPanel.add(rangeBtn);

        topPanel.add(highestBtn);
        topPanel.add(avgBtn);

        topPanel.add(companyViewBtn);

        add(topPanel, BorderLayout.NORTH);

        // TABLE
        model = new DefaultTableModel();

        model.setColumnIdentifiers(
            new String[] {
                "ID",
                "Name",
                "Branch",
                "Company",
                "Status",
                "Package"
            }
        );

        table = new JTable(model);

        JScrollPane pane =
        new JScrollPane(table);

        add(pane, BorderLayout.CENTER);

        // ================= VIEW PLACEMENTS =================

        viewBtn.addActionListener(e -> {

            resetColumns();

            try {

                model.setRowCount(0);

                Connection con =
                DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/college_placement_management",
                    "root",
                    "sathveera@07"
                );

                Statement st =
                con.createStatement();

                ResultSet rs =
                st.executeQuery(
                    "SELECT * FROM placements"
                );

                while(rs.next()) {

                    model.addRow(new Object[] {

                        rs.getInt("student_id"),

                        rs.getString("student_name"),

                        rs.getString("branch"),

                        rs.getString("company_applied"),

                        rs.getString("status"),

                        rs.getDouble("package_lpa")
                    });
                }

                con.close();

            } catch(Exception ex) {

                ex.printStackTrace();
            }
        });

        // ================= SELECTED =================

        selectedBtn.addActionListener(e -> {

            resetColumns();

            loadData(
                "SELECT * FROM placements WHERE status='Selected'"
            );
        });

        // ================= REJECTED =================

        rejectedBtn.addActionListener(e -> {

            resetColumns();

            loadData(
                "SELECT * FROM placements WHERE status='Rejected'"
            );
        });

        // ================= SHORTLISTED =================

        shortlistedBtn.addActionListener(e -> {

            resetColumns();

            loadData(
                "SELECT * FROM placements WHERE status='Shortlisted'"
            );
        });

        // ================= APPLIED =================

        appliedBtn.addActionListener(e -> {

            resetColumns();

            loadData(
                "SELECT * FROM placements WHERE status='Applied'"
            );
        });

        // ================= SEARCH STUDENT =================

        searchBtn.addActionListener(e -> {

            resetColumns();

            String name =
            searchField.getText();

            try {

                model.setRowCount(0);

                Connection con =
                DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/college_placement_management",
                    "root",
                    "sathveera@07"
                );

                Statement st =
                con.createStatement();

                ResultSet rs =
                st.executeQuery(
                    "SELECT * FROM placements WHERE student_name LIKE '%"
                    + name +
                    "%'"
                );

                boolean found = false;

                while(rs.next()) {

                    found = true;

                    model.addRow(new Object[] {

                        rs.getInt("student_id"),

                        rs.getString("student_name"),

                        rs.getString("branch"),

                        rs.getString("company_applied"),

                        rs.getString("status"),

                        rs.getDouble("package_lpa")
                    });
                }

                if(!found) {

                    JOptionPane.showMessageDialog(
                        null,
                        "Student Not Found"
                    );
                }

                con.close();

            } catch(Exception ex) {

                ex.printStackTrace();
            }
        });

        // ================= SEARCH COMPANY =================

        companyBtn.addActionListener(e -> {

            resetColumns();

            String company =
            companyField.getText();

            try {

                model.setRowCount(0);

                Connection con =
                DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/college_placement_management",
                    "root",
                    "sathveera@07"
                );

                Statement st =
                con.createStatement();

                ResultSet rs =
                st.executeQuery(
                    "SELECT * FROM placements WHERE company_applied LIKE '%"
                    + company +
                    "%'"
                );

                boolean found = false;

                while(rs.next()) {

                    found = true;

                    model.addRow(new Object[] {

                        rs.getInt("student_id"),

                        rs.getString("student_name"),

                        rs.getString("branch"),

                        rs.getString("company_applied"),

                        rs.getString("status"),

                        rs.getDouble("package_lpa")
                    });
                }

                if(!found) {

                    JOptionPane.showMessageDialog(
                        null,
                        "Company Not Found"
                    );
                }

                con.close();

            } catch(Exception ex) {

                ex.printStackTrace();
            }
        });

        // ================= PACKAGE RANGE =================

        rangeBtn.addActionListener(e -> {

            resetColumns();

            String min =
            minField.getText();

            String max =
            maxField.getText();

            try {

                model.setRowCount(0);

                Connection con =
                DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/college_placement_management",
                    "root",
                    "sathveera@07"
                );

                Statement st =
                con.createStatement();

                ResultSet rs =
                st.executeQuery(
                    "SELECT * FROM placements WHERE package_lpa BETWEEN "
                    + min +
                    " AND " +
                    max +
                    " ORDER BY package_lpa ASC"
                );

                boolean found = false;

                while(rs.next()) {

                    found = true;

                    model.addRow(new Object[] {

                        rs.getInt("student_id"),

                        rs.getString("student_name"),

                        rs.getString("branch"),

                        rs.getString("company_applied"),

                        rs.getString("status"),

                        rs.getDouble("package_lpa")
                    });
                }

                if(!found) {

                    JOptionPane.showMessageDialog(
                        null,
                        "No Students Found In This Range"
                    );
                }

                con.close();

            } catch(Exception ex) {

                ex.printStackTrace();
            }
        });

        // ================= HIGHEST PACKAGE =================

        highestBtn.addActionListener(e -> {

            resetColumns();

            try {

                model.setRowCount(0);

                Connection con =
                DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/college_placement_management",
                    "root",
                    "sathveera@07"
                );

                Statement st =
                con.createStatement();

                ResultSet rs =
                st.executeQuery(
                    "SELECT * FROM placements WHERE package_lpa=(SELECT MAX(package_lpa) FROM placements)"
                );

                while(rs.next()) {

                    model.addRow(new Object[] {

                        rs.getInt("student_id"),

                        rs.getString("student_name"),

                        rs.getString("branch"),

                        rs.getString("company_applied"),

                        rs.getString("status"),

                        rs.getDouble("package_lpa")
                    });
                }

                con.close();

            } catch(Exception ex) {

                ex.printStackTrace();
            }
        });

        // ================= AVERAGE PACKAGE =================

        avgBtn.addActionListener(e -> {

            try {

                Connection con =
                DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/college_placement_management",
                    "root",
                    "sathveera@07"
                );

                Statement st =
                con.createStatement();

                ResultSet rs =
                st.executeQuery(
                    "SELECT AVG(package_lpa) AS avgpkg FROM placements WHERE status='Selected'"
                );

                if(rs.next()) {

                    double avg =
                    rs.getDouble("avgpkg");

                    JOptionPane.showMessageDialog(
                        null,
                        "Average Package = "
                        + avg +
                        " LPA"
                    );
                }

                con.close();

            } catch(Exception ex) {

                ex.printStackTrace();
            }
        });

        // ================= VIEW COMPANIES =================

        companyViewBtn.addActionListener(e -> {

            try {

                model.setRowCount(0);

                model.setColumnCount(0);

                model.addColumn("Company ID");
                model.addColumn("Company Name");
                model.addColumn("Role");
                model.addColumn("Package");
                model.addColumn("Min CGPA");

                Connection con =
                DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/college_placement_management",
                    "root",
                    "sathveera@07"
                );

                Statement st =
                con.createStatement();

                ResultSet rs =
                st.executeQuery(
                    "SELECT * FROM companies"
                );

                while(rs.next()) {

                    model.addRow(new Object[] {

                        rs.getInt("company_id"),

                        rs.getString("company_name"),

                        rs.getString("role_name"),

                        rs.getDouble("package_lpa"),

                        rs.getDouble("min_cgpa")
                    });
                }

                con.close();

            } catch(Exception ex) {

                ex.printStackTrace();
            }
        });

        setVisible(true);
    }

    // ================= RESET COLUMNS =================

    public void resetColumns() {

        model.setRowCount(0);

        model.setColumnCount(0);

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Branch");
        model.addColumn("Company");
        model.addColumn("Status");
        model.addColumn("Package");
    }

    // ================= LOAD DATA =================

    public void loadData(String query) {

        try {

            model.setRowCount(0);

            Connection con =
            DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/college_placement_management",
                "root",
                "sathveera@07"
            );

            Statement st =
            con.createStatement();

            ResultSet rs =
            st.executeQuery(query);

            while(rs.next()) {

                model.addRow(new Object[] {

                    rs.getInt("student_id"),

                    rs.getString("student_name"),

                    rs.getString("branch"),

                    rs.getString("company_applied"),

                    rs.getString("status"),

                    rs.getDouble("package_lpa")
                });
            }

            con.close();

        } catch(Exception ex) {

            ex.printStackTrace();
        }
    }

    // ================= MAIN METHOD =================

    public static void main(String[] args) {

        new LoginPage();
    }
}