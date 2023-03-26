<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<h1>Welcome</h1>

<p><a href="${pageContext.request.contextPath}/student-list">Список всех студентов</a>
<p><a href="${pageContext.request.contextPath}/add-new-student-form">Добавить нового студента</a>
<p><a href="${pageContext.request.contextPath}/update-student-form">Изменить данные студента</a>
<p><a href="${pageContext.request.contextPath}/delete-student-form">Удалить студента</a>
</body>

</html>