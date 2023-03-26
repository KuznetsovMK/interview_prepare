<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Delete student</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<h1>изменить данные студента
</h1>
<form action="/update-student" method="post">
    <p>id: <input name="id"/>
    <p>New name: <input name="name"/>
    <p>New Age: <input name="age"/>
    <p><input type="submit" value="Submit"/>
</form>
</body>
</html>