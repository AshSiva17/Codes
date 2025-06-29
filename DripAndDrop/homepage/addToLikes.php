<?php
/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
Uploads item to likes datatable
 */
session_start();

include "../config/connect.php";

$id = filter_input(INPUT_POST, 'id', FILTER_SANITIZE_NUMBER_INT);
$email = $_SESSION['email'];

$cmd = "INSERT INTO `likes`(`id`, `email`) VALUES (?,?)";
$stmt = $dbh->prepare($cmd);
$params = [$id, $email];
$sucess = $stmt->execute($params);

if ($sucess) {
    $message = "Added";
} else {
    $message = "Error trying to add";
}

echo json_encode($message);