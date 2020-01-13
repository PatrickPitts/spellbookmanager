<!DOCTYPE html>


<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <link href="css/add-spell-form.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="images/d20favicon.png">
    <title>Add a New Spell to the Database</title>
    <script src="js/add-spell-scripts.js"></script>
</head>
<body style="display: flex;">
<nav>
    <a href="/">Back to Directory</a>
</nav>
<div class="main">
    <div style="float:left;flex:2;">
        <h2>[[${title}]]</h2><br>
        <form th:action="@{/{action}(action=${action})}" th:object="${spell}" method="post">
            <input type="hidden" th:field="*{id}" th:value="${spell.id}">

            <table>
                <tr>
                    <td><label th:for="name" style="float:right;" class="tooltip">Spell Name:
                        <span class="tooltiptext"><b>Spell Name:</b><br>What's the name of your spell?</span> </label>
                    </td>
                    <td><input type="text" id="name" name="name" th:field="*{name}"/></td>
                    <td><label th:for="school" style="float:right;" class="tooltip">School:
                        <span class="tooltiptext"><b>School:</b><br>Which of the following 8 schools of magic does this spell belong to?
            <ul><li>Abjuration</li><li>Conjuration</li><li>Divination</li><li>Evocation</li><li>Enchantment</li>
                <li>Illusion</li><li>Necromancy</li><li>Transmutation</li></ul></span></label></td>
                    <td><select name="school" id="school" th:field="*{school}">
                        <option th:each="val : ${schoolList}" th:value="${val}" th:text="${val}"></option>
                    </select></td>
                </tr>
                <tr>
                    <td><label th:for="spellLevel" style="float:right;">Spell Level: </label></td>
                    <td><select name="spellLevel" id="spellLevel" th:field="*{spellLevel}">
                        <option th:each="val : ${#numbers.sequence(0,9)}" th:value="${val}" th:text="${val}"></option>
                    </select></td>
                    <td><label th:for="castingTime" style="float:right;">Casting Time: </label></td>
                    <td><input type="text" id="castingTime" name="castingTime" th:field="*{castingTime}"/></td>


                </tr>
                <tr>
                    <td><label th:for="range" style="float:right;">Range: </label></td>
                    <td><input type="text" id="range" name="range" th:field="*{range}"/></td>
                    <td><label th:for="ritualCasting" style="float:right;">Ritual Casting?</label></td>
                    <td><input type="checkbox" id="ritualCasting" name="ritualCasting" th:field="*{ritualCasting}"></td>

                </tr>
                <tr>
                    <td><label th:for="verbal" style="float:right;">Verbal Component?</label></td>
                    <td><input type="checkbox" id="verbal" name="verbal"
                               th:field="*{verbalComponent}"></td>
                    <td><label th:for="concentration" style="float:right;">Concentration?</label></td>
                    <td><input type="checkbox" id="concentration" name="concentration" th:field="*{concentration}"/>
                    </td>

                </tr>
                <tr>
                    <td><label th:for="somatic" style="float:right;" class="tooltip">Somatic Component?
                        <span class="tooltiptext"><b>Somatic Components:</b><br>Does your spell require extraneous gesticulation? Check it off here!</span>
                    </label></td>
                    <td><input type="checkbox" id="somatic" name="somatic" th:field="*{somaticComponent}"></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td><label th:for="material" style="float:right;" class="tooltip">Material Components:
                        <span class="tooltiptext"><b>Material Components:</b><br> If your spell requires specific material components, type them in here.
            If you're spell DOESN'T have any Material components, leave the text set to "None". When displaying components, we wont
            rendure any material components if this field is set to "None".</span>
                    </label></td>
                    <td><input type="text" id="material" name="material" th:field="*{materialComponents}"></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td><label th:for="duration" style="float:right;">Duration: </label></td>
                    <td><input type="text" id="duration" name="duration" th:field="*{duration}"/></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td><label th:for="source" style="float:right;">Source: </label></td>
                    <td><input type="text" id="source" name="source" th:field="*{source}"/></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
            <label th:for="description" class="tooltip">Spell Description:
                <span class="tooltiptext"><b>Spell Description:</b><br>Describe the effects of your spell here. Take care with text formatting,
    as this text is displayed directly in HTML. Use HTML tags to implement any formatting options, such as line breaks, bold face,
    or lists. Check out some of the options to the right for quick </span> </label><br>
            <textarea id="description" name="description" rows="25" cols="75"
                      th:field="*{description}"></textarea><br>

            <!--
    <label th:for="name">Spell Name:    </label>
    <input type="text" id="name" name="name" th:field="*{name}"/>
    <label th:for="school">School: </label>
    <select name="school" id="school" th:field="*{school}">
        <option th:each="val : ${schoolList}" th:value="${val}" th:text="${val}"></option>
    </select>

    <label th:for="spellLevel">Spell Level: </label>
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

    <lable th:for="components">Components:</lable>
    <label th:for="verbal">Verbal:</label><input type="checkbox" id="verbal" name="verbal"
                                                 th:field="*{verbalComponent}">
    <label th:for="somatic">Somatic:</label><input type="checkbox" id="somatic" name="somatic"
                                                   th:field="*{somaticComponent}">
    <label th:for="somatic">Material: </label><input type="text" id="material" name="material"
                                                     th:field="*{materialComponents}"><br>

    <label th:for="duration">Duration: </label>
    <input type="text" id="duration" name="duration" th:field="*{duration}"/>

    <label th:for="concentration">Concentration?: </label>
    <input type="checkbox" id="concentration" name="concentration" th:field="*{concentration}"/>
    <br>

    <label th:for="description">Spell Description:</label><br>
    <textarea id="description" name="description" rows="25" cols="75"
              th:field="*{description}"></textarea><br>

    <label th:for="source">Source: </label>
    <input type="text" id="source" name="source" th:field="*{source}"/><br>
    -->
            <input type="submit" value="Submit"/>

        </form>
    </div>
    <div style="float: left; margin:5em; flex:1">
        <button onclick="addLinebreakToClipboard()">Linebreak to Clipboard</button>
        <br>
        <hr>
        <button onclick="addLinebreakAndAtHigherLevelsToClipboard()">'At Higher Levels.' to
            Clipboard
        </button>
        <br>
        <hr>
        <input type="number" name="listItemCount" id="listItemCount" step="1" style="width:4em"/>
        <label th:for="listItemCount">Number of List Items: </label><br>

        <button onclick="buildUnorderdList()">Copy Bulletpoint List</button>
        <br>
        <hr>
        <input type="number" id="tableNumRows" step="1" style="width:4em"/>
        <label th:for="tableNumRows">Rows in Table (including header)</label><br>
        <input type="number" id="tableNumCols" step="1" style="width:4em"/>
        <label th:for="tableNumCols">Columns in Table</label><br>
        <button onclick="buildTable()">Table to Clipboard</button>

        <br>
    </div>


</div>
</body>
</html>