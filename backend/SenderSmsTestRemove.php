<?php
include 'IConnect.php';
include "manager.php";

$_POST['Method'] = "isFriend";
//$_POST['token'] = "fU4pXLmuSDmRXM6GEAOM_y:APA91bGa9HDJxgP03gZ6XcnVkOH1cbVR_ZCRV1z1MfzhwBf5hZmDUCtOWMhQNlBnMOEW3WCbIypyVykqEqBsI6pgujJ__WSu9wW9aAVZpveVCFuU_iKobe5JqeJNZqnAAXoOLOTKwL3g";
$_POST['senderID'] = "145";
$_POST['receiverID'] = "1";
$_POST['value'] = "ihsan".rand(); 

$_POST['type'] = "1";	
$_POST['vis'] = "0";
//$_POST['email'] = "ihsanguldur@outlook.com";
//$_POST['password'] = "ihsan3535";
//$_POST['required'] = "76561199154140941";
$riotApiKey = "RGAPI-bdf0f8f1-8145-4365-a0aa-1418e2908902";
$steamApiKey = "F58917BA87AFC19AE8186AF97B829F87";

if(isset($_POST['Method'])){

	$manager = new Manager();

	if($_POST['Method'] == "getForLogin"){
		if(isset($_POST['email'], $_POST['password'],$_POST['token'])){
			$userManager = $manager->getUserManager();
			$userManager->getForLogin($_POST['email'], $_POST['password'],$_POST['token']);
		}

	}else if($_POST['Method'] == "isFriend"){
		echo $_POST['value'] ;
		if(isset($_POST['senderId'],$_POST['receiverId'])){
			$userManager = $manager->getUserManager();
			$userManager->isFriend($_POST['senderId'],$_POST['receiverId']);
		}
	}else if($_POST['Method'] == "pushNotficaton"){
		if(isset($_POST['token'], $_POST['title'], $_POST['body'])){
			$getNotficationManager = $manager->getNotficationManager();
			$getNotficationManager->sendNotfication($_POST['token'], $_POST['title'], $_POST['body']);
		}

	}else if($_POST['Method'] == "onCreateUser"){
		if(isset($_POST['name'], $_POST['lastName'], $_POST['birthDate'], $_POST['email'], $_POST['userName'], $_POST['password'], $_POST['phone'])){
			$userManager = $manager->getUserManager();
			$userManager->onCreateUser($_POST['name'], $_POST['lastName'], $_POST['birthDate'], $_POST['email'], $_POST['userName'], $_POST['password'], $_POST['phone']);
		}

	}else if($_POST['Method'] == "isDuplicate"){
		$retkon = array();		
		if(isset($_POST['value'], $_POST['key'])){
			$userManager = $manager->getUserManager();
			$userManager->isDuplicate($_POST['key'], $_POST['value']);
		}

	}else if($_POST['Method'] == "getGameWithName"){
		if(isset($_POST['gameName'])){
			$gamesManager = $manager->getGamesManager();
			$gamesManager->getGameWithName($_POST['gameName']);
		}

	}else if($_POST['Method'] == "getAllGames"){
		$gamesManager = $manager->getGamesManager();
		$gamesManager->getAllGames();

	}else if($_POST['Method'] == "summonerByName"){
		if(isset($_POST['required'])){
			$lolUserAPI = $manager->getLolUserAPI($riotApiKey);
			$id = $lolUserAPI->summonerByName($_POST['required']);
			$lolUserAPI->leagueById($id);
		}

	}else if($_POST['Method'] == "lolOptions"){
		$lolAPI = $manager->getLolAPI();
		$lolAPI->getLolOptions();

	}else if($_POST['Method'] == "valorantOptions"){
		$valorantAPI = $manager->getValorantAPI($riotApiKey);
		$valorantAPI->getValorantOptions();

	}else if($_POST['Method'] == "dota2Options"){
		$dota2API = $manager->getDota2API($steamApiKey);
		$dota2API->getDota2Options();

	}else if($_POST['Method'] == "csGOOptions"){
		$csGOAPI = $manager->getCsGOAPI($steamApiKey); 
		$csGOAPI->getCsGOOptions();

	}else if($_POST['Method'] == "getCsGOPlayerStats"){
		if(isset($_POST['required'])){
			$csGOUserAPI = $manager->getCsGOUserAPI($steamApiKey);
			$csGOUserAPI->getCsGOPlayerStats($_POST['required']);
		}

	}else if($_POST['Method'] == "getDota2PlayerStats"){
		$dota2UserAPI = $manager->getDota2UserAPI();
		$dota2UserAPI->getDota2PlayerStats();

	}else if($_POST['Method'] == "getValorantPlayerStats"){
		$valorantUserAPI = $manager->getValorantUserAPI();
		$valorantUserAPI->getValorantPlayerStats();

	}else if($_POST['Method'] == "insertPlayerFeatures"){
		if(isset($_POST['option1'],$_POST['option2'],$_POST['about'],$_POST['gameID'],$_POST['gUserName'])){
			$pfManager = $manager->getPlayerFeaturesManager();
			$pfManager->insertPlayerFeatures($_POST['option1'],$_POST['option2'],$_POST['about'],$_POST['gameID'],$_POST['gUserName']);
		}

	}else if($_POST['Method'] == "playerFeaturesControl"){
		if(isset($_POST['gameID'],$_POST['gUserName'])){
			$pfManager = $manager->getPlayerFeaturesManager();
			$pfManager->playerFeaturesControl($_POST['gameID'],$_POST['gUserName']);
		}

	}else if($_POST['Method']=="getBubbles"){
		if(isset($_POST['senderId'])){
			$id=$_POST['senderId'];	
			$bubbleManagmentServices=$manager->getBubbleManagmentServices();
			echo $bubbleManagmentServices->getBubblesModifine($id);
		}

	}else if($_POST['Method']=="lastMessage"){
		if(isset($_POST['userId'])){
			$id=$_POST['userId'];	
			$trgid=$_POST['targetUserId'];	
			$messageManagmentServices=$manager->getMessageManagmentServices();
			echo $messageManagmentServices->lastMessage($id,$trgid);	
		}

	}else if($_POST['Method']=="getChat"){
		if(isset($_POST['userId'])){
			$messageManagmentServices=$manager->getMessageManagmentServices();
			echo $messageManagmentServices->getChat($_POST['userId'],$_POST['targetUserId'],$_POST['limit']);	
		}

	}else if ($_POST['Method']=="addmessage"){
		$senderID=$_POST['senderID'];
		$receiverID=$_POST['receiverID'];
		$value=$_POST['value'];	
		$type=$_POST['type'];
		$vis=$_POST['vis'];	
		$messageManagmentServices=$manager->getMessageManagmentServices();
		echo $messageManagmentServices->addmessage($senderID,$receiverID,$value,$type,$vis);

	}else if($_POST['Method']=="lastMessageControl"){
		if(isset($_POST['userId'])){
			$id=$_POST['userId'];	
			$messagesId=$_POST['messagesId'];	
			$trgid=$_POST['targetUserId'];
			$messageManagmentServices=$manager->getMessageManagmentServices();
			echo $messageManagmentServices->lastMessageControl($id,$trgid,$messagesId);
		}

	}else if($_POST['Method']=="bubblesControl"){
		if(isset($_POST['userId'],$_POST['msgId'])){
			$id=$_POST['userId'];	
			$msgId=$_POST['msgId'];	
			$trgid=$_POST['targetUserId'];	
			$bubbleManagmentServices=$manager->getBubbleManagmentServices();
			echo $bubbleManagmentServices->bubblesControl($id,$trgid,$msgId);	
		}

	}else if($_POST['Method']=="\"sendMessageFile\""){
		if(isset($_POST['senderID'],$_POST['receiverID'],$_POST['value'],$_POST['type'],$_POST['vis'])){
			$senderID = str_replace("\"", "",$_POST['senderID']);
			$receiverID = str_replace("\"", "",$_POST['receiverID']);
			$value = str_replace("\"", "",$_POST['value']);
			$type = str_replace("\"", "",$_POST['type']);
			$vis = str_replace("\"", "",$_POST['vis']);
			$messageManagmentServices=$manager->getMessageManagmentServices();
			if($type=="3" || $type==3 ){
				$extensions= array("mp4","mov","wmv ","flv");
				$limit=10485760;
			}else{
				$extensions= array("jpeg","jpg","png");
				$limit=8388608;
			}
			echo $messageManagmentServices->sendMessageImg($senderID,$receiverID,$value,$type,$vis ,$_FILES,$extensions,$limit);
		}

	}else if($_POST['Method']=="getUrl"){
		if(isset($_POST['msgId'])){
			$id=$_POST['msgId'];	
			$messageManagmentServices=$manager->getMessageManagmentServices();
			echo $messageManagmentServices->getimgUrl($id);		
		}

	}else if($_POST['Method']=="\"sendMessageVideo\""){
		if(isset($_POST['senderID'],$_POST['receiverID'],$_POST['value'],$_POST['type'],$_POST['vis'])){
			$senderID = str_replace("\"", "",$_POST['senderID']);
			$receiverID = str_replace("\"", "",$_POST['receiverID']);
			$value = str_replace("\"", "",$_POST['value']);
			$type = str_replace("\"", "",$_POST['type']);
			$vis = str_replace("\"", "",$_POST['vis']);
			$messageManagmentServices=$manager->getMessageManagmentServices();	
			echo $messageManagmentServices->sendMessageImg($senderID,$receiverID,$value,$type,$vis ,$_FILES,$extensions,$limit);
		}

	}else if($_POST['Method']=="getFriendUser"){
		if(isset($_POST['userId'])){
			$val=$_POST['searchName'];	
			$id=$_POST['userId'];	
			$userManager = $manager->getUserManager();
			echo $userManager->getFriendUser($id,$val);
		}

	}else if ($_POST['Method']=="newaddmessage"){
		$senderID=$_POST['senderID'];
		$receiverID=$_POST['receiverID'];
		$value=$_POST['value'];	
		$type=$_POST['type'];
		$vis=$_POST['vis'];	
		$messageManagmentServices=$manager->getMessageManagmentServices();
		echo $messageManagmentServices->newaddmessage($senderID,$receiverID,$value,$type,$vis);

	}else if($_POST['Method']=="\"addImages\""){
		if(isset($_POST['type'])){
			$type = str_replace("\"", "",$_POST['type']);
			$type = str_replace(" ", "",$type);
			$postManagmentServices=$manager->getPostManagmentServices();
			$format= array("mp4","mov","wmv ","flv");
			if(strval ( $type ) =="1"){
				$limit=104857600;
			}else{
				$format= array("jpeg","jpg","png");
				$limit=8388608;
			}
			echo $postManagmentServices->fileupload($format,$limit,$_FILES,$type);
		}

	}else if($_POST['Method']=="removeFile"){
		if(isset($_POST['fileId'])){
			$fileId = $_POST['fileId'];
			$fileName = $_POST['fileName'];
			$postManagmentServices=$manager->getPostManagmentServices();
			echo $postManagmentServices->filedelete($fileId,$fileName);
		}

	}else if($_POST['Method']=="postSend"){
		if(isset($_POST['userId'])){
			$text = $_POST['text'];
			$userId = $_POST['userId'];
			$dataIsNull = $_POST['dataIsNull'];
			$filesId;
			if($dataIsNull==0){
				$filesId=$_POST['filesId'];
			}else{
				$filesId=null;
			}
			$postManagmentServices=$manager->getPostManagmentServices();
			echo $postManagmentServices->postSend($text,$userId,$dataIsNull,$filesId);	
		}

	}else if($_POST['Method']=="getpost"){
		if(isset($_POST['userId'])){
			$userId = $_POST['userId'];
			$limit = $_POST['limit'];
			$postManagmentServices=$manager->getPostManagmentServices();
			echo $postManagmentServices->getFriendsPost($userId,$limit);
		}

	}else if($_POST['Method']=="likeControlSet"){//be??enildiyse be??eni klad??rma be??enilmediysa be??enme
		if(isset($_POST['userId'])){
			$postId = $_POST['postId'];
			$userId = $_POST['userId'];
			$postManagmentServices=$manager->getPostManagmentServices();
			echo $postManagmentServices->likeControlSet($postId,$userId);	
		}

	}else if($_POST['Method']=="getPostData"){//be??enildiyse be??eni klad??rma be??enilmediysa be??enme
		if(isset($_POST['postId'])){
			$postId = $_POST['postId'];
			$postManagmentServices=$manager->getPostManagmentServices();
			echo $postManagmentServices->getPostData($postId);
		}

	}else if($_POST['Method']=="getCommentForPostId"){
		if(isset($_POST['connectId'])){
			$connectId = $_POST['connectId'];
			$limit = $_POST['limit'];
			$postManagmentServices=$manager->getPostManagmentServices();
			echo $postManagmentServices->getComments($connectId,$limit,TRUE);
		}

	}else if($_POST['Method']=="getCommentForUserId"){
		if(isset($_POST['connectId'])){
			$connectId = $_POST['connectId'];
			$limit = $_POST['limit'];
			$postManagmentServices=$manager->getPostManagmentServices();
			echo $postManagmentServices->getComments($connectId,$limit,FALSE);
		}

	}else if($_POST['Method']=="addComment"){
		if(isset($_POST['userId'])){
			$userId = $_POST['userId'];
			$value = $_POST['value'];
			$isSubComment = $_POST['isSubComment'];
			$postId = $_POST['connectId'];
			$postManagmentServices=$manager->getPostManagmentServices();
			echo $postManagmentServices->addComments($userId,$value,$isSubComment,$postId);
		}
	}else if($_POST['Method']=="getUser"){
		if(isset($_POST['userId'])){
				
			$id=$_POST['userId'];	
			$userManager= $manager->getUserManager();
			echo $userManager->getUser($id);
		}
	}else if($_POST['Method']=="getUserImage"){
		if(isset($_POST['userId'])){
				
			$id=$_POST['userId'];	
			$userManager= $manager->getUserManager();
			echo $userManager->getUserImage($id);
		}
	}else if($_POST['Method']=="getPostForId"){
		if(isset($_POST['userId'])){
			$userId = $_POST['userId'];
			$limit = $_POST['limit'];
			$PostManagment=$manager->getPostManagmentServices();
			echo $PostManagment->getPostForId($userId,$limit);
		}		
	}
}
 ?>