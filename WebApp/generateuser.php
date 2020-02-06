<?php 

$username = "chengdu-research";
$password = 'Basi37!';

$dir = "/samba/weerstation_share/users/";

file_put_contents($dir . $username, password_hash($password, PASSWORD_DEFAULT));

?>
