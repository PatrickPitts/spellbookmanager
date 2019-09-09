package org.nerdcore.spellbookmanager.models;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.json.simple.JSONObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Spell {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(length=5000)
    private String description;
    private int level;
    private String school;
    private String castingTime;
    private String range;
    private boolean verbalComponent;
    private boolean somaticComponent;
    private String materialComponents;
    private String duration;
    private String source;
    private boolean ritualCasting;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCastingTime() {
        return castingTime;
    }

    public void setCastingTime(String castingTime) {
        this.castingTime = castingTime;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public boolean isVerbalComponent() {
        return verbalComponent;
    }

    public void setVerbalComponent(boolean verbalComponent) {
        this.verbalComponent = verbalComponent;
    }

    public boolean isSomaticComponent() {
        return somaticComponent;
    }

    public void setSomaticComponent(boolean somaticComponent) {
        this.somaticComponent = somaticComponent;
    }

    public String getMaterialComponents() {
        return materialComponents;
    }

    public void setMaterialComponents(String materialComponent) {
        this.materialComponents = materialComponent;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isRitualCasting() {
        return ritualCasting;
    }

    public void setRitualCasting(boolean ritualCasting) {
        this.ritualCasting = ritualCasting;
    }


    @Override
    public String toString() {
        String ret = "";
        ret += "Name: " + this.name + "\n";
        ret += "School: " + this.school + "\n";
        ret += "Range: " + this.range + "\n";
        ret += "Level: " + this.level + "\n";
        ret += "castingTime: " + this.castingTime + "\n";
        ret += "verbalComponent: " + this.verbalComponent + "\n";
        ret += "somaticComponent: " + this.somaticComponent + "\n";
        ret += "materialComponents: " + this.materialComponents + "\n";
        ret += "duration: " + this.duration + "\n";
        ret += "description: " + this.description + "\n";
        ret += "source: " + this.source + "\n";
        ret += "ritualCasting: " + this.ritualCasting;

        return ret;
    }

    public JSONObject getJSONObject(){
        JSONObject obj = new JSONObject();
        obj.put("name", this.name);
        obj.put("school", this.school);
        obj.put("range", this.range);
        obj.put("level", this.level);
        obj.put("castingTime", this.castingTime);
        obj.put("verbalComponent", this.verbalComponent);
        obj.put("somaticComponent", this.somaticComponent);
        obj.put("materialComponents", this.materialComponents);
        obj.put("duration", this.duration);
        obj.put("description", this.description);
        obj.put("source", this.source);
        obj.put("ritualCasting", this.ritualCasting);
        return obj;
    }

    public Spell(JSONObject obj) {
        this.name = (String) obj.get("name");
        this.school = (String) obj.get("school");
        this.range = (String) obj.get("range");
        this.level = ((Long) obj.get("level")).intValue();
        this.castingTime = (String) obj.get("castingTime");
        this.verbalComponent = (Boolean) obj.get("verbalComponent");
        this.somaticComponent = (Boolean) obj.get("somaticComponent");
        this.materialComponents = (String) obj.get("materialComponents");
        this.duration = (String) obj.get("duration");
        this.description = (String) obj.get("description");
        this.source = (String) obj.get("source");
        this.ritualCasting = (Boolean) obj.get("ritualCasting");


    }


    public Spell() {
    }
}
