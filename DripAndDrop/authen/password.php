<?php

/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
PHP to check if password is valid then send AJAX response
 */

$password = filter_input(INPUT_POST, "input");

$upperCase = false;
$lowerCase = false;
$specialChar = false;
$digit = false;
$length6 = strlen($password) >= 6;


for ($i = 0; $i < strlen($password); $i++){
    $char = $password[$i];
    if (ctype_upper($char)){
        $upperCase = true;
    }
}

for ($i = 0; $i < strlen($password); $i++){
    $char = $password[$i];
    if (ctype_lower($char)){
        $lowerCase = true;
    }
}

for ($i = 0; $i < strlen($password); $i++){
    $char = $password[$i];
    if (ctype_digit($char)){
        $digit = true;
    }
}

for ($i = 0; $i < strlen($password); $i++){
    $char = $password[$i];
    if (!ctype_alnum($char)){
        $specialChar = true;
    }
}

$isValid = $upperCase && $lowerCase && $specialChar && $digit && $length6;

echo json_encode($isValid);

?>