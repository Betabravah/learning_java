package lab2;

public class StudentTest {
    public static void main(String [] args){
        Undergrad undergrad1 = new Undergrad("Leul", 2);
        Grad grad1 = new Grad("Chemeda", 1);
        Intern intern1 = new Intern("Chaltu", 4, 3, 5);
        ReaserchAssistant ra1 = new ReaserchAssistant("Bikila", 1, 3000);
        System.out.println(undergrad1.description());
        System.out.println(grad1.description());
        System.out.println(intern1.description());
        System.out.println(ra1.description());
    }
    
}
