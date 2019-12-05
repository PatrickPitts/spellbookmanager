<!DOCTYPE html>
<html lang="en">
<head>
    <link href="css/spellstyles.css" rel="stylesheet" type="text/css">
</head>
<body>
<nav>
    <a href="/">Spell Directory</a>
</nav>
<div class="main">
    <h2>Your Spell Books</h2>
    <table>
        <tr th:each="spellbook : ${spellbookList}">
            <td>
                <div class="selector">
                    <a th:href="@{/view-spellbook(spellbookID=${spellbook.spellbookID})}"
                       th:text="${spellbook.spellbookName}">
                        SpellbookName
                    </a></div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="selector">
                    <a th:href="@{/add-spellbook}">+ New Spell Book</a>
                </div>
            </td>
        </tr>
    </table>
</div>
</div>
</body>
</html>
