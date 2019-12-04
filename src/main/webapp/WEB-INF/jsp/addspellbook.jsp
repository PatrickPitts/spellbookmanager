<!DOCTYPE html>
<html lang="en" xmlns:th="https://www.thymeleaf.org">
<head>
    <link href="css/spellstyles.css" rel="stylesheet" type="text/css">
</head>
<body>
<nav>
    <a href="/spellbook-directory">Back to Spell Books</a>
    <a href="/spell-directory">Back to Spell Directory</a>
</nav>
<div class="main">

<h2>Create a New Spellbook</h2>
<form th:action="@{/add-spellbook}" th:object="${spellBook}" method="post">
    <div th:if="${spellbookNameAlreadyExists}">That name has already been taken, try a different one.</div>
    <label th:for="spellbookName">Spellbook Name: </label>
    <input type="text" id="spellbookName" name="spellbookName" th:field="*{spellbookName}"/><br>
    <label th:for="casterClass">Caster Class? :</label>
    <select name="casterClass" id="casterClass" th:field="*{casterClass}">
        <option th:value="NA" th:text="NA"></option>
        <option th:each="val : ${casterList}" th:value="${val}" th:text="${val}"></option>
    </select>
    <input type="submit" value="Add This Spellbook"/>
</form>
</div>
</body>
</html>
