<!DOCTYPE html>


<html lang="en" xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity5"
                xmlns:th="https://www.thymeleaf.org">
<head>
    <link href="css/spellstyles.css" rel="stylesheet" type="text/css">
</head>
<body>
<nav>
    <a th:href="@{/spellbook-directory}">Spellbook Management</a>
    <a th:href="@{/logout}">Log out</a>
    <hr>
    <div sec:authorize="hasRole('ADMIN')">
        Administrator Options:
    <a th:href="@{/add-spell}">Add another spell to the list</a>
    <a th:href="@{/spells-to-caster}">Assign Spells to a Caster Class</a>

        </div>
</nav>
<div class="main">
    <div name="spell-directory-actions" class="spell-directory-actions">
        <h2>Welcome <span sec:authentication="name"></span></h2>
        <h2><a th:href="@{/spell-directory}">Spell Directory</a></h2>
        <div style="border:solid black;">
            <strong>Search Options:</strong><br>
            <th:block th:if="${spellbook != null}">
            <form th:action="@{/spells-for-spellbook(spellbookID=${spellbook.spellbookID})}"
                  th:object="${spellSearchParams}" method="post">
                Searching for Spells for [[${spellbook.spellbookName}]]<br>

                </th:block>
                <th:block th:unless="${spellbook != null}">
                <form th:action="@{/search}" th:object="${spellSearchParams}" method="post">

                    </th:block>

                    <form th:action="@{spellbook!=null ? '/spells-for-spellbook(spellbookID=${spellbook.spellbookID})' : '/search'}"
                          th:object="${spellSearchParams}" method="post">

                        <label th:for="name">Name:</label>
                        <input type="text" id="name" name="name" th:field="*{spellName}"/><br>
                        <label th:for="spellLevel">Spell Level: </label>
                        <select name="spellLevel" id="spellLevel" th:field="*{spellLevel}">
                            <option th:value="N"></option>
                            <option th:each="val : ${#numbers.sequence(0,9)}" th:value="${val}"
                                    th:text="${val}"></option>
                        </select><br>
                        <label for="school">School:</label>
                        <select name="school" id="school" th:field="*{school}">
                            <option value=""></option>
                            <option th:each="val : ${schoolList}" th:value="${val}" th:text="${val}"></option>
                        </select><br>
                        <label for="caster">Caster Class:</label>
                        <select name="caster" id="caster" th:field="*{caster}">
                            <option value=""></option>
                            <option th:each="val : ${casterList}" th:value="${val}" th:text="${val}"></option>
                        </select><br>
                        <label for="ritual">Ritual Casting?</label>
                        <input type="checkbox" name="ritual" id="ritual" th:field="*{ritualCasting}"/><br>
                        <label for="concentration">Concentration?</label>
                        <input type="checkbox" name="concentration" id="concentration" th:field="*{concentration}"/><br>
                        <input type="submit" value="Search!">
                    </form>
        </div>
    </div>
    <div name="spell-list-display" class="spell-display-list">
        <table>
            <tr th:each="spell : ${spells}">
                <td style="text-align: left;">
                    <h3><strong>
                        <a th:href="@{/spell(spellname=${spell.name})}" th:text="${spell.name}">
                            Spell Name
                        </a>
                    </strong></h3>
                    <h5>
                        Level [[${spell.spellLevel}]] [[${spell.school}]]
                        <th:block th:if="${spell.ritualCasting}">(Ritual)</th:block>
                        <th:block th:if="${spell.concentration}">(Concentration)</th:block>
                        <br>
                        <div th:text="${spell.abbreviatedDescription}">Spell Txt</div>
                        <br>

                        <a th:href="@{/edit-spell(spellname=${spell.name})}" class="directory-row-element"
                           sec:authorize="hasRole('ADMIN')">
                            Edit...
                        </a>
                        <th:block th:if="${spellbook != null}">
                            <th:block th:if="${#lists.contains(spellbook.listOfSpellNames, spell.name)}">
                                <div style="text-align: center">&#10004;</div>
                            </th:block>

                            <th:block th:unless="${#lists.contains(spellbook.listOfSpellNames, spell.name)}">
                                <a th:href="@{/add-to-spellbook(spellname=${spell.name},spellbookID=${spellbook.spellbookID})}"
                                   class="directory-row-element" method="post">
                                    <div>(+) Add This Spell</div>
                                </a>
                            </th:block>
                        </th:block>
                    </h5>

                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>

