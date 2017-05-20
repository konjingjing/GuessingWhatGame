<?php
	$con=mysqli_connect("localhost","root","","reportdb");
	$type=$_POST['type'];
	$detail=$_POST['detail'];
	$reporter=$_POST['reporter'];
	$reported=$_POST['reported'];
	$sql="INSERT INTO report (type, detail, reporter, reported) VALUES ('$type', '$detail','$reporter','$reported');";
	if (mysqli_query($con,$sql)) {
		echo "Report has been sent successfully";
	}
	else{
		echo "Error reporting";
	}
	$con->close();
?>