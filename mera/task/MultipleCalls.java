import java.util.Random;

public class MultipleCalls {

    public static int counter = 0;

    public Tobacco getTobacco() {
        Random ran = new Random();
        return new Tobacco("name " + counter, "qr " + counter, ran.nextInt(), ran.nextInt());
    }

    public Location getLocation(int locationId) {
        return new Location("locationName " + counter, "country " + counter, locationId);
    }

    public record Tobacco (String name, String qr, int id, int locationId) {
    }

    public record Location (String locationName, String country, long id) {
    }

    public record TobaccoDto (String name, String qr, int id, int locationId, String locationName, String country) {
    }

    public static void main(String[] args) {



    }
}
