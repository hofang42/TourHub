<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chat with Google Maps</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .chat-container {
            display: flex;
            flex-direction: column;
            height: 100vh;
            width: 100%;
            max-width: 600px;
            margin: 0 auto;
        }
        .chat-box {
            flex-grow: 1;
            padding: 10px;
            border: 1px solid #ccc;
            overflow-y: auto;
            background-color: #f9f9f9;
            margin-bottom: 10px;
        }
        .chat-input {
            display: flex;
        }
        .chat-input input {
            flex-grow: 1;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-right: 5px;
        }
        .chat-input button {
            padding: 10px;
            border: none;
            background-color: #007bff;
            color: white;
            border-radius: 5px;
            cursor: pointer;
        }
        .map-container {
            height: 400px;
            margin-top: 20px;
        }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&callback=initMap" async defer></script>
    <script>
        let map;
        function initMap() {
            const defaultLocation = { lat: 10.8231, lng: 106.6297 }; // Vị trí mặc định (TPHCM)
            map = new google.maps.Map(document.getElementById("map"), {
                zoom: 10,
                center: defaultLocation,
            });
        }

        function showLocation() {
            const address = document.getElementById("address").value;
            const geocoder = new google.maps.Geocoder();

            geocoder.geocode({ address: address }, (results, status) => {
                if (status === "OK") {
                    map.setCenter(results[0].geometry.location);
                    new google.maps.Marker({
                        map: map,
                        position: results[0].geometry.location,
                    });
                } else {
                    alert("Không tìm thấy địa chỉ: " + status);
                }
            });
        }
    </script>
</head>
<body>
    <div class="chat-container">
        <div class="nhung-easy" id="chatBox">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3835.8560699639074!2d108.25627786927338!3d15.968891008319618!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3142116949840599%3A0x365b35580f52e8d5!2zxJDhuqFpIGjhu41jIEZQVCDEkMOgIE7hurVuZw!5e0!3m2!1svi!2s!4v1728023022468!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
        <div class="chat-input">
            <input type="text" id="address" placeholder="Nhập địa chỉ...">
            <button onclick="showLocation()">Trỏ đến địa chỉ</button>
        </div>
        <div class="map-container" id="map"></div>
    </div>
    
</body>
</html>
