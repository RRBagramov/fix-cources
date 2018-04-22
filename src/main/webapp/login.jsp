<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <h1>Please login to continue</h1>
</head>
<body>
<div style="text-align: center;">
    <form action="/login" method="post">

        Login: <input type="text" name="login">
        <br>
        Password: <input type="password" name="password">
        <br><br>
        <input type="submit" value="Login">
    </form>
    <a href="/signUp">I'm new user!</a>
</div>
</body>
</html>
