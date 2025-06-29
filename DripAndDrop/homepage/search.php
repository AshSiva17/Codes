<?php
include "../config/connect.php";

$text = filter_input(INPUT_GET,"text",FILTER_SANITIZE_SPECIAL_CHARS);
$length = filter_input(INPUT_GET,"len",FILTER_VALIDATE_INT);

$command = "SELECT `title` FROM `listings` WHERE `title` LIKE ?";
$stmt = $dbh ->prepare($command);

$args = [$text.'%'];
$stmt->execute($args);

$names = [];
while ($row = $stmt->fetch()) {
    $name = [ "title" => $row["title"]];
    array_push($names, $name);
}

echo json_encode($names);

?>