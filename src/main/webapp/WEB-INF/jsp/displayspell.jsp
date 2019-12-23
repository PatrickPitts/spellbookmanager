<!DOCTYPE html>
<html>
<head>
    <link href="css/spellstyles.css" rel="stylesheet" type="text/css">
    <title>[[${spell.name}]]</title>
</head>
<nav>
    <a href="/">Back to Directory</a>
    <hr th:if="${spellbook != null}">
    <a th:if="${spellbook != null}" th:href="@{/drop-spell-from-spellbook(spellname=${spell.name},spellbookID=${spellbook.spellbookID})}">Drop this spell from [[${spellbook.spellbookName}]]</a>
    <a th:if="${spellbook != null}" th:href="@{/view-spellbook(spellbookID=${spellbook.spellbookID})}">Back to Spellbook [[${spellbook.spellbookName}]]</a>
</nav>
<body class="main">
<div th:class="${spell.school}" th:classappend="spell-display-header">
    <h1 th:text="${spell.name}">Spell Name</h1>

    <div th:switch="${spell.spellLevel}">
        <p th:case="0">[[${spell.school}]] Cantrip</p>
        <p th:case="*">Level [[${spell.spellLevel}]] [[${spell.school}]]
            <th:block th:if="${spell.ritualCasting}">(ritual)</th:block></p>
    </div>
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
    <b>Casters: </b><th:block th:each="caster : ${casterList}" th:text="${caster} ">txt</th:block>
    <p th:utext="${spell.description}">Txt</p><br>
</div>

</body>
</html>