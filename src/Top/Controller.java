package Top;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Controller{
    @FXML
    private TextField StickInp;
    @FXML private TextField TopInp;
    @FXML private TextField DelayInp;
    @FXML private Button StartButton;
    @FXML private Button StartButton1;
    @FXML private Label ErrorMessage;
    public static int StickNum, TopNum,DelayTime;


    public void handleAction(ActionEvent event) { //Solve it
        try {
            ErrorMessage.setText("");
            StickNum = Integer.parseInt(StickInp.getText());
            TopNum = Integer.parseInt(TopInp.getText());
            try {
                DelayTime = Integer.parseInt(DelayInp.getText());
            }catch(Exception e){ //default를 1000으로
                DelayTime=1000;
            }
            if(DelayTime<=0) DelayTime=1000;
            if(StickNum<=0 || TopNum<=0){
                PrintError();
            }
            else {
                // System.out.println(StickNum);
                // System.out.println(TopNum);
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("sample2.fxml"));
                primaryStage.setTitle("하노이 탑");
                primaryStage.setScene(new Scene(root, 1400, 800));
                primaryStage.show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
            PrintError();
        }
    }
    public void handleAction2(ActionEvent event) {  //print Console
        try {
            ErrorMessage.setText("");
            StickNum = Integer.parseInt(StickInp.getText());
            TopNum = Integer.parseInt(TopInp.getText());

            if(StickNum<=0 || TopNum<=0){
                PrintError();
            }
            else {
                Solve A = new Solve();
                A.reset();
                A.start();
                int i;
                System.out.println("탑 : 시작 기둥 -> 끝 기둥");
                for (i = 0; i < A.anstop; i++) {
                    System.out.printf("%d : %d -> %d\n", A.ans[i].top, A.ans[i].s, A.ans[i].e);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            PrintError();
        }

        return;
    }
    public void handleAction3(ActionEvent event) { //Solve it
        try {
            ErrorMessage.setText("");
            StickNum = Integer.parseInt(StickInp.getText());
            TopNum = Integer.parseInt(TopInp.getText());

            if(StickNum<=0 || TopNum<=0){
                PrintError();
            }
            else {
                // System.out.println(StickNum);
                // System.out.println(TopNum);
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("sample3.fxml"));
                primaryStage.setTitle("하노이 탑");
                primaryStage.setScene(new Scene(root, 1400, 800));
                primaryStage.show();
                Stage primaryStage2 = new Stage();
                Parent root2 = FXMLLoader.load(getClass().getResource("Box.fxml"));
                primaryStage2.setTitle("키보드 입력");
                primaryStage2.setScene(new Scene(root2, 400, 100));
                primaryStage2.show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
            PrintError();
        }
    }
    public void PrintError(){
        ErrorMessage.setText("다시 입력해 주세요");
    }
}
