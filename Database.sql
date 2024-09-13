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
	userStatus NVARCHAR(50),
	role NVARCHAR(50),
);
drop table [USER]

INSERT INTO [User] (username, password, firstName, lastName, phone, email, address, userStatus, role)
VALUES ('hofang', 'thanhhoang123', 'John', 'Doe', '1234567890', 'john.doe@example.com', '123 Main St, Anytown, USA', 'Active', 'Admin');


