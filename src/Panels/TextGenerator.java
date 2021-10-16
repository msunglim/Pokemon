package Panels;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TextGenerator extends TextArea {

    public TextGenerator(String s){
        setPrefSize(1000, 100);
        setText(s);
        setFocused(false);

        setEditable(false);
        VBox vbox = new VBox(this);

    }
}
