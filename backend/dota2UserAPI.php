<?php 

class Dota2UserAPI{
	public $printer;
	public function __construct(){
		$this->printer = new Printer();
	}

	public function getDota2PlayerStats(){
		$type = "unknown";
		$source = "-1";
		echo $this->printer->getTypeSource($type,$source,"","");
	}

}

 ?>