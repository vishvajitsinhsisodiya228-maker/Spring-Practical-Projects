CREATE DATABASE IF NOT EXISTS gym_db;
USE gym_db;
CREATE TABLE IF NOT EXISTS members (
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60),
    phone VARCHAR(10),
    plan VARCHAR(10),
    months INT,
    fee DECIMAL(10,2)
);
