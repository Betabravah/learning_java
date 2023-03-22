package OOP.oopLab;

public class fraction {
    private int num;
    private int den;
    public fraction(int x, int y){
        this.num = x;
        this.den = y;
    }

    
    public static fraction simplifier(fraction f){
        int x = gcd(f.num, f.den);
        f.num = f.num / x;
        f.den = f.den / x;
        return new fraction(f.num, f.den);
    }
    public static int gcd(int num, int den){
        int a, b;
        if(num < den){
            b = num;
            a = den;
        }
        else{
            b = den;
            a = num;
        }
        if (a % b == 0){
            return b;
        }
        else{
            return gcd(b, (a % b));
        }
    }
    public static void main(String [] args){
        fraction frac = new fraction(24,3);
        System.out.println(gcd(72, 9));
        System.out.println(gcd(63, 7));
        System.out.println(gcd(49, 3));
        System.out.printf("%s, %s", simplifier(frac).num, simplifier(frac).den);
    }
}
