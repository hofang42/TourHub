create database DoAnDatabase;
use DoAnDatabase;

CREATE TABLE [User] (
    user_Id INT PRIMARY KEY IDENTITY(1,1),
    password NVARCHAR(50) NOT NULL,
    first_Name NVARCHAR(50),
    last_Name NVARCHAR(50),
    phone VARCHAR(20),
    email NVARCHAR(100),
    address NVARCHAR(200),
    created_At DATE DEFAULT GETDATE(),
	user_Status NVARCHAR(50),
	role VARCHAR(50),
	avatar VARCHAR(50)
);

-- Create ReportError table
CREATE TABLE ReportError (
    report_Id INT PRIMARY KEY IDENTITY(1,1),
    report_Date DATE,
    report_Details NVARCHAR(MAX),
	report_Type VARCHAR(50),
    user_Id INT,
    FOREIGN KEY (user_Id) REFERENCES [User](user_Id)
);

-- Create Customer table
CREATE TABLE Customer (
    cus_Id INT PRIMARY KEY IDENTITY(1,1),
    cus_Birth DATE,
    user_Id INT,
    FOREIGN KEY (user_Id) REFERENCES [User](user_Id)
);

-- Create Company table
CREATE TABLE Company (
    company_Id INT PRIMARY KEY IDENTITY(1,1),
    tax_Code VARCHAR(13),
    balance DECIMAL(12, 0),
    bank_Information VARCHAR(50),
    user_Id INT,
    FOREIGN KEY (user_Id) REFERENCES [User](user_Id)
);
-- Create Tour table
CREATE TABLE Tour (
    tour_Id CHAR(8) PRIMARY KEY,
    tour_Name NVARCHAR(255),
    tour_Description NVARCHAR(MAX),
    start_Date DATE,
	end_Date DATE,
	location VARCHAR(50), --Example: Nhuan Duc, Cu Chi
	purchases_Time INT,
	average_Review_Rating DECIMAL(2,2),
	number_Of_Review INT,
	total_Time VARCHAR(10),
    price DECIMAL(10, 0),
    slot INT,
    tour_Status VARCHAR(50),
    created_At DATE DEFAULT GETDATE(),
    tour_Img VARCHAR(50),
	company_Id INT,
    FOREIGN KEY (company_Id) REFERENCES Company(company_Id)
);

-- Create Booking table
CREATE TABLE Booking (
    book_Id INT PRIMARY KEY IDENTITY(1,1),
    book_Date DATE,
    slot_Order INT,
    total_Cost DECIMAL(12, 0),
    book_Status VARCHAR(50),
    cus_Id INT,
    tour_Id CHAR(8),
    FOREIGN KEY (cus_Id) REFERENCES Customer(cus_Id),
    FOREIGN KEY (tour_Id) REFERENCES Tour(tour_Id)
);

-- Create Bill table
CREATE TABLE Bill (
    bill_Id INT PRIMARY KEY IDENTITY(1,1),
    amount DECIMAL(10, 0),
    bill_Date DATE,
    pay_Method VARCHAR(50),
    book_Id INT,
    FOREIGN KEY (book_Id) REFERENCES Booking(book_Id)
);

-- Create Discount table
CREATE TABLE Discount (
    discount_Id INT PRIMARY KEY IDENTITY(1,1),
    start_Day DATE,
    end_Day DATE,
    code VARCHAR(8),
    quantity INT,
    percent_Discount DECIMAL(5, 2),
    require VARCHAR(255),
    tour_Id CHAR(8),
    FOREIGN KEY (tour_Id) REFERENCES Tour(tour_Id)
);

-- Create Wishlist table
CREATE TABLE Wishlist (
    wish_Id INT PRIMARY KEY IDENTITY(1,1),
    cus_Id INT,
    tour_Id CHAR(8),
    FOREIGN KEY (cus_Id) REFERENCES Customer(cus_Id),
    FOREIGN KEY (tour_Id) REFERENCES Tour(tour_Id)
);

