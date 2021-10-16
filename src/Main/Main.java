package Main;

import Panels.BattlePanel;
import Pokemon.PokemonUniverse;
import Support.Player;
import Support.SkillUniverse;
import Support.TypeUniverse;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    private PokemonUniverse pu;
    private SkillUniverse su;
    private Player p1;

    private  Player p2;
    private TypeUniverse tu;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        tu = new TypeUniverse();
        su = new SkillUniverse(tu);
        pu = new PokemonUniverse(this, tu);


        p1 = new Player(pu.getPokemon(0),new ImageView(new Image("file:data/Player.png")));
        p2 = new Player(pu.getPokemon(1),new ImageView(new Image("file:data/Enemy.png")));
        BattlePanel p = new BattlePanel(p1,p2);
        primaryStage.setTitle("Pokemon Battle!");
        primaryStage.setScene(new Scene(p, 1280, 720));

        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public PokemonUniverse getPu(){
        return pu;
    }

    public SkillUniverse getSu(){
        return su;
    }
}
