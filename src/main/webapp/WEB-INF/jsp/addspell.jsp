<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>


<html lang="en">
<head>
    <link href="spellstyles.css" rel="stylesheet" type="text/css">
</head>
<body>
<h2>Add A Spell to the Directory</h2>
<form action="#" th:action="@{/}" th:object="${spell}" method="post">
    <label th:for="name">Spell Name:  </label>
    <input type="text" id="name" name="name" th:field="*{name}"/>
    <label th:for="school">School:  </label>
    <select name="school" id="school" th:field="*{school}">
        <option th:each="val : ${schoolList}" th:value="${val}" th:text="${val}"></option>
    </select>

    <label th:for="level">Spell Level:  </label>
    <select name="level" id="level" th:field="*{level}">
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
    <input type="text" id="duration" name="duration" th:field="*{duration}" /><br>
    
    <label th:for="description">Spell Description:</label><br>
    <textarea id="description" name="description" rows="10" cols="50" th:field="*{description}"></textarea><br>

    <label th:for="source">Source: </label>
    <input type="text" id="source" name="source" th:field="*{source}"  /><br>
    <input type="submit" value="Submit"/>
    
</form>

<a href="/">Back to Directory</a>
</body>
</html>