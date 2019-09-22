<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="spellstyles.css" rel="stylesheet" type="text/css">
</head>
<body>
<h2>Display all Spell Books</h2>

<form th:action="@{/view-spellbook/">
    <select name="spellbookListDisplay" th:field="*{spellbookName}" MULTIPLE>
        <option th:each="val : ${spellbookList}" th:value="${val.spellbookName}" th:text="${val.spellbookName}"></option>
    </select>

</form>

<form th:action="@{/add-spellbook}">
    <input type="submit" value="Create a New Spellbook">
</form>
</body>
</html>
