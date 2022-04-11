<?php 
class dbconnection{
	public $db;
	public function dbcon(){
		try {
			$db = new PDO("mysql:host=localhost;dbname=gamingsm", "root", "");
		     $db->query("SET CHARACTER SET utf8");
		     $this->db=$db;//global değişkene atıyor
		} catch ( PDOException $e ){
		     print $e->getMessage();
		}
	}
}
?>