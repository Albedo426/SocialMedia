<?php 


class NotficationManager  extends dbconnection
    {
        public function __construct(){
            parent::dbcon();
            $this->printer = new Printer();
        }
        public function setNotficatonChannel($id, $key){
            $row = $this->db->query("SELECT * FROM notification WHERE userId = '{$id}'")->fetch(PDO::FETCH_ASSOC);
            if($row == null){

                 $this->insertNotficaton($id, $key);
            }else{
                 $this->updateNotficatons($id, $key);
            }
        }
        public function getUserKeyForId($id){
            $row = $this->db->query("SELECT * FROM notification,users WHERE users.userID=notification.userId and users.userId = '{$id}'")->fetch(PDO::FETCH_ASSOC);
            if($row != null){
                return $row;
            }else{
                 return 0;
            }
        }
        public function insertNotficaton($id, $key){
            $row = $this->db->prepare("INSERT INTO notification SET 
            userId = ?,
            token = ?");
            $insert = $row->execute(array($id, $key));
            if($insert){
                $retkon = "1";
            }else{
                $retkon = "0";
            }
        }
        public function updateNotficatons($id, $key){
            $query = $this->db->prepare("UPDATE notification SET
            token = :token
            WHERE userId = :userId");
            $update = $query->execute(array(
                 "token" => $key,
                 "userId" => $id
            ));
             if($update){
                $retkon = "3";
            }else{
                $retkon = "2";
            }
        }
        public function sendNotfication($token,$title,$body,$userId){
        //fU4pXLmuSDmRXM6GEAOM_y:APA91bGa9HDJxgP03gZ6XcnVkOH1cbVR_ZCRV1z1MfzhwBf5hZmDUCtOWMhQNlBnMOEW3WCbIypyVykqEqBsI6pgujJ__WSu9wW9aAVZpveVCFuU_iKobe5JqeJNZqnAAXoOLOTKwL3g
        $firebase_url = 'https://fcm.googleapis.com/fcm/send';
        $fields = array(
            'to' => $token, //KULLANICI TOKEN -> Kullanıcı telefonunda oluşan Token
            'notification' => array('title' => $title, 'body' => $body), //Notification tipinde mesaj
            'data' => array('userId' => $userId) //data tipinde mesaj
        );
 
        $headers = array(
            'Authorization:key=AAAADLCO7IM:APA91bGffLc5EMNHSNXDHJsnf0gRixLnoHlMwOBBwEYiPPawWGc5MIYN4nW3aC3PQDqsz5_-SUnSf7NMx8jqz9oeoxfYRqyCT-FfoKydhojiGYrYEMNrT3ANzYnT0V-fxWhG4mjeaotL' , //SERVER API KEY -> Konsoldan aldık
            'Content-Type:application/json'
        ); 
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $firebase_url); 
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true); 
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($ch, CURLOPT_IPRESOLVE, CURL_IPRESOLVE_V4 ); 
        curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
    
        $result = curl_exec($ch);
       
        curl_close($ch); 
        $result=json_decode($result,true);
            return $this->printer->boolConvertJson($result['success']);
        }
        
    }

    
?>