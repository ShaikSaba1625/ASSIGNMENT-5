CREATE DATABASE IF NOT EXISTS transport_db;
USE transport_db;

CREATE TABLE IF NOT EXISTS bus_pass (
    pass_id VARCHAR(40) PRIMARY KEY,
    register_number VARCHAR(40) NOT NULL,
    student_name VARCHAR(100) NOT NULL,
    route_number INT NOT NULL,
    boarding_stop VARCHAR(100) NOT NULL,
    validity_date DATE NOT NULL,
    amount_paid DECIMAL(10, 2) NOT NULL
);
