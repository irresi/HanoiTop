package Top;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class ShowController2 implements Initializable {
    @FXML private Canvas canvas;
    @FXML private GraphicsContext gc;
    @FXML private ImageView arrow;
    @FXML private Label IM;
    @FXML private Label Success;
    public static int ss,ee,ssdone,eedone;
    private Solve A = new Solve();
    private int first;
    public void UpdateArrow(){
        arrow.setX(GetStickX(ee));
    }
    @Override public void initialize(URL location, ResourceBundle resources) {
        //System.out.println("initialized");
        A.reset();
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.WHITE);
        gc.fillRect(0,0,1400,800);
        DrawColumns();
        IM.setVisible(false);
        Success.setVisible(false);
        System.out.println();
        arrow.setX(GetStickX(1));
        int i;
        ///그리는 부분 찾기
        for(i=1;i<=Controller.TopNum;i++){
            DrawTop(1,i,Controller.TopNum-i+1);
        }
        Thread thread = new Thread() {
            @Override public void run() {
                int i=0,flag=1;
                ss=1;ee=1;
                A.init();
                while(flag==1){
                    //delay function

                    ssdone=eedone=0;
                    ss=ee=1;
                    while(ssdone==0 || eedone==0){
                        if(ee>Controller.StickNum){
                            if(ssdone==0) ss=Controller.StickNum;
                            ee=Controller.StickNum;
                            arrow.setX(GetStickX(ee));
                            ShowInvalidMove();
                        }
                        if(ee<=0){
                            if(ssdone==0) ss=1;
                            ee=1;
                            arrow.setX(GetStickX(1));
                            ShowInvalidMove();
                        }
                        UpdateArrow();
                        try{
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(A.Check_Move(ss,ee)==-1) {
                        ShowInvalidMove();
                        continue;
                    };
                    //System.out.println(i);
                    DrawTop(A.ans[i].e,A.ans[i].elv+1,A.ans[i].top);
                    RemoveTop(A.ans[i].s,A.ans[i].slv+1,A.ans[i].top);
                    DrawErasedColumn(A.ans[i].s,A.ans[i].slv+1);
                    //RecoverTop(A.ans[i].s,A.ans[i].slv+1);
                    i++;
                    System.out.printf("%d번 이동\n",i);
                    System.out.printf("%d - > %d\n",ss,ee);
                    A.DoneCheck();
                    if(A.done==1){
                        Exit();
                    }
                }

            }
        };
        thread.setDaemon(true);
        thread.start();
    }
    private void Exit() { //Invalid move show and hide

        Success.setVisible(true);
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Platform.exit();
    }
    private void ShowInvalidMove() { //Invalid move show and hide

        IM.setVisible(true);
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        IM.setVisible(false);
    }

    private void DrawTop(int top, int lv, int size){

        Stop[] stops1 = new Stop[] {
                new Stop(0, Color.BLACK),
                new Stop(1, Color.RED)
        };
        LinearGradient gradient1 = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops1);
        gc.setFill(gradient1);
        gc.setStroke(Color.BLACK);
        gc.fillRoundRect(GetStickX(top)-size*2,700-lv*20,size*4+15,20,10,10);
        return;
    }
    private void RemoveTop(int top, int lv, int size) {
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.WHITE);
        gc.fillRoundRect(GetStickX(top)-Controller.TopNum*2,700-lv*20,Controller.TopNum*4+15,20,10,10);
        return;
    }
    private void DrawErasedColumn(int top, int lv){
        Stop[] stops1 = new Stop[] {
                new Stop(0, Color.BLACK),
                new Stop(1, Color.WHITE)
        };
        LinearGradient gradient1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops1);
        gc.setFill(gradient1);
        gc.setStroke(Color.BLACK);
        gc.fillRect(GetStickX(top),700-lv*20,15,20);

    }
    private void DrawColumn(int x, int y){
        Stop[] stops1 = new Stop[] {
                new Stop(0, Color.BLACK),
                new Stop(1, Color.WHITE)
        };
        LinearGradient gradient1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops1);
        gc.setFill(gradient1);
        gc.setStroke(Color.BLACK);
        gc.fillRoundRect(x,y,15,700,10,10);

    }
    private int GetStickX(int num){
        return num*1400/(Controller.StickNum+1);
    }
    private void DrawColumns(){
        int i;
        for(i=1;i<=Controller.StickNum;i++){
            DrawColumn(GetStickX(i),0);
        }
        Stop[] stops1 = new Stop[] {
                new Stop(0, Color.BLACK),
                new Stop(1, Color.WHITE)
        };
        LinearGradient gradient1 = new LinearGradient(0, 1, 0, 0, true, CycleMethod.NO_CYCLE, stops1);
        gc.setFill(gradient1);
        gc.setStroke(Color.BLACK);
        gc.fillRoundRect(0,700,1400,15,10,10);

    }
}
