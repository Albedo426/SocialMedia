<?php 

class ValorantAPI{

	public $printer;
	public $baseUrl;
	public function __construct($apiKey){
		$headerUrl = "https://ap.api.riotgames.com/val/";
		$API = "&api_key=".$apiKey;
		$this->printer = new Printer();
		$this->baseUrl = new BaseUrl($headerUrl,$API);
	}
	
	/*public function getChampions(){
		$pattern1 = '/\"name\":\"([^\"]+)\"/';
		$pattern2 = '/\"characters\":\[([^\]]+)\]/';
		$requiredUrl = $this->baseUrl->generateForAPI("content/v1/contents?locale=en-US");
		$content = file_get_contents($requiredUrl);
		preg_match($pattern2,$content,$matches1);
		echo gettype($matches1);
		//print_r($matches1[0]);
		$characters = json_encode($matches1[0]);
		//print_r($characters);
		preg_match_all($pattern1,$characters,$result);
		//print_r($result);
		
		//$unique = array_unique($matches[1]);
		//$result = array_diff($unique, ["Null UI Data!"]);
		//echo $this->printer->getDatas($result,"option1");
	}
	public function getChampions(){
		$pattern1 = '/\"name\":\"([^\"]+)\"/';
		$requiredUrl = $this->baseUrl->generateForAPI("content/v1/contents?locale=en-US");
		$content = file_get_contents($requiredUrl);
		$data = json_decode($content,true);
		$charData = json_encode($data['characters']);
		preg_match_all($pattern1,$charData,$matches);
		$unique = array_unique($matches[1]);
		$result = array_diff($unique, ["Null UI Data!"]);
		echo $this->printer->getDatas($result,"option1");
	}*/

	public function getValorantOptions(){
		$pattern = '/\"name\":\"([^\"]+)\"/';
		$requiredUrl = $this->baseUrl->generateForAPI("content/v1/contents?locale=en-US");
		$content = file_get_contents($requiredUrl);
		$data = json_decode($content,true);
		$charactersData = json_encode($data['characters']);
		$weaponsData = json_encode($data['equips']);
		preg_match_all($pattern,$charactersData,$matches1);
		preg_match_all($pattern, $weaponsData, $matches2);
		$unique1 = array_unique($matches1[1]);
		$unique2 = array_unique($matches2[1]);
		$characters = array_diff($unique1,["Null UI Data!"]);
		$weapons = array_diff($unique2, ["SPIKE","Melee","Golden Gun","Paint Shells","Boom Bot","Showstopper","Shock Bolt","BIG KNIFE","Snowball Launcher","Blast Pack"]);
		echo $this->printer->getOptions($characters,$weapons);
	}

}

 ?>