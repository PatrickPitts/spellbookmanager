<!DOCTYPE html>
<html>
<head>
    <link href="spellstyles.css" rel="stylesheet" type="text/css">

</head>
<body th:class="${spell.school}">

<h2 th:text="${spell.name}">Spell Name</h2>

<div th:switch="${spell.spellLevel}">
    <p  th:case="0">[[${spell.school}]] Cantrip</p>
    <p th:case="*">Level [[${spell.spellLevel}]] [[${spell.school}]]</p>

</div>
<div class="spell-description-wrapper">
    <b>Casting Time: </b>[[${spell.castingTime}]]<br>
    <b>Range: </b>[[${spell.range}]]<br>
    <b>Components: </b>
    <th:block th:if="${spell.verbalComponent}">V</th:block>
    <th:block th:if="${spell.somaticComponent}">S</th:block>
    <th:block th:if="${spell.materialComponents} != 'None'">
        M ([[${spell.materialComponents}]])
    </th:block><br>
    <b>Duration: </b>
    <th:block th:if="${spell.concentration}">Concentration, up to </th:block>
    [[${spell.duration}]]<br>
    <b>Casters: </b><!--#TODO Incorporate Casters List-->
    <p th:utext="${spell.description}">Txt</p><br>
</div>
<a href="/">Back to Directory</a>
</body>
</html>