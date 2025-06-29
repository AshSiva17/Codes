<?php

/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
PHP to delete payments methods from payments datatable 
 */

include "../config/connect.php";

$id = filter_input(INPUT_POST, 'id', FILTER_SANITIZE_NUMBER_INT);

$cmd = "DELETE FROM `payments` WHERE `id`=?";
$stmt = $dbh->prepare($cmd);
$params = [$id];
$sucess = $stmt->execute($params);

echo json_encode($sucess);