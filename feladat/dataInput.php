<?php
$con = new PDO('mysql:host=localhost;dbname=testing', 'root', '');

$start    = new DateTime('2023-01-01');
$end      = new DateTime('2023-12-31');
$interval = DateInterval::createFromDateString('1 day');
$period   = new DatePeriod($start, $interval, $end);

foreach ($period as $date) {
    $day = date('D', strtotime($date->format('Y-m-d')));
    $timestamp = $date->getTimestamp();   
    if($date->format('W')%2 == 0){
        //páros hét:  hétfő     
        if($day == 'Mon'){   
                  
            $query="INSERT INTO rendido( kezdIdo, vegIdo, idoPont) VALUES ('10:00','12:00',FROM_UNIXTIME($timestamp))";
            if ($con->query($query) === TRUE) {
                echo "New record created successfully";
              } else {
                echo "Error: " . $query . "<br>" . $con->error;
              }
        }
    }else{
        //páratlan hét:  szerda 
        if($day == 'Wed'){     
            
            $query="INSERT INTO rendido( kezdIdo, vegIdo, idoPont) VALUES ('12:00','16:00',FROM_UNIXTIME($timestamp))";
            if ($con->query($query) === TRUE) {
                echo "New record created successfully";
              } else {
                echo "Error: " . $query . "<br>" . $con->error;
              }
        }       
    }
    //minden hét péntek
    if($day=='Fri'){  
        
        $query="INSERT INTO rendido( kezdIdo, vegIdo, idoPont) VALUES ('10:00','16:00',FROM_UNIXTIME($timestamp))";
        if ($con->query($query) === TRUE) {
            echo "New record created successfully";
          } else {
            echo "Error: " . $query . "<br>" . $con->error;
          }
    }
    //adott nap
    if($date->format('Y-m-d')=='2023-06-08'){
        
        $query="INSERT INTO rendido( kezdIdo, vegIdo, idoPont) VALUES ('08:00','10:00',FROM_UNIXTIME($timestamp))";
        if ($con->query($query) === TRUE) {
            echo "New record created successfully";
          } else {
            echo "Error: " . $query . "<br>" . $con->error;
          }
    }
    //2 dátum közti csütörtökök
   
    if($day=='Thu'){
      if('2023-06-01' <= $date->format('Y-m-d')) {
        if($date->format('Y-m-d') <= '2023-11-30'){
        
          $query="INSERT INTO rendido( kezdIdo, vegIdo, idoPont) VALUES ('16:00','20:00',FROM_UNIXTIME($timestamp))";
          if ($con->query($query) === TRUE) {
              echo "New record created successfully";
            } else {
              echo "Error: " . $query . "<br>" . $con->error;
            }
        }      
      }
    }
     
            
        
    
}

?>