package Top;

public class MoveInfo {
    int top, s, slv, e, elv;

    public MoveInfo(int top, int s, int slv, int e, int elv) {
        this.top = top;
        this.s = s;
        this.slv = slv;
        this.e = e;
        this.elv = elv;
    }

    public MoveInfo(int top, int s, int e) {
        this.top = top;
        this.s = s;
        this.e = e;
    }
}
