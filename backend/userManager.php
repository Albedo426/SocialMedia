<?php 

class UserManager extends dbconnection{
	public $printer;
	public function __construct(){
		parent::dbcon();
		$this->printer = new Printer();
	}
	
	public function userUpdate($userId, $name, $lastname,$email,$userName, $password,$phone, $profimg){
	
		$query = $this->db->prepare("UPDATE users SET
		name = ?,
		lastname = ?,
		email = ?,
		userName = ?,
		password = ?,
		phone = ?,
		profilePhoto = ?
		WHERE userID  = ? ");
		$update = $query->execute(array($name, $lastname, $email, $userName, $password, $phone, $profimg,$userId));
		if ( $update ){
		   $retkon = "1";
		}else{
			$retkon = "0";
		}
		echo $this->printer->boolConvertJson($retkon);

	}
	public function getForLogin($email, $password, $key){
		$row = $this->db->query("SELECT * FROM users WHERE email = '{$email}' and password = '{$password}'")->fetch(PDO::FETCH_ASSOC);
		if($row == null){
			$row['userID'] = 0;
		}else{
			
			$NotficationManager = new NotficationManager();
			$NotficationManager->setNotficatonChannel($row['userID'], $key);
		}
		echo $this->printer->getData($row,"users");
	}

	public function getFriendUser($id,$val){		
		$query = $this->db->query("SELECT *  FROM users,friends WHERE ( users.name LIKE '%{$val}%'  ) and (users.userID = friends.senderId or users.userID =friends.receiverId)  and  (users.userID != {$id}) and (friends.senderId={$id} or friends.receiverId={$id}) and (friends.confirmation=1) limit 10", PDO::FETCH_ASSOC);
			if ( $query->rowCount() ){
				echo $this->printer->getDatas($query,"users");	
			}
	}

	public function getUser($id){
			
		$query = $this->db->query("SELECT *  FROM users WHERE users.userID={$id}", PDO::FETCH_ASSOC);
		if ( $query->rowCount() ){
			echo $this->printer->getDatas($query,"users");	
		}
	}
	public function getUserImage($id){
			
		$query = $this->db->query("SELECT profilePhoto  FROM users WHERE userID={$id}", PDO::FETCH_ASSOC);
		if ( $query->rowCount() ){
			echo $this->printer->getDatas($query,"users");	
		}else{
			echo "test";
		}
	}


	public function onCreateUser($name, $lastName,$birthDate, $email, $userName, $password, $phone){
		$row = $this->db->prepare("INSERT INTO users SET 
			name = ?,
			lastName = ?,
			birthDate = ?,
			email = ?,
			userName = ?,
			password = ?,
			phone = ?");
		$insert = $row->execute(array($name, $lastName, $birthDate, $email, $userName, $password, $phone));
		if($insert){
			$retkon = "1";
		}else{
			$retkon = "0";
		}
		echo $this->printer->boolConvertJson($retkon);

	}

	public function isDuplicate($key,$value){
		$row = $this->db->query("SELECT * FROM users WHERE {$key} = '{$value}'")->fetch(PDO::FETCH_ASSOC);
		if($row){
			$retkon = "1";
		}else{
			$retkon = "0";
		}
		echo $this->printer->boolConvertJson($retkon);
	}

	public function friendStatus($senderId,$receiverId){
		$row = $this->db->query("SELECT confirmation FROM friends WHERE (friends.senderId = {$senderId} and friends.receiverId = {$receiverId}) or (friends.senderId = {$receiverId} and friends.receiverId = {$senderId})")->fetch(PDO::FETCH_ASSOC);
		if($row){
			echo $this->printer->getData($row,'friendStatus');
		}
	}

	public function getFriend($senderId,$receiverId){
		$query = $this->db->query("SELECT * FROM friends WHERE (receiverId = {$receiverId} and senderId = {$senderId}) or (senderId = {$receiverId} and receiverId = {$senderId})")->fetch(PDO::FETCH_ASSOC);
		return $query;
	}

	public function isFriend($senderId, $receiverId){

		$query = $this->db->query("SELECT * FROM friends WHERE (receiverId = {$receiverId} and senderId = {$senderId}) or (senderId = {$receiverId} and receiverId = {$senderId})")->fetch(PDO::FETCH_ASSOC);
		if($query){
			if($query['confirmation'] == "1"){
				$retkon = "0";
			}else{
				$retkon = $query['receiverId'];
			}
		}else{
			$retkon = "-1";
		}
		echo $this->printer->boolConvertJson($retkon);
	}

	public function pendingFriendRequest($senderId,$receiverId){
		if($this->getFriend($senderId,$receiverId)){
			$retkon = "0";
		}else{
			$row = $this->db->prepare("INSERT INTO friends SET
				senderId = ?,
				receiverId = ?,
				confirmation = ?");
			$insert = $row->execute(array($senderId,$receiverId,"0"));
			if($insert){
				$retkon = "1";
			}else{
				$retkon = "0";
		}
		}
		echo $this->printer->boolConvertJson($retkon);
	}
	public function addFriend($senderId,$receiverId){
		$query = $this->db->prepare("UPDATE friends SET
			confirmation = ?
			WHERE (receiverId = {$receiverId} and senderId = {$senderId}) or (senderId = {$receiverId} and receiverId = {$senderId})");
		$update = $query->execute(array("1"));
		if($update){
			$retkon = "1";
		}else{
			$retkon = "0";
		}
		echo $this->printer->boolConvertJson($retkon);
	}

	public function deleteFriend($senderId,$receiverId){
		$query = $this->db->exec("DELETE FROM friends WHERE (receiverId = {$receiverId} and senderId = {$senderId}) or (senderId = {$receiverId} and receiverId = {$senderId})");
		if($query){
			$retkon = "1";
		}else{
			$retkon = "0";
		}
		echo $this->printer->boolConvertJson($retkon);
	}

	public function getUserWithUserName($val){
		$query = $this->db->query("SELECT * FROM users WHERE ( users.name LIKE '%{$val}%' or users.userName LIKE '%{$val}%')",PDO::FETCH_ASSOC);
		if($query){
			echo $this->printer->getDatas($query,"users");
		}
	}

	public function getUserWithGameUserName($val){
		$query = $this->db->query("SELECT DISTINCT users.* FROM users,gtusers,playerfeatures WHERE (playerfeatures.gUserName LIKE '%{$val}%') and (playerfeatures.playerFeaturesID = gtusers.playerFeaturesID) and (users.userID = gtusers.userID)",PDO::FETCH_ASSOC);
		if($query){
			echo $this->printer->getDatas($query,"users");
		}
	}

	public function getFilteredUser($option1,$option2,$source1,$source2){
		$query = $this->db->query("SELECT users.* FROM users,playerfeatures,gtusers WHERE ((playerfeatures.opsiyon1 = '{$option1}' and playerfeatures.opsiyon2 = '{$option2}' and playerfeatures.source1 = '{$source1}' and playerfeatures.source2 = '{$source2}') and (users.userID = gtusers.userID) and (playerfeatures.playerFeaturesID = gtusers.playerFeaturesID))",PDO::FETCH_ASSOC);
		if($query){
			if($query->rowCount() == 0){
				echo $this->printer->getData(array("userID" => "0"),"users");
			}else{
				echo $this->printer->getDatas($query,"users");
			}
		}
	}
		// YUKARIDAKİ SORGUDA SOURCELARI BAŞA AL
	/*public function cancelFriendRequest($senderId,$receiverId){
	}*/

}
 ?>