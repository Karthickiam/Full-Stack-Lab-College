<?php
session_start();
if(isset($_POST["logout"])){
unset($_SESSION["id"]);
unset($_SESSION["username"]);
header("Location:login.php");
}
$redis = new Redis();
$redis->connect('127.0.0.1', 6379);
$email = $redis->get('E-mail'); 
?>
<!DOCTYPE html>
<html>
    <body>
        <style>
            h1{
                color:#fff;
            }
            body{
                background-color:#000;
            }
            Button{
                color:white;
                background-color:red;
                cursor: pointer;
                text-align: center;
                align:center;
                border-radius:5px;
                left: 50%;
                width:10%;
                height:5%;
                position: absolute;
            }
            </style>
            <h1>Hello, <?php echo $_SESSION["username"]; ?></h1>
        <h1><?php echo $email; ?></h1>

    <marquee width="100%" direction="left" height="100px">
        <h1>Welcome to My Home Page :)</h1>
</marquee>
<form method="post">
<Button name="logout">logout</Button>
        </form>
    </body>
</html>