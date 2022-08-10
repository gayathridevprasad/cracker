<?php
	include("config.php");
	$radiochoice=$_REQUEST['radiochoice'];
	
	
	
	if($radiochoice=="Invoice")
	{
		$table=date('F_Y')."invoice";
		$table_sales=date('F_Y')."sales_invoice";
		
		
	}
	else
	{
		$table=date('F_Y')."profoma";
		$table_sales=date('F_Y')."sales_profoma";
		//$sales="profoma_sales";
	}
	
	//mysql_query("CREATE TABLE IF NOT EXISTS invoice_sales(invoice varchar(20),partyname varchar(100),date varchar(20),netamount varchar(50),PRIMARY KEY (invoice)) ",$con);
	
	mysql_query("CREATE TABLE IF NOT EXISTS invoice_sales(invoice varchar(20),name varchar(100),qty varchar(20),price varchar(50),per varchar(50),amount varchar(50)) ",$con);

	mysql_query("CREATE TABLE IF NOT EXISTS $table_sales(invoice varchar(20),partyname varchar(100),date varchar(20),netamount varchar(50),PRIMARY KEY (invoice)) ",$con);
	
	mysql_query("CREATE TABLE IF NOT EXISTS $table(invoice varchar(20),name varchar(100),qty varchar(20),price varchar(50),per varchar(50),amount varchar(50)) ",$con);
	$invoice=$_REQUEST['invoice'];
	$name=$_REQUEST['name'];
	$qty=$_REQUEST['qty'];
	$price=$_REQUEST['price'];
	$per=$_REQUEST['per'];
	$amount=$_REQUEST['amount'];
	$netamount=$_REQUEST['netamount'];
	$dtae=$_REQUEST['date'];
	$partyname1=$_REQUEST['partyname'];
	$availstock=$_REQUEST['availstock'];

	$flag['code']=0;
	
	mysql_query("UPDATE stock SET stock = '$availstock' WHERE name ='$name'",$con);
	mysql_query("insert into $table_sales values('$invoice','$partyname1','$dtae','$netamount') ",$con);
	mysql_query("insert into invoice_sales values('$invoice','$partyname1','$dtae','$netamount') ",$con);
	//mysql_query("insert into profoma_sales values('$invoice','$partyname1','$dtae','$netamount') ",$con);
	if($r=mysql_query("insert into $table values('$invoice','$name','$qty','$price','$per','$amount') ",$con))
	{
		$flag['code']=1;
		//echo"hi";
	}

	print(json_encode($flag));
	mysql_close($con);
?>

