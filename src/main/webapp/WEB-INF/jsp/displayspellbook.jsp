<!DOCTYPE html>
<html lang="en">
<head>
    <link href="css/spellstyles.css" rel="stylesheet" type="text/css">
</head>
<body>
<nav>
    <a href="/spellbook-directory">< Back to Spell Books</a>

    <a href="/spell-directory"><<< Back to Spell Directory</a>
    <hr>
    <a th:href="@{/delete-spellbook(spellbookID=${spellbook.spellbookID})}"
       onclick="return confirm('Are you sure you want to delete this Spellbook? All your data will be lost (Not that you couldn\'t make it again, it\'s not that hard.)')">
        Delete This Spellbook</a>

</nav>
<div class="main">

    <h2>[[${spellbook.spellbookName}]]</h2>

    <div>
        <a th:href="@{/spells-for-spellbook(spellbookID=${spellbook.spellbookID})}">+ Add Spell</a>
    </div>
    <th:block th:each="spellLevel : ${#numbers.sequence(0,9)}">
        <div style="text-decoration: underline; color: darkgray">
            <th:block th:if="${spellLevel == 0}">
                Cantrips:<br>
            </th:block>
            <th:block th:unless="${spellLevel == 0}">
                Level [[${spellLevel}]] spells<br>
            </th:block>
        </div>
        <th:block th:each="spell : ${spellbook.listOfSpells}">
            <th:block th:if="${spell.spellLevel == spellLevel}">
                <a th:href="@{/spell(spellname=${spell.name},spellbookID=${spellbook.spellbookID})}" th:text="${spell.name}">
                    Spell Name
                </a><br>
            </th:block>
        </th:block>
    </th:block>
    <br>
</div>
</body>
</html>
