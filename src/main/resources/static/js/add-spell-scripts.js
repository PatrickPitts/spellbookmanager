function buildTable() {
    var textArea = document.createElement('textarea');
    textArea.setAttribute('readonly', '');
    textArea.style.position = 'absolute';
    textArea.style.left = '-9999px';

    var rows = document.getElementById("tableNumRows").value;
    var cols = document.getElementById("tableNumCols").value;

    var td = "<td></td>";

    var txt = "<table>\n  <tr>\n";
    for (var i = 0; i < cols; i++) {
        txt += "    <th></th>\n";
    }
    txt += "  </tr>\n  ";
    for (i = 1; i < rows; i++) {
        txt+="  <tr>\n";

        for (var j = 0; j < cols; j++) {
            txt += "    "+td + "\n";
        }
        txt += "  </tr>\n";
    }
    txt += "</table>";
    textArea.value=txt;
    document.body.appendChild(textArea);
    textArea.select();
    document.execCommand("copy");
    document.body.removeChild(textArea);
    document.getElementById("description").focus();
}

function buildUnorderdList() {

    var textArea = document.createElement('textarea');
    textArea.setAttribute('readonly', '');
    textArea.style.position = 'absolute';
    textArea.style.left = '-9999px';
    var txt = "<ul> \n";
    var ulCount = document.getElementById("listItemCount").value;
    for (var i = 0; i < ulCount; i++) {
        txt += "<li></li> \n";
    }
    txt += "</ul>";
    textArea.value = txt;
    document.body.appendChild(textArea);
    textArea.select();
    document.execCommand("copy");
    document.body.removeChild(textArea);
    document.getElementById("description").focus();

}


function addLinebreakToClipboard() {

    var textArea = document.createElement('textarea');
    textArea.setAttribute('readonly', '');
    textArea.style.position = 'absolute';
    textArea.style.left = '-9999px';

    var txt = "<br>&emsp;";
    textArea.value = txt;
    document.body.appendChild(textArea);
    textArea.select();
    document.execCommand("copy");
    document.body.removeChild(textArea);
    document.getElementById("description").focus();
}

function addLinebreakAndAtHigherLevelsToClipboard() {
    var textArea = document.createElement('textarea');
    textArea.setAttribute('readonly', '');
    textArea.style.position = 'absolute';
    textArea.style.left = '-9999px';

    var txt = "<br>&emsp;<b><i>At Higher Levels.</i></b>";
    textArea.value = txt;
    document.body.appendChild(textArea);
    textArea.select();
    document.execCommand("copy");
    document.body.removeChild(textArea);
    document.getElementById("description").focus();
}

function addBr() {
    var textarea = document.getElementById("description");
    var start = textarea.selectionStart;
    var finish = textarea.selectionEnd;
    var allText = textarea.value;

    var selection = allText.substring(start, finish);
    var newText = allText.substring(0, start) + "<br>&emsp;" + selection + allText.substring(finish, allText.length);
    textarea.value = newText;

}

function AtHigherLevel() {
    var textarea = document.getElementById("description");
    var start = textarea.selectionStart;
    var finish = textarea.selectionEnd;
    var allText = textarea.value;

    var selection = allText.substring(start, finish);
    textarea.value = allText.substring(0, start) + "<br>&emsp;<i><b>" + selection + "</i></b>" + allText.substring(finish, allText.length);

}

document.onkeydown = function (e) {
    if (e.ctrlKey && e.shiftKey && e.which === 81) {
        addBr();
    } else if (e.ctrlKey && e.which === 81) {
        AtHigherLevel();
    }
}

window.onload = function () {
    document.getElementById("name").focus;
}