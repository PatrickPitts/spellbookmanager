<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="spellstyles.css" rel="stylesheet" type="text/css">
</head>
<body>
<h2>Display all Spell Books</h2>

<select name="newList" MULTIPLE>
    <option>A</option>
    <option>B</option>
    <option>C</option>
</select>

<form th:action="@{/add-spellbook}">
    <input type="submit" value="Create a New Spellbook">
</form>
</body>
</html>
