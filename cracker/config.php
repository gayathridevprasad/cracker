<?php
$host='127.0.0.1';
	$uname='root';
	$pwd='root';
	$db="billing";

	$con = mysql_connect($host,$uname,$pwd) or die("connection failed");
	mysql_select_db($db,$con) or die("db selection failed");
	
	?>