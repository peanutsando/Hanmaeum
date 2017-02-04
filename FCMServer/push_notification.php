<?php

// request FCM
function send_notification ($tokens, $message)
{
	
	$url = 'https://fcm.googleapis.com/fcm/send';
	$fields = array(
			'registration_ids' => $tokens,
			'data' => $message
	);

	$headers = array(
			'Authorization:key =' . GOOGLE_API_KEY,
			'Content-Type: application/json'
	);

	$ch = curl_init();
	curl_setopt($ch, CURLOPT_URL, $url);
	curl_setopt($ch, CURLOPT_POST, true);
	curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	curl_setopt ($ch, CURLOPT_SSL_VERIFYHOST, 0);
	curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
	curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
	
	$result = curl_exec($ch);
	
	if ($result === FALSE) {
		die('Curl failed: ' . curl_error($ch));
	}
	
	curl_close($ch);
	
	return $result;
}

if(isset($_POST["Message"])){

	$msg = $_POST["Message"];
	

	// Connect database and get tokens and then call send_notification method
	
	include_once 'config.php';
	$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME);
	
	$sql = "Select Token From users";
	
	$result = mysqli_query($conn,$sql);
	$tokens = array();
	
	if(mysqli_num_rows($result) > 0 ){
	
		while ($row = mysqli_fetch_assoc($result)) {
			$tokens[] = $row["Token"];
		}
	}
	
	mysqli_close($conn);
	
	$message = array("message" => "$msg");
	$message_status = send_notification($tokens, $message);
	echo $message_status;
}

?>