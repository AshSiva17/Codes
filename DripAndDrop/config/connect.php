<?php

try {
    $dbh = new PDO(
        "mysql:host=localhost;dbname=lateoj_db",
        "fill-in username",
        "fill-in password"
    );
} catch (Exception $e) {
    die("ERROR: Couldn't connect. {$e->getMessage()}");
}
