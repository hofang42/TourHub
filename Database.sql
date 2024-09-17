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

-- Create Customer table
CREATE TABLE Customer (
    cus_Id INT PRIMARY KEY IDENTITY(1,1),
    cus_Birth DATE,
    user_Id INT,
    FOREIGN KEY (user_Id) REFERENCES [User](user_Id)
);

-- Create Provider table
CREATE TABLE Provider (
    company_Id INT PRIMARY KEY IDENTITY(1,1),
    tax_Code VARCHAR(50),
    balance DECIMAL(10, 2),
    bank_Information VARCHAR(255),
    user_Id INT,
    FOREIGN KEY (user_Id) REFERENCES [User](user_Id)
);

-- Create Tour table
CREATE TABLE Tour (
    tour_Id INT PRIMARY KEY IDENTITY(1,1),
    tour_Name VARCHAR(255),
    description TEXT,
    total_Time DECIMAL(5, 2),
    price money,
    slot INT,
    tour_Status VARCHAR(50),
    companyId INT,
    createdAt DATETIME,
    tourImg VARCHAR(255),
    FOREIGN KEY (companyId) REFERENCES Provider(company_Id)
);

-- Create Booking table
CREATE TABLE Booking (
    book_Id INT PRIMARY KEY IDENTITY(1,1),
    book_Date DATETIME,
    slot_Order INT,
    total_Cost DECIMAL(10, 2),
    book_Status VARCHAR(50),
    cus_Id INT,
    tour_Id INT,
    FOREIGN KEY (cus_Id) REFERENCES Customer(cus_Id),
    FOREIGN KEY (tour_Id) REFERENCES Tour(tour_Id)
);

-- Create Bill table
CREATE TABLE Bill (
    bill_Id INT PRIMARY KEY,
    amount DECIMAL(10, 2),
    date DATETIME,
    method VARCHAR(50),
    book_Id INT,
    FOREIGN KEY (book_Id) REFERENCES Booking(book_Id)
);

-- Create Discount table
CREATE TABLE Discount (
    discount_Id INT PRIMARY KEY,
    start_Day DATE,
    end_Day DATE,
    code VARCHAR(50),
    quantity INT,
    percentDes DECIMAL(5, 2),
    require VARCHAR(255),
    tour_Id INT,
    FOREIGN KEY (tour_Id) REFERENCES Tour(tour_Id)
);

-- Create Wishlist table
CREATE TABLE Wishlist (
    wish_Id INT PRIMARY KEY,
    cus_Id INT,
    tour_Id INT,
    FOREIGN KEY (cus_Id) REFERENCES Customer(cus_Id),
    FOREIGN KEY (tour_Id) REFERENCES Tour(tour_Id)
);

-- Create Review table
CREATE TABLE Review (
    review_Id INT PRIMARY KEY,
    title VARCHAR(255),
    comment TEXT,
    rating_Star INT,
    user_Id INT,
    tour_Id INT,
    FOREIGN KEY (user_Id) REFERENCES [User](user_Id),
    FOREIGN KEY (tour_Id) REFERENCES Tour(tour_Id)
);

-- Create ReportError table
CREATE TABLE ReportError (
    report_Id INT PRIMARY KEY,
    report_Date DATETIME,
    report_Details TEXT,
    user_Id INT,
    report_Type VARCHAR(50),
    FOREIGN KEY (user_Id) REFERENCES [User](user_Id)
);



