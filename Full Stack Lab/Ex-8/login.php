<!DOCTYPE html>
<html>
<body align="center">
    <?php
    try {
        // Create a Redis Instance
        $redis = new Redis();
        $redis->connect('127.0.0.1', 6379);
    } catch (Exception $ex) {
        echo $ex->getMessage();
    }
    session_start();
    $conn=mysqli_connect('localhost','root','','users');
    if(isset($_POST['submit'])){
        if(isset($_POST['email']) && isset($_POST['password'])){
            $email=$_POST['email'];
            $password=$_POST['password'];
            $sql = "SELECT userid,username FROM login WHERE email = '$email' and passcode = '$password'";
        $result = mysqli_query($conn,$sql);
            $count = mysqli_num_rows($result);
            $row  = mysqli_fetch_array($result);
            if(is_array($row)) {
                $_SESSION["id"] = $row['userid'];
                $_SESSION["username"] = $row['username'];
                }else {
                    $message = "Invalid Username or Password!";
                   }
            if($_SESSION["username"]){
                echo 'here';
                $redis->set('E-mail', $email);
                $name = $redis->get('E-mail');
                echo $name;
                header('location:home.php');
            }else{
                echo 'Nope';
            }
        }
    }
    if(isset($_COOKIE["Email"])&&isset($_COOKIE["Password"])){
        $sql = "SELECT userid FROM login WHERE email = '".$_COOKIE["Email"]."' and passcode = '".$_COOKIE["Password"]."'";
        $result = mysqli_query($conn,$sql);
            $count = mysqli_num_rows($result);
            if($count==1){
                echo 'here';
                header('location:home.php');
            }else{
                echo 'Username or Password is wrong';
            }
    }
    ?>
    <form method="post">
        <h1>Welcome To My Web Page</h1>
        <input type="email" placeholder="Enter Your Email ID" name="email"/><br><br>
        <input type="Password" placeholder="Enter Your Password" name="password"/><br><br>
        <input type="submit" name="submit"/><br><br>
        <a href="register.php">New User? Click here</a>
    </form>
</body>
</html>