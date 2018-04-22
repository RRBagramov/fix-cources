<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>
    <h1>Please enter your login and password</h1>
</head>
<body>
<div style="text-align: center;">
    <form action="/signUp" method="post">

        Login: <input type="text" name="login">
        <br>
        Password: <input type="password" name="password">
        <br><br>
        <input type="submit" value="register">
    </form>

    <a href="/login">Do you have account?</a>
</div>
</body>
</html>
