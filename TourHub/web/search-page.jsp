<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="includes/header.jsp" %>

<body class="search-page">
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
                            <div class="rd-navbar-brand"><a class="brand-name" href="index.html"><img class="logo-default" src="assests/images/logo-default-208x46.png" alt="" width="208" height="46"/><img class="logo-inverse" src="assests/images/logo-inverse-208x46.png" alt="" width="208" height="46"/></a></div>
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
        
        <body>
            <div class="search-bar">
                <div class="current-place">
                    <p>Xperience / Search Results</p>
                </div>

                <div class="search-container">
                    <button class="pick-location-btn" id="button" onclick="openPopup()">
                        <i class="fa-solid fa-location-dot"></i>
                        <p>Ho Chi Minh City</p>
                        <i class="fa-solid fa-caret-down"></i>
                    </button>

                    <input class="search-box" type="text" placeholder="Any ideas on what to do for your next trip?"/>
                </div>

                <div class="location-popup" id="popup">

                    <div class="popup-content">
                        <p>Choose your destination</p>
                        <i class="close fa-solid fa-xmark" alt="Close" onclick="closePopup()"></i>
                        <div class="location-container">
                            <div class="row">
                              <div class="location col-md-6">
                                <button>Ha Noi</button>
                              </div>
                              <div class="location col-md-6">
                                <button>Ho Chi Minh</button>
                              </div>

                              <div class="location col-md-6">
                                <button>Da Nang</button>
                              </div>
                              <div class="location col-md-6">
                                <button>Hue</button>
                              </div>

                              <div class="location col-md-6">
                                <button>Hoi An</button>
                              </div>
                              <div class="location col-md-6">
                                <button>Quang Ninh</button>
                              </div>

                              <div class="location col-md-6">
                                <button>Ha Noi</button>
                              </div>
                              <div class="location col-md-6">
                                <button>Ho Chi Minh</button>
                              </div>

                              <div class="location col-md-6">
                                <button>Da Nang</button>
                              </div>
                              <div class="location col-md-6">
                                <button>Hue</button>
                              </div>

                              <div class="location col-md-6">
                                <button>Hoi An</button>
                              </div>
                              <div class="location col-md-6">
                                <button>Quang Ninh</button>
                              </div>

                              <div class="location col-md-6">
                                <button>Ha Noi</button>
                              </div>
                              <div class="location col-md-6">
                                <button>Ho Chi Minh</button>
                              </div>

                              <div class="location col-md-6">
                                <button>Da Nang</button>
                              </div>
                              <div class="location col-md-6">
                                <button>Hue</button>
                              </div>

                              <div class="location col-md-6">
                                <button>Hoi An</button>
                              </div>
                              <div class="location col-md-6">
                                <button>Quang Ninh</button>
                              </div>
                            </div>
                          </div>
                    </div>


                </div>
            </div>
            <div class="body-container">
                <!-- Sidebar -->
                <div class="sidebar">

                    <div class="xperience">
                        <div>
                            <h2>Xperience Highlights</h2>
                        </div>

                        <div>
                            <button class="btn-new-experience">New In Experience</button>
                        </div>
                    </div>


                    <div class="price-range">
                        <h3>Price Range</h3>
                        <select class="price-select">
                            <option value="0">All</option>
                            <option value="0">0 VND - 1.000.000 VND+</option>
                            <option value="0">1.000.000 VND - 3.000.000 VND+</option>
                            <option value="0">2.000.000 VND - 4.000.000 VND+</option>
                            <option value="0">3.000.000 VND - 5.000.000 VND+</option>
                            <option value="0">5.000.000 VND - 8.000.000 VND+</option>
                            <option value="0">8.000.000 VND - 10.000.000 VND+</option>
                            <option value="0">10.000.000 VND - 15.000.000 VND+</option>
                            <option value="0">15.000.000 VND - 20.000.000 VND+</option>
                        </select>
                    </div>

                    <div class="category">
                        <h3>Category</h3>
                        <ul>
                            <li><input type="checkbox" /> All</li>
                            <li><input type="checkbox" /> Nature Tours</li>
                            <li><input type="checkbox" /> Sightseeing</li>
                            <li><input type="checkbox" /> Arts & Culture</li>
                            <li><input type="checkbox" /> Water Tours</li>
                            <li><input type="checkbox" /> Site Tours</li>
                            <li><input type="checkbox" /> Culinary Tours</li>
                            <li><input type="checkbox" /> Themed Tours</li>
                            <li><input type="checkbox" /> Half-day Tours</li>
                            <li><input type="checkbox" /> Bus tour</li>
                            <li><input type="checkbox" /> Day Tour</li>
                            <li><input type="checkbox" /> Island Hopping</li>
                        </ul>
                        <button class="btn-see-all">See All</button>
                    </div>
                </div>

                <!-- Main Content -->
                <div class="main-content">
                    <div class="main-content-header">
                        <h4>Showing Tours activities in Ho Chi Minh City</h4>
                        <div class="sort-options">
                            <label for="sort">Sort by:</label>
                            <select class="sort-bar" id="sort">
                                <option value="popular">Most Popular</option>
                                <option value="popular">Lowest Price</option>
                                <option value="popular">Highest Price</option>
                                <option value="popular">Highest Rating</option>
                                <option value="popular">Most Relevant</option>
                            </select>
                        </div>
                    </div>

                    <div class="tour-grid">
                        <!-- Tour Cards -->
                        <div class="tour-card">
                            <img src="Image/88.jpg" alt="Singapore Tour" />
                            <div class="tour-card-info">
                                <div class="tour-card-header">
                                    <p class="tour-location">
                                        <i class="fa-solid fa-location-dot"></i>
                                        Singapore
                                    </p>
                                    <i class="wishlist-btn fa-regular fa-heart"></i>
                                </div>

                                <p class="tour-name">Singapore and Malaysia Full Package Tour</p>

                                <div class="tour-card-footer">
                                    <p class="review">
                                        <i class="fa-solid fa-star"></i>   
                                        <span class="review-point">9.7</span> (6 reviews)
                                    </p>
                                    <p class="price">8.390.000 VND</p>
                                </div>                  
                            </div>
                        </div>

                        <!-- Corrected Second Tour Card -->
                        <div class="tour-card">
                            <img src="Image/beste-reistijd-hoi-an-vietnam-2400x1350.jpg" alt="Hoi An Tour" />
                            <div class="tour-card-info">
                                <div class="tour-card-header">
                                    <p class="tour-location">
                                        <i class="fa-solid fa-location-dot"></i>
                                        Vietnam
                                    </p>
                                    <i class="wishlist-btn fa-regular fa-heart"></i>
                                </div>

                                <p class="tour-name">Hoi An Full Package Tour</p>

                                <div class="tour-card-footer">
                                    <p class="review">
                                        <i class="fa-solid fa-star"></i>   
                                        <span class="review-point">9.5</span> (15 reviews)
                                    </p>
                                    <p class="price">5.390.000 VND</p>
                                </div>                  
                            </div>
                        </div>

                        <div class="tour-card">
                            <img src="Image/KTG04873.jpg" alt="joi An Tour" />
                            <div class="tour-card-info">
                                <div class="tour-card-header">
                                    <p class="tour-location">
                                        <i class="fa-solid fa-location-dot"></i>
                                        Vietnam
                                    </p>
                                    <i class="wishlist-btn fa-regular fa-heart"></i>
                                </div>

                                <p class="tour-name">Hoi An Full Package Tour</p>

                                <div class="tour-card-footer">
                                    <p class="review">
                                        <i class="fa-solid fa-star"></i>   
                                        <span class="review-point">9.5</span> (15 reviews)
                                    </p>
                                    <p class="price">5.390.000 VND</p>
                                </div>                  
                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <script>
                // document.getElementById('button').addEventListener("click", function() {
                //     document.querySelector(".location-popup").style.display = "flex";
                // });

                // document.querySelector(".close").addEventListener("click", function() {
                //     document.querySelector(".location-popup").style.display = "none";
                // })

                let popup = document.getElementById("popup");

                function openPopup() {
                    popup.classList.add("location-popup-open");
                    document.body.style.overflow = 'hidden';
                }

                function closePopup() {
                    popup.classList.remove("location-popup-open");
                    document.body.style.overflow = 'auto';
                }
            </script>
        </body>

        <%@include file="includes/footer.jsp" %>