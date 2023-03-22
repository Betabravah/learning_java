package OOP.oopLab.rehearse2;

public class main {
    public static void main(String [] args){
        // abstractAnimal dimet = new AnimalSubClass("Wuro", 9);
        // AnimalSubClass wsha = new AnimalSubClass("Buchi", 7);
        // AnimalSubClass lion = new AnimalSubClass("Lion", 7);
        // AnimalSubClass wef = new AnimalSubClass("Bird", 7);
        // System.out.println(dimet.is_domestic());
        // System.out.println(lion.is_domestic());
        // System.out.println(lion.is_mammal());
        // System.out.println(wef.is_mammal());

        AbstractCar toyota1 = new Toyota("Toyota2020", 2008);
        Toyota toyota2 = new Toyota("ToyotaBrandNew", 2018);
        Hyundai hyundai1 = new Hyundai("HyundaiXP", "HYundai A30s");
        Hyundai hyundai2 = new Hyundai("HYNDDAI", "Hyundai A10");

        toyota1.setModel("ToyotaG35");
        toyota1.is_new();
        System.out.println(toyota1.getModel());


    }
    
}
