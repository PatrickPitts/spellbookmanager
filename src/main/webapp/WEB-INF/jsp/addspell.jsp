
<!DOCTYPE html>


<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <link href="spellstyles.css" rel="stylesheet" type="text/css">
</head>
<body>
<h2>[[${title}]]</h2><br>

<th:block th:if="${action}=='edit-spell'">
    [[${spell.id}]]
</th:block>

<form th:action="@{/{action}(action=${action})}" th:object="${spell}" method="post">
    <input type="hidden" th:field="*{id}" th:value="${spell.id}">
    <label th:for="name">Spell Name:  </label>
    <input type="text" id="name" name="name" th:field="*{name}"/>
    <label th:for="school">School:  </label>
    <select name="school" id="school" th:field="*{school}">
        <option th:each="val : ${schoolList}" th:value="${val}" th:text="${val}"></option>
    </select>

    <label th:for="spellLevel">Spell Level:  </label>
    <select name="spellLevel" id="spellLevel" th:field="*{spellLevel}">
        <option th:each="val : ${#numbers.sequence(0,9)}" th:value="${val}" th:text="${val}"></option>
    </select>

    <br>

    <label th:for="castingTime">Casting Time: </label>
    <input type="text" id="castingTime" name="castingTime" th:field="*{castingTime}"/>

    <label th:for="ritualCasting">Ritual Casting? : </label>
    <input type="checkbox" id="ritualCasting" name="ritualCasting" th:field="*{ritualCasting}">
    <br>

    <label th:for="range">Range: </label>
    <input type="text" id="range" name="range" th:field="*{range}"/><br>

    <lable th:for="components">Components:  </lable>
    <label th:for="verbal">Verbal:</label><input type="checkbox" id="verbal" name="verbal" th:field="*{verbalComponent}">
    <label th:for="somatic">Somatic:</label><input type="checkbox" id="somatic" name="somatic" th:field="*{somaticComponent}">
    <label th:for="somatic">Material: </label><input type="text" id="material" name="material" th:field="*{materialComponents}"><br>

    <label th:for="duration">Duration: </label>
    <input type="text" id="duration" name="duration" th:field="*{duration}" />

    <label th:for="concentration">Concentration?: </label>
    <input type="checkbox" id="concentration" name="concentration" th:field="*{concentration}" />
    <br>
    
    <label th:for="description">Spell Description:</label><br>
    <textarea id="description" name="description" rows="10" cols="50" th:field="*{description}"></textarea><br>

    <label th:for="source">Source: </label>
    <input type="text" id="source" name="source" th:field="*{source}"  /><br>
    <input type="submit" value="Submit"/>
    
</form>
<a href="/">Back to Directory</a>
</body>
</html>