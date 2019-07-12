package Top;

import java.util.Collections;
import java.util.Stack;
import java.util.Vector;

public class Solve {
    final static int inf=(int)1e8, MN=11,MM=30;
    int[][]  CurTop = new int[MN+1][MM+1];
    private int dp[][]= new int[MN+1][MM+1],sel[][] = new int[MN+1][MM+1],chk[] = new int[MN+1];
    private int n = Controller.StickNum,m=Controller.TopNum,ban,ban2;
    static MoveInfo[] ans = new MoveInfo[100000000];
    static int anstop,done;
    //움직이는 횟수를 저장한 다이나믹 배열, [현재 기둥][현재 탑]에서 옮겨야 할 탑의 개수, 현재 기둥에 있는 원판의 개수 체크
    /*public void TempSet() {
        CurTop[1].push(5);
        CurTop[1].push(4);
        CurTop[1].push(3);
        CurTop[2].push(2);
        CurTop[3].push(1);
    }*/

    private int emp(int num){
        int i;
        for(i=1;i<=n;i++){
            if(i==ban || i==ban2) continue;
            if(chk[i]==0) return i;
            if(num!=-1 &&  CurTop[i][chk[i]-1]>num) return i;
        }
        System.out.println("No empty Column");
        return -1;
    }
    public void move(int from, int to) {
        CurTop[to][chk[to]++] = CurTop[from][--chk[from]];
        return;
    }
    public int Check_Move(int from, int to){
        //System.out.println(chk[from] + " "+chk[to]);
        if(chk[from]<=0 || from ==to) return -1;
        if(chk[to]>0 && CurTop[to][chk[to]-1]<CurTop[from][chk[from]-1]) return -1;
        ans[anstop++]= new MoveInfo(CurTop[from][chk[from]-1],from,chk[from]-1,to,chk[to]);
        CurTop[to][chk[to]++]=CurTop[from][--chk[from]];
        return 0;
    }
    public void solve(int lv, int s, int e, int from, int to){
        if(s>e) return;
        if(e-s+1<=lv-1){
            ban=to;
            ban2=from;
            int wh,i,j;
            Vector<Integer> v = new Vector<>();
            for(i=s;i<e;i++){
                wh=emp(i);
                ans[anstop++]= new MoveInfo(i,from,chk[from]-1,wh,chk[wh]);
                //System.out.printf("%d : %d -> %d\n",i,from,wh);
                move(from,wh);
                v.addElement(wh);
            }
            Collections.reverse(v);
            ans[anstop++]= new MoveInfo(e,from,chk[from]-1,to,chk[to]);
            //System.out.printf("%d : %d -> %d\n",e,from,to);
            move(from,to);
            for(i=e-1,j=0;i>=s;i--,j++){
                ans[anstop++]= new MoveInfo(i,v.get(j),chk[v.get(j)]-1,to,chk[to]);
                //System.out.printf("%d : %d -> %d\n",i,v.get(j),to);
                move(v.get(j),to);
            }
            ban=ban2=0;
            return;
        }
        ban=to;
        ban2=from;
        int wh=emp(s); //-1
        ban=ban2=0;
        solve(lv,s,e-sel[lv][e-s+1],from, wh);
        solve(lv-1,e-sel[lv][e-s+1]+1,e,from, to);
        solve(lv,s,e-sel[lv][e-s+1],wh,to);
        return;
    }
    public void init(){
        int i,j,k;
        anstop=0;
        for(i=1;i<=MN;i++){
            for(j=1;j<i;j++){
                dp[i][j]=2*j-1;
                sel[i][j]=j;
            }
            for(j=i;j<=MM;j++){
                dp[i][j]=inf;
                for(k=1;k<j;k++){
                    if(dp[i][j]>dp[i][j-k]+dp[i-1][k]+dp[i][j-k]){
                        dp[i][j]=dp[i][j-k]+dp[i-1][k]+dp[i][j-k];
                        sel[i][j]=k;
                    }
                }
            }
        }
        for(i=m;i>=1;i--) CurTop[1][chk[1]++]=i;
    }
    public void start(){
        init();
        System.out.println(dp[n][m]+"번 옮김");
        solve(n,1,m,1,n);
    }
    public void reset(){
        int i;
        done=0;
        for(i=0;i<anstop;i++){
            ans[i].top=ans[i].s=ans[i].slv=ans[i].e=ans[i].elv=0;
        }
        for(i=0;i<m;i++) CurTop[1][i]=0;
        chk[n]=0;
        return;
    }

    public void DoneCheck() {
        if(chk[n]==m) done=1;
        return;
    }
}
