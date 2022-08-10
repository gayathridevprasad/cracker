<?php
	include("config.php");
	//$table=date('F_Y')."sales_invoice"; 
	$id=$_REQUEST['id'];
	
	$id = strtolower($id);
	
	
	
	    $r=mysql_query("select * from stock ",$con);

	
	while($row=mysql_fetch_array($r))
	{
		$flag['name1'][]=$row;
	//	echo $flag['name1'][];
	}
	 
	echo json_encode($flag);
	mysql_close($con);
?>