<?php 

class Dota2API{
	public $printer;
	public $baseUrl;
	public function __construct($apiKey){
		$headerUrl = "http://api.steampowered.com/";
		$API = "?key=".$apiKey."&language=tr";
		$this->printer = new Printer();
		$this->baseUrl = new BaseUrl($headerUrl,$API);
	}

	public function getDota2Options(){
		$pattern = '/\"localized_name\":\"([^\"]+)\"/';
		$requiredUrl = $this->baseUrl->generateForAPI("IEconDOTA2_570/GetHeroes/v0001");
		$content = file_get_contents($requiredUrl);
		preg_match_all($pattern, $content, $champions);
		$roles = ["Guvenli Koridor","Orta Koridor","Yan Koridor","Kısmi Destek","Tam Destek"];
		echo $this->printer->getOptions($champions[1],$roles);
	}
}

 ?>