-- Create Review table
CREATE TABLE Review (
    review_Id INT PRIMARY KEY IDENTITY(1,1),
    comment NVARCHAR(MAX),
    rating_Star INT,
    user_Id INT,
    tour_Id CHAR(8),
    FOREIGN KEY (user_Id) REFERENCES [User](user_Id),
    FOREIGN KEY (tour_Id) REFERENCES Tour(tour_Id)
);
insert into [User] (password,email) values ('12345678As','idk123@gmail.com')
insert into Customer values ('11-05-2004',1)
insert into Company values ('1234567890123', 800000000, 'Techcombank - 9876543210', 2)

SELECT book_Id, book_Date,slot_Order, total_Cost, book_Status, Tour.tour_Name FROM Booking
inner join Tour on Booking.tour_Id = Tour.tour_Id
inner join [User] on Booking.cus_Id = [User].user_Id
where Booking.cus_Id = 1

select * from [User]	
select * from Customer
INSERT INTO [User] (password, first_Name, last_Name, phone, email, address, user_Status, role, avatar)
VALUES 
('password123', 'John', 'Doe', '1234567890', 'john.doe@example.com', '123 Main St', 'Active', 'Admin', 'avatar1.jpg'),
('password456', 'Jane', 'Smith', '0987654321', 'jane.smith@example.com', '456 Oak St', 'Active', 'User', 'avatar2.jpg'),
('password789', 'Mike', 'Johnson', '1112223333', 'mike.johnson@example.com', '789 Pine St', 'Inactive', 'User', 'avatar3.jpg'),
('password321', 'Emily', 'Davis', '3334445555', 'emily.davis@example.com', '321 Cedar St', 'Active', 'Moderator', 'avatar4.jpg'),
('password654', 'David', 'Brown', '5556667777', 'david.brown@example.com', '654 Willow St', 'Active', 'Admin', 'avatar5.jpg');

INSERT INTO [User] (password, first_Name, last_Name, phone, email, address, user_Status, role, avatar)
VALUES 
('123', 'John', 'Doe', '1234567890', 'admin', '123 Main St', 'Active', 'Admin', 'avatar1.jpg'),
('123', 'John', 'Doe', '1234567890', 'customer', '123 Main St', 'Active', 'Customer', 'avatar1.jpg'),
('123', 'John', 'Doe', '1234567890', 'provider', '123 Main St', 'Active', 'Provider', 'avatar1.jpg')

SELECT report_Id, report_Date, report_Details, report_Type FROM ReportError

INSERT INTO ReportError (report_Date, report_Details, report_Type, user_Id) VALUES 
('2024-10-01', 'Error while processing payment.', 'Payment Error', 1),
('2024-10-02', 'Unable to find tour details.', 'Tour Not Found', 1),
('2024-10-03', 'Application crashed unexpectedly.', 'System Crash', 1),
('2024-10-04', 'User unable to reset password.', 'Password Issue', 1),
('2024-10-05', 'Discount code not applying.', 'Discount Error', 1);

SELECT COUNT(*) FROM Booking
SELECT SUM(total_Cost) FROM Booking WHERE book_Status = 'Confirmed'
SELECT COUNT(*) FROM Booking
SELECT AVG(rating_Star) FROM Review
SELECT tour_Id, COUNT(*) FROM Booking GROUP BY tour_Id
SELECT location, COUNT(*) FROM Tour t INNER JOIN Booking b ON t.tour_Id = b.tour_Id GROUP BY location


select * from Company
select * from booking where cus_Id = 1

SELECT book_Id, book_Date,
                    slot_Order, total_Cost, book_Status, cus_Id, tour_Id FROM Booking where cus_Id = '1'

SELECT TOP 10 user_Id, first_Name, last_Name, email, created_At, role 
FROM [User] 
WHERE role = 'customer' OR role = 'company' 
ORDER BY created_At DESC;

ALTER TABLE Tour
ALTER COLUMN average_Review_Rating DECIMAL(3,1);

ALTER TABLE [User]
ADD username NVARCHAR(100);
SELECT COUNT(*) FROM [User] WHERE created_At BETWEEN '2023-12-01' AND '2025-12-01' and role='customer'


SELECT COUNT(*) FROM booking GROUP BY DAY(book_date)

