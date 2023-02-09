<?php
 
$connect = new PDO("mysql:host=localhost;dbname=testing", "root", "");
// Beviteli mezős adatok

$title = $_POST['title'];
$start = $_POST['start'];
$end = $_POST['end'];
$id = $_POST['id'];
$old_date_timestamp = strtotime($start);
$new_date = date('Y-m-d', $old_date_timestamp);
$dayName =  date('l',strtotime($start));
$idoKezd = date('H:i', strtotime($start));

// Lekért adatok
$id1;
$title1;
$start1;
$end1;

// Beállítás
$match = 0;

if (isset($title) AND isset($start) AND isset($end))
{
    // Rendelési időben történik-e a felvétel
    $result1 = $connect->query("SELECT * FROM rendIdo WHERE idoPont = '$new_date'");
    foreach($result1 as $row)
    {
        $id1 = $row['id'];
        $title1 = "rendelesi ido";
        $start1 = $row['idoPont']." ".$row['kezdIdo'];
        $end1 = $row['idoPont']." ".$row['vegIdo'];
    }
    //van-e akkor más időpont már
    $result2 = $connect->query("SELECT * FROM events WHERE start_event = '$new_date'");
    foreach($result2 as $row)
    {
        $match = 1;
    }

    if (isset($id1) AND isset($title1) AND isset($start1) AND isset($end1) AND ($start1 <= $start AND $end1 >= $end) AND $match == 0)
    {
        $connect->exec("UPDATE events SET title='$title', start_event='$start', end_event='$end',het='$dayName',ido='$idoKezd' WHERE id='$id'");

        echo "Sikeres";
    }else{
        echo "Nem sikeres";
    }

    
}

?>