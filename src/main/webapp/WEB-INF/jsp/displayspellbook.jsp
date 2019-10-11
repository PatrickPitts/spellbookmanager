<!DOCTYPE html>
<html lang="en">
<head>
    <link href="spellstyles.css" rel="stylesheet" type="text/css">
</head>
<body>
<h2>[[${spellbook.spellbookName}]]</h2>

<div class="spellbook-selector">
    <a th:href="@{/spells-for-spellbook(spellbookID=${spellbook.spellbookID})}">+ Add Spell</a>
</div>
<th:block th:each="spellLevel : ${#numbers.sequence(0,9)}">
    <div style="text-decoration: underline;font-size: small; color: darkgray">
    <th:block th:if="${spellLevel == 0}">
        Cantrips:<br>
    </th:block>
    <th:block th:unless="${spellLevel == 0}">
        Level [[${spellLevel}]] spells<br>
    </th:block>
    </div>
    <th:block th:each="spell : ${spellbook.listOfSpells}">
        <th:block th:if="${spell.spellLevel == spellLevel}">
            <a th:href="@{/spell(spellname=${spell.name})}" th:text="${spell.name}">
                Spell Name
            </a><br>
        </th:block>
    </th:block>
</th:block>
<br>
<a href="/spellbook-directory" class="selector">< Back to Spell Books</a><br>
<a href="/" class="selector"><<< Back to Spell Directory</a>
</body>
</html>
