<?php 
class BubbleManagment extends dbconnection
	{
		public $lastindextemp1;
		public $printer;
		public function __construct(){
			parent::dbcon();
			$this->printer = new Printer();
		}

		public function getBubblesModifine($id){
			
			$query = $this->db->query("SELECT 
				users.name,
				users.lastName ,
				 bubbleId  , 
				 bubbles.senderId , 
				 bubbles.receiverId,
				  sendDate,messagesId ,value,type ,
				  users.profilePhoto 
				  FROM bubbles,messages,users WHERE bubbles.senderId = '{$id}'  and messages.messagesId =bubbles.messageId and users.userID =bubbles.receiverId order by messages.sendDate DESC ", PDO::FETCH_ASSOC);
			if ( $query->rowCount()){
				echo $this->printer->getDatas($query,"bubbles");	
			}
		}

		public function deletebubble($id){
			$query = $db->prepare("DELETE FROM bubbles WHERE bubbleId = :id");
								$delete = $query->execute(array(
								   'id' => $id
								));	
		}

		public function updateBubble($last_id ,$senderID,$receiverID){
			$query = $this->db->prepare("UPDATE bubbles SET
					messageId = :newId
					WHERE senderID=:senderID and receiverID=:receiverID or senderID=:receiverID and receiverID=:senderID");
					$update = $query->execute(array(
					     "newId" => $last_id ,
					     "senderID" =>$senderID,
					     "receiverID" => $receiverID
					));
					if ( $update ){
						$last_id = $this->db->lastInsertId();
						$this->lastindextemp1=$last_id;
					    return 1;
					}else{
						return 0;
					}
		}

		public function addBubble($last_id ,$senderID,$receiverID){

				$retkon= array();
			    $query = $this->db->prepare("INSERT INTO bubbles SET
				senderId = :senderID,
				receiverId = :receiverID,
				messageId = :messageId;
				INSERT INTO bubbles SET
				senderId = :receiverID,
				receiverId = :senderID,
				messageId = :messageId");
				$insert = $query->execute(array(
				      "senderID" => $senderID,
				      "receiverID" => $receiverID,
				      "messageId" => $last_id,
				));
					if ( $insert ){
					    return 1;
					}else{
						
						return 0;
					}
		}
		
		public function bubblesControl($id,$trgid,$msgId){
			$query = $this->db->query("SELECT  * FROM bubbles,messages WHERE bubbles.senderID={$id}   and messages.messagesId=bubbles.messageId order by  messages.sendDate DESC limit 1")->fetch(PDO::FETCH_ASSOC);
				if ( $query ){
					if($query['messagesId']==$msgId){
				    	$retkon="1";
					}else{
					    $retkon="0";
				    }
				}else{
				    $retkon="0";
				}	
				return $this->printer->boolConvertJson($retkon);	

		}
		
	}
?>