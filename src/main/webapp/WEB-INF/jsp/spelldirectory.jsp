<!DOCTYPE html>


<html lang="en">
<head>
    <link href="spellstyles.css" rel="stylesheet" type="text/css">
    <%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>

</head>
<body>
<h2>Spell Directory</h2>

<div id="directory-wrapper">
    <div name="spell-directory-actions" class="spell-directory-actions">
        Sorting options<br>
        Spellbook Management<br>
        <a th:href="@{/add-spell}">Add another spell to the list</a><br>

        Search Options:<br>
        <form th:action="@{/search}" th:object="${searchParams}" method="post">
            <label th:for="name">Name:</label>
            <input type="text" id="name" name="name" th:field="*{spellName}"/><br>
            <label th:for="level">Spell Level:  </label>
            <!--TODO: Adjust SpellLevel Search paramter-->
            <select name="level" id="level" th:field="*{spellLevel}">
                <option th:each="val : ${#numbers.sequence(0,9)}" th:value="${val}" th:text="${val}"></option>
            </select><br>
            <label for="school">School:</label>
            <select name="school" id="school" th:field="*{school}">
                <option th:each="val : ${schoolList}" th:value="${val}" th:text="${val}"></option>
            </select><br>
            <label for="ritual">Ritual Casting?</label>
            <input type="checkbox" name="ritual" id="ritual"/>
            <label for="concentration">Concentration?</label>
            <input type="checkbox" name="concentration" id="concentration"/>
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

