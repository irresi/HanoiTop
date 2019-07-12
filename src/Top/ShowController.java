package Top;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class ShowController implements Initializable {
    @FXML private Canvas canvas;
    @FXML private GraphicsContext gc;
    @Override public void initialize(URL location, ResourceBundle resources) {
        //System.out.println("initialized");
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.WHITE);
        gc.fillRect(0,0,1400,800);
        DrawColumns();
        Solve A = new Solve();
        A.reset();
        A.start();
        int i;
        ///그리는 부분 찾기
        for(i=1;i<=Controller.TopNum;i++){
            DrawTop(1,i,Controller.TopNum-i+1);
        }
        Thread thread = new Thread() {
            @Override public void run() {
                int i;
                for(i=0;i<A.anstop;i++){
                    //delay function
                 try{
                     Thread.sleep(Controller.DelayTime);
                 } catch (InterruptedException e) {
                    e.printStackTrace();
                 }
                    //System.out.println(i);
                    DrawTop(A.ans[i].e,A.ans[i].elv+1,A.ans[i].top);
                    RemoveTop(A.ans[i].s,A.ans[i].slv+1,A.ans[i].top);
                    DrawErasedColumn(A.ans[i].s,A.ans[i].slv+1);
                    //RecoverTop(A.ans[i].s,A.ans[i].slv+1);
                }

            }
        };
        thread.setDaemon(true);
        thread.start();

    }

    public void DrawTop(int top, int lv, int size){

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
    public void RemoveTop(int top, int lv, int size) {
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.WHITE);
        gc.fillRoundRect(GetStickX(top)-Controller.TopNum*2,700-lv*20,Controller.TopNum*4+15,20,10,10);
        return;
    }
    public void DrawErasedColumn(int top, int lv){
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
