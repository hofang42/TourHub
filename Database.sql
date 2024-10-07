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
