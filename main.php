<?php  
error_reporting(E_ALL); 
ini_set('display_errors',1); 

$link=mysqli_connect("localhost","root","sj12sj!2*4@", "parkingmanagement" );  
if (!$link)  
{  
    echo "MySQL 접속 에러(서버 작동X)";
    //echo mysqli_connect_error();
    exit();  
}  

mysqli_set_charset($link,"utf8"); 

// $sql="select * from details_of_usage where ID='root'";
// $result=mysqli_query($link,$sql);
// $data = array(); 
// if($result)
// {
	// $row_count = mysqli_num_rows($result);
	
	// if ( 0 == $row_count ){
	// }
	// else{
		// //echo "usagelist";
		// while($row=mysqli_fetch_array($result)){
			// array_push($data, 
				// array('CheckIn'=>$row["CheckIn"],
				// 'CheckOut'=>$row["CheckOut"],
				// 'Place'=>$row["Place"]
			// ));
		// }
			// //header('Content-Type: application/json; charset=utf8');
			// $json = json_encode(array("jaegook"=>$data));
			// echo $json;
	// }
// }
	
	
// mysqli_free_result($result);

$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

if(!$android){
	echo "서버 정상 작동중";
}
else{
	//POST 값을 읽어온다.
	//$country=isset($_POST['country']) ? $_POST['country'] : '';
	$task=isset($_POST['task']) ? $_POST['task'] : '';
	
	if($task=="LoginCheck")
	{
		$id=isset($_POST['id']) ? $_POST['id'] : '';
		$pw=isset($_POST['pw']) ? $_POST['pw'] : '';
		
		$sql="select PW from customer where ID='$id'";
		$result=mysqli_query($link,$sql);		
		
		
		
		if($result)
		{
			$row=mysqli_fetch_array($result);
			$row_count = mysqli_num_rows($result);
			
			$pwcv=$row["PW"];
			
			if($pwcv==$pw)
			{
				echo "login_OK";
			}
			else{
				echo "login_Cancel";
			}
			mysqli_free_result($result);
		}
		else
		{
			echo "에러 났다.";
		}
	}
	else if($task=="overlapCheck")
	{
		$id=isset($_POST['id']) ? $_POST['id'] : '';
		
		$sql="select * from customer where ID='$id'";
		$result=mysqli_query($link,$sql);

		if($result)
		{
			$row_count = mysqli_num_rows($result);
			if(0 == $row_count)
			{
				echo "overlapCheck_OK";
			}
			else
			{
				echo "overlapCheck_Cancel";
			}
			mysqli_free_result($result);
		}
		else
		{
			echo "에러 났다.";
		}
		//$sql="select * from Person where address='$country'";
		//$result=mysqli_query($link,$sql);
	}
	else if($task=="signup")
	{
		$id=isset($_POST['id']) ? $_POST['id'] : '';
		$pw=isset($_POST['pw']) ? $_POST['pw'] : '';
		$name=isset($_POST['name']) ? $_POST['name'] : '';
		$email=isset($_POST['email']) ? $_POST['email'] : '';
		$pn=isset($_POST['pn']) ? $_POST['pn'] : '';
		
		$sql="insert into customer(ID,PW,Name,Email,PhoneNumber) values('$id','$pw','$name','$email','$pn')";
		$result=mysqli_query($link,$sql);		
		
		
		
		
		if($result)
		{
			//$row_count = mysqli_num_rows($result);
			echo "signup_OK";
			mysqli_free_result($result);
		}
		else
		{
			echo "signup_Cancel";
		}
		//$sql="select * from Person where address='$country'";
		//$result=mysqli_query($link,$sql);
	}
	else if($task=="usage")
	{
		$id=isset($_POST['id']) ? $_POST['id'] : '';
		
		$sql="select * from details_of_usage where ID='$id'";
		$result=mysqli_query($link,$sql);
		
		$data = array(); 
		
		if($result)
		{
			$row_count = mysqli_num_rows($result);
			
			if ( 0 == $row_count ){
			}
			else{
				//echo "usagelist";
				while($row=mysqli_fetch_array($result)){
					array_push($data, 
						array('CheckIn'=>$row["CheckIn"],
						'CheckOut'=>$row["CheckOut"],
						'Place'=>$row["Place"]
					));
				}
					//header('Content-Type: application/json; charset=utf8');
					$json = json_encode(array("usagelist"=>$data));
					echo $json;
			}
			
			
			mysqli_free_result($result);
		}
		else
		{
			echo "에러 났다.";
		}

	}
	else if($task=="notify_usinglot")
	{
		$place=isset($_POST['place']) ? $_POST['place'] : '';
		
		$sql="select no from beacon where place='$place' && status='using'";
		
		$result=mysqli_query($link,$sql);
		
		$data = array();
		
		if($result)
		{
			$row_count = mysqli_num_rows($result);
			
			if ( 0 == $row_count ){
			}
			else{
				while($row=mysqli_fetch_array($result)){
					array_push($data, 
						array('no'=>$row["no"]
					));
				}
					//header('Content-Type: application/json; charset=utf8');
					$json = json_encode(array("usinglotIs"=>$data));
					echo $json;
			}
			mysqli_free_result($result);
		}
		else
		{
			echo "에러 났다.";
		}

	}
	else if($task=="simple_parking")
	{
		$id=isset($_POST['id']) ? $_POST['id'] : '';
		
		$sql="select * from current_using where ID='$id'";
		$result=mysqli_query($link,$sql);
		
		$row=mysqli_fetch_array($result);

		if($result)
		{
			$row_count = mysqli_num_rows($result);
			if(0 == $row_count)
			{
				echo "simple_parking_OK";
			}
			else
			{
				echo "simple_parking_Cancel";
				echo "#";
				echo $row["place"];
				echo "#";
				echo $row["beacon_no"];
				echo "#";
				echo $row["check_in"];
			}
			mysqli_free_result($result);
		}
		else
		{
			echo "에러 났다.";
		}
	}
	else if($task=="check_in_able")
	{
		$minor=isset($_POST['minor']) ? $_POST['minor'] : '';
		
		$sql="select * from beacon where minor='$minor'";
		
		$result=mysqli_query($link,$sql);
		
		$row=mysqli_fetch_array($result);

		if($result)
		{
			$row_count = mysqli_num_rows($result);
			if(0 == $row_count)
			{
				echo "check_in_able_cancel";
			}
			else
			{
				if($row["status"]=="wait")
				{
					echo "check_in_able_true";
					echo "#";
					echo $row["place"];
					echo "#";
					echo $row["no"];
				}
				else if($row["status"]=="using")
				{
					echo "check_in_able_false";
					echo "#";
					echo $row["place"];
					echo "#";
					echo $row["no"];
				}
			}
			mysqli_free_result($result);
		}
		else
		{
			echo "에러 났다.";
		}
	}
	else if($task=="check_in_doing")
	{
		$minor=isset($_POST['minor']) ? $_POST['minor'] : '';
		$id=isset($_POST['id']) ? $_POST['id'] : '';
		
		$sql="update beacon set status='using' where minor='$minor'";
		
		mysqli_query($link,$sql);
		
		
		$sql="select * from beacon where minor='$minor'";
		
		$result=mysqli_query($link,$sql);
		
		$row=mysqli_fetch_array($result);
		
		$place = $row["place"];
		$beacon_no = $row["no"];
		
		mysqli_free_result($result);
		
		$now = new DateTime('now');
		$date = $now->format('Y-m-d H:i:s');
		
		$sql="insert into current_using(id,place,beacon_no,check_in) values('$id','$place','$beacon_no','$date')";
		$result = mysqli_query($link,$sql);
		
		
		if($result)
		{		
			echo "check_in_doing_success";
			echo "#";
			echo $place;
			echo "#";
			echo $beacon_no;
			
		}
		else
		{
			echo "에러 났다.";
		}
	}
	else if($task=="check_out_doing")
	{
		$id=isset($_POST['id']) ? $_POST['id'] : '';
		
		$sql="select * from current_using where id='$id'";
		$result=mysqli_query($link,$sql);
		$row=mysqli_fetch_array($result);
		
		$place=$row["place"];
		$no=$row["beacon_no"];
		$c_in=$row["check_in"];
		
		mysqli_free_result($result);
		
		$sql="delete from current_using where id='$id'";
		mysqli_query($link,$sql);
		
		$sql="update beacon set status='wait' where place='$place' && no='$no'";
		mysqli_query($link,$sql);
		
		$now = new DateTime('now');
		$date = $now->format('Y-m-d H:i:s');
		
		$sql="insert into details_of_usage(id,CheckIn,CheckOut,Place) values('$id','$c_in','$date','$place')";
		$result = mysqli_query($link,$sql);
		
		
		
		if($result)
		{
			echo "check_out_doing_success";
			mysqli_free_result($result);
		}
		else
		{
			echo "에러 났다.";
		}
	}
}
mysqli_close($link);  
?>
