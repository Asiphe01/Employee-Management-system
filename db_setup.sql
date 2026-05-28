-- Database: employeedb
-- User: postgres (default, or create a new user if preferred)
-- Password: password (or your chosen password)

-- Connect to PostgreSQL and create the database
-- You might need to run this from a PostgreSQL client like psql or pgAdmin
-- CREATE DATABASE employeedb;

-- Connect to the employeedb database before running the following commands

-- Create the employee table
CREATE TABLE employee (
    employee_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100),
    role VARCHAR(100),
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100) UNIQUE NOT NULL
);

-- You can optionally add some test data
-- INSERT INTO employee (employee_id, name, department, role, password, phone, email) VALUES
-- (
--     'EMP001', 'John Doe', 'IT', 'Software Engineer', 'password123', '123-456-7890', 'john.doe@example.com'
-- );
