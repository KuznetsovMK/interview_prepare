<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Students</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<a href="${pageContext.request.contextPath}/">main page</a>
<h1>Student list</h1>

<ul>
    <c:forEach items="${students}" var="student">
        <li>${student.name}, age:${student.age}, id:${student.id}</li>
    </c:forEach>
</ul>
</body>

</html>