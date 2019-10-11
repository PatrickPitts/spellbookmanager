<!DOCTYPE html>
<html lang="en">
<head>
    <link href="spellstyles.css" rel="stylesheet" type="text/css">
</head>
<body>
<h2>Display all Spell Books</h2>

<th:block th:each="spellbook : ${spellbookList}">
    <a th:href="@{/view-spellbook(spellbookID=${spellbook.spellbookID})}">
        <div th:text="${spellbook.spellbookName}" class="selector">SpellbookName</div>
    </a>
</th:block>
<a th:href="@{/add-spellbook}">
    <div class="spellbook-selector"> + New Spell Book</div>
</a>

</body>
</html>
