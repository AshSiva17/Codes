<?php

/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
PHP to end session and logout user
 */

session_start();
session_unset();
session_destroy();
?>
<!DOCTYPE html>
<html>

<!DOCTYPE html>

<html>

<head>
    <title>Drip & Drop</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/createAccount.css">
</head>

<body>
    <div id="header">
        <h1>Drip&Drop</h1>
        <img src="../images/Drip.png" id="headerImg">
    </div>

    <div id="content">
        <p id="message">You have been logged out</p>
        <a href="login.html" id="logBtn">Exit</a>
    </div>
    <div id="footer">
        &copy; Jemuel, Ashwin, Omar, Shourya, McMaster University 2025
    </div>
</body>

</html>