/**
 * Created by abdul on 2017-01-29.
 * 101042166
 */
public class CustomerTestProgram {
    public static void main(String args[]) {
        CellPhone iPhone = new CellPhone("iPhone 6Plus", "Apple", 12, 400.00f);
        CellPhone galaxy = new CellPhone("Galaxy S7", "Samsung", 18, 200.00f);
        CellPhone priv = new CellPhone("PRIV", "BlackBerry", 24, 210.00f);
        System.out.println(new Customer("Rob Banks", iPhone, new PhonePlan(200, 2500000, false)));
        System.out.println(new Customer("Rita Book", priv, new PhonePlan(100, 500000, false)));
        System.out.println(new Customer("Sue Permann", iPhone, new PhonePlan(60, 2500000, false)));
        System.out.println(new Customer("Tim Bur", iPhone, new PhonePlan(30, 0, true)));
        System.out.println(new Customer("April Rain", galaxy, new PhonePlan(200, 1000000, true)));
    }
}
