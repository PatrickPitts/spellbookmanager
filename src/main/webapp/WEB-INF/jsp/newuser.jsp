<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org"
      xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>Phylactory: New User</title>
</head>
<body class="main">
<div class="login-form-wrapper">
    <form th:action="@{/new-user}" th:object="${basicUser}" method="post">
        <input type="hidden" th:field="${basicUser.role}" th:value="USER">
        <table>
            <tr>
                <td><label> User Name : </label></td>
                <td><input type="text" th:field="${basicUser.username}"/></td>
            </tr>
            <tr>
                <td><label> Password: </label></td>
                <td><input type="password" th:field="${basicUser.password}"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Sign Up"/></td>
                <td></td>
            </tr>

        </table>

    </form>
</div>

<p>Don't worry, login credentials are used just to show you your own spell books. Still, don't use your bank account
    password, security's
    pretty minimal around here.
</p>
<div th:if="${repeatUsername}">That username is already taken, try a different one!</div>
<a href="/"><--- Back to Login</a>
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