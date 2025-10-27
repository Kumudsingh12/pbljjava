# Java JDBC Applications

This project demonstrates three Java applications using JDBC for database connectivity, CRUD operations, and MVC architecture.

## 📋 Table of Contents

- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Database Setup](#database-setup)
- [Running the Application](#running-the-application)
- [Project Structure](#project-structure)
- [Features](#features)

## 🎯 Overview

### Part A: Employee Data Fetching
- Connects to MySQL database
- Retrieves and displays data from Employee table
- Demonstrates basic JDBC operations (Connection, Statement, ResultSet)

### Part B: Product CRUD Operations
- Menu-driven application for managing products
- Implements Create, Read, Update, Delete operations
- Uses PreparedStatement for safe parameterized queries
- Includes transaction handling with `setAutoCommit()`, `commit()`, and `rollback()`

### Part C: Student Management (MVC Architecture)
- Implements MVC design pattern
- Model: Student class representing data structure
- View: Menu-driven user interface
- Controller: StudentDAO class handling database operations
- Demonstrates separation of concerns

## 🔧 Prerequisites

1. **Java Development Kit (JDK)** - Version 8 or higher
2. **MySQL Server** - Version 5.7 or higher
3. **MySQL JDBC Driver** (mysql-connector-java.jar)
   - Download from: https://dev.mysql.com/downloads/connector/j/
   - Or use Maven dependency (if using Maven):
     ```xml
     <dependency>
         <groupId>com.mysql</groupId>
         <artifactId>mysql-connector-j</artifactId>
         <version>8.0.33</version>
     </dependency>
     ```

## 🗄️ Database Setup

### Option 1: Using the SQL Script (Recommended)

1. Open MySQL Command Line or MySQL Workbench
2. Run the `setup_database.sql` script:
   ```bash
   mysql -u root -p < setup_database.sql
   ```
   Or copy and paste the contents into your MySQL client

### Option 2: Manual Setup

1. Create the database:
   ```sql
   CREATE DATABASE testdb;
   USE testdb;
   ```

2. Create the tables (see `setup_database.sql` for complete schema)

### Database Configuration

Update the database credentials in `main.java` if different from defaults:

```java
class DBConfig {
    public static final String URL = "jdbc:mysql://localhost:3306/testdb";
    public static final String USER = "root";      // Your MySQL username
    public static final String PASS = "1234";       // Your MySQL password
}
```

## 🚀 Running the Application

### Compilation

```bash
javac -cp ".:mysql-connector-java-8.0.33.jar" main.java
```

Or if you have the JAR in a lib folder:
```bash
javac -cp ".:lib/*" main.java
```

### Execution

```bash
java -cp ".:mysql-connector-java-8.0.33.jar" JDBC_Applications
```

Or if you have the JAR in a lib folder:
```bash
java -cp ".:lib/*" JDBC_Applications
```

### Using an IDE (IntelliJ IDEA / Eclipse)

1. Add the MySQL JDBC JAR to your project's classpath
2. **IntelliJ IDEA**:
   - File → Project Structure → Libraries → Add JAR
3. **Eclipse**:
   - Right-click project → Build Path → Add External Archives
4. Run the `JDBC_Applications` class

## 📁 Project Structure

```
Full Stack/
├── main.java                 # Complete JDBC application
├── setup_database.sql        # Database setup script
└── README.md                 # This file
```

## ✨ Features

### Part A: Employee Data
- ✅ Basic JDBC connection
- ✅ Using Statement for queries
- ✅ Reading from ResultSet
- ✅ Displaying formatted output

### Part B: Product Management
- ✅ Full CRUD operations
- ✅ PreparedStatement for parameterized queries
- ✅ Transaction management (AutoCommit, Commit, Rollback)
- ✅ Menu-driven interface
- ✅ Error handling

### Part C: Student Management (MVC)
- ✅ Model-View-Controller architecture
- ✅ Student model class
- ✅ View layer (menu interface)
- ✅ Controller layer (DAO pattern)
- ✅ Separation of concerns

## 🗃️ Database Schema

### Employee Table
- `EmpID` (INT, PRIMARY KEY)
- `Name` (VARCHAR(100))
- `Salary` (DECIMAL(10,2))

### Product Table
- `ProductID` (INT, PRIMARY KEY)
- `ProductName` (VARCHAR(100))
- `Price` (DECIMAL(10,2))
- `Quantity` (INT)

### Student Table
- `StudentID` (INT, PRIMARY KEY)
- `Name` (VARCHAR(100))
- `Department` (VARCHAR(50))
- `Marks` (INT, CHECK constraint: 0-100)

## 🎮 Usage

The application provides a main menu:

```
========== JDBC APPLICATIONS ==========
1. Fetch Employee Data (Part a)
2. Manage Products (Part b)
3. Manage Students (Part c)
4. Exit
```

Each section can be accessed through the menu system.

## 🐛 Troubleshooting

### Common Issues

1. **ClassNotFoundException: com.mysql.cj.jdbc.Driver**
   - Solution: Make sure MySQL JDBC driver JAR is in the classpath

2. **Access denied for user 'root'@'localhost'**
   - Solution: Check your MySQL credentials in DBConfig class

3. **Communications link failure**
   - Solution: Verify MySQL server is running on port 3306

4. **Unknown database 'testdb'**
   - Solution: Run the `setup_database.sql` script to create the database

## 📝 Notes

- The Scanner is shared across all classes to avoid resource leaks
- All resources (Connection, Statement, ResultSet) are properly closed using try-with-resources
- Transaction handling is implemented in Part B for data integrity
- MVC architecture in Part C demonstrates good software design principles

## 👨‍💻 Author

Full Stack Development Project - Semester 5

