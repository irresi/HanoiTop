package Top;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class KeyController /*implements Initializable*/ {


    @FXML
    //private TextField TextInp;
    /*public static KeyEvent A;
    public void initialize(){
        KeyCode keyCode;
        while(true) {
            keyCode = A.getCode();

            if (keyCode.equals(KeyCode.RIGHT)) {
                GoRight();
            } else if (keyCode.equals(KeyCode.LEFT)) {
                GoLeft();
            } else if (keyCode.equals(KeyCode.SPACE)) {
                SelectIt();
            }
            try{
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }*/
    public void GoRight(){
        //System.out.println("Right");
        if(ShowController2.ssdone==0) ShowController2.ss++;
        ShowController2.ee++;
        //System.out.println(ShowController2.ss+" "+ShowController2.ee+" "+ShowController2.ssdone+" "+ShowController2.eedone);
    }
    public void GoLeft(){
//        System.out.println("Left");
        if(ShowController2.ssdone==0) ShowController2.ss--;
        ShowController2.ee--;
        //System.out.println(ShowController2.ss+" "+ShowController2.ee+" "+ShowController2.ssdone+" "+ShowController2.eedone);
    }
    public void SelectIt(){
//        System.out.println("Select");
        if(ShowController2.ssdone==1) ShowController2.eedone=1;
        ShowController2.ssdone=1;
        //System.out.println(ShowController2.ss+" "+ShowController2.ee+" "+ShowController2.ssdone+" "+ShowController2.eedone);
    }

    /*@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tf1.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("[KeyPressed]");
                System.out.println("tf1.KeyCode: " + event.getCode());
                System.out.println("tf1.Text: " + event.getText());
                System.out.println("tf1.Character: " + event.getCharacter());
            }
        });// tf1()
    }*/
}
