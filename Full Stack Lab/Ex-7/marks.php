<?php
$conn = new mysqli('localhost', 'root', '', 'Results');
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}
else{
    $sql="SELECT * FROM marks";
    $result=$conn->query($sql);

    while($row = $result->fetch_assoc()) {
        $records[] = $row;
    }
    echo json_encode($records);
}
?>