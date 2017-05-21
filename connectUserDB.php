<?php
	require 'connect.php';
	$con=mysqli_connect("localhost","root","","userdb");
	$rs = $con->query("SELECT * FROM user ");
	$i=0;
	$output = '{"user":[';
	while($result = $rs->fetch_array(MYSQLI_ASSOC))
	{
		if(($output != '{') && $i !=0)
		{
			$output .= ",";
		}
		$output .= '{"userID":"'.$result["userID"].'",';
		$output .= '{"profileName":"'.$result["profileName"].'",';
		$output .= '{"profileImage":"'.$result["profileImage"].'",';
		$output .= '{"rating":"'.$result["rating"].'",';
		$output .= '{"userPlayer2ID":"'.$result["userPlayer2ID"].'",';
	}
	$output .="]}";
	
	$rating=$result['rating'];
	$sql="UPDATE user SET userID = '$rating');";
	if (mysqli_query($con,$sql)) {
		echo "Report has been sent successfully";
	}
	else{
		echo "Error reporting";
	}

	$con->close();
	echo($output);
?>