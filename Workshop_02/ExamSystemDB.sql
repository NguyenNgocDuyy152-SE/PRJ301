CREATE DATABASE ExamSystemDB;
GO
USE ExamSystemDB;
GO

-- Tạo bảng Users
CREATE TABLE tblUsers (
    Username VARCHAR(50) PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Role VARCHAR(20) NOT NULL CHECK (Role IN ('Instructor', 'Student'))
);
GO

-- Tạo bảng Exam Categories
CREATE TABLE tblExamCategories (
    Category_ID INT IDENTITY(1,1) PRIMARY KEY,
    Category_Name VARCHAR(50) NOT NULL,
    Description TEXT NULL
);
GO

-- Tạo bảng Exams
CREATE TABLE tblExams (
    Exam_ID INT IDENTITY(1,1) PRIMARY KEY,
    Exam_Title VARCHAR(100) NOT NULL,
    Subject VARCHAR(50) NOT NULL,
    Category_ID INT NOT NULL,
    Total_Marks INT NOT NULL,
    Duration INT NOT NULL,
    FOREIGN KEY (Category_ID) REFERENCES tblExamCategories(Category_ID) ON DELETE CASCADE
);
GO

-- Tạo bảng Questions
CREATE TABLE tblQuestions (
    Question_ID INT IDENTITY(1,1) PRIMARY KEY,
    Exam_ID INT NOT NULL,
    Question_Text TEXT NOT NULL,
    Option_A VARCHAR(100) NOT NULL,
    Option_B VARCHAR(100) NOT NULL,
    Option_C VARCHAR(100) NOT NULL,
    Option_D VARCHAR(100) NOT NULL,
    Correct_Option CHAR(1) NOT NULL CHECK (Correct_Option IN ('A', 'B', 'C', 'D')),
    FOREIGN KEY (Exam_ID) REFERENCES tblExams(Exam_ID) ON DELETE CASCADE
);
GO
