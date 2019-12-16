<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org"
      xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>Spring Security Example </title>
</head>
<body class="main">
<h2>Welcome to Spellbook Manager! Please log in to continue.</h2><br>
<div th:if="${param.error}">
    Invalid username and password.
</div>
<div th:if="${param.logout}">
    You have been logged out.
</div>
<div class="login-form-wrapper">
    <form th:action="@{/login}" method="post">
        <table>
            <tr>
                <td><label> User Name: </label></td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td><label> Password: </label></td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Sign In"/></td>
            </tr>
        </table>

    </form>
</div>
<br>
<div>New to Spellbook Manger? <a href="/new-user">Create new Account</a></div>
</body>
</html>
<style>
    body {
        font-family: Alegreya, sans-serif;
        background: #FBE28A;
    }

    .login-form-wrapper {
        border: solid 2px black;
        font-size: 1em;
        padding: 1em;
        -webkit-border-radius: 5px;
        -moz-border-radius: 5px;
        border-radius: 5px;
        text-align: center;
        width: inherit;

    }
</style>
