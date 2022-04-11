<?php 

class PlayerFeaturesManager extends dbconnection{
	
	public $printer;
	public function __construct(){
		parent::dbcon();
		$this->printer = new Printer();
	}

	public function playerFeaturesControl($gameID,$gUserName){
		$row =  $this->db->query("SELECT * FROM playerfeatures WHERE gameID = '{$gameID}' and gUserName = '{$gUserName}'")->fetch(PDO::FETCH_ASSOC);
		if($row){
			$retkon = "0";
		}else{
			$retkon = "1";
		}

		echo $this->printer->boolConvertJson($retkon);
	}

	public function insertPlayerFeatures($option1,$option2,$about,$gameID,$gUserName,$source1,$source2){
		$row = $this->db->prepare("INSERT INTO playerfeatures SET
			opsiyon1 = ?,
			opsiyon2= ?,
			about = ?,
			gameID = ?,
			gUserName = ?,
			source1 = ?,
			source2 = ?");
		$insert = $row->execute(array($option1,$option2,$about,$gameID,$gUserName,$source1,$source2));
		if($insert){
			$retkon = "1";
		}else{
			$retkon = "0";
		}
		echo $this->printer->boolConvertJson($retkon);
	}

	public function getPlayerFeaturesWithUserID($userID){
		$row = $this->db->query("SELECT playerfeatures.opsiyon1, playerfeatures.opsiyon2, playerfeatures.about, playerfeatures.gUserName, games.gameName, games.gameImage,playerfeatures.source1, playerfeatures.source2 FROM playerfeatures,gtusers,games WHERE gtusers.userID = '{$userID}' and gtusers.playerFeaturesID = playerfeatures.playerFeaturesID and playerfeatures.gameID = games.gameID",PDO::FETCH_ASSOC);
		if ($row){
			echo $this->printer->getDatas($row,'playerFeaturesForProfile');
		}else{
			echo 'dönmedi.';
		}
	}

}

 ?>