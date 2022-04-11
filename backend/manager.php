<?php
include "printer.php";
include "baseUrl.php";
include "userManager.php";
include "gamesManager.php";
include "lolAPI.php";
include "lolUserAPI.php";
include "valorantAPI.php";
include "valorantUserAPI.php";
include "dota2API.php";
include "dota2UserAPI.php";
include "csGOAPI.php";
include "csGOUserAPI.php";
include "playerFeaturesManager.php";
include 'BubbleManagment.php';
include 'MessageManagment.php';
include 'PostManagment.php';
include 'uploadfile.php';
include 'NotficationManager.php';
class Manager{

	public function __construct(){}

	public function getUserManager(){
		return new UserManager();
	}
	public function getNotficationManager(){
		return new NotficationManager();
	}
	public function getPlayerFeaturesManager(){
		return new PlayerFeaturesManager();
	}

	public function getGamesManager(){
		return new GamesManager();
	}

	public function getLolAPI(){
		return new LolAPI();
	}

	public function getLolUserAPI($apiKey){
		return new LolUserAPI($apiKey);
	}

	public function getValorantAPI($apiKey){
		return new ValorantAPI($apiKey);
	}

	public function getValorantUserAPI(){
		return new ValorantUserAPI();
	}

	public function getDota2API($apiKey){
		return new dota2API($apiKey);
	}

	public function getDota2UserAPI(){
		return new Dota2UserAPI();
	}

	public function getCsGOAPI($apiKey){
		return new CsGOAPI($apiKey);
	}

	public function getCsGOUserAPI($apiKey){
		return new CsGOUserAPI($apiKey);
	}

	public function getBubbleManagmentServices(){	
		return new BubbleManagment();
	}
	
	public function getMessageManagmentServices(){	
		return new MessageManagment();
	}

	public function getPostManagmentServices(){	
		return new PostManagment();
	}

}

 ?>