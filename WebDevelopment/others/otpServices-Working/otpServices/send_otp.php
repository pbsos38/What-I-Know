<?php
session_start();
$con=mysqli_connect('localhost','root','','test');
$email=$_POST['email'];

$res=mysqli_query($con,"select * from user where email='$email'");
$count=mysqli_num_rows($res);

if($count>0){
	$otp=rand(11111,99999);
	mysqli_query($con,"update user set otp='$otp' where email='$email'");
    
    $html="Your otp verification code is ".$otp;
	$_SESSION['EMAIL']=$email;
	smtp_mailer($email,'OTP Verification',$html);
    echo "yes";
	
        }
else
{
	//echo "not_exist";
    
    mysqli_query($con,"insert into user values('$email','$otp')");
    $msg  = mysqli_error($con);
    echo $msg;
    
    $otp=rand(11111,99999);
	mysqli_query($con,"update user set otp='$otp' where email='$email'");
    
    $html="Your otp verification code is ".$otp;
	$_SESSION['EMAIL']=$email;
	smtp_mailer($email,'OTP Verification',$html);
    echo "yes";
}

function smtp_mailer($to,$subject, $msg){
	require_once("smtp/class.phpmailer.php");
	$mail = new PHPMailer(); 
	$mail->IsSMTP(); 
	$mail->SMTPDebug = 1; 
	$mail->SMTPAuth = true; 
	$mail->SMTPSecure = 'TLS'; 
	$mail->Host = "smtp.sendgrid.net";
	$mail->Port = 587; 
	$mail->IsHTML(true);
	$mail->CharSet = 'UTF-8';
	$mail->Username = "pbsos38";
	$mail->Password = "sendgrid.com1";
	$mail->SetFrom("pbsos38@gmail.com");
	$mail->Subject = $subject;
	$mail->Body =$msg;
	$mail->AddAddress($to);
	if(!$mail->Send()){
		return 0;
	}else{
		return 1;
	}
}
?>