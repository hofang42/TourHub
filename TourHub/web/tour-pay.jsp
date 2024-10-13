<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <link rel="stylesheet" href="assests/css/tour-pay.css" />

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
        <!--Page content-->
        <div class="main-container-wrapper">
            <div class="main-container">
                <div class="tour-qr-container">
                    <div class="tour-qr-title">
                        Quét mã QR để thanh toán
                    </div>

                    <div class="tour-qr">
                        <img class="tour-qr-img" src="" alt="">
                        <span class="paid-content-container">
                            Nội dung chuyển khoản:
                            <span id="paid-content"></span>
                        </span>
                    </div>
                </div>


                <div class="total-cost-overview">
                    <div class="total-cost-heading">
                        <div class="pay-title">
                            Giá bạn trả
                        </div>

                        <div class="total-cost-container">
                            <span class="total-cost">
                                ${book.total_Cost}
                            </span>

                            <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample"
                               role="button" aria-expanded="false" aria-controls="collapseExample"
                               style="color: black; background-color: transparent; border: none;">
                                <i class="fa-solid fa-chevron-down"></i>
                            </a>
                        </div>
                    </div>               

                    <div class="collapse" id="collapseExample" style="border-top: solid 1px rgba(197, 197, 197, 0.726);">
                        <div class="card card-body" style="border: none; padding: 15px 0px;">
                            <div class="detail-price-container">
                                <div class="detal-price-name">
                                    
                                </div>

                                <div class="detail-price">
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="continue-btn-conatiner">
                    <button class="continue-btn">Tôi đã thanh toán</button>
                </div>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous">
        </script>

    <script>
        document.addEventListener("DOMContentLoaded", function() {
            let MY_BANK = {
                BANK_ID: "MB",
                ACCOUNT_NO: "0364173531"
            };
            var totalcost = "${book.total_Cost}"
            const paid_content = document.getElementById("paid-content");
            const qr_img = document.querySelector(".tour-qr-img");

            let QR = "https://img.vietqr.io/image/" + MY_BANK.BANK_ID + "-" + MY_BANK.ACCOUNT_NO + "-compact2.jpg?amount=" + totalcost + "&addInfo=noidungchuyenkhoan";
            qr_img.src = QR;
            paid_content.innerHTML = "noidungchuyenkhoan";
        });
    </script>
    
    <script>
        // Giả sử booking_Detail có dạng như sau:
        var bookingDetail = "${book.booking_Detail}";

        console.log(bookingDetail);

        // Tách chuỗi thành mảng các phần tử bằng dấu chấm phẩy
        var detailsArray = bookingDetail.split(";");

        // Lấy các thẻ div tương ứng
        var nameContainer = document.querySelector('.detal-price-name');
        var priceContainer = document.querySelector('.detail-price');

        // Xóa nội dung cũ trong các container
        nameContainer.innerHTML = "";
        priceContainer.innerHTML = "";

        // Lặp qua từng phần tử của mảng
        for (var i = 0; i < detailsArray.length; i++) {
            if (i % 2 === 0) {
                // Nếu i là chẵn, thêm tên vào detal-price-name
                nameContainer.innerHTML += detailsArray[i] + "<br>";
            } else {
                // Nếu i là lẻ, thêm giá vào detail-price
                priceContainer.innerHTML += detailsArray[i] + "<br>";
            }
        }
    </script>
</body>

<%@include file="includes/footer.jsp" %>    