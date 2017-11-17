/**
 * Abdul-Malik Marikar
 * 101042166
 */
public class ClientRequest {
    private String pickupLocation;
    private String dropOffLocation;

    public String getPickupLocation() { return pickupLocation; }
    public String getDropoffLocation() { return dropOffLocation; }

    public ClientRequest (String p, String d) {
        pickupLocation = p;
        dropOffLocation = d;
    }

    public String toString() {
        return pickupLocation + " ==> " + dropOffLocation;
    }
}