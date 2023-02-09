<?php
$connect = new PDO("mysql:host=localhost;dbname=testing", "root", "");

$id = $_POST["id"];

if(isset($id))
{
    $connect->exec(" DELETE from events WHERE id='$id' ");
 
}

?>
