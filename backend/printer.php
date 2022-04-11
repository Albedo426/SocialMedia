<?php 
class Printer 
	{
		public function __construct() {}

		public function getDatas($query,$name){
			$list[$name]= array();
			foreach( $query as $row ){
				array_push($list[$name],  $row);
			}
			return json_encode($list);
		}

		public function getData($row,$name){
			$list[$name]= array();
			array_push($list[$name],  $row);
			echo json_encode($list);
		}

		public function boolConvertJson($bool){
 			$retkon= array();
			$retkon['control']=$bool;
			return json_encode($retkon);
		}

		public function getTypeSource($type1,$source1,$type2,$source2){
			$list['sources'] = array();
			if($type1 != ""){
				$response['type'] = $type1;
				$response['source'] = $source1."";
				array_push($list['sources'], $response);
			}
			if($type2 != ""){
				$response['type'] = $type2;
				$response['source'] = $source2."";
				array_push($list['sources'], $response);
			}
			return json_encode($list);
		}
		public function defauldConvertJson($data){
			return json_encode($data);
		}
		public function getOptions($option1,$option2){
			$list['options'] = array();
			$response['option1'] = array();
			$response['option2'] = array();
			foreach($option1 as $row){
				array_push($response['option1'],$row);
			}
			foreach ($option2 as $row) {
				array_push($response['option2'],$row);
			}
			array_push($list['options'],$response);
			return json_encode($list);
		}

		public function getBubblesModfMessage($query){
			$uslist["bubbles"]= array();
			foreach( $query as $row ){
				$bubbles= array();
				$bubbles['chatBubbleId']=$row['bubbleId'];
				$bubbles['senderId']=$row['senderId'];
				$bubbles['receiverId']=$row['receiverId'];
				$bubbles['sendDate']=$row['sendDate'];
				$bubbles['messagesId']=$row['messagesId'];
				$bubbles['value']=$row['value'];
				$bubbles['type']=$row['type'];
				array_push($uslist["bubbles"], $bubbles);
			}
			echo json_encode($uslist);	
		}
		
	}
?>