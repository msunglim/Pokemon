package Pokemon;

import Support.Skill;
import Support.Type;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

public class Pokemon {

    private String name;
    private ImageView iv;
    private int hp;
    private HashMap<Integer, Skill> skillList;
    private int currHP;
    private Type type;

    public Pokemon(String name, ImageView iv, int hp, Type type) {
        this.name = name;
        this.iv = iv;
        this.hp = hp;
        this.type = type;
        skillList = new HashMap(4);
        currHP = hp;
    }

    public Type getType(){
        return type;
    }
    public void setCurrHP(int updatedHP) {
        if (updatedHP <= 0) {
            currHP = 0;
        } else {
            currHP = updatedHP;
        }
    }

    public int getCurrHP() {
        return currHP;
    }

    public int getHp() {
        return hp;
    }

    public HashMap<Integer, Skill> getSkillList() {
        return skillList;
    }

    public Skill getSkill(int i) {
        return skillList.get(i);
    }

    public void learnSkill(Skill skill) {
        skillList.put(skillList.size(), skill);
    }

    public String getName() {
        return name;
    }

    public ImageView getIv() {
        iv.setFitWidth(200);
        iv.setFitHeight(200);
        return iv;
    }
}
