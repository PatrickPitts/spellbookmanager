<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org"
      xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>Phylactory: New User</title>
</head>
<body>

<form th:action="@{/new-user}" th:object="${basicUser}"  method="post">
    <input type="hidden" th:field="${basicUser.role}" th:value="USER">
    <div><label> User Name : <input type="text" th:field="${basicUser.username}"/> </label></div>
    <div><label> Password: <input type="password" th:field="${basicUser.password}"/> </label></div>
    <div><input type="submit" value="Sign Up"/></div>
</form>
<div th:if="${repeatUsername}">That username is already taken, try a different one!</div>
<a href="/login"><--- Back to Login</a>
</body>
</html>