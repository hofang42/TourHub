<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="includes/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    /* Add pointer cursor for buttons */
    .group-btn .btn {
        cursor: pointer;
    }
</style>
<body>
    <!-- Page preloader-->
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
    <!-- Page-->
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
                            <div class="rd-navbar-brand"><a class="brand-name" href="index.html"><img class="logo-default" src="assests/images/logo-favicon/logo.png" alt="" width="208" height="46"/>
                                    <img class="logo-inverse" src="assests/images/logo-favicon/logo.png" alt="" width="208" height="46"/></a></div>
                        </div>
                        <div class="rd-navbar-aside-center">
                            <div class="rd-navbar-nav-wrap">
                                <!-- RD Navbar Nav-->
                                <ul class="rd-navbar-nav">
                                    <li class="active"><a href="index.jsp">Home</a>
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
                        <div class="rd-navbar-aside-right"><a class="button button-sm button-secondary button-nina" href="login">Login</a></div>
                        <div class="rd-navbar-aside-right"><a class="button button-sm button-secondary button-nina" href="register">Register</a></div>
                    </div>
                </nav>
            </div>
        </header>
        <section class="section">
            <div class="swiper-form-wrap">
                <!-- Swiper-->
                <div class="swiper-container swiper-slider swiper-slider_height-1 swiper-align-left swiper-align-left-custom context-dark bg-gray-darker" data-loop="false" data-autoplay="5500" data-simulate-touch="false" data-slide-effect="fade">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide" data-slide-bg="assests/images/banner/bg1.webp">
                            <div class="swiper-slide-caption">
                                <div class="container container-bigger swiper-main-section">
                                    <div class="row row-fix justify-content-sm-center justify-content-md-start">
                                        <div class="col-md-6 col-lg-5 col-xl-4 col-xxl-5">
                                            <h3>Hundreds of Amazing Destinations</h3>
                                            <!--<div class="divider divider-decorate"></div>-->
                                            <p class="text-spacing-sm">We offer a variety of destinations to travel to, ranging from exotic to some extreme ones. They include very popular countries and cities like Paris, Rio de Janeiro, Cairo and a lot of others.</p><a class="button button-default-outline button-nina button-sm" href="#">learn more</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide" data-slide-bg="assests/images/banner/bg2.jpg">
                            <div class="swiper-slide-caption">
                                <div class="container container-bigger swiper-main-section">
                                    <div class="row row-fix justify-content-sm-center justify-content-md-start">
                                        <div class="col-md-6 col-lg-5 col-xl-4 col-xxl-5">
                                            <h3>The Trip of Your Dream</h3>
                                            <!--<div class="divider divider-decorate"></div>-->
                                            <p class="text-spacing-sm">Our travel agency is ready to offer you an exciting vacation that is designed to fit your own needs and wishes. Whether it’s an exotic cruise or a trip to your favorite resort, you will surely have the best experience.</p><a class="button button-default-outline button-nina button-sm" href="#">learn more</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide" data-slide-bg="assests/images/banner/bg3.jpg">
                            <div class="swiper-slide-caption">
                                <div class="container container-bigger swiper-main-section">
                                    <div class="row row-fix justify-content-sm-center justify-content-md-start">
                                        <div class="col-md-6 col-lg-5 col-xl-4 col-xxl-5">
                                            <h3>unique Travel Insights</h3>
                                            <!--<div class="divider divider-decorate"></div>-->
                                            <p class="text-spacing-sm">Our team is ready to provide you with unique weekly travel insights that include photos, videos, and articles about untravelled tourist paths. We know everything about the places you’ve never been to!</p><a class="button button-default-outline button-nina button-sm" href="#">learn more</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Swiper controls-->
                    <div class="swiper-pagination-wrap">
                        <div class="container container-bigger">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="swiper-pagination"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="section section-variant-1 bg-default novi-background bg-cover"> 
            <div class="container container-wide">
                <div class="row row-fix justify-content-xl-end row-30 text-center text-xl-left">
                    <div class="col-xl-8">
                        <div class="parallax-text-wrap">
                            <h3>Our Best Tours</h3><span class="parallax-text">Hot tours</span>
                        </div>
                        <hr class="divider divider-decorate">
                    </div>
                    <div class="col-xl-3 text-xl-right"><a class="button button-secondary button-nina" href="#">view all tours</a></div>
                </div>
                <div class="group-btn" role="group" aria-label="City Options">
                    <button type="button" class="btn btn-primary active" city="Phú Quốc">Phú Quốc</button>
                    <button type="button" class="btn btn-outline-primary" city="Da Nang">Da Nang</button>
                    <button type="button" class="btn btn-outline-primary" city="Hà Nội">Ha Noi</button>
                    <button type="button" class="btn btn-outline-primary" city="TP Ho Chi Minh">TP Ho Chi Minh</button>
                    <button type="button" class="btn btn-outline-primary" city="Quy Nhon">Quy Nhon</button>
                </div>
                <div class="row row-50">
                    <c:if test="${empty tours}">
                        <p>No tours available.</p>
                    </c:if>                   
                </div>
            </div>
        </section>
        <section class="section section-lg text-center bg-gray-lighter novi-background bg-cover">
            <div class="container container-bigger">
                <h3>testimonials</h3>
                <div class="divider divider-decorate"></div>
                <!-- Owl Carousel-->
                <div class="owl-carousel owl-layout-1" data-items="1" data-dots="true" data-nav="true" data-stage-padding="0" data-loop="true" data-margin="30" data-mouse-drag="false" data-autoplay="true">
                    <article class="quote-boxed">
                        <div class="quote-boxed-aside"><img class="quote-boxed-image" src="assests/images/quote-user-1-210x210.jpg" alt="" width="210" height="210"/>
                        </div>
                        <div class="quote-boxed-main">
                            <div class="quote-boxed-text">
                                <p>I wanted to thank you very much for planning the trip to France for my boyfriend and me. It was amazing and exceeded my expectations! We had a wonderful time and were very pleased with the accommodations in Paris and Bayeux. Our private/small tour guides were fantastic! I appreciate all the effort to get us to the Eiffel Tower finally. </p>
                            </div>
                            <div class="quote-boxed-meta">
                                <p class="quote-boxed-cite">Ann McMillan</p>
                                <p class="quote-boxed-small">Regular Customer</p>
                            </div>
                        </div>
                    </article>
                    <article class="quote-boxed">
                        <div class="quote-boxed-aside"><img class="quote-boxed-image" src="assests/images/quote-user-2-210x210.jpg" alt="" width="210" height="210"/>
                        </div>
                        <div class="quote-boxed-main">
                            <div class="quote-boxed-text">
                                <p>I had a marvelous time in our travels to Madagascar, Zimbabwe and Botswana, I had just wonderful experiences.I loved the location of the Gorges Camp as I felt like it was only the time we got to see real and rural Africans and how they truly lived. The service was amazing and everyone was very attentive!</p>
                            </div>
                            <div class="quote-boxed-meta">
                                <p class="quote-boxed-cite">Debra Ortega</p>
                                <p class="quote-boxed-small">Regular Customer</p>
                            </div>
                        </div>
                    </article>
                    <article class="quote-boxed">
                        <div class="quote-boxed-aside"><img class="quote-boxed-image" src="assests/images/quote-user-3-210x210.jpg" alt="" width="210" height="210"/>
                        </div>
                        <div class="quote-boxed-main">
                            <div class="quote-boxed-text">
                                <p>Just wanted to say many, many thanks for helping me set up an amazing Costa Rican adventure! My nephew and I had a blast! All of the accommodations were perfect as were the activities that we did (canopy, coffee tour, hikes, fishing, and massages!) We have such fond memories and can't thank you enough!</p>
                            </div>
                            <div class="quote-boxed-meta">
                                <p class="quote-boxed-cite">Samantha Smith</p>
                                <p class="quote-boxed-small">Regular Customer</p>
                            </div>
                        </div>
                    </article>
                </div>
            </div>
        </section>

        <section class="section section-md text-center text-md-left bg-gray-700 novi-background bg-cover">
            <div class="container container-wide">
                <div class="row row-fix row-50 justify-content-sm-center">
                    <div class="col-xxl-8">
                        <div class="box-cta box-cta-inline">
                            <div class="box-cta-inner">
                                <h3 class="box-cta-title">Buy a tour without leaving your home</h3>
                                <p>Using our website, you can book any tour just in a couple of clicks.</p>
                            </div>
                            <div class="box-cta-inner"><a class="button button-secondary button-nina" href="#">Book Now</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </section>    
        <% 
            // Retrieve the JSON string from the request attribute
            String toursJson = (String) request.getAttribute("toursJson");
            // Escape special characters for safe embedding
            String encodedToursJson = toursJson
                .replace("\\", "\\\\")  // Escape backslashes
                .replace("\"", "\\\"")  // Escape double quotes
                .replace("\n", "\\n")   // Escape new lines
                .replace("\r", "\\r");  // Escape carriage returns
        %>

        <script>
            // Safely embed the JSON data into JavaScript
            const toursJson = "<%= encodedToursJson %>";

            // Parse the JSON string into a JavaScript object
            const tours = JSON.parse(toursJson);

            // Function to display tours based on the selected city
            function displayTours(city) {
                // Filter tours based on the selected city
                const filteredTours = tours.filter(tour =>
                    tour.tourName.toLowerCase().includes(city.toLowerCase())
                );

                const cityList = document.querySelector('.row.row-50');
                // Clear the existing list
                cityList.innerHTML = '';

                if (filteredTours.length === 0) {
                    cityList.innerHTML = 'No tours found for the selected city.';
                    return;
                }

                // Display filtered tours
                filteredTours.forEach(tour => {
                    const colDiv = document.createElement('div');
                    colDiv.classList.add('col-md-6', 'col-xl-4');

                    const article = document.createElement('article');
                    article.classList.add('event-default-wrap');

                    const eventDefault = document.createElement('div');
                    eventDefault.classList.add('event-default');

                    const figure = document.createElement('figure');
                    figure.classList.add('event-default-image');

                    const img = document.createElement('img');
                    img.src = tour.tourImg;
                    img.alt = tour.tourName;
                    img.width = 570;
                    img.height = 370;
                    figure.appendChild(img);

                    eventDefault.appendChild(figure);

                    const captionDiv = document.createElement('div');
                    captionDiv.classList.add('event-default-caption');

                    const learnMoreBtn = document.createElement('a');
                    learnMoreBtn.classList.add('button', 'button-xs', 'button-secondary', 'button-nina');
                    learnMoreBtn.href = "#";
                    learnMoreBtn.textContent = "Learn more";
                    captionDiv.appendChild(learnMoreBtn);

                    eventDefault.appendChild(captionDiv);
                    article.appendChild(eventDefault);

                    const eventDefaultInner = document.createElement('div');
                    eventDefaultInner.classList.add('event-default-inner');

                    const tourInfoDiv = document.createElement('div');

                    const tourName = document.createElement('h5');
                    const tourLink = document.createElement('a');
                    tourLink.classList.add('event-default-title');
                    tourLink.href = "#";
                    tourLink.textContent = tour.tourName;
                    tourName.appendChild(tourLink);
                    tourInfoDiv.appendChild(tourName);
                    eventDefaultInner.appendChild(tourInfoDiv);

                    const priceSpan = document.createElement('span');
                    priceSpan.classList.add('heading-5');
                    priceSpan.textContent = tour.price + " VND";
                    tourInfoDiv.appendChild(priceSpan);

                    const totalTimeDiv = document.createElement('div');
                    totalTimeDiv.classList.add('heading-6');
                    totalTimeDiv.textContent = tour.totalTime;
                    eventDefaultInner.appendChild(totalTimeDiv);

                    article.appendChild(eventDefaultInner);

                    colDiv.appendChild(article);
                    cityList.appendChild(colDiv);
                });
            }

            document.addEventListener('DOMContentLoaded', function () {
                // Automatically display tours for "Phú Quốc" when the page loads
                displayTours("Phú Quốc");

                // Add event listeners to buttons
                document.querySelectorAll('button[city]').forEach(button => {
                    button.addEventListener('click', function () {
                        const city = this.getAttribute('city');  // Get the city from the button's city attribute
                        displayTours(city);  // Call displayTours function with the selected city
                    });
                });
            });
            // Function to handle button click and change classes
            function handleButtonClick(event) {
                // Get the currently active button
                const activeButton = document.querySelector('.group-btn .active');

                // If there is an active button, remove the 'active' class
                if (activeButton) {
                    activeButton.classList.remove('active');
                    activeButton.classList.add('btn-outline-primary');
                    activeButton.classList.remove('btn-primary');
                }

                // Add 'active' class to the clicked button
                const clickedButton = event.currentTarget;
                clickedButton.classList.remove('btn-outline-primary');
                clickedButton.classList.add('btn-primary');
                clickedButton.classList.add('active');

                // Call the function to display tours based on the selected city
                const city = clickedButton.getAttribute('city');
                displayTours(city);
            }

// Function to initialize event listeners
            function initializeButtonListeners() {
                document.querySelectorAll('.group-btn .btn').forEach(button => {
                    button.addEventListener('click', handleButtonClick);
                });
            }

// Run initialization on DOMContentLoaded
            document.addEventListener('DOMContentLoaded', initializeButtonListeners);

        </script>

        <%@include file="includes/footer.jsp" %>