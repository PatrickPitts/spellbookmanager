<!DOCTYPE html>


<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:form="http://www.springframework.org/tags/form">
<head>
    <link href="css/spellstyles.css" rel="stylesheet" type="text/css">
    <script src="js/spells-to-caster-scripts.js"></script>
</head>
<body>
<form th:action="@{/spells-to-caster}" th:object="${casterSpellList}" method="post" id="spell-to-caster-form"
      onsubmit="buildCasterList()">


    <table>
        <tr>
            <td>
                <label th:for="casterClassName">Caster Class Name:</label><br>
                <input type="text" th:field="${casterSpellList.casterClassName}" id="casterClassName"/>
            </td>
            <td>
                <select id="masterList" multiple ondblclick="sendSelectedFromMasterListToClassSpellList()"
                        onkeydown="sendSelectedFromClassSpellListToMasterList()"  size="20">
                    <th:block th:each="spellLevel : ${#numbers.sequence(0,9)}">
                        <th:block th:if="${spellLevel == 0}">
                            <option th:value="N" th:text="'------Cantrips------'"></option>
                        </th:block>
                        <th:block th:unless="${spellLevel==0}">
                            <option th:value="N" th:text="'-----Level ' + ${spellLevel} + ' Spells-----'"></option>
                        </th:block>
                        <th:block th:each="spell : ${spells}">
                            <th:block th:if="${spell.spellLevel == spellLevel}">
                                <option th:value="${spell.name}" th:text="${spell.name}"></option>
                            </th:block>
                        </th:block>
                    </th:block>



                </select>
            </td>
            <td>
                <select id="classSpellList" multiple th:field="${casterSpellList.listOfSpellNames}"
                        ondblclick="sendSelectedFromClassSpellListToMasterList()" size=20
                        style="min-width: 20em;">
                </select>
            </td>
            <td>
                <input type="submit"/>
            </td>
        </tr>
    </table>
</form>
<script>

    function keySendFromMasterToCasterList(e){
        if(e.keyCode===32){
            sendSelectedFromMasterListToClassSpellList();
        }
    }

    function buildCasterList() {

        var lengthOfList = document.getElementById("classSpellList").length;
        for (var i = 0; i < lengthOfList; i++) {
            document.getElementById("classSpellList").options[i].selected = true;
        }
        document.getElementById("spell-to-caster-form").submit();
    }

</script>
</body>
</html>
