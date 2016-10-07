<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="/css/login.css" />
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker();
        } );
    </script>
</head>
<body>
<div id="content_container">
    <div id="header"> <div class="header_content_tagline">
        <h2><font color="red">${errorRegistered}</font></h2>
        <form method="post" action="RegistrationController">
            <center>
                <table border="1" width="100%" cellpadding="5">
                    <thead>
                    <tr>
                        <th colspan="2">Enter Information Here</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>First Name</td>
                        <td><input type="text" name="firstName" /></td>
                    </tr>
                    <tr>
                        <td>Second Name</td>
                        <td><input type="text" name="secondName" /></td>
                    </tr>
                    <tr>
                        <td>Birth Day</td>
                        <td><input type="text" id="datepicker" name="birthDay" /></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="email" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" /></td>
                    </tr>
                    <tr>
                        <td>Repeat Password</td>
                        <td><input type="password" name="repeatPassword" /></td>
                    </tr>
                    <tr>
                        <td>Your text</td>
                        <td><textarea cols="40" rows="3" name="textField"></textarea>></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit" /></td>
                        <td><input type="reset" value="Reset" /></td>
                    </tr>
                    <tr>
                        <td colspan="2">Already registered!! <a href="login.jsp">Login Here</a></td>
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

