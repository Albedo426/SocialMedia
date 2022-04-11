<?php 

class CsGOUserAPI{
	public $printer;
	public $baseUrl;
	public function __construct($apiKey){
		$headerUrl = "http://api.steampowered.com/";
		$API = "&key=".$apiKey;
		$this->printer = new Printer();
		$this->baseUrl = new BaseUrl($headerUrl,$API);
	}

	public function getCsGOPlayerStats(){
		$type = "unknown";
		$source = "-1";
		echo $this->printer->getTypeSource($type,$source,"","");
	}

	/*error_reporting(0);
		$patternKills = '/"name":"total_kills"[^\d]*":([^\"]+)\}/';
		$patternHeadShots = '/"name":"total_kills_headshot"[^\d]*":([^\"]+)\}/';
		$patternDeaths = '/"name":"total_deaths"[^\d]*":([^\"]+)\}/';

		$requiredUrl = $this->baseUrl->generateForAPI("ISteamUserStats/GetUserStatsForGame/v0002?appid=730&steamid=".$steamID);
		try {
			$content = file_get_contents($requiredUrl);

			preg_match_all($patternKills, $content, $matchesForKills);
			preg_match_all($patternHeadShots, $content, $matchesForHeadshot);
			preg_match_all($patternDeaths, $content, $matchesForDeaths);

			$headshotPerc = ($matchesForHeadshot[1][0]/$matchesForKills[1][0])*100;
			$kd = ($matchesForKills[1][0]/$matchesForDeaths[1][0]);
			$sourceHS = round($headshotPerc,2);
			$sourceKD = round($kd,2);
			echo $this->printer->getTypeSource("headshotPerc",$sourceHS,"kd",$sourceKD);
		} catch (Error $e) {
			echo $this->printer->getTypeSource("headshotPerc","-1","kd","-1");
			
		}*/
}

 ?>