<?php 

class Fileuploader extends dbconnection {
	public array $format;
	public  $limit;
	public 	function __construct(array $format,$limit){
		parent::dbcon();
		$this->format=$format;
		$this->limit=$limit;
	}
	public function insertsql($fileName,$type){
		$query = $this->db->prepare("INSERT INTO files SET
									url = :url,
									type = :type");
									$insert = $query->execute(array(
									      "url" => $fileName,
									      "type" => $type,
									));
									if ( $insert ){
							        	$controller=$this->db->lastInsertId();
									}else{
										unlink('images/'.$fileName);
										$controller="3";
									}
		return $controller;
	}
	public function imgdelete($fileId,$fileName){
		$controller="0";
		$query = $this->db->prepare("DELETE FROM files WHERE fileId = ?");
		$delete = $query->execute(array($fileId));
		if($delete){
			unlink('images/'.$fileName);
		}else{
		$controller="1";
		}
		$retkon= array();
		$retkon['control']=$controller;
		return json_encode($retkon);
	}
	public function imgupload($file){
		$_FILES = $file;
		$file_name = $_FILES['file']['name'];
						      $file_size =$_FILES['file']['size'];
						      $file_tmp =$_FILES['file']['tmp_name'];
						      $file_type=$_FILES['file']['type'];
						      $arrayVar = explode(".", $_FILES['file']['name']);
							  $extension = end($arrayVar);
						      $file_ext=strtolower($extension);

						      $extensions= $this->format;//array("jpeg","jpg","png");
						      if(in_array($file_ext,$extensions)== false )
						      {
						        $retkon="1";

						      }else if($file_size > $this->limit){//8 MB
						        $retkon="2";

						      }else{
						      	$newname=time()."_".$file_name;
							  	move_uploaded_file($file_tmp,"images/".$newname);
							    $retkon=$newname;//resim yüklendi
						      }
						      
						      return $retkon; 
	}
}

?>