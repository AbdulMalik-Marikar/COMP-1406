/**
 * Created by abdul on 2017-01-27.
 * 101042166
 */
public class CellPhoneTestProgram {
    public static void main(String args[]) {

        //Imputing information to satify the constructor
        CellPhone iPhone = new CellPhone("iPhone6Plus","Apple",12,915);
        CellPhone galaxy =new CellPhone("Galaxy S7","Samsung",18,900.00f);
        CellPhone priv = new CellPhone ("PRIV","BlackBerry",24,890.00f);

        System.out.println("Here is the Apple phone information:");
        System.out.println(iPhone.getModel());
        System.out.println(iPhone.getManufacturer());
        System.out.println(iPhone.getMonthsWarranty());
        System.out.println(iPhone.getPrice());
        System.out.println("\nHere is the Samsung phone information:");
        System.out.println(galaxy.getModel());
        System.out.println(galaxy.getManufacturer());
        System.out.println(galaxy.getMonthsWarranty());
        System.out.println(galaxy.getPrice());
        System.out.println("\nHere is the BlackBerry phone information:");
        System.out.println(priv.getModel());
        System.out.println(priv.getManufacturer());
        System.out.println(priv.getMonthsWarranty());
        System.out.println(priv.getPrice());

        //change the model and price of the iphone
        iPhone.getModel();
        iPhone.setModel("iPhoneSE");
        iPhone.getPrice();
        iPhone.setPrice(590.00f);

        System.out.println("\nHere is the Apple phone's new information:");
        System.out.println(iPhone.getModel());
        System.out.println(iPhone.getManufacturer());
        System.out.println(iPhone.getMonthsWarranty());
        System.out.println(iPhone.getPrice());

        //total price of all phones
        float totalprice= iPhone.getPrice()+ galaxy.getPrice()+priv.getPrice();
        System.out.println("\nThe total cost of all the phones is $"+ totalprice);

        //Calculating which one has the largest warranty
        if (iPhone.getMonthsWarranty()>galaxy.getMonthsWarranty()&&iPhone.getMonthsWarranty()>priv.getMonthsWarranty())
            System.out.println("\nThe Apple phone has the longest warranty");
        else if (galaxy.getMonthsWarranty()>iPhone.getMonthsWarranty()&& galaxy.getMonthsWarranty()>priv.getMonthsWarranty())
            System.out.println("\nThe Samsung phone has the longest warranty");
        else if(priv.getMonthsWarranty()>iPhone.getMonthsWarranty()&& priv.getMonthsWarranty()>galaxy.getMonthsWarranty())
            System.out.println("\nThe BlackBerry phone has the longest warranty");



    }
}




