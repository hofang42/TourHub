<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Messenger Chat Integration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
        }

        .container {
            text-align: center;
        }

        h1 {
            color: #333;
        }
    </style>
</head>
<body>
    <!-- Facebook Messenger Chat Plugin -->
    <div id="fb-root"></div>
    <script>
      window.fbAsyncInit = function() {
        FB.init({
          appId            : 'YOUR_APP_ID', // Replace with your Facebook App ID
          autoLogAppEvents : true,
          xfbml            : true,
          version          : 'v17.0' // Use the latest SDK version
        });
      };

      (function(d, s, id){
         var js, fjs = d.getElementsByTagName(s)[0];
         if (d.getElementById(id)) {return;}
         js = d.createElement(s); js.id = id;
         js.src = "https://connect.facebook.net/en_US/sdk/xfbml.customerchat.js";
         fjs.parentNode.insertBefore(js, fjs);
       }(document, 'script', 'facebook-jssdk'));
    </script>

    <div class="fb-customerchat"
        attribution="setup_tool"
        page_id="120820724456656"  <!-- Your Page ID here -->
        theme_color="#0084ff"
        logged_in_greeting="Hi! How can we help you?"
        logged_out_greeting="Hi! How can we help you?">
    </div>

    <!-- Main Content -->
    <div class="container">
        <h1>Welcome to Our Website</h1>
        <p>Feel free to chat with us using Messenger!</p>
    </div>
</body>
</html>
