<?php

class GamesManager extends dbconnection{
	public $printer;
	public function __construct(){
		parent::dbcon();
		$this->printer = new Printer();
	}

	public function getGameWithName($gameName){
		$row = $this->db->query("SELECT * FROM games WHERE gameName = '{$gameName}'")->fetch(PDO::FETCH_ASSOC);
		if($row == null){
			$row['gameID'] = 0;
		}
		echo $this->printer->getData($row,"games");
	}

	public function getAllGames(){
		$row = $this->db->query("SELECT * FROM games",PDO::FETCH_ASSOC);
		echo $this->printer->getDatas($row,"games");
	}

}

 ?>