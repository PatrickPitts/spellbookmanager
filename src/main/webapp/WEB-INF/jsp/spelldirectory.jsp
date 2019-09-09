<!DOCTYPE html>


<html lang="en">
<head>
    <link href="spellstyles.css" rel="stylesheet" type="text/css">
</head>
<body>
<h2>Spell Directory</h2>
<div id="directory-wrapper">
    <th:block th:each="spell : ${spells}" >
        <a th:href="@{/spell(spellname=${spell.name})}">
        <div th:text="${spell.name}" th:class="${spell.school}">Spell Name</div>
        </a>
    </th:block>
</div>
<br>
<br><br><br>
<a href="/add-spell">Add another spell to the list</a><br>
</body>
</html>

