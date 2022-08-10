<?php
	include("config.php");
	$radiochoice=$_REQUEST['radiochoice'];
	if($radiochoice=="Invoice")
	{
		$table=date('F_Y')."invoice";
		//$table_sales=date('M_Y')."sales_invoice";
		
	}
	else
	{
		$table=date('F_Y')."profoma";
		//$table_sales=date('M_Y')."sales_profoma";
	}
	$id=$_REQUEST['id'];
	
	
	
	$r=mysql_query("select * from $table where invoice ='$id'",$con);

	while($row=mysql_fetch_array($r))
	{
		$flag['name1'][]=$row;
	//	echo $flag['name1'][];
	}
	 
	echo json_encode($flag);
	mysql_close($con);
?>