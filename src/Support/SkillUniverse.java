package Support;

public class SkillUniverse {

    private Skill []skillList = new Skill[10];
    public SkillUniverse(TypeUniverse tu){
        //노말
        skillList[0] = new Skill("전광석화", 30,tu.getType(4) );
        //전기
        skillList[1] = new Skill("백만볼트", 30, tu.getType(2));
        //강철
        skillList[2] = new Skill("아이언테일", 30, tu.getType(3));
        //노말
        skillList[4]  =new Skill("아무것도안하기",0, tu.getType(4));
        //물
        skillList[5] = new Skill("파도타기", 30, tu.getType(0));
        //불
        skillList[6] = new Skill("불대문자", 30, tu.getType(1));
        //노말
        skillList[7] = new Skill("몸통박치기",50,tu.getType(4));


    }

    public Skill getSkill(int i){
        return skillList[i];
    }
}
