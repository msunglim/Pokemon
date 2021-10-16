package Support;

import Pokemon.Pokemon;
import javafx.scene.image.ImageView;

public class Player {
    private Pokemon pokemon;
    private ImageView iv;
    public Player(Pokemon pokemon, ImageView iv){
        this.pokemon = pokemon;
        this.iv = iv;
        iv.setFitHeight(150);
        iv.setFitWidth(150);
    }
    public ImageView getIv(){
        return  iv;
    }

    public Pokemon getPokemon(){
        return pokemon;
    }
    public void setIv(ImageView iv){
        this.iv = iv;
    }
}
