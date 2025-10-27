-- ================================================================
-- Database Setup for JDBC Applications
-- ================================================================

-- Create Database
CREATE DATABASE IF NOT EXISTS testdb;
USE testdb;

-- ================================================================
-- Part (a): Employee Table
-- ================================================================
DROP TABLE IF EXISTS Employee;
CREATE TABLE Employee (
    EmpID INT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Salary DECIMAL(10, 2) NOT NULL
);

-- Insert sample data
INSERT INTO Employee (EmpID, Name, Salary) VALUES
(1, 'John Doe', 50000.00),
(2, 'Jane Smith', 55000.00),
(3, 'Bob Johnson', 48000.00),
(4, 'Alice Williams', 60000.00);

-- ================================================================
-- Part (b): Product Table
-- ================================================================
DROP TABLE IF EXISTS Product;
CREATE TABLE Product (
    ProductID INT PRIMARY KEY,
    ProductName VARCHAR(100) NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    Quantity INT NOT NULL
);

-- Insert sample data
INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES
(101, 'Laptop', 999.99, 50),
(102, 'Mouse', 25.50, 200),
(103, 'Keyboard', 75.00, 150),
(104, 'Monitor', 299.99, 80);

-- ================================================================
-- Part (c): Student Table (MVC Application)
-- ================================================================
DROP TABLE IF EXISTS Student;
CREATE TABLE Student (
    StudentID INT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Department VARCHAR(50) NOT NULL,
    Marks INT NOT NULL CHECK (Marks >= 0 AND Marks <= 100)
);

-- Insert sample data
INSERT INTO Student (StudentID, Name, Department, Marks) VALUES
(1001, 'Michael Brown', 'Computer Science', 85),
(1002, 'Emily Davis', 'Engineering', 92),
(1003, 'David Wilson', 'Mathematics', 78),
(1004, 'Sarah Miller', 'Physics', 88);

-- Display tables
SELECT 'Employee Table:' AS Info;
SELECT * FROM Employee;

SELECT 'Product Table:' AS Info;
SELECT * FROM Product;

SELECT 'Student Table:' AS Info;
SELECT * FROM Student;

