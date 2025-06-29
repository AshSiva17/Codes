<?php

/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
PHP to upload payment information
 */

session_start();

include "../config/connect.php";

$email = $_SESSION['email'];

$cardnumber = filter_input(INPUT_POST, 'cardnumber',FILTER_SANITIZE_SPECIAL_CHARS);
$expiry = filter_input(INPUT_POST, 'expiry', FILTER_SANITIZE_SPECIAL_CHARS);
$cvv = filter_input(INPUT_POST, 'cvv', FILTER_SANITIZE_NUMBER_INT);
$street = filter_input(INPUT_POST, 'street', FILTER_SANITIZE_SPECIAL_CHARS);
$city = filter_input(INPUT_POST, 'city', FILTER_SANITIZE_SPECIAL_CHARS);
$zip = filter_input(INPUT_POST, 'zip', FILTER_SANITIZE_SPECIAL_CHARS);

if (!$cardnumber || !$expiry || !$cvv || !$street || !$city || !$zip) {
    echo "Error: Missing input fields.";
    exit;
}

try {
    $cmd = "INSERT INTO `payments`(`email`, `cardnumber`, `expiry`, `cvv`, `street`, `city`, `zip`) 
            VALUES (?,?,?,?,?,?,?)";
    $stmt = $dbh->prepare($cmd);
    $params = [$email, $cardnumber, $expiry, $cvv, $street, $city, $zip];
    $stmt->execute($params);
    echo "Payment saved successfully.";
} catch (PDOException $e) {
    http_response_code(500);
    echo "Database error: " . $e->getMessage();
}

