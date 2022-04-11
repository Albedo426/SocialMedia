<?php 

class ValorantUserAPI{

	public $printer;
	public function __construct(){
		$this->printer = new Printer();
	}

	public function getValorantPlayerStats(){
		$type = "unknown";
		$source = "-1";
		echo $this->printer->getTypeSource($type,$source,"","");
	}

}

 ?>