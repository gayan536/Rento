
CREATE TABLE IF NOT EXISTS customers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS categories (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS vehicles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    category_id BIGINT,
    price_per_day DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS drivers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    license_number VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS bookings (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT NOT NULL,
    vehicle_id BIGINT NOT NULL,
    driver_id BIGINT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS payments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    booking_id BIGINT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    payment_date DATE NOT NULL,
    status VARCHAR(50) NOT NULL
);

INSERT INTO customer (first_name, last_name, email, phone, driver_license_number, address) 
VALUES
('John', 'Doe', 'john.doe@example.com', '0771234567', 'B1234567', '123 Main Street, Cityville'),
('Jane', 'Smith', 'jane.smith@example.com', '0719876543', 'B9876543', '456 Oak Avenue, Townsburg'),
('Michael', 'Johnson', 'michael.j@example.com', '0724567890', 'B4567890', '789 Pine Road, Villagetown');

INSERT INTO categories (name) VALUES ('Sedan'), ('SUV'), ('Van');
