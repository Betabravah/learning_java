package OOP.oopLab.rehearse2;

public class Application {
    public static void main(String[] arg) {
        Vector2 v1 = new Vector2(1, 2);
        Vector2 v2 = new Vector2(3, 4);

        Vector2 sum = (Vector2) v1.add(v2);

        Vector3 v3 = new Vector3(1, 2, 1);
        Vector3 v4 = new Vector3(3, 4, 2);

        Vector3 sum2 = (Vector3) v3.add(v4);

        System.out.println(v1.x);
        System.out.println(v1.y);
        System.out.println(sum.x);
        System.out.println(sum.y);
        System.out.println(sum2.x);
        System.out.println(sum2.y);
        System.out.println(sum2.z);

    }
    
}
