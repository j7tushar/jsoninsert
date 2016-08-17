<?php 

$conn = mysqli_connect('localhost','root','','json_data');
  //$result = mysqli_query($conn,"INSERT INTO harshad(name1,email,password1) VALUES('".$name."','".$email."','".$password."')") or die(mysql_error());
	//echo $result;
	$q  = mysqli_query($conn,"select * from harshad");
	
	while($res= mysqli_fetch_assoc($q));
	{
	echo $res[0];
	echo $res[1];
	echo $res[2];
	echo $res[3];
	}

echo "hello";
?>