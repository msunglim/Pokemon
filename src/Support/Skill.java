package Support;

public class Skill {

    private String name;
    private int damage;
    private Type type;

    public Skill(String name, int damage, Type type) {
        this.name = name;
        this.damage = damage;
        this.type = type;
    }
    public Type getType(){
        return type;
    }
    public int getDamage(){
        return damage;
    }
    public String getName(){
        return name;
    }
}
