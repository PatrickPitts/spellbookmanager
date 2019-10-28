<!DOCTYPE html>
<html lang="en">
<head>
    <link href="css/spellstyles.css" rel="stylesheet" type="text/css">
</head>
<body>
<nav>
    <a href="/spell-directory">Spell Directory</a>
</nav>
<div class="main">
    <h2>Spell Books</h2>
    <table>
        <tr th:each="spellbook : ${spellbookList}">
            <td>
                <div class="selector">
                    <a th:href="@{/view-spellbook(spellbookID=${spellbook.spellbookID})}"
                       th:text="${spellbook.spellbookName}">
                        SpellbookName
                    </a></div>
            </td>
            <td>
                <button>Delete [[${spellbook.spellbookName}]]?</button>
            </td>

        </tr>
    </table>
</div>
<a th:href="@{/add-spellbook}">
    <div class="spellbook-selector"> + New Spell Book</div>
</a>
</div>
</body>
</html>
