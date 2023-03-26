<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Create new student</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<form action="/create-student" method="post">
    <p>Name: <input name="name"/>
    <p>Age: <input name="age"/>
    <p><input type="submit" value="Submit"/>
</form>
</body>
</html>