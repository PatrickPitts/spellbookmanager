<!DOCTYPE html>
<html lang="en" xmlns:th="https://www.thymeleaf.org">
<head>
    <link href="css/spellstyles.css" rel="stylesheet" type="text/css">
    <title>Spell Directory</title>
</head>
<body>
<nav>
    <div th:if="${session.username == null}">
        <form th:object="${basicUser}" th:action="@{/login}">
            <label th:for="basicUser-username">Username:</label><input type="text" th:field="${basicUser.username}"
                                                                       id="basicUser-username"><br>
            <label th:for="basicUser-password">Password:</label><input type="password" th:field="${basicUser.password}"
                                                                       id="basicUser-password">
            <input type="submit" value="Login">
        </form>
        <div th:if="${session.invalidUsername}" style="color:lightpink">Invalid Username or Password</div>
        New to Spellbook Manager? <a th:href="@{/new-user}">Click here</a> to make a new account.
        <hr>
    </div>
    <div th:if="${session.username}">
        <a th:href="@{/spellbook-directory}">My Spellbooks</a>
        <a th:href="@{/logout}">Log out</a>
        <hr>
    </div>
    <div>
        Administrator Options:
        <a th:href="@{/add-spell}">Add another spell to the list</a>
        <a th:href="@{/spells-to-caster}">Assign Spells to a Caster Class</a>
    </div>
</nav>
<div class="main">
    <div name="spell-directory-actions" class="spell-directory-actions">
        <th:block th:if="${session.username}">
            <h2>Welcome <span th:text="${session.username}"></span></h2>
        </th:block>

        <h2><a th:href="@{/}">Spell Directory</a></h2>
        <div>
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
                <table style="border:solid black;">
                    <tr>
                        <th colspan="2"><strong>Search Options:</strong></th>
                    </tr>
                    <tr>
                        <td style="text-align: right;"><label th:for="name">Name:</label></td>
                        <td style="text-align: left;"><input type="text" id="name" name="name" th:field="*{spellName}"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;"><label th:for="spellLevel">Spell Level: </label></td>
                        <td style="text-align: left;"><select name="spellLevel" id="spellLevel"
                                                              th:field="*{spellLevel}">
                            <option th:value="N"></option>
                            <option th:each="val : ${#numbers.sequence(0,9)}" th:value="${val}"
                                    th:text="${val}"></option>
                        </select></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;"><label for="school">School:</label></td>
                        <td style="text-align: left;"><select name="school" id="school" th:field="*{school}">
                            <option value=""></option>
                            <option th:each="val : ${schoolList}" th:value="${val}" th:text="${val}"></option>
                        </select></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;"><label for="caster">Caster Class:</label></td>
                        <td style="text-align: left;"><select name="caster" id="caster" th:field="*{caster}">
                            <option value=""></option>
                            <option th:each="val : ${casterList}" th:value="${val}" th:text="${val}"></option>
                        </select></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;"><label for="ritual">Ritual Casting?</label></td>
                        <td style="text-align: left;"><input type="checkbox" name="ritual" id="ritual"
                                                             th:field="*{ritualCasting}"/></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;"><label for="concentration">Concentration?</label></td>
                        <td style="text-align: left;"><input type="checkbox" name="concentration" id="concentration"
                                                             th:field="*{concentration}"/><br>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="submit" value="Search!">
                        </td>
                    </tr>
                </table>

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

