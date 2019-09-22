<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="spellstyles.css" rel="stylesheet" type="text/css">
</head>
<body>
<h2>Create a New Spellbook</h2>
<form th:action="@{/add-spellbook}" th:object="${spellBook}" method="post">
    <label th:for="spellbookName">Spellbook Name: </label>
    <input type="text" id="spellbookName" name="spellbookName" th:field="*{spellbookName}"/><br>
    <label th:for="casterClass">Caster Class? :</label>
    <select name="casterClass" id="casterClass" th:field="*{casterClass}">
        <option th:value="NA" th:text="NA"></option>
        <option th:each="val : ${casterList}" th:value="${val}" th:text="${val}"></option>
    </select>
    <input type="submit" value="Add This Spellbook"/>
</form>
</body>
</html>
