-- Create the database
CREATE DATABASE FoodOrderDB;
USE FoodOrderDB;

-- Create tblUsers
CREATE TABLE tblUsers (
    userID VARCHAR(50) PRIMARY KEY NOT NULL,
    fullName NVARCHAR(500) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phoneNumber VARCHAR(15) NOT NULL,
    roleID NVARCHAR(5) NOT NULL,
    password VARCHAR(50) NOT NULL
);

INSERT INTO tblUsers (userID, fullName, email, phoneNumber, roleID, password) VALUES
('user1', 'Alice Brown', 'alice@example.com', '1234567890', 'USR', 'password1'),
('admin', 'Admin User', 'admin@example.com', '9876543210', 'ADM', 'adminpassword');

-- Create tblFoodItems
CREATE TABLE tblFoodItems (
    foodID VARCHAR(50) PRIMARY KEY NOT NULL,
    foodName NVARCHAR(200) NOT NULL,
    price DECIMAL(18,2) NOT NULL CHECK (price >= 0),
    quantity INT NOT NULL CHECK (quantity >= 0),
    category NVARCHAR(100) NOT NULL
);

INSERT INTO tblFoodItems (foodID, foodName, price, quantity, category) VALUES
('F001', N'Margherita Pizza', 10.99, 50, N'Pizza'),
('F002', N'Caesar Salad', 7.99, 30, N'Salad'),
('F003', N'Cheeseburger', 8.99, 40, N'Burger');

-- Create tblOrders
CREATE TABLE tblOrders (
    orderID VARCHAR(50) PRIMARY KEY NOT NULL,
    userID VARCHAR(50) NOT NULL,
    orderDate DATE NOT NULL DEFAULT GETDATE(),
    totalAmount DECIMAL(18,2) NOT NULL CHECK (totalAmount >= 0),
    FOREIGN KEY (userID) REFERENCES tblUsers(userID) ON DELETE CASCADE
);

-- Create tblOrderDetails
CREATE TABLE tblOrderDetails (
    orderDetailID INT IDENTITY(1,1) PRIMARY KEY,
    orderID VARCHAR(50) NOT NULL,
    foodID VARCHAR(50) NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    price DECIMAL(18,2) NOT NULL CHECK (price >= 0),
    FOREIGN KEY (orderID) REFERENCES tblOrders(orderID) ON DELETE CASCADE,
    FOREIGN KEY (foodID) REFERENCES tblFoodItems(foodID)
);
