
<?php
error_reporting(0);
include 'db_config.php';
 
$name = $_GET['name'];
$email = $_GET['email'];
$password = $_GET['password'];
 
$sql = "INSERT INTO `harshad` (`name1`, `email`, `password1`) VALUES ('$name', '$email', '$password');";
//echo $sql;
 
if (mysqli_query($conn,$sql)) 
{
$msg = array("msg" => "inserted successfully");
} else {
echo "Error: " . $sql . "<br>" . mysqli_error($connection);
}
 
$json = $msg;
 
header('content-type: application/json');
echo json_encode($json);
 
 
@mysqli_close($conn);
 
?>