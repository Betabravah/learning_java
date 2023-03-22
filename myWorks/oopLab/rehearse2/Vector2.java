package OOP.oopLab.rehearse2;

public class Vector2 implements Addable{
    public int x;
    public int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Addable add(Addable obj) {
        Vector2 v = (Vector2) obj;
        int a = x + v.x;
        int b = y + v.y;
        return new Vector2(a, b);
    }
    
}
