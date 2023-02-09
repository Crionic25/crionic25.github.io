<?php

//load.php

$connect = new PDO('mysql:host=localhost;dbname=testing', 'root', '');

$data = array();
//$data1 = array();

$query = "SELECT * FROM rendIdo ORDER BY id";

$query1 = "SELECT * FROM events ORDER BY id";

$statement = $connect->prepare($query);
$statement1 = $connect->prepare($query1);

$statement->execute();
$statement1->execute();

$result = $statement->fetchAll();
$result1 = $statement1->fetchAll();

foreach($result as $row)
{
 $data[] = array(
  'id'   => $row["id"],
  'title'   => "rendelesi ido",
  'start'   => $row["idoPont"]." ".$row["kezdIdo"],
  'end'   => $row["idoPont"]." ".$row["vegIdo"]
 );
}

foreach($result1 as $row)
{
 $data[] = array(
    'id'   => $row["id"],
    'title'   => $row["title"],
    'start'   => $row["start_event"],
    'end'   => $row["end_event"]
 );
}

echo json_encode($data);
//echo json_encode($data1);

?>