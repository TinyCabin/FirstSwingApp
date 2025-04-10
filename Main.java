import java.util.List;

public class Main {
    public static void main(String[] args) {

        CarShowroomContainer container = new CarShowroomContainer();
        container.addCenter("Autko", 16);
        container.addCenter("Diselek", 55);
        container.addCenter("MacQueen", 52);
        container.addCenter("Kopciuch", 75);
        container.addCenter("Garda", 10);
        CarShowroom showroom = container.getShowroom("Garda");

        // Pojazdy
        Vehicle vehicle1 = new Vehicle("Ford", "Focus", ItemCondition.NEW, 55000.00, 2020, 15000, 1600);
        Vehicle vehicle2 = new Vehicle("BMW", "X5", ItemCondition.USED, 89000.00, 2018, 85000, 3000);
        Vehicle vehicle3 = new Vehicle("Fiat", "500", ItemCondition.NEW, 12000.00, 2015, 52000, 900);
        Vehicle vehicle4 = new Vehicle("Ford", "Fiesta", ItemCondition.USED, 49000.00, 2023, 3000, 1000);
        Vehicle vehicle5 = new Vehicle("Ford", "Focus", ItemCondition.DAMAGED, 42000.00, 2019, 78000, 1600);

        // Dodanie
        showroom.addProduct(vehicle1);
        showroom.addProduct(vehicle2);
        showroom.addProduct(vehicle3);
        showroom.addProduct(vehicle4);
        showroom.addProduct(vehicle5);
        //showroom.summary();

        //Szukaj Forda
        List<Vehicle>searchResults = showroom.search("Ford");
        System.out.println("\n-- Wynik szukania (Ford):");
        for (Vehicle vehicle : searchResults) {
            vehicle.print();
        }


        new ShowroomGUI(container);
    }
}
