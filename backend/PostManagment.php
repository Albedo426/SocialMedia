<?php 

class PostManagment  extends dbconnection
	{
		public $printer;
		public function __construct(){
			parent::dbcon();
			$this->printer = new Printer();
		}
		public 	function getPostData($postId){
			$query = $this->db->query("SELECT fileId,url,type from postdatas,files where postdatas.filesId=files.fileId and postdatas.postId={$postId}", PDO::FETCH_ASSOC);
				if ( $query ){
					return $this->printer->getDatas($query,"files");
				}
		}
		public 	function fileupload(array $format,$limit,$file,$type){
			$_FILES = $file;
						
			$upf=new Fileuploader($format,$limit);
			$imgnamer= $upf->imgupload($file);
			$responsee["files"]= array();
			$filee= array();
			$filee['fileId']="0";
			$filee['url']="0";
			$filee['type']="0";
					
			if($imgnamer== "1" || $imgnamer== "2" ){
				$filee['fileId']=$imgnamer;
			}else {
				//dosya yüklenmiş $imgnamerın içince  dosya adı var
				$nameimg=$imgnamer;
				$imgnamer=$upf->insertsql($imgnamer,$type);
				$filee= array();
				$filee['fileId']=$imgnamer;
				$filee['url']=$nameimg;
				$filee['type']=$type;
					
			}	
			array_push($responsee["files"], $filee);

			return json_encode($responsee);
		}
		public function filedelete($fileId,$fileName){
			$ar=array();
			$upf=new Fileuploader($ar,0);
			return $upf->imgdelete($fileId,$fileName);
		}
		public function getFriendsPost($userId,$limit){
			$query = $this->db->query("SELECT posts.postId,posts.value,posts.dataIsNull,posts.createDate ,users.userId,users.name,users.lastName,users.profilePhoto, (SELECT COUNT(*) from likes where likes.postId=posts.postId) as 'likeCount',(SELECT COUNT(*) from comments where comments.postId=posts.postId) as 'commentCount',(SELECT COUNT(*) from likes where likes.postId=posts.postId and likes.userId={$userId}) as 'didIslike' from posts,friends,users where posts.userId=users.userID and (posts.userId=friends.senderId or posts.userId=friends.receiverId ) and (friends.senderId={$userId} or friends.receiverId ={$userId} ) and confirmation=1 and posts.userId!={$userId} ORDER by posts.createDate DESC limit {$limit}", PDO::FETCH_ASSOC);
			if ( $query ){
				echo $this->printer->getDatas($query,"posts");	
			}
		}
		public function getPostForId($userId,$limit){
			$query = $this->db->query("SELECT posts.postId,posts.value,posts.dataIsNull,posts.createDate ,users.userId,users.name,users.lastName,users.profilePhoto, (SELECT COUNT(*) from likes where likes.postId=posts.postId) as 'likeCount',(SELECT COUNT(*) from comments where comments.postId=posts.postId) as 'commentCount',(SELECT COUNT(*) from likes where likes.postId=posts.postId and likes.userId={$userId}) as 'didIslike' from posts,users where posts.userId=users.userID and posts.userId={$userId} ORDER by posts.createDate DESC limit {$limit}", PDO::FETCH_ASSOC);
			if ( $query ){
				echo $this->printer->getDatas($query,"posts");	
			}
		}

		public function getlikes($postId,$limit){
			$query = $this->db->query("SELECT users.userID,users.name,users.lastName FROM likes,posts,users WHERE likes.postId=posts.postId and users.userID=likes.userId and posts.postId={$postId} ORDER by likes.likeId DESC limit {$limit}", PDO::FETCH_ASSOC);
			if ( $query ){
				echo $this->printer->getDatas($query,"likes");	
			}
		}
		public function likeControlSet($postId,$userId){

			$query = $this->db->query("SELECT * FROM likes WHERE  postId={$postId} and userId	={$userId}", PDO::FETCH_ASSOC);

			if ( ($count=$query->rowCount()) ){
				$this->deletelike($postId,$userId);
				return $this->printer->boolConvertJson("-1");
			}else{
				$this->addlike($postId,$userId);
				return $this->printer->boolConvertJson("1");

			}
		}
		public function addlike($postId,$userId){
			$query = $this->db->prepare("INSERT INTO likes SET userId = ?, postId = ?");
			$insert = $query->execute(array($userId,$postId));
			if ( $insert ){
				$retkon=0;
			}else{
				$retkon=1;
			}
			return $this->printer->boolConvertJson($retkon);
		}
		public function deletelike($postId,$userId){
			$query = $this->db->prepare("DELETE FROM likes WHERE userId = ? and postId = ? ");
			$delete = $query->execute(array($userId,$postId));
			if ( $delete ){
				$retkon=0;
			}else{
				$retkon=1;
			}
			return $this->printer->boolConvertJson($retkon);
		}
		public function getComments($postId,$limit,$isSubComment){
			if($isSubComment){
				$query = $this->db->query("SELECT com.commentsId,users.userID,users.name,users.lastName,users.profilePhoto, com.value,com.date,com.isSubComment,com.postId , (SELECT count(commentsId) from comments where comments.postId=com.commentsId and comments.isSubComment=1) as 'subCommentCount' FROM comments as com ,posts,users WHERE com.postId=posts.postId  and com.isSubComment=0  and  users.userID=com.userId and posts.postId={$postId}  ORDER by com.commentsId DESC limit {$limit}", PDO::FETCH_ASSOC);
			}else{
				$query = $this->db->query("SELECT  com.commentsId,users.userID,users.name,users.lastName,users.profilePhoto, com.value,com.date,com.isSubComment,com.postId , (SELECT count(commentsId) from comments where comments.postId=com.commentsId and comments.isSubComment=1) as 'subCommentCount' FROM comments as com ,users WHERE com.isSubComment=1 and users.userID=com.userId and com.postId={$postId} ORDER by com.commentsId DESC limit {$limit}", PDO::FETCH_ASSOC);
			}
			
			if ( $query ){
				return $this->printer->getDatas($query,"comments");	
			}
		}

		public function addComments($userId,$value,$isSubComment,$postId){
			$query = $this->db->prepare("INSERT INTO comments SET userId = ?, value = ?,isSubComment = ?, postId = ?");
			$insert = $query->execute(array($userId,$value,$isSubComment,$postId));
			if ( $insert ){
				$retkon=0;
			}else{
				$retkon=1;
			}
			return $this->printer->boolConvertJson($retkon);
		}
		public function deleteComments($postId,$userId){
			$query = $db->prepare("DELETE FROM comments WHERE userId = ? and postId = ? ");
			$delete = $query->execute(array($userId,$postId));
			if ( $delete ){
				$retkon=0;
			}else{
				$retkon=1;
			}
			return $this->printer->boolConvertJson($retkon);
		}
		public 	function postSend($text,$userId,$dataIsNull,$filesId){
			$controller="1";
				$query = $this->db->prepare("INSERT INTO posts (value, userId, dataIsNull) VALUES (?,?,?)");
				$this->db->beginTransaction();
				$insert = $query->execute(array($text,$userId,$dataIsNull,));
				if ( $insert ){

					$last_id = $this->db->lastInsertId();
					$this->db->commit();
					if($dataIsNull==0){//reism varsa
						$controller=$this->connectImage($last_id,$filesId);
						if($controller=="1"){
							$this->db->rollback();
						}
					}
					$controller="0";
				}else{
					$controller="1";
				}	
				$retkon=$controller;
				return $this->printer->boolConvertJson($retkon);
				//resim yok
		}
		public function connectImage($postId, $filesId){
			$controller="0";
			$query = $this->db->prepare("INSERT INTO postdatas (postId, filesId) VALUES (?,?)");
			try {

				$this->db->beginTransaction();//şuna göre düzenlencek
				for ($i=0; $i < count($filesId); $i++) { 
					$data = [
						$postId,$filesId[$i]
					];
					$query->execute($data);
				}
				$this->db->commit();
			} catch (Exception $e) {
					$controller="1";
			}
			return $controller;
			
		}

	}
		

	
?>