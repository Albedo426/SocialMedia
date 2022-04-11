<?php
class BaseUrl{
	public function __construct($headerUrl,$API){
		$this->headerUrl = $headerUrl;
		$this->API = $API;
	}

	public function generateForAPI($whichAPI){
		return $this->headerUrl.$whichAPI.$this->API;
	}

	public function generateForOther($whichUrl){
		return $this->headerUrl.$whichUrl;
	}
}


 ?>