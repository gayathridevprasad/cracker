
<?php
	include("config.php");
	
	$id=$_REQUEST['id'];
	
	
	
	$r=mysql_query("select * from invoice_sales",$con);
	

	while($row=mysql_fetch_array($r))
	{
		$flag['name1'][]=$row;
	//	echo $flag['name1'][];
	}
	 
	echo json_encode($flag);
	mysql_close($con);
?>