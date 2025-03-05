/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Huy
 * Created: Feb 28, 2025
 */

CREATE DATABASE StartupProjectDB;
GO

USE StartupProjectDB;
GO

-- Bảng người dùng
CREATE TABLE tblUsers (
    Username VARCHAR(50) PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Role VARCHAR(20) NOT NULL CHECK (Role IN ('Founder', 'Team Member'))
);
-- Bảng dự án khởi nghiệp
CREATE TABLE tblStartupProjects (
    project_id INT PRIMARY KEY IDENTITY(1,1),
    project_name VARCHAR(100) NOT NULL,
    Description TEXT,
    Status VARCHAR(20) NOT NULL CHECK (Status IN ('Ideation', 'Development', 'Launch', 'Scaling')),
    estimated_launch DATE NOT NULL
);

-- Thêm tài khoản đăng nhập mẫu
INSERT INTO tblUsers (Username, Name, Password, Role) 
VALUES 
('admin', 'Gia Huy', 'admin123', 'Founder'),
('john_doe', 'John Doe', 'password123', 'Team Member'),
('jane_smith', 'Jane Smith', 'securepass', 'Founder');
