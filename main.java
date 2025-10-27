/*
    JDBC_Applications.java — Fixed & Clean Version
    ----------------------------------------------
    ✅ No Scanner or Connection resource leaks
    ✅ Consistent Driver loading
    ✅ AutoCommit managed correctly
    ✅ Proper closing of resources
*/

import java.sql.*;
import java.util.*;

// ================================================================
// Common Database Configuration
// ================================================================
class DBConfig {
    public static final String URL = "jdbc:mysql://localhost:3306/testdb";
    public static final String USER = "root";
    public static final String PASS = "1234";

    // Shared Scanner for entire application
    public static final Scanner sc = new Scanner(System.in);
}

// ================================================================
// Part (a): Connecting to MySQL and Fetching Data from Employee Table
// ================================================================
class FetchEmployeeData {
    public static void fetchData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASS);
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery("SELECT * FROM Employee")) {

                System.out.println("\n--- Employee Data ---");
                System.out.println("EmpID\tName\tSalary");
                System.out.println("---------------------------");

                while (rs.next()) {
                    int id = rs.getInt("EmpID");
                    String name = rs.getString("Name");
                    double salary = rs.getDouble("Salary");
                    System.out.println(id + "\t" + name + "\t" + salary);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// ================================================================
// Part (b): CRUD Operations on Product Table Using JDBC
// ================================================================
class ProductCRUD {
    public static void manageProducts() {
        Scanner sc = DBConfig.sc; // use shared Scanner

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASS)) {
                con.setAutoCommit(false);

                while (true) {
                    System.out.println("\n--- Product Management ---");
                    System.out.println("1. Add Product");
                    System.out.println("2. View Products");
                    System.out.println("3. Update Product");
                    System.out.println("4. Delete Product");
                    System.out.println("5. Back to Main Menu");
                    System.out.print("Enter choice: ");
                    int ch = sc.nextInt();

                    switch (ch) {
                        case 1 -> addProduct(con, sc);
                        case 2 -> viewProducts(con);
                        case 3 -> updateProduct(con, sc);
                        case 4 -> deleteProduct(con, sc);
                        case 5 -> { return; }
                        default -> System.out.println("Invalid choice!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        String sql = "INSERT INTO Product VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, price);
            ps.setInt(4, qty);
            ps.executeUpdate();
            con.commit();
            System.out.println("✅ Product added successfully!");
        }
    }

    static void viewProducts(Connection con) throws SQLException {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Product")) {
            System.out.println("\nProductID\tProductName\tPrice\tQuantity");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" +
                                   rs.getDouble(3) + "\t" + rs.getInt(4));
            }
        }
    }

    static void updateProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Product ID to Update: ");
        int id = sc.nextInt();
        System.out.print("Enter new Price: ");
        double price = sc.nextDouble();

        String sql = "UPDATE Product SET Price=? WHERE ProductID=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, price);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                con.commit();
                System.out.println("✅ Product updated successfully!");
            } else {
                con.rollback();
                System.out.println("❌ Product not found!");
            }
        }
    }

    static void deleteProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Product ID to Delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM Product WHERE ProductID=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                con.commit();
                System.out.println("✅ Product deleted successfully!");
            } else {
                con.rollback();
                System.out.println("❌ Product not found!");
            }
        }
    }
}

// ================================================================
// Part (c): Student Management Application using MVC Architecture
// ================================================================

// ----- Model -----
class Student {
    private int studentID;
    private String name;
    private String department;
    private int marks;

    public Student(int studentID, String name, String department, int marks) {
        this.studentID = studentID;
        this.name = name;
        this.department = department;
        this.marks = marks;
    }

    public int getStudentID() { return studentID; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public int getMarks() { return marks; }
}

// ----- Controller -----
class StudentDAO {
    public void addStudent(Student s) {
        String sql = "INSERT INTO Student VALUES (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, s.getStudentID());
            ps.setString(2, s.getName());
            ps.setString(3, s.getDepartment());
            ps.setInt(4, s.getMarks());
            ps.executeUpdate();
            System.out.println("✅ Student Added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewStudents() {
        String sql = "SELECT * FROM Student";
        try (Connection con = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASS);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\nID\tName\tDepartment\tMarks");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t" +
                                   rs.getString(2) + "\t" +
                                   rs.getString(3) + "\t" +
                                   rs.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMarks(int id, int marks) {
        String sql = "UPDATE Student SET Marks=? WHERE StudentID=?";
        try (Connection con = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, marks);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Updated!" : "❌ Student Not Found!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        String sql = "DELETE FROM Student WHERE StudentID=?";
        try (Connection con = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Deleted!" : "❌ Student Not Found!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// ----- View -----
class StudentApp {
    public static void manageStudents() {
        Scanner sc = DBConfig.sc; // shared Scanner
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Marks");
            System.out.println("4. Delete Student");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    int marks = sc.nextInt();
                    dao.addStudent(new Student(id, name, dept, marks));
                }
                case 2 -> dao.viewStudents();
                case 3 -> {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter new Marks: ");
                    int marks = sc.nextInt();
                    dao.updateMarks(id, marks);
                }
                case 4 -> {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    dao.deleteStudent(id);
                }
                case 5 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}

// ================================================================
// MAIN MENU
// ================================================================
public class JDBC_Applications {
    public static void main(String[] args) {
        Scanner sc = DBConfig.sc;

        while (true) {
            System.out.println("\n========== JDBC APPLICATIONS ==========");
            System.out.println("1. Fetch Employee Data (Part a)");
            System.out.println("2. Manage Products (Part b)");
            System.out.println("3. Manage Students (Part c)");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> FetchEmployeeData.fetchData();
                case 2 -> ProductCRUD.manageProducts();
                case 3 -> StudentApp.manageStudents();
                case 4 -> {
                    System.out.println("Exiting Program...");
                    sc.close(); // ✅ Close Scanner once before exit
                    System.exit(0);
                }
                default -> System.out.println("Invalid Choice!");
            }
        }
    }
}