INSERT INTO Tour (tour_Id, tour_Name, tour_Description, start_Date, end_Date, location, purchases_Time, average_Review_Rating, number_Of_Review, total_Time, price, slot, tour_Status, created_At, tour_Img, company_Id) VALUES
('T0000001', N'Tour Cu Chi', N'Tour khám phá địa đạo Củ Chi với hướng dẫn viên giàu kinh nghiệm.', '2024-10-01', '2024-10-03', 'Nhuan Duc, Cu Chi', 10, 4.5, 20, '3d2n', 7000000, 20, 'Available', '2024-09-15', 'img1.jpg', 3),
('T0000002', N'Tour Mekong Delta', N'Tour khám phá vùng sông nước miền Tây.', '2024-10-05', '2024-10-07', 'Ben Tre, Tien Giang', 15, 4.7, 35, '3d2n', 5000000, 30, 'Available', '2024-09-16', 'img2.jpg', 3),
('T0000003', N'Tour Da Lat', N'Tour đến thành phố Đà Lạt, tận hưởng không khí trong lành và cảnh quan núi rừng.', '2024-11-01', '2024-11-05', 'Da Lat', 20, 4.8, 40, '5d4n', 12000000, 25, 'Available', '2024-09-17', 'img3.jpg', 3),
('T0000004', N'Tour Ha Long Bay', N'Tour tham quan vịnh Hạ Long, một trong những kỳ quan thiên nhiên thế giới.', '2024-12-01', '2024-12-04', 'Ha Long', 10, 4.9, 50, '4d3n', 15000000, 15, 'Available', '2024-09-18', 'img4.jpg', 3),
('T0000005', N'Tour Ninh Binh', N'Tour tham quan Tràng An, chùa Bái Đính tại Ninh Bình.', '2024-11-10', '2024-11-12', 'Ninh Binh', 5, 4.6, 25, '3d2n', 6000000, 20, 'Available', '2024-09-19', 'img5.jpg', 3),
('T0000006', N'Tour Phong Nha', N'Tour khám phá hang động Phong Nha, một trong những hang động nổi tiếng thế giới.', '2024-11-20', '2024-11-23', 'Phong Nha', 8, 4.7, 30, '4d3n', 9000000, 18, 'Available', '2024-09-20', 'img6.jpg', 3),
('T0000007', N'Tour Sapa', N'Tour khám phá vùng cao Sapa với cảnh đẹp núi rừng và các bản làng dân tộc.', '2024-12-10', '2024-12-13', 'Sapa', 12, 4.9, 60, '4d3n', 13000000, 10, 'Available', '2024-09-21', 'img7.jpg', 3),
('T0000008', N'Tour Phu Quoc', N'Tour nghỉ dưỡng tại đảo Phú Quốc với bãi biển đẹp và nhiều hoạt động vui chơi.', '2024-10-15', '2024-10-20', 'Phu Quoc', 18, 4.8, 45, '6d5n', 17000000, 22, 'Available', '2024-09-22', 'img8.jpg', 3),
('T0000009', N'Tour Hue', N'Tour tham quan kinh thành Huế và các di tích lịch sử.', '2024-10-25', '2024-10-27', 'Hue', 14, 4.6, 35, '3d2n', 7000000, 28, 'Available', '2024-09-23', 'img9.jpg', 3),
('T0000010', N'Tour Ho Chi Minh City', N'Tour khám phá thành phố Hồ Chí Minh và các di tích lịch sử.', '2024-10-30', '2024-11-01', 'Ho Chi Minh City', 25, 4.5, 20, '2d1n', 3000000, 40, 'Available', '2024-09-24', 'img10.jpg', 3);


INSERT INTO Booking (book_Date, slot_Order, total_Cost, book_Status, cus_Id, tour_Id, option_Id ) VALUES 
('2024-09-01', 1, 5000000, 'Confirmed', 1, 'T0000001'),
('2024-09-03', 2, 3000000, 'Pending', 1, 'T0000002'),
('2024-09-05', 3, 8000000, 'Cancelled', 1, 'T0000003'),
('2024-09-07', 1, 12000000, 'Confirmed', 1, 'T0000004'),
('2024-09-10', 4, 4500000, 'Confirmed', 1, 'T0000005');

