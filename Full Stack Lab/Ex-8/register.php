<!DOCTYPE html>
<html>
<body align="center">
    <?php
    $conn=mysqli_connect('localhost','root','','users');
    if(isset($_POST['submit'])){
        session_start();
        if(isset($_POST['email']) && $_POST['password'] && isset($_POST['name']) && $_POST['phone']){
            $email=$_POST['email'];
            $password=$_POST['password'];
            $name=$_POST['name'];
            $phone=$_POST['phone'];
            $sql = "INSERT INTO login (email,passcode,username,phone) VALUES('$email','$password','$name','$phone')";
            if ($conn->query($sql) === TRUE) {
                setcookie("Email", $email);
                setcookie("Password", $password);
                $_SESSION["username"]=$name;
                echo "New record created successfully";
                header('location:home.php');
              } else {
                echo "Error: " . $sql . "<br>" . $conn->error;
              }
        }
    }
    ?>
    <form method="post">
        <h1>Welcome To My Web Page</h1>
        <input type="text" placeholder="Enter Your Name" name="name"/><br><br>
        <input type="email" placeholder="Enter Your Email ID" name="email"/><br><br>
        <input type="Password" placeholder="Enter Your Password" name="password"/><br><br>
        <input type="phone" placeholder="Enter Your Phone Number" name="phone"/><br><br>
        <input type="submit" name="submit"/><br><br>
        <a href="login.php">Already have an account? Click here</a>
    </form>
</body>
</html>