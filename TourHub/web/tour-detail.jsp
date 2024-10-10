<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html class="wide wow-animation" lang="en">
    <head>
        <!-- Site Title-->
        <title>Home</title>
        <meta name="format-detection" content="telephone=no" />
        <meta
            name="viewport"
            content="width=device-width, height=device-height, initial-scale=1.0"
            />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta charset="utf-8" />
        <link
            rel="icon"
            href="assests/images/logo-favicon/logo.png"
            type="image/x-icon"
            />
        <!-- Stylesheets -->
        <link
            rel="stylesheet"
            type="text/css"
            href="//fonts.googleapis.com/css?family=Oswald:200,400%7CLato:300,400,300italic,700%7CMontserrat:900"
            />
        <link rel="stylesheet" href="assests/css/bootstrap.css" />
        <link rel="stylesheet" href="assests/css/style.css" />
        <link rel="stylesheet" href="assests/css/fonts.css" />
        <link rel="stylesheet" href="assests/css/index.css" />

        <!-- Owl Carousel CSS -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css"
            />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css"
            />

        <!-- jQuery (required for Owl Carousel) -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

        <!-- Owl Carousel JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>

        <link rel="stylesheet" href="assests/css/tour-detail.css" />
        <link rel="stylesheet" href="assests/css/wave.css" />

        <!-- Font Awesome -->
        <script
            src="https://kit.fontawesome.com/d14313468c.js"
            crossorigin="anonymous"
        ></script>

        <!-- Bootstrap CSS -->
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
            crossorigin="anonymous"
            />
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"
            />

        <!-- Flatpickr CSS -->
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css"
            />

        <!--<link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.2/dist/css/bootstrap.min.css">-->
        <!--[if lt IE 10]>
          <div
            style="
              background: #212121;
              padding: 10px 0;
              box-shadow: 3px 3px 5px 0 rgba(0, 0, 0, 0.3);
              clear: both;
              text-align: center;
              position: relative;
              z-index: 1;
            "
          >
            <a href="http://windows.microsoft.com/en-US/internet-explorer/"
              ><img
                src="assests/images/ie8-panel/warning_bar_0000_us.jpg"
                border="0"
                height="42"
                width="820"
                alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today."
            /></a>
          </div>
          <script src="js/html5shiv.min.js"></script>
        <![endif]-->
    </head>
</html>


