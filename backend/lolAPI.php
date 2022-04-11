<?php 
class LolAPI {
	public $printer;
	public $baseUrl;
	public function __construct(){
		$headerUrl = "http://ddragon.leagueoflegends.com/cdn/";
		$this->printer = new Printer();
		$this->baseUrl = new BaseUrl($headerUrl,NULL);
	}

	public function getLolOptions(){
		$pattern = '/\"name\":\"([^\"]+)\"/';
		$requiredUrl = $this->baseUrl->generateForOther("11.6.1/data/tr_TR/champion.json");
		$content = file_get_contents($requiredUrl);
		preg_match_all($pattern,$content,$champions);
		$roles = ["Üst Koridor","Ormancı","Orta Koridor","Alt Koridor","Destek"];
		echo $this->printer->getOptions($champions[1],$roles);
	}
}
