package Support;

import Pokemon.Pokemon;

public class AI {
    private Pokemon p;
    public AI(Pokemon p){
        this.p = p;
    }

    public Skill useSkill(){

        //range 0~3
        int random = (int)(Math.random()*4);
        return p.getSkill(random);
    }

    public Pokemon getPokemon(){
        return p;
    }
}
