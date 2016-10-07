<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="/css/login.css" />
</head>
<body>
<div id="content_container">
    <div id="header"> <div class="header_content_tagline">
        <h2><font color="red">${errorLogin}</font></h2>
        <form method="post" action="login">
            <center>
            <table border="1" width="100%" cellpadding="3">
                <thead>
                <tr>
                    <th colspan="2">Login Here</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>Email</td>
                    <td><input type="text" name="email" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Login" /></td>
                    <td><input type="reset" value="Reset" /></td>
                </tr>
                <tr>
                    <td colspan="2">Yet Not Registered!! <a href="registration.jsp">Register Here</a></td>
                </tr>
                </tbody>
            </table>
            </center>
        </form>
    </div>
    </div>
</div>
</body>
</html>

