<!DOCTYPE html>
<html lang="en">
<head>
    <link href="css/spellstyles.css" rel="stylesheet" type="text/css">
</head>
<body>
<nav>
    <a href="/spell-directory">Spell Directory</a>
</nav>
<div class="main">
    <h2>Display all Spell Books</h2>

    <th:block th:each="spellbook : ${spellbookList}">
        <div class="selector">
            <a th:href="@{/view-spellbook(spellbookID=${spellbook.spellbookID})}"
               th:text="${spellbook.spellbookName}">
                SpellbookName
            </a>
            <button>Delete [[${spellbook.spellbookname}]]?</button>
        </div>
    </th:block>
    <a th:href="@{/add-spellbook}">
        <div class="spellbook-selector"> + New Spell Book</div>
    </a>
</div>
</body>
</html>
