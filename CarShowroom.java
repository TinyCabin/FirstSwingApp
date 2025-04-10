import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.*;

public class CarShowroom  {

    private String nazwa;
    private List<Vehicle> samochody;
    private int pojemnoscMax;

    public CarShowroom(String nazwa, int pojemnoscMax) {
        this.nazwa = nazwa;
        this.samochody = new ArrayList<>();
        this.pojemnoscMax = pojemnoscMax;
    }

    int zlicz(String marka, String model) {
        int ilosc = 0;
        for (Vehicle pojazd : samochody){
            if (pojazd.getMarka().equals(marka) && pojazd.getModel().equals(model)) {
                ilosc++;
            }
        }
        return ilosc;
    }

    void addProduct(Vehicle vehicle){
        int sum = zlicz(vehicle.getMarka(), vehicle.getModel());
        System.out.println("Samochody o tym samym modelu i marce są w ilosci: " + sum);

        if(samochody.size() < pojemnoscMax){
            samochody.add(vehicle);
            System.out.println("Dodano samochod: " + vehicle.getMarka() + " " + vehicle.getModel());
        }
        else{
            System.err.println("Brak miejsca w salonie");
        }
    }


    public void getProduct(Vehicle samochod) {
        if (samochody.contains(samochod)) {
            samochody.remove(samochod);
            System.out.println("Usunięto jeden samochód ze salonu: " + samochod);
        } else {
            System.err.println("Samochód nie istnieje w salonie.");
        }
    }


    void removeProduct(String marka, String model){
        String nazwaUsunieta = marka + " " + model;
        samochody.removeIf(item -> item.getModel().equals(marka) && item.getModel().equals(model));
        System.out.println("Usunieto wszystkie smaochody typu: " + nazwaUsunieta);
    }

    public List<Vehicle> search(String name) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : samochody) {
            if (vehicle.getMarka().equals(name)) {
                result.add(vehicle);
            }
        }
        result.sort(Comparator.comparing(Vehicle::getMarka));
        return result;
    }

    public List<Vehicle> searchPartial(String partialName) {
        return samochody.stream()
                .filter(vehicle -> vehicle.getMarka().toLowerCase().contains(partialName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public int countByCondition(ItemCondition itemCondition) {
        int i = 0;
        for (Vehicle vehicle : samochody) {
            if (vehicle.getStan() == itemCondition)
                i++;
        }
        return i;
    }

    public void summary() {
        System.out.println("Pojazdy w salonie " + nazwa + ":");

        for (Vehicle vehicle : samochody) {
            vehicle.print();
            System.out.println("-----");
        }
    }

    public List<Vehicle> sortByName() {
        return samochody.stream()
                .sorted(Comparator.comparing(Vehicle::getMarka))
                .collect(Collectors.toList());
    }

    public List<Vehicle> sortByAmount() {
        Map<String, Long> vehicleCounts = samochody.stream()
                .collect(Collectors.groupingBy(Vehicle::getMarka, Collectors.counting()));

        return samochody.stream()
                .sorted(Comparator.comparing(vehicle -> vehicleCounts.get(vehicle.getMarka()), Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public Vehicle max() {
        return Collections.max(samochody, Comparator.comparing(vehicle -> zlicz(vehicle.getMarka(), vehicle.getModel())));
    }

    public double getMaksymalnaPojemnoscSalonu() {
        return pojemnoscMax;
    }

    public List<Vehicle> getListaSamochodow() {
        return samochody;
    }

    public String getNazwaSalonu() {
        return nazwa;
    }

    public List<Vehicle>  getVehicleList() {
        return  samochody;
    }
}