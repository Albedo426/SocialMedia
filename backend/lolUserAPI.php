<?php 

class LolUserAPI{
	public $printer;
	public $baseUrl;
	public function __construct($apiKey){
		$headerUrl = "https://tr1.api.riotgames.com/lol/";
		$API = "?api_key=".$apiKey;
		$this->printer = new Printer();
		$this->baseUrl = new BaseUrl($headerUrl,$API);
	}

	public function summonerByName($userName){
		$requiredUrl = $this->baseUrl->generateForAPI("summoner/v4/summoners/by-name/".$userName);
		$content = file_get_contents($requiredUrl);
		$data = json_decode($content,true);
		return $data['id'];
	}

	public function leagueById($id){
		$patternTier = '/\"tier\":\"([^\"]+)\"/';
		$patternRank = '/\"rank\":\"([^\"]+)\"/';
		$requiredUrl = $this->baseUrl->generateForAPI("league/v4/entries/by-summoner/".$id);
		$content = file_get_contents($requiredUrl);
		if ($content == "[]"){
			$source = "unranked";
		}else{
			preg_match_all($patternTier, $content, $matchesTier);
			preg_match_all($patternRank, $content, $matchesRank);
			$source = $matchesTier[1][0]." ".$matchesRank[1][0];
		}
		echo $this->printer->getTypeSource("rank",$source,"","");
	}
}
?>