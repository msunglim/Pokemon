package Pokemon;

import Main.Main;
import Support.TypeUniverse;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class PokemonUniverse {
    Pokemon list[] = new Pokemon[10];
    public PokemonUniverse(Main main, TypeUniverse tu){

        list[0] = new Pokemon("피카츄", new ImageView(new Image("file:data/Pikachu.png")), 100, tu.getType(2));
        list[1] = new Pokemon("야돈", new ImageView(new Image("file:data/Yadon.png")), 180, tu.getType(0));


        list[0].learnSkill(main.getSu().getSkill(0));
        list[0].learnSkill(main.getSu().getSkill(1));
        list[0].learnSkill(main.getSu().getSkill(4));
        list[0].learnSkill(main.getSu().getSkill(7));

        list[1].learnSkill(main.getSu().getSkill(5));
        list[1].learnSkill(main.getSu().getSkill(6));
        list[1].learnSkill(main.getSu().getSkill(4));
        list[1].learnSkill(main.getSu().getSkill(7));
    }



    public Pokemon getPokemon(int i){
        return list[i];
    }
}
