create database DoAnDatabase;
use DoAnDatabase;

CREATE TABLE [User] (
    userId INT PRIMARY KEY IDENTITY(1,1),
    username NVARCHAR(50) NOT NULL,
    password NVARCHAR(50) NOT NULL,
    firstName NVARCHAR(50),
    lastName NVARCHAR(50),
    phone NVARCHAR(20),
    email NVARCHAR(100),
    address NVARCHAR(200),
    createdAt DATETIME DEFAULT GETDATE(),
	verified BIT DEFAULT 0,
);

