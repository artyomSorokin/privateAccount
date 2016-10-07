
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Private Account</title>
    <link rel="stylesheet" type="text/css" href="/css/privateAccount.css">

</head>
</head>
<body>
<div id="main"> <div id="header">
    <form method="get" action="logout">
    <table cellspacing="0" cellpadding="5">
        <tr>
            <td valign="top">${user.firstName}</td>
            <td><input width="20px" type="submit" value="Выход"/></td>
        </tr>
        <tr>
            <td valign="top">${user.secondName}</td>
        </tr>
        <tr>
            <td valign="top">${user.birthDay}</td>
        </tr>
        <tr>
            <td valign="top">${user.email}</td>
        </tr>
    </table>
    </form>
</div>
    <div id="content">
        <div id="gallery">

            <h2>My pictures</h2>

                    <c:forEach items="${listPhoto}" var="photo">
                                <%--<td>${book.id}</td>--%>
                            <img src="/image?id=${photo.id}">
                            <%--<c:out value="${photo.name}"/>--%>
                                <%--<td><c:out value="${book.author}" /></td>
                                <td><c:out value="${book.year}" /></td>--%>
                    </c:forEach>



            </div>
        <div id="upload">
            Загрузите ваши фотографии на сервер
            <form method="post" action="upload" enctype="multipart/form-data">
                <p><input type="file" name="file" multiple accept="image/*,image/jpeg">
                    <input type="submit" value="отправить"></p>
            </form>
        </div>
    </div>
</div>
</body>
</html>
