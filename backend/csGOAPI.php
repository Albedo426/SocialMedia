<?php 

class CsGOAPI{
	public $printer;
	public $baseUrl;
	public function __construct($apiKey){
		$headerUrl = "http://api.steampowered.com/";
		$API = "?key=".$apiKey."&language=tr";
		$this->printer = new Printer();
		$this->baseUrl = new BaseUrl($headerUrl,$API);
	}

	public function getCsGOOptions(){
		$weapons = array();
		$requiredUrl = $this->baseUrl->generateForAPI("IEconItems_730/GetSchema/v0002");
		$content = file_get_contents($requiredUrl);
		$data = json_decode($content,true);
		foreach ($data['result']['items'] as $row) {
			if(($row['item_type_name'] == "Tabanca") || ($row['item_type_name'] == "Tüfek") || ($row['item_type_name'] == "Dürbünlü Tüfek") || ($row['item_type_name'] == "Makineli Tüfek") || ($row['item_type_name'] == "Hafif Makineli") || ($row['item_type_name'] == "Pompalı Tüfek")){
					array_push($weapons, $row['item_name']);
			}
		}
		$roles = ["In Game Leader","Entry Fragger","The Supporter","The Lurker","AWP Player","Rifler"];
		echo $this->printer->getOptions($weapons,$roles);
	}
}

 ?>