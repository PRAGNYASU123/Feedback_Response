<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FeedBack</title>
    
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/style/proj.css">
</head>
<body>
<div class ="box">
<h2 class = "head">FeedBack</h2>

<form action="<%= application.getContextPath() %>/Response" method="post">

<input class="imp" type="text" placeholder="Full Name" name="name" required><br><br>

<input class="imp" type="email" placeholder="Email Address" name="email" required><br><br>

<textarea id="message" name="message" rows="5" cols="15" placeholder="Enter Your Opinion Here.." required></textarea><br><br>

<button type="Submit">Send</button>

</form>

</div>
</body>
</html>