<body class="search-page">
    <!-- Page-->
    <div class="page-loader"> 
        <div class="page-loader-body"> 
            <div class="preloader-wrapper big active"> 
                <div class="spinner-layer spinner-blue"> 
                    <div class="circle-clipper left">
                        <div class="circle"> </div>
                    </div>
                    <div class="gap-patch">
                        <div class="circle"> </div>
                    </div>
                    <div class="circle-clipper right">
                        <div class="circle"></div>
                    </div>
                </div>
                <div class="spinner-layer spinner-red">
                    <div class="circle-clipper left">
                        <div class="circle"></div>
                    </div>
                    <div class="gap-patch">
                        <div class="circle"> </div>
                    </div>
                    <div class="circle-clipper right">
                        <div class="circle"></div>
                    </div>
                </div>
                <div class="spinner-layer spinner-yellow"> 
                    <div class="circle-clipper left">
                        <div class="circle"></div>
                    </div>
                    <div class="gap-patch">
                        <div class="circle"></div>
                    </div>
                    <div class="circle-clipper right">
                        <div class="circle"> </div>
                    </div>
                </div>
                <div class="spinner-layer spinner-green"> 
                    <div class="circle-clipper left">
                        <div class="circle"></div>
                    </div>
                    <div class="gap-patch">
                        <div class="circle"></div>
                    </div>
                    <div class="circle-clipper right">
                        <div class="circle"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="page">
        <!-- Page Header-->
        <header class="section page-header">
            <!-- RD Navbar-->
            <div class="rd-navbar-wrap rd-navbar-corporate">
                <nav class="rd-navbar" data-layout="rd-navbar-fixed" data-sm-layout="rd-navbar-fixed" data-md-layout="rd-navbar-fixed" data-md-device-layout="rd-navbar-fixed" data-lg-layout="rd-navbar-fullwidth" data-xl-layout="rd-navbar-static" data-lg-device-layout="rd-navbar-fixed" data-xl-device-layout="rd-navbar-static" data-md-stick-up-offset="130px" data-lg-stick-up-offset="100px" data-stick-up="true" data-sm-stick-up="true" data-md-stick-up="true" data-lg-stick-up="true" data-xl-stick-up="true">
                    <div class="rd-navbar-collapse-toggle" data-rd-navbar-toggle=".rd-navbar-collapse"><span></span></div>
                    <div class="rd-navbar-top-panel rd-navbar-collapse novi-background">
                        <div class="rd-navbar-top-panel-inner">
                            <ul class="list-inline">
                                <li class="box-inline list-inline-item"><span class="icon novi-icon icon-md-smaller icon-secondary mdi mdi-phone"></span>
                                    <ul class="list-comma">
                                        <li><a href="tel:#">1-800-1234-567</a></li>
                                        <li><a href="tel:#">1-800-6780-345</a></li>
                                    </ul>
                                </li>
                                <li class="box-inline list-inline-item"><span class="icon novi-icon icon-md-smaller icon-secondary mdi mdi-map-marker"></span><a href="#">2130 Fulton Street, San Diego, CA 94117-1080 USA</a></li>
                                <li class="box-inline list-inline-item"><span class="icon novi-icon icon-md-smaller icon-secondary mdi mdi-email"></span><a href="mailto:#">mail@demolink.org</a></li>
                            </ul>
                            <ul class="list-inline">
                                <li class="list-inline-item"><a class="icon novi-icon icon-sm-bigger icon-gray-1 mdi mdi-facebook" href="#"></a></li>
                                <li class="list-inline-item"><a class="icon novi-icon icon-sm-bigger icon-gray-1 mdi mdi-twitter" href="#"></a></li>
                                <li class="list-inline-item"><a class="icon novi-icon icon-sm-bigger icon-gray-1 mdi mdi-instagram" href="#"></a></li>
                                <li class="list-inline-item"><a class="icon novi-icon icon-sm-bigger icon-gray-1 mdi mdi-google-plus" href="#"></a></li>
                                <li class="list-inline-item"><a class="icon novi-icon icon-sm-bigger icon-gray-1 mdi mdi-linkedin" href="#"></a></li>
                            </ul>
                        </div>
                        <div class="rd-navbar-top-panel-inner"></div>
                    </div>
                    <div class="rd-navbar-inner">
                        <!-- RD Navbar Panel-->
                        <div class="rd-navbar-panel">
                            <!-- RD Navbar Toggle-->
                            <button class="rd-navbar-toggle" data-rd-navbar-toggle=".rd-navbar-nav-wrap"><span></span></button>
                            <!-- RD Navbar Brand-->
                            <div class="rd-navbar-brand"><a class="brand-name" href="index.html"><img class="logo-default" src="assests/images/logo-favicon/logo.png" alt="" width="208" height="46"/><img class="logo-inverse" src="assests/images/logo-inverse-208x46.png" alt="" width="208" height="46"/></a></div>
                        </div>
                        <div class="rd-navbar-aside-center">
                            <div class="rd-navbar-nav-wrap">
                                <!-- RD Navbar Nav-->
                                <ul class="rd-navbar-nav">
                                    <li><a href="index.jsp">Home</a>
                                    </li>
                                    <li><a href="about-us.jsp">About Us</a>
                                    </li>
                                    <li><a href="contacts.jsp">Contacts</a>
                                    </li>
                                    <li><a href="typography.jsp">Typography</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="rd-navbar-aside-right"><a class="button button-sm button-secondary button-nina" href="#">Book a tour now</a></div>
                    </div>
                </nav>
            </div>
        </header>

        <div id="blur">
            <div class="search-bar">
                <input type="text" placeholder="Any ideas on what to do for your next trip?">
                <button>Search</button>
            </div>

            <div class="header">
                <!--Content before waves-->
                <div class="inner-header">
                    <div class="inner-header">
                        <div class="tour-header">
                            <div class="page-location">
                                <a href="#">Xperience</a> /
                                <a href="#">Vietnam</a> /
                                <a href="#">Ho Chi Minh City</a> /
                                <a href="#">Cu Chi District</a> /
                                <a href="#">Nhuan Duc Town</a>
                            </div>

                            <div class="tour-info-container-wrapper">
                                <div class="tour-info-container">
                                    <div class="tour-name">
                                        <span class="tour-title">${tour.tour_Name}</span>
                                    </div>

                                    <div class="tour-location">
                                        <i class="fa-solid fa-location-dot"></i>
                                        <span class="location">${tour.location}</span>
                                        <a href="#">Show Map</a>
                                    </div>

                                    <div class="tour-time">
                                        <div class="nearest-tour-date-container">
                                            <i class="fa-solid fa-calendar-days"></i>
                                            <span class="nearest-tour-date-text">Nearest Tour Date | </span>
                                            <span class="nearest-tour-date">Sat, 28 Sep 2024</span>
                                        </div>

                                        <div class="tour-duration-container">
                                            <i class="fa-regular fa-clock"></i>
                                            <span class="tour-duration-text">Tour Duration | </span>
                                            <span class="tour-duration">${tour.total_Time} Hours</span>
                                        </div>
                                    </div>
                                </div>                       

                                <div class="tour-save-share">
                                    <button class="save-btn">
                                        <i class="fa-regular fa-bookmark"></i>
                                    </button>
                                    <button class="share-btn">
                                        <i class="fa-regular fa-share-from-square"></i>
                                    </button>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>

                <!--Waves Container-->
                <div>
                    <svg class="waves" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                         viewBox="0 24 150 28" preserveAspectRatio="none" shape-rendering="auto">
                    <defs>
                    <path id="gentle-wave"
                          d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z" />
                    </defs>
                    <g class="parallax">
                    <use xlink:href="#gentle-wave" x="30" y="0" fill="rgba(255,255,255,0.7)" />
                    <use xlink:href="#gentle-wave" x="30" y="3" fill="rgba(255,255,255,0.5)" />
                    <use xlink:href="#gentle-wave" x="30" y="5" fill="rgba(255,255,255,0.3)" />
                    <use xlink:href="#gentle-wave" x="48" y="7" fill="#fff" />
                    </g>
                    </svg>
                </div>
                <!--Waves end-->
            </div>

            <div class="content flex">
                <div class="tour-gallery">
                    <div class="image-highlight">
                        <img class="tour-img" src="${tour.tour_Img.get(0)}" alt="Tour Image 1">
                        <a href="javascript:void(0)" onclick="toggle('popup1')">
                            <div class="img-viewmore">
                                <h3>
                                    <i class="fa-solid fa-image"></i>
                                    View All Image
                                </h3>
                            </div>
                        </a>
                    </div>

                    <c:if test="${not empty tour.tour_Img[0]}">
                        <div class="image-1">
                            <img class="tour-img" src="${tour.tour_Img.get(0)}" alt="Tour Image 2">
                            <a href="javascript:void(0)" onclick="toggle('popup1')">
                                <div class="img-viewmore">
                                    <h3>
                                        <i class="fa-solid fa-image"></i>
                                        View All Image
                                    </h3>
                                </div>
                            </a>
                        </div>
                    </c:if>

                    <c:if test="${not empty tour.tour_Img[0]}">
                        <div class="image-2">
                            <img class="tour-img" src="${tour.tour_Img.get(0)}" alt="Tour Image 3">
                            <a href="javascript:void(0)" onclick="toggle('popup1')">
                                <div class="img-viewmore">
                                    <h3>
                                        <i class="fa-solid fa-image"></i>
                                        View All Image
                                    </h3>
                                </div>
                            </a>
                        </div>
                    </c:if>

                    <c:if test="${not empty tour.tour_Img[0]}">
                        <div class="image-3">
                            <img class="tour-img" src="${tour.tour_Img.get(0)}" alt="Tour Image 4">
                            <a href="javascript:void(0)" onclick="toggle('popup1')">
                                <div class="img-viewmore">
                                    <h3>
                                        <i class="fa-solid fa-image"></i>
                                        View All Image
                                    </h3>
                                </div>
                            </a>
                        </div>
                    </c:if>

                    <c:if test="${not empty tour.tour_Img[0]}">
                        <div class="image-4">
                            <img class="tour-img" src="${tour.tour_Img.get(0)}" alt="Tour Image 5">
                            <a href="javascript:void(0)" onclick="toggle('popup1')">
                                <div class="img-viewmore">
                                    <h3>
                                        <i class="fa-solid fa-image"></i>
                                        View All Image
                                    </h3>
                                </div>
                            </a>
                        </div>
                    </c:if>
                </div>


                <div class="tour-detail">
                    <div class="tour-detail-left-section">
                        <div class="left-section-above">
                            <div class="average-rating">
                                <span class="average-rating-point">${tour.average_Review_Rating}</span>

                                <div class="rank">
                                    <span class="rank-type">Xuất sắc</span>
                                    <br>
                                    <span class="number-rating">Từ ${tour.number_Of_Review} đánh giá</span>
                                </div>
                            </div>

                            <div class="map">
                                <i class="fa-solid fa-map-location-dot"></i>
                                <div class="map-content">
                                    <span class="view-map">Xem bản đồ</span>
                                    <br>
                                    <span>Hoà Vang</span>
                                </div>

                            </div>
                        </div>

                        <div class="left-section-below">
                            <div class="experiment">
                                <h5>Bạn sẽ trải nghiệm</h5>
                                <ul>
                                    <li>Tận hưởng khung cảnh tuyệt vời của đỉnh Bà Nà đứng trên cầu Vàng, một trong những chiếc cầu đẹp nhất thế giới</li>
                                    <li>Ngắm nhìn vẻ đẹp hùng vĩ của núi Chúa từ buồng cáp treo</li>
                                    <li>Ghé thăm làng Pháp với những khu vườn theo kiến trúc Pháp tinh tế</li>
                                    <li>Vui chơi thoả thích tại công viên Fantasy Park</li>
                                </ul>
                                <a href="javascript:void(0)" class="view-more-experiment" onclick="toggle('popup3')">Đọc
                                    thêm</a>
                            </div>
                            <div class="split"></div>

                            <div class="more-information">
                                <a href="javascript:void(0)" onclick="toggle('popup2')">Thông tin liên hệ, Tiện ích, Dịch vụ
                                    ngôn ngữ và nhiều thông tin khác</a>
                            </div>
                        </div>
                    </div>

                    <div class="tour-detail-right-section">
                        <div class="price-section">
                            <div class="price">
                                <span class="start-from">Bắt đầu từ</span>
                                <h4>${tour.price}</h4>
                            </div>

                            <button class="find-tour-btn">Tìm tour</button>
                        </div>

                        <div class="view-review">
                            <span class="view-review-content">Ấn tượng từ những du khách khác</span>
                            <a href="#">Xem tất cả đánh giá</a>
                        </div>

                        <div class="highlight-review">
                            <div class="review-info">
                                <span class="reviewer">Nguyen</span>
                                <span class="rating">10/10</span>
                            </div>

                            <div class="review-content">
                                <span>Hướng dẫn viên và tài xế nhiệt tình,
                                    vui vẻ, chu đáo. Đưa rước đúng giờ,
                                    sẽ tiếp tục book dịch vụ Traveloka
                                    nếu có nhu cầu.</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="tour-itinerary">
                    <h4>Lịch trình tour</h4>
                    <span>
                        <ul>
                            <li>07:30-17:00 Đón khác</li>
                            <li>Đến Bà Nà Hills, di chuyển bằng cáp treo </li>
                            <li>Tham quan Bà Nà Hills và check in cầu vàng, vườn Le Jardin D'Amour, chùa Linh Ứng, hầm rượu Debay </li>
                            <li>Di chuyển tiếp bằng cáp treo lên đến đỉnh núi </li>
                            <li>Dùng bữa trưa trên đỉnh Núi Chúa </li>
                            <li>Tiếp tục khám phá Bà Nà </li>
                            <li>Ra về bằng cáp treo</li>
                        </ul>
                    </span>
                    <a href="javascript:void(0)" onclick="toggle('popup4')">Xem lịch trình đầy đủ</a>
                </div>

                <div class="tour-booking">
                    <h4>Có vé trống cho bạn</h4>

                    <div class="date-picking">
                        <button class="calendar" onclick="openCalendar(); toggle('blur');">
                            <i class="fa-solid fa-calendar-days"></i>
                            Xem lịch
                        </button>

                        <input type="text" id="calendarInput" style="display: none;" />
                        <div class="date-wrapper">
                            <button class="scroll-left" onclick="scrollLeft1()">
                                <i class="fa-solid fa-arrow-right fa-flip-horizontal"></i>
                            </button>

                            <div class="date-section" id="date-section">
                                <!-- Tạo 14 khung ngày -->
                                <div class="date-container" data-index="0" onclick="selectDate(this)">
                                    <span class="day-of-week" data-dayofweek="0"></span>
                                    <span class="date" data-formatteddate="0"></span>
                                </div>
                                <div class="date-container" data-index="1" onclick="selectDate(this)">
                                    <span class="day-of-week" data-dayofweek="1"></span>
                                    <span class="date" data-formatteddate="1"></span>
                                </div>
                                <div class="date-container" data-index="2" onclick="selectDate(this)">
                                    <span class="day-of-week" data-dayofweek="2"></span>
                                    <span class="date" data-formatteddate="2"></span>
                                </div>
                                <div class="date-container" data-index="3" onclick="selectDate(this)">
                                    <span class="day-of-week" data-dayofweek="3"></span>
                                    <span class="date" data-formatteddate="3"></span>
                                </div>
                                <div class="date-container" data-index="4" onclick="selectDate(this)">
                                    <span class="day-of-week" data-dayofweek="4"></span>
                                    <span class="date" data-formatteddate="4"></span>
                                </div>
                                <div class="date-container" data-index="5" onclick="selectDate(this)">
                                    <span class="day-of-week" data-dayofweek="5"></span>
                                    <span class="date" data-formatteddate="5"></span>
                                </div>
                                <div class="date-container" data-index="6" onclick="selectDate(this)">
                                    <span class="day-of-week" data-dayofweek="6"></span>
                                    <span class="date" data-formatteddate="6"></span>
                                </div>
                                <div class="date-container" data-index="7" onclick="selectDate(this)">
                                    <span class="day-of-week" data-dayofweek="7"></span>
                                    <span class="date" data-formatteddate="7"></span>
                                </div>
                                <div class="date-container" data-index="8" onclick="selectDate(this)">
                                    <span class="day-of-week" data-dayofweek="8"></span>
                                    <span class="date" data-formatteddate="8"></span>
                                </div>
                                <div class="date-container" data-index="9" onclick="selectDate(this)">
                                    <span class="day-of-week" data-dayofweek="9"></span>
                                    <span class="date" data-formatteddate="9"></span>
                                </div>
                                <div class="date-container" data-index="10" onclick="selectDate(this)">
                                    <span class="day-of-week" data-dayofweek="10"></span>
                                    <span class="date" data-formatteddate="10"></span>
                                </div>
                                <div class="date-container" data-index="11" onclick="selectDate(this)">
                                    <span class="day-of-week" data-dayofweek="11"></span>
                                    <span class="date" data-formatteddate="11"></span>
                                </div>
                                <div class="date-container" data-index="12" onclick="selectDate(this)">
                                    <span class="day-of-week" data-dayofweek="12"></span>
                                    <span class="date" data-formatteddate="12"></span>
                                </div>
                                <div class="date-container" data-index="13" onclick="selectDate(this)">
                                    <span class="day-of-week" data-dayofweek="13"></span>
                                    <span class="date" data-formatteddate="13"></span>
                                </div>
                            </div>


                            <button class="scroll-right" onclick="scrollRight()">
                                <i class="fa-solid fa-arrow-right"></i>
                            </button>
                        </div>
                    </div>                  

                    <div class="tour-options-section">
                        <c:forEach items="${tourOptions}" var="option">
                            <div class="tour-option">
                                <div class="tour-option-left-section">
                                    <span class="option-name">${option.option_Name}</span>
                                    <span class="option-note">${option.option_Description}</span>
                                    <a href="javascript:void(0)" class="tour-option-detail" onclick="toggle('popup5')">Xem chi tiết</a>
                                    <span class="refund-section">${option.day_Of_Week}</span>
                                </div>
                                <div class="tour-option-right-section">
                                    <div class="top-pick-logo">Top pick ${option.available_Slots}</div>
                                    <div class="option-price-section">
                                        <div class="option-price">${option.option_Price}</div>
                                        <button class="option-pick-btn">Chọn vé</button>
                                    </div>
                                </div>                            
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <jsp:include page="/ViewTourReview?id=${tour.tour_Id}" />
                <div class="tour-rating">

                </div>
            </div>
        </div>

        <!--        Popup1-->
        <div id="popup1">
            <div id="carouselExampleIndicators" class="carousel slide">
                <div class="carousel-indicators">
                    <c:forEach var="i" begin="0" end="${fn:length(tour.tour_Img) - 1}">
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${i}"
                                class="${i == 0 ? 'active' : ''}" aria-current="${i == 0 ? 'true' : 'false'}"
                                aria-label="Slide ${i + 1}"></button>
                    </c:forEach>
                </div>
                <div class="carousel-inner">
                    <c:forEach var="image" items="${tour.tour_Img}">
                        <div class="carousel-item ${image eq tour.tour_Img[0] ? 'active' : ''}">
                            <img src="${image}" class="carousel-img d-block w-100" alt="...">
                        </div>
                    </c:forEach>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
                        data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
                        data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
            <button type="button" class="btn-close" aria-label="Close" onclick="toggle('popup1')"></button>
        </div>
        <!--        Popup2-->
        <div id="popup2">
            <h3>Thêm thông tin</h3>
            <div class="information-wrapper">
                <div class="language-service">
                    <h4>Dịch vụ ngôn ngữ</h4>
                    <span>Dịch vụ có sẵn: Tiếng Việt</span>
                </div>

                <div class="suitable">
                    <h4>Phù hợp với</h4>
                    <span>Đam mê nghiên cứu, Khám phá văn hoá, Gia đình vui vẻ, Ẩm thực châu Á</span>
                </div>

                <div class="contact-information">
                    <h4>Liên hệ đối tác:</h4>
                    <span>+84787204299</span>
                </div>

                <div class="extra-information">
                    <h4>Thông tin thêm</h4>
                    <span>
                        <ul>
                            <li>Dịch vụ đón trả miễn phí cho khách ở tại trung tâm thành phố Đà Nẵng.</li>
                            <li>Phí đón trả khách sẽ được áp dụng nếu khách có nguyện vọng được đón trả tại những địa điểm
                                sau:</li>
                            <ol>
                                <li>Dải resort từ Hyatt Regency Danang Resort and Spa đến Grandvrio Ocean Resort Danang:
                                    200.000 VND/1 chiều/nhóm</li>
                                <li>Khách sạn Danang Golden Bay, Citadines Blue Cove Danang: 200.000 VND/1 chiều/nhóm</li>
                                <li>InterContinental Đà Nẵng Sun Peninsula Resort: 300.000/chiều/xe 4 chỗ, 350.000/chiều/xe
                                    7 chỗ, 400.000 VND/chiều/xe 16 chỗ</li>
                                <li>Thừa Thiên - Huế, Khu vực Lăng Cô: 750.000/chiều/xe 4 chỗ, 1.000.000/chiều/xe 7 chỗ,
                                    1.300.000 VND/chiều/xe 16 chỗ</li>
                                <li>hành phố Huế: 1.100.000/chiều/xe 4 chỗ, 1.200.000/chiều/xe 7 chỗ, 1.800.000 VND/chiều/xe
                                    16 chỗ</li>
                                <li>Thành phố Hội An: 250.000/chiều/xe 4 chỗ, 350.000/chiều/xe 7 chỗ, 400.000 VND/chiều/xe
                                    16 chỗ</li>
                                <li>Sân bay Quốc tế Đà Nẵng: 100.000 VND/1 chiều/nhóm</li>
                            </ol>
                            <li>Nhà cung cấp tour sẽ liên hệ với bạn để xác nhận phí đón trả. Phí sẽ được thu trực tiếp bởi
                                tài xế khi kết thúc tour.</li>
                            <li>
                                Đối với tour ghép: Trong trường hợp khách không yêu cầu dịch vụ đưa đón, vui lòng có mặt tại
                                Khách sạn Novotel Hotel (36 Bạch Đằng, Đà Nẵng) trong khoảng 08:15 – 08:30 để khởi hành
                                tour. </li>
                        </ul>
                    </span>
                </div>
            </div>

            <button type="button" class="btn-close" aria-label="Close" onclick="toggle('popup2')">
            </button>
        </div>
        <!--        Popup3-->
        <div id="popup3">
            <h3>Bạn sẽ trải nghiệm</h3>
            <div class="experiment-wrapper">
                <ul>
                    <li>Tận hưởng khung cảnh tuyệt vời của đỉnh Bà Nà đứng trên cầu Vàng, một trong những chiếc cầu đẹp nhất
                        thế giới</li>
                    <li>Ngắm nhìn vẻ đẹp hùng vĩ của núi Chúa từ buồng cáp treo</li>
                    <li>Ghé thăm làng Pháp với những khu vườn theo kiến trúc Pháp tinh tế</li>
                    <li>Vui chơi thoả thích tại công viên Fantasy Park</li>
                </ul>

                <span>Bà Nà Hills là khu phức hợp giải trí và resort lớn nhất tại Việt Nam. Cùng nhau đi tour và xả láng cả
                    ngày tại Bà Nà Hills ngay nào! Tận hưởng không khí mát lạnh cùng phong cảnh tuyệt vời, ăn hết mình với
                    đủ loại ẩm thực và chơi hết sức với những lễ hội và các hoạt động giải trí đa dạng diễn ra hằng ngày,
                    tất cả đều ngay tại đây!</span>

                <img src="assests/images/new-image/jojo1.jpg" alt="">
                <span>Tận hưởng bầu không khí mát lạnh khi bạn "lướt" mây lên đến đỉnh Bà Nà </span>

                <img src="assests/images/new-image/jojo2.jpg" alt="">
                <span>Đừng quên "đua tốc độ" và tham gia rất nhiều trò chơi hấp dẫn khác tại Bà Nà nhé! </span>

                <img src="assests/images/new-image/jojo3.jpg" alt="">
                <span>Ngắm nhìn Bà Nà lấp lánh trong ánh đèn khi hoàng hôn buông xuống</span>
            </div>

            <button type="button" class="btn-close" aria-label="Close" onclick="toggle('popup3')"></button>
            </button>
        </div>
        <!--        Popup4-->
        <div id="popup4">
            <h3>Lịch trình tour</h3>

            <div class="tour-itinerary-wrapper">
                <ul>
                    <li>07:30-17:00 Đón khách </li>
                    <li>Đến Bà Nà Hills, di chuyển bằng cáp treo </li>
                    <li>Tham quan Bà Nà Hills và check in cầu vàng, vườn Le Jardin D'Amour, chùa Linh Ứng, hầm rượu Debay
                    </li>
                    <li>Di chuyển tiếp bằng cáp treo lên đến đỉnh núi </li>
                    <li>Dùng bữa trưa trên đỉnh Núi Chúa </li>
                    <li>Tiếp tục khám phá Bà Nà </li>
                    <li>Ra về bằng cáp treo</li>
                    <li>Trở về trung tâm Đà Nẵng</li>
                    <li>Kết thúc tour. </li>
                </ul>
            </div>

            <button type="button" class="btn-close" aria-label="Close" onclick="toggle('popup4')"></button>
            </button>
        </div>
        <!--        Popup5-->
        <div id="popup5">
            <h4>(Dành cho khách Việt Nam) Tour ghép - Khởi hành từ Đà Nẵng (Kèm bữa trưa)</h4>
            <div class="tour-option-detail-wrapper">
                <div class="tour-time-popup">
                    <span>
                        Thời lượng tour:
                        - 9,5 giờ
                    </span>

                    <span>
                        Thời gian và điểm đón:
                        <ul>
                            <li>Khách sẽ được đón tại nơi lưu trú nằm tại trung tâm thành phố Đà Nẵng từ 07:30 – 08:30 </li>
                            <li>Đối với khách nằm ngoài khu vực trung tâm thành phố Đà Nẵng, vui lòng tham khảo Thông tin
                                thêm trên trang Sản phẩm để biết thêm chi tiết </li>
                        </ul>
                    </span>
                </div>

                <span class="refund-section-popup">
                    Easy Refund
                </span>

                <div class="price-wrapper">
                    <span class="price-wrapper-inner">1.216.867 VND</span>

                    <button>Chọn vé</button>
                </div>

                <div>
                    <nav id="navbar-example2" class="navbar-inner bg-body-tertiary px-3 mb-3">
                        <ul class="nav nav-pills">
                            <li class="nav-item">
                                <a class="nav-link" href="#scrollspyHeading1">First</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#scrollspyHeading2">Second</a>
                            </li>
                            <li class="nav-item"></li>
                            <a class="nav-link" href="#scrollspyHeading3">Third</a>
                            </li>
                            <li class="nav-item"></li>
                            <a class="nav-link" href="#scrollspyHeading4">Fourth</a>
                            </li>
                            <li class="nav-item"></li>
                            <a class="nav-link" href="#scrollspyHeading5">Fifth</a>
                            </li>
                        </ul>
                    </nav>
                    <div data-bs-spy="scroll" data-bs-target="#navbar-example2" data-bs-root-margin="0px 0px -40%"
                         data-bs-smooth-scroll="true" class="navbar-content scrollspy-example bg-body-tertiary p-3 rounded-2" tabindex="0">
                        <h4 id="scrollspyHeading1">First heading</h4>
                        <span>
                            Giá đã bao gồm
                            Bữa ăn:

                            1 buffet trưa
                            Có lựa chọn chay
                            1 chai nước
                            Phương tiện di chuyển:

                            Xe có máy điều hoà để đưa đón và trung chuyển
                            Dịch vụ khác:

                            Vé vào cửa
                            Bảo hiểm du lịch
                            Hướng dẫn viên nói tiếng Việt - Anh
                            Cáp treo hai chiều
                            Quà tặng đặc biệt từ nhà cung cấp tour
                            Giá không bao gồm
                            Chi phí cá nhân
                            Tiền tip
                            Dịch vụ không kèm trong tour
                            Thức ăn và thức uống không kèm trong tour
                        </span>
                        <h4 id="scrollspyHeading2">Second heading</h4>
                        <span>
                            Hiệu lực của voucher

                            Sử dụng vào ngày đã chọn
                            Có hiệu lực vào mọi ngày bình thường
                            Có hiệu lực vào mọi ngày lễ
                            Dành cho khách Việt Nam
                        </span>
                        <h4 id="scrollspyHeading3">Third heading</h4>
                        <span>
                            Nếu đặt chỗ của bạn đã được xác nhận, nhân viên điều hành tour sẽ liên hệ với bạn qua điện thoại
                            ít nhất 24 giờ trước khi tour bắt đầu.
                            Nếu nhân viên điều hành tour không liên hệ với bạn trong khoảng thời gian đó, vui lòng liên hệ
                            với nhà điều hành tour qua số điện thoại trên voucher của bạn.
                            Xuất trình voucher Traveloka hợp lệ của bạn (không phải giấy chứng nhận thanh toán hay hóa đơn)
                            trên điện thoại cho nhân viên điều hành tour.
                        </span>
                        <h4 id="scrollspyHeading4">Fourth heading</h4>
                        <span>
                            Đặt chỗ này không thể thay đổi lịch.
                            Yêu cầu hoàn tiền muộn nhất là 2 ngày trước ngày đi đã chọn của bạn để nhận được 100% hoàn tiền.
                            Đặt chỗ của bạn sẽ không được hoàn lại nếu bạn yêu cầu hoàn tiền ít hơn 2 ngày trước ngày đi đã
                            chọn.
                            Số tiền hoàn lại cuối cùng sẽ không bao gồm phí dịch vụ, phiếu giảm giá và / hoặc phí chuyển
                            khoản ngân hàng mã duy nhất.
                            Để hủy đặt chỗ của bạn và yêu cầu hoàn tiền, vui lòng truy cập mục Đặt chỗ của tôi. Trong phần
                            Quản lý đặt chỗ, chạm vào Hoàn tiền và thực hiện theo quy trình gửi hoàn tiền (có trên Ứng dụng
                            Traveloka phiên bản 3.18 trở lên hoặc trang web Traveloka trên máy tính).
                        </span>
                        <h4 id="scrollspyHeading5">Fifth heading</h4>
                        <span>
                            Điều khoản & Điều kiện
                            Thông tin chung
                            Phụ phí 100.000 VND/khách, thanh toán trực tiếp với nhà cung cấp tour, áp dụng vào các ngày khởi
                            hành tour sau: 18 Th04, 30 Th04, 1 Th05, 31 Th08 – 3 Th09 2024.
                            Dịch vụ đón trả miễn phí cho khách ở tại trung tâm thành phố Đà Nẵng.
                            Phí đón trả khách sẽ được áp dụng nếu khách có nguyện vọng được đón trả tại những địa điểm sau:
                            1.
                            Dải resort từ Hyatt Regency Danang Resort and Spa đến Grandvrio Ocean Resort Danang: 200.000
                            VND/1 chiều/nhóm
                            2.
                            Khách sạn Danang Golden Bay, Citadines Blue Cove Danang: 200.000 VND/1 chiều/nhóm
                            3.
                            InterContinental Đà Nẵng Sun Peninsula Resort: 300.000/chiều/xe 4 chỗ, 350.000/chiều/xe 7 chỗ,
                            400.000 VND/chiều/xe 16 chỗ
                            4.
                            Thừa Thiên - Huế, Khu vực Lăng Cô: 750.000/chiều/xe 4 chỗ, 1.000.000/chiều/xe 7 chỗ, 1.300.000
                            VND/chiều/xe 16 chỗ
                            5.
                            Thành phố Huế: 1.100.000/chiều/xe 4 chỗ, 1.200.000/chiều/xe 7 chỗ, 1.800.000 VND/chiều/xe 16 chỗ
                            6.
                            Thành phố Hội An: 250.000/chiều/xe 4 chỗ, 350.000/chiều/xe 7 chỗ, 400.000 VND/chiều/xe 16 chỗ
                            7.
                            Sân bay Quốc tế Đà Nẵng: 100.000 VND/1 chiều/nhóm
                            8.
                            Nhà cung cấp tour sẽ liên hệ với bạn để xác nhận phí đón trả. Phí sẽ được thu trực tiếp bởi tài
                            xế khi kết thúc tour.
                        </span>
                    </div>
                </div>
            </div>
            <button type="button" class="btn-close" aria-label="Close" onclick="toggle('popup5')"></button>
            </button>
        </div>

        <div class="tour-content">



        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
    <script>
                function toggle(action) {
                    var blur = document.getElementById('blur');

                    if (action === 'blur') {
                        // Chỉ kích hoạt blur
                        blur.classList.toggle('active');
                    } else if (action === 'popup1') {
                        // Kích hoạt popup 1 và blur
                        blur.classList.toggle('active');
                        var popup1 = document.getElementById('popup1');
                        popup1.classList.toggle('active');
                    } else if (action === 'popup2') {
                        // Kích hoạt popup 2 và blur
                        blur.classList.toggle('active');
                        var popup2 = document.getElementById('popup2');
                        popup2.classList.toggle('active');
                    } else if (action === 'popup3') {
                        // Kích hoạt popup 2 và blur
                        blur.classList.toggle('active');
                        var popup3 = document.getElementById('popup3');
                        popup3.classList.toggle('active');
                    } else if (action === 'popup4') {
                        // Kích hoạt popup 2 và blur
                        blur.classList.toggle('active');
                        var popup4 = document.getElementById('popup4');
                        popup4.classList.toggle('active');
                    } else if (action === 'popup5') {
                        // Kích hoạt popup 2 và blur
                        blur.classList.toggle('active');
                        var popup5 = document.getElementById('popup5');
                        popup5.classList.toggle('active');
                    } else if (action === 'calendar') {
                        // Kích hoạt popup 2 và blur
                        blur.classList.toggle('active');
                        const section = document.getElementById('date-section');
                        if (section) {
                            section.scrollLeft -= 600; // Di chuyển về phía trái
                        }
                    } else {
                        // Đóng popup (khi người dùng nhấn nút "Close")
                        var popups = document.getElementsByClassName('popup');
                        for (var i = 0; i < popups.length; i++) {
                            popups[i].classList.remove('active');
                        }
                        blur.classList.remove('active');
                    }
                }
    </script>

    <script>
        function scrollRight() {
            const section = document.getElementById('date-section');
            if (section) {
                section.scrollLeft += 200; // Di chuyển về phía phải
            }
        }

        function scrollLeft1() {
            const section = document.getElementById('date-section');
            if (section) {
                section.scrollLeft -= 200; // Di chuyển về phía trái
            }
        }

        // Lắng nghe sự kiện click ra ngoài popup để tắt hiệu ứng mờ
        document.addEventListener('click', function (event) {
            var calendarElement = document.querySelector('.flatpickr-calendar');
            var blurElement = document.getElementById('blur');

            // Kiểm tra sự tồn tại của các phần tử trước khi xử lý
            if (calendarElement && !calendarElement.contains(event.target) && !event.target.closest('.calendar')) {
                if (blurElement && blurElement.classList.contains('active')) {
                    toggle(null);
                }
            }
        }, true);
    </script>

    <script>
        // Biến lưu ngày được chọn, mặc định là ngày hiện tại
        let selectedDate = new Date();

        // Function để hiển thị 14 ngày với ngày hiện tại hoặc đã chọn ở giữa
        // Function để hiển thị 14 ngày với ngày hiện tại hoặc đã chọn ở giữa
        function displayDateRange(centerDate) {
            const daysOfWeek = ['Chu nhat', 'Thu hai', 'Thu ba', 'Thu tu', 'Thu nam', 'Thu sau', 'Thu bay'];

            // Tìm tất cả các phần tử .date-container
            let dateContainers = document.querySelectorAll('.date-container');

            // Vòng lặp để tạo 14 ngày
            for (let i = 0; i < 14; i++) {
                let date = new Date(centerDate);
                date.setDate(centerDate.getDate() + i); // Hiển thị 7 ngày trước và 7 ngày sau

                let dayOfWeek = daysOfWeek[date.getDay()];
                let formattedDate = date.getDate() + ' thg ' + (date.getMonth() + 1);

                // Cập nhật nội dung của phần tử date-container
                dateContainers[i].querySelector('[data-dayofweek]').innerText = dayOfWeek;
                dateContainers[i].querySelector('[data-formatteddate]').innerText = formattedDate;

                // Cập nhật sự kiện onclick với giá trị ngày mới
                dateContainers[i].onclick = () => selectDate(dateContainers[i], date.toISOString()); // Sử dụng hàm mũi tên

                // Đặt class selected cho ngày hiện tại
                if (i === 0) {
                    dateContainers[i].classList.add('selected');
                } else {
                    dateContainers[i].classList.remove('selected');
                }
            }

            // Cập nhật biến selectedDate thành ngày đã chọn
            selectedDate = centerDate;

            filterTourOptions(selectedDate);
        }


        const daysMapping = {
            'Chu nhat': 0,
            'Thu hai': 1,
            'Thu ba': 2,
            'Thu tu': 3,
            'Thu nam': 4,
            'Thu sau': 5,
            'Thu bay': 6
        };


        function filterTourOptions(selectedDate) {
            const dayOfWeek = selectedDate.getDay(); // Lấy số ngày trong tuần từ selectedDate
            console.log("Selected day of week:", dayOfWeek); // Kiểm tra giá trị ngày đã chọn

            const tourOptions = [...document.querySelectorAll('.tour-option')];

            tourOptions.forEach(option => {
                const refundSection = option.querySelector('.refund-section').innerText;
                console.log("Refund section text:", refundSection); // In ra nội dung refund-section
                const optionDayOfWeek = daysMapping[refundSection]; // Sử dụng ánh xạ để lấy số

                console.log("Option day of week:", optionDayOfWeek); // Kiểm tra giá trị dayOfWeek trong mỗi option

                if (optionDayOfWeek === dayOfWeek) {
                    option.style.display = 'flex'; // Hiển thị tourOption
                } else {
                    option.style.display = 'none'; // Ẩn tourOption
                }
            });
        }


        // Function để xử lý việc chọn ngày trong date-wrapper
        function selectDate(element, dateStr) {
            console.log("Date string selected:", dateStr);
            // Xóa class 'selected' khỏi tất cả các ngày
            let allDates = document.querySelectorAll('.date-container');
            allDates.forEach(date => date.classList.remove('selected'));

            // Thêm class 'selected' vào phần tử được nhấp vào
            element.classList.add('selected');

            // Cập nhật ngày đã chọn
            selectedDate = new Date(dateStr);

            console.log("Updated selectedDate:", selectedDate);

            filterTourOptions(selectedDate);
        }

        // Function để mở lịch và đặt ngày mặc định
        function openCalendar() {
            flatpickr("#calendarInput", {
                dateFormat: "Y-m-d",
                defaultDate: new Date(),
                minDate: "today",
                onChange: function (selectedDates, dateStr, instance) {
                    if (selectedDates.length > 0) {
                        selectedDate = new Date(selectedDates[0]);
                        displayDateRange(selectedDate);
                    }
                },
                onClose: function () {
                    toggle('calendar');
                }
            }).open();
        }

        // Khi trang tải, hiển thị dải ngày với ngày đầu tiên là ngày hiện tại
        window.onload = function () {
            displayDateRange(new Date());
        };
    </script>

    <script>
        const options = document.querySelectorAll('.tour-option');
        options.forEach(option => {
            const dayOfWeek = option.getAttribute('data-dayofweek');
            console.log(dayOfWeek); // Kiểm tra xem giá trị có được in ra không
        });
    </script>
    <script>
        document.querySelectorAll('.nav-link').forEach(anchor => {
            anchor.addEventListener('click', function (e) {
                e.preventDefault();
                document.querySelector(this.getAttribute('href')).scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
            });
        });
    </script>
</body>

<%@include file="includes/footer.jsp" %>