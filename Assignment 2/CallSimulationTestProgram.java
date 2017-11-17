/**
 * Created by abdul on 2017-01-27.
 * 101042166
 */
public class CallSimulationTestProgram {
    public static void main(String args[]) {
        // Create some phone objects
        CellPhone iPhone = new CellPhone("iPhone 6Plus", "Apple", 12, 915.00f);
        CellPhone galaxy = new CellPhone("Galaxy S7", "Samsung", 18, 900.00f);
        CellPhone priv = new CellPhone("PRIV", "BlackBerry", 12, 890.00f);
        // Create some customer objects. Only Tim and April have a "pay-as-you-go" plan
        // (identified by a true Boolean value), the others are on standard monthly plans
        // (identified by a false Boolean value). Realistically, these purchases would
        // occur at different times on different days but we are assuming that all 5
        // Customers purchase at the same time.
        Customer rob = new Customer("Rob Banks", iPhone, new PhonePlan(200, 2500000, false));
        Customer april = new Customer("April Rain", galaxy, new PhonePlan(200, 1000000, true));
        Customer rita = new Customer("Rita Book", priv, new PhonePlan(100, 500000, false));
        Customer sue = new Customer("Sue Permann", iPhone, new PhonePlan(100, 2500000, false));
        Customer tim = new Customer("Tim Bur", iPhone, new PhonePlan(30, 0, true));
        // Show the Customers
        System.out.println("Here are the customers:");
        System.out.println(rob);
        System.out.println(april);
        System.out.println(rita);
        System.out.println(sue);
        System.out.println(tim);
        // Have the customers make some phone calls to other customers
        rob.Phone(sue, 12); // a 12 minute call from Rob's phone to Sue's phone
        rita.Phone(april, 27);
        rob.Phone(tim, 3);
        tim.Phone(rita, 19);
        // This line now assumes that Tim's call was cut off after 8 minutes,
        // because his plan only allows 8 more minutes.
        tim.Phone(sue, 8);
        // Output to show how the remaining unused minutes on each account
        // have changed
        System.out.println("\nRob's minutes = " + rob.getplanphone().getMinutesRemaining());
        System.out.println("April's minutes = "+ april.getplanphone().getMinutesRemaining());
        System.out.println("Rita's minutes = " + rita.getplanphone().getMinutesRemaining());
        System.out.println("Sue's minutes = " + sue.getplanphone().getMinutesRemaining());
        System.out.println("Tim's minutes = " + tim.getplanphone().getMinutesRemaining());
        // Try some more calls
        rob.Phone(tim, 1); // Should not connect at all
        rob.Phone(sue, 1);
        sue.Phone(tim, 1); // Should not connect at all
        // Tim gets his phone working again by paying for more minutes
        tim.buyMinutes(100);
        // Output to show how the remaining unused minutes on Tim's account has changed
        System.out.println("\nTim's minutes = " + tim.getplanphone().getMinutesRemaining());
        // Tim lets Rob know that his phone is working again.
        // Then Rob tells Sue who then phones Tim to chat.
        tim.Phone(rob, 24); // OK now rob.phone(sue, 15);
        sue.Phone(tim, 68); // Sue's limit will exceed and she must pay extra
        rita.Phone(sue, 65); // Both customers exceed their minutes and must pay extra
        // Output to show how the remaining unused minutes on each account have changed
        System.out.println("\nRob's minutes = " + rob.getplanphone().getMinutesRemaining());
        System.out.println("April's minutes = "+ april.getplanphone().getMinutesRemaining());
        System.out.println("Rita's minutes = " + rita.getplanphone().getMinutesRemaining());
        System.out.println("Sue's minutes = " + sue.getplanphone().getMinutesRemaining());
        System.out.println("Tim's minutes = " + tim.getplanphone().getMinutesRemaining());
        // Now simulate internet data access
        rob.accessInternet(45600); // used up 45.6MB
        rita.accessInternet(2700000); // use up 2.7GB
        rob.accessInternet(1200000); // use up 1.2GB
        tim.accessInternet(10000); // attempt to use 10MB ... won't work
        sue.accessInternet(2500000); // used up exactly 2.5GB
        april.accessInternet(1900000); // attempt to use 1.9GB, only 1GB used, then stops
        // Output to show how the remaining unused data on each account have changed
        System.out.println("\nRob's data = " + rob.getplanphone().getDataRemaining() + "KB");
        System.out.println("April's data = "+ april.getplanphone().getDataRemaining() + "KB");
        System.out.println("Rita's data = " + rita.getplanphone().getDataRemaining() + "KB");
        System.out.println("Sue's data = " + sue.getplanphone().getDataRemaining() + "KB");
        System.out.println("Tim's data = " + tim.getplanphone().getDataRemaining() + "KB");
        // Pretend that the month is over and print out all of the billing statements
        rob.printMonthlyStatement();
        april.printMonthlyStatement();
        System.out.println();
        rita.printMonthlyStatement();
        sue.printMonthlyStatement();
        tim.printMonthlyStatement();
    }
}