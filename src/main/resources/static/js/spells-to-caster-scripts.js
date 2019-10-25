function sendSelectedFromClassSpellListToMasterList(){
    var spellName = document.getElementById("classSpellList").value;
    var classSpellList = document.getElementById("classSpellList");
    var masterList = document.getElementById("masterList");
    masterList.add(new Option(spellName,spellName));
    //sortSelectList(masterList);
    classSpellList.options[classSpellList.selectedIndex]=null;
}

function sendSelectedFromMasterListToClassSpellList(){
    var spellName = document.getElementById("masterList").value;
    var classSpellList = document.getElementById("classSpellList");
    var masterList = document.getElementById("masterList");
    if(spellName !== "N"){
        classSpellList.add(new Option(spellName, spellName));
        //sortSelectList(classSpellList);
        masterList.options[masterList.selectedIndex]=null;
        classSpellList.selectedIndex = classSpellList.options.length-1;
    }


}


function sortSelectList(list){

    var temp = [];
    while(list.options.length > 0){
        temp.push(list.options[0].value);
        list.options[0] = null
    }
    temp.sort();
    for(var i = 0; i < temp.length; i++){
        list.add(new Option(temp[i],temp[i]))
    }
}

function selectAllCasterSpellList() {

    var casterSpellList = document.getElementById("casterSpellList");
    for(var i = 0; i < casterSpellList.length; i++){
        casterSpellList.options[i].selected = true;
    }
}