INSERT INTO Booking (book_Date, slot_Order, total_Cost, book_Status, cus_Id, tour_Id, option_Id) VALUES 
('2024-07-01', 1, 5000000, 'Confirmed', 1, 'T0000001')


CREATE TABLE Tours (
    tourId INT PRIMARY KEY IDENTITY,        -- Primary Key for the table
    tourName NVARCHAR(255) NOT NULL,        -- Name of the tour (Unicode for special characters)
    description NTEXT,                       -- Description of the tour (Unicode for long text)
    totalTime NVARCHAR(10),                 -- Duration of the tour (e.g., 6 days 5 nights)
    price DECIMAL(10, 0),                    -- Price of the tour (whole number, adjust decimal places as needed)
    slot INT,                                -- Available slots or number of people per tour
    tourStatus NVARCHAR(50),                -- Status of the tour (e.g., active, cancelled)
    companyId INT,                           -- Foreign Key for company, assuming there's a Company table
    tourImg NVARCHAR(255),                   -- URL or path to the tour image (Unicode in case of special characters)
    createdAt DATETIME DEFAULT GETDATE()     -- Timestamp for record creation
);


DROP TABLE Tours

INSERT INTO Tours
(tourName, description, totalTime, price, slot, tourStatus, companyId, tourImg) 
VALUES 
(
    N'Hà Nội - Ninh Bình - Tràng An - Hạ Long - Sapa - Lào Cai - Phú Thọ',
    N'Khởi hành thứ 2: Chiêm ngưỡng động Thiên Cung, các hòn Đỉnh Hương - Trống Mái, tham quan Tràng An, Fansipan, Đền Hùng.',
    '6N5D',
    16000.00,  -- Assuming this is the price
    30,        -- Example for 30 available slots
    'Active',  -- Tour status
    1,         -- Assuming 1 is the ID of the company offering the tour
    'https://saigontourist.net/uploads/destination/TrongNuoc/Hanoi/Ba-Dinh-Square-in-Hanoi-Vietnam_428152555.jpg'
);

INSERT INTO Tours
    (tourName, description, totalTime, price, slot, tourStatus, companyId, tourImg) 
VALUES 
(
    N'DU LỊCH PHÚ QUỐC - KHÁM PHÁ BẮC ĐẢO - SAFARI - VINWONDER - GRAND WORLD',
    N'- Khởi hành (thứ 3): 1/10 ; 10, 24/12
     - Tham quan điểm Vườn tiêu, Lò chế biến rượu Sim rừng Phú Quốc
     - Khám phá Vinpearl Safari Phú Quốc – vườn thú hoang dã đầu tiên tại Việt Nam với quy mô 180ha, cùng hơn 130 loài động vật quý hiếm và các chương trình biểu diễn, chụp ảnh với động vật.
     - Tham quan VinWonder Phú Quốc – công viên chủ đề được chia làm 6 phân khu, tượng trưng cho 6 vùng lãnh địa với 12 chủ đề được lấy cảm hứng từ các nền văn minh nổi tiếng.',
    '3N2D',  -- 3 days, 2 nights
    12000.00, -- Example price, adjust as needed
    25, -- Number of available slots
    'Active', -- Tour status is active
    2, -- Assuming 2 is the ID of the company offering the tour
    'https://saigontourist.net/uploads/destination/TrongNuoc/Phuquoc/phu-quoc-beach_599336339.jpg' -- Replace with actual image URL if available
);

INSERT INTO Tours
    (tourName, description, totalTime, price, slot, tourStatus, companyId, tourImg) 
VALUES 
(
    N'DU LỊCH BẾN TRE - TRÀ VINH - SÓC TRĂNG - BẠC LIÊU - MŨI CÀ MAU - HÀ TIÊN - "THẤT SƠN" AN GIANG - LONG XUYÊN - ĐỒNG THÁP MƯỜI',
    N'- Khởi hành (thứ 3 cách tuần)
     - Thăm làng nghề truyền thống, thưởng thức trà, trái cây bốn mùa.
     - Viếng chùa Âng - một trong những ngôi chùa cổ kính nhất trong hệ thống hơn 140 ngôi chùa Khmer tại Trà Vinh.
     - Viếng Thiền Viện Trúc Lâm An Giang nên thơ giữa lòng thị trấn Núi Sập.',
    '6N5D',  -- 6 days, 5 nights
    20000.00, -- Example price, adjust as needed
    20, -- Number of available slots
    'Active', -- Tour status is active
    2, -- Assuming 2 is the ID of the company offering the tour
    'https://saigontourist.net/uploads/destination/TrongNuoc/Mientay/bac%20lieu/House-of-Cong-Tu---the-Son_1092573599.jpg' -- Replace with actual image URL if available
);

