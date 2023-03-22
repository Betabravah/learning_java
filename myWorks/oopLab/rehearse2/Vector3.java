package OOP.oopLab.rehearse2;

public class Vector3 implements Addable{
    public int x;
    public int y;
    public int z;

    public Vector3(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public Addable add(Addable obj) {
        Vector3 v = (Vector3) obj;
        int a = x + v.x;
        int b = y + v.y;
        int c = z + v.z;
        return new Vector3(a, b, c);
    }
    
}
