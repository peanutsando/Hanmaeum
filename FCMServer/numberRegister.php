<?php

if(isset($_POST["Number"])){

	$number = $_POST["Number"];
	//데이터베이스에 접속해서 토큰을 저장
	include_once 'config.php';
	$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME);
	$query = "Insert into notice values('$number')";
	
	mysqli_query($conn, $query);

	mysqli_close($conn);
}

?>