INSERT INTO Tours
    (tourName, description, totalTime, price, slot, tourStatus, companyId, tourImg) 
VALUES 
(
    N'DU LỊCH ĐÀ LẠT - MONGO LAND - ĐỒI CHÈ CẦU ĐẤT - CHÙA LINH PHƯỚC - THÁC DATANLA',
    N'- Khởi hành: thứ 7 hàng tuần
     - Chụp hình tại Đồi chè Cầu Đất.
     - Tham quan Thác Datanla - nổi tiếng với vẻ đẹp hoang sơ, thơ mộng mà dữ dội, đặc trưng của đại ngàn Tây Nguyên (tự túc chi phí tham gia trò chơi máng trượt)
     - Tham quan Mongo Land - nơi được mệnh danh là tiểu Mông Cổ ở Đà Lạt với hàng ngàn góc check-in sống ảo siêu dễ thương như khu vực lều Mông Cổ, cối xay gió, sa mạc xương rồng, ruộng cỏ Tây Bắc, nông trại thú cưng như lạc đà Alpaca, hươu sao, dê mini, thỏ sư tử.',
    '4N3D',  -- 4 days, 3 nights
    15000.00, -- Example price, adjust as needed
    20, -- Number of available slots
    'Active', -- Tour status is active
    2, -- Assuming 2 is the ID of the company offering the tour
    'https://saigontourist.net/uploads/destination/TrongNuoc/Dalat/cafe-dalat_516916252.jpg'
);
INSERT INTO Tours
    (tourName, description, totalTime, price, slot, tourStatus, companyId, tourImg) 
VALUES 
(
    N'DU LỊCH NHA TRANG - LÀNG YẾN MAI SINH - DỐC LẾT - I RESORT',
    N'- Khởi hành: thứ 7 hàng tuần
     - Viếng tháp Bà Ponagar, thư giãn và tắm khoáng tại Suối khoáng nóng I resort.
     - Tham quan Làng Yến Mai Sinh, tìm hiểu về quy trình dẫn dụ Chim Yến nuôi trong nhà và khai thác tổ yến, mua sắm đặc sản Yến Sào nổi tiếng của Khánh Hòa.',
    '4N3D',  -- 4 days, 3 nights
    16000.00, -- Example price, adjust as needed
    20, -- Number of available slots
    'Active', -- Tour status is active
    2, -- Assuming 2 is the ID of the company offering the tour
    'https://saigontourist.net/uploads/destination/TrongNuoc/Nhatrang/hot-springs-in-Nha-Trang_718046614.jpg'
);
INSERT INTO Tours
    (tourName, description, totalTime, price, slot, tourStatus, companyId, tourImg) 
VALUES 
(
    N'DU LỊCH HÀ NỘI - YÊN TỬ - HẠ LONG - KHU DU LỊCH TRÀNG AN - BÁI ĐÍNH',
    N'- Khởi hành thứ 7 (bao gồm Vé máy bay): 21, 28/09/2024
     - Ngắm toàn cảnh vịnh Bái Tử Long yên bình.
     - Du ngoạn vịnh Hạ Long – một trong 7 kỳ quan thiên nhiên mới của thế giới, chiêm ngưỡng động Thiên Cung, các hòn Đỉnh Hương – Trống Mái (Gà Chọi) – Chó Đá.',
    '4N3D',  -- 4 days, 3 nights
    25000.00, -- Example price, adjust as needed
    20, -- Number of available slots
    'Active', -- Tour status is active
    2, -- Assuming 2 is the ID of the company offering the tour
    'https://saigontourist.net/uploads/destination/TrongNuoc/Hanoi/Ba-Dinh-Square-in-Hanoi-Vietnam_428152555.jpg'
);



SELECT * FROM Tours
