import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

public class CarShowroomContainer {

    private Map<String, CarShowroom> salony;

    public CarShowroomContainer() {
        this.salony = new HashMap<>();
    }

    public void addCenter(String nazwa, double pojemnoscMax) {
        salony.put(nazwa, new CarShowroom(nazwa, (int) pojemnoscMax));
    }

    public void removeCenter(String nazwa) {
        salony.remove(nazwa);
    }

    public Map<String, CarShowroom> getSalony(){
        return salony;
    }

    public CarShowroom getShowroom(String nazwaSalonu) {
        return salony.get(nazwaSalonu);
    }

    public void summary() {
        for (Map.Entry<String, CarShowroom> entry : salony.entrySet()) {
            double percentageFilled = ((double) entry.getValue().getListaSamochodow().size() / entry.getValue().getMaksymalnaPojemnoscSalonu()) * 100;
            System.out.println("Nazwa salonu: " + entry.getKey() + ", Procentowe zapełnienie: " + percentageFilled + "%");
        }
    }
    public List<String> findEmpty() {
        List<String> emptySalons = new ArrayList<>();
        for (Map.Entry<String, CarShowroom> entry : salony.entrySet()) {
            if (entry.getValue().getListaSamochodow().isEmpty()) {
                emptySalons.add(entry.getValue().getNazwaSalonu());
            }
        }
        return emptySalons;
    }

    public void sortCentersByCurrentLoad() {
        List<Map.Entry<String, CarShowroom>> sortedEntries = new ArrayList<>(salony.entrySet());

        // Sortowanie listy według obciążenia salonu
        Collections.sort(sortedEntries, new Comparator<Map.Entry<String, CarShowroom>>() {
            @Override
            public int compare(Map.Entry<String, CarShowroom> entry1, Map.Entry<String, CarShowroom> entry2) {
                int size1 = entry1.getValue().getListaSamochodow().size();
                int size2 = entry2.getValue().getListaSamochodow().size();
                return Integer.compare(size1, size2);
            }
        });

        // Wyświetlanie posortowanych wyników
        System.out.println("Posortowane salony według aktualnego obciążenia:");
        for (Map.Entry<String, CarShowroom> entry : sortedEntries) {
            double percentageFilled = ((double) entry.getValue().getListaSamochodow().size() / entry.getValue().getMaksymalnaPojemnoscSalonu()) * 100;
            System.out.println("Nazwa salonu: " + entry.getKey() + ", Procentowe zapełnienie: " + percentageFilled + "%");
        }
    }
}