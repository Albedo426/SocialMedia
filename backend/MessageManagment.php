<?php 

class MessageManagment  extends dbconnection
	{
		public $lastindextemp1;
		public $lastindextemp2;
		public $printer;
		public function __construct(){
			parent::dbcon();
			$this->printer = new Printer();
		}
		public function lastMessage($id,$trgid){
			$query = $this->db->query("SELECT  * FROM messages WHERE senderID='{$id}' and receiverID='{$trgid}' or senderID='{$trgid}' and receiverID='{$id}'  ORDER BY messagesId DESC ")->fetch(PDO::FETCH_ASSOC);
			if ( $query ){
				return $this->printer->getData($query,"message");	
			}
		}
		
		public function lastMessageControl($id,$trgid,$messagesId){
			$query = $this->db->query("SELECT  * FROM messages WHERE senderID='{$id}' and receiverID='{$trgid}' or senderID='{$trgid}' and receiverID='{$id} ' order by  sendDate DESC limit 1")->fetch(PDO::FETCH_ASSOC);
			if ( $query ){
					if($query['messagesId']==$messagesId){
				   		$data="1"; 
					}else{
				   		$data="0"; 
				    }
				    //veri var
				}else{
				   $data="2"; 
				    //veri yok
			}	
			return $this->printer->boolConvertJson($data);
		}
		public function getimgUrl($id){
			$query = $this->db->query("SELECT * FROM messsagedata WHERE messageId='{$id}' ")->fetch(PDO::FETCH_ASSOC);
				if ( $query ){
					$retkon=$query["url"];

				}else{
				    $retkon="0";
				}
				return $this->printer->boolConvertJson($retkon);	

		}
		public function getChat($id,$trgid,$limit){

			$query = $this->db->query("SELECT *, (SELECT users.profilePhoto  from users where users.userID='{$id}') as 'SenderImage' , (SELECT users.profilePhoto  from users where users.userID='{$trgid}') as 'reciverImage'  FROM (SELECT * FROM messages WHERE senderID='{$id}' and receiverID='{$trgid}' or senderID='{$trgid}' and receiverID='{$id}' ORDER BY messagesId DESC LIMIT {$limit}) sub order by messagesId asc", PDO::FETCH_ASSOC);
			if ( $query ){
				echo $this->printer->getDatas($query,"message");	
			}
		}
		public function pushNotfication($senderID,$receiverID){
			$NotficationManager = new NotficationManager();
			$receiverValue=$NotficationManager->getUserKeyForId($receiverID);
			$senderValue=$NotficationManager->getUserKeyForId($senderID);
			$NotficationManager->sendNotfication($receiverValue['token'],$senderValue['name'].$senderValue['lastName'],"mesajınız var",$senderValue['userId']);
		}
		public function addmessage($senderID,$receiverID,$value,$type,$vis){

			$query = $this->db->prepare("INSERT INTO messages SET
			senderID = ?, receiverID = ?, value = ?, type = ? ,vis = ?");
			$insert = $query->execute(array($senderID,$receiverID,$value,$type,$vis));
			if ( $insert ){
				$last_id = $this->db->lastInsertId();
				$bubM=new BubbleManagment();
				$contr=$bubM->updateBubble($last_id,$senderID,$receiverID);
				$this->lastindextemp1=$last_id;
				$this->lastindextemp2=$bubM->lastindextemp1;
			    if($contr==0){
			    	$this->deletemessage($last_id);
			    }
			    $this->pushNotfication($senderID,$receiverID);
				$retkon=$contr;
				return $this->printer->boolConvertJson($retkon);
			}else{
				return "asd";
			}
		}
		public function newaddmessage($senderID,$receiverID,$value,$type,$vis){

			$query = $this->db->prepare("INSERT INTO messages SET
			senderID = ?, receiverID = ?, value = ?, type = ? ,vis = ?");
			$insert = $query->execute(array($senderID,$receiverID,$value,$type,$vis));
			if ( $insert ){
				$last_id = $this->db->lastInsertId();
				$bubM=new BubbleManagment();
				$contr=$bubM->addBubble($last_id,$senderID,$receiverID);
				$this->lastindextemp1=$last_id;
				$this->lastindextemp2=$bubM->lastindextemp1;
			    $retkon= array();
			    if($contr==0){
			    	$this->deletemessage($last_id);
			    }
			    $this->pushNotfication($senderID,$receiverID);
				$retkon=$contr;
				return $this->printer->boolConvertJson($retkon);
			}
		}
		public function deletemessage($Id){
				$query = $this->db->prepare("DELETE FROM messages WHERE messageId = :id");
						$delete = $query->execute(array(
						   'id' => $Id
				));	
		}

		public function sendMessageImg($senderID,$receiverID,$value,$type,$vis,$file, array $format,$limit){
			$_FILES = $file;
			$arrayVar = explode(".", $_FILES['file']['name']);
			$extension = end($arrayVar);
			$file_ext=strtolower($extension);
			$extensions= $format;
			$file_size =$_FILES['file']['size'];
			if(in_array($file_ext,$extensions) and $file_size <= $limit){
			$result=$this->addmessage($senderID,$receiverID,$value,$type,$vis);
				if(strcmp ($result[8], "1" ) ){//burda yazan 8 bir değerine geldiği için 
					$upf=new Fileuploader($format,$limit);
					$imgcontroller= $upf->imgupload($file);
					if($imgcontroller== "1"  ){
						$this->deletemessage($this->lastindextemp1);

					}else if($imgcontroller =="2"  ){
						$this->deletemessage($this->lastindextemp1);
					
					}else{
						$query = $this->db->prepare("INSERT INTO messsagedata SET
									url = :url,
									messageId	 = :messageId");
									$insert = $query->execute(array(
									      "url" => $imgcontroller,
									      "messageId" => $this->lastindextemp1,
									));
									if ( $insert ){
										 $this->pushNotfication($senderID,$receiverID);
							        	$imgcontroller="0";
									}else{
										$imgcontroller="3";
									}	
					}
				    $retkon=$imgcontroller;
				}else{
				    $retkon="4";
				}
			}else{
				    $retkon="5";
			}
			return $this->printer->boolConvertJson($retkon);
		}

		
	}



	
?>