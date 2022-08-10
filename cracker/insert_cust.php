<?php
	include("config.php");
	
	$cname=$_REQUEST['cname'];
	$caddress=$_REQUEST['caddress'];
	$ccity=$_REQUEST['ccity'];
	$ccode=$_REQUEST['ccode'];
	
	$flag['code']=0;
	
	
	
	if($r=mysql_query("insert into cust values('$cname','$caddress','$ccity','$ccode') ",$con))
	{
		$flag['code']=1;
		//echo"hi";
	}

	print(json_encode($flag));
	mysql_close($con);
?>

