<!DOCTYPE html>
<html>
<head>
    <link href="spellstyles.css" rel="stylesheet" type="text/css">

</head>
<body th:class="${spell.school}">

<h2 th:text="${spell.name}">Spell Name</h2>

<div th:switch="${spell.level}">
    <p  th:case="0">[[${spell.school}]] Cantrip</p>
    <p th:case="*">Level [[${spell.level}]] [[${spell.school}]]</p>

</div>

<p class="spell-description-wrapper" th:text="${spell.description}">Spell Description Text</p><br><br>
<a href="/">Back to Directory</a>
</body>
</html>