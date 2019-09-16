<!DOCTYPE html>


<html lang="en">
<head>
    <link href="spellstyles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="directory-wrapper">
    <div name="spell-directory-actions" class="spell-directory-actions">
        <h2><a th:href="@{/}">Spell Directory</a></h2>
        Sorting options<br>
        Spellbook Management<br>
        <a th:href="@{/add-spell}">Add another spell to the list</a><br>

        Search Options:<br>
        <form th:action="@{/search}" th:object="${searchParams}" method="post">
            <label th:for="name">Name:</label>
            <input type="text" id="name" name="name" th:field="*{spellName}"/><br>
            <label th:for="level">Spell Level: </label>
            <select name="level" id="level" th:field="*{spellLevel}">
                <option th:value="N"></option>
                <option th:each="val : ${#numbers.sequence(0,9)}" th:value="${val}" th:text="${val}"></option>
            </select><br>
            <label for="school">School:</label>
            <select name="school" id="school" th:field="*{school}">
                <option value=""></option>
                <option th:each="val : ${schoolList}" th:value="${val}" th:text="${val}"></option>
            </select><br>
            <label for="ritual">Only Ritual Casting?</label>
            <input type="checkbox" name="ritual" id="ritual" th:field="*{ritualCasting}"/>
            <label for="concentration">Only Concentration?</label>
            <input type="checkbox" name="concentration" id="concentration" th:field="*{concentration}"/>
            <input type="submit" value="Submit">
        </form>
    </div>
    <div name="spell-list-display" class="spell-display-list">

        <th:block th:each="spell : ${spells}">

            <a th:href="@{/spell(spellname=${spell.name})}">
                <div th:text="${spell.name}" th:class="${spell.school}">Spell Name</div>
            </a>


            <a th:href="@{/edit-spell(spellname=${spell.name})}">
                <div th:class="${spell.school}">Edit...</div>
            </a>

            <a th:href="@{/delete-spell(spellname=${spell.name})}">
                <div th:class="${spell.school}">[X]</div>
            </a><br>


        </th:block>
    </div>
</div>
<br>
<br><br><br>

</body>
</html>

