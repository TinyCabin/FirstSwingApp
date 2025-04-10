public class Vehicle implements Comparable<Vehicle> {

    private String marka;
    private String model;
    private ItemCondition stan;
    private double cena;
    private int rokProdukcji;
    private double przebieg;
    private double pojemnoscSilnika;

    public Vehicle(String marka, String model, ItemCondition stan, double cena, int rokProdukcji, double przebieg, double pojemnoscSilnika) {
        this.marka = marka;
        this.model = model;
        this.stan = stan;
        this.cena = cena;
        this.rokProdukcji = rokProdukcji;
        this.przebieg = przebieg;
        this.pojemnoscSilnika = pojemnoscSilnika;
    }

    public void print() {
        System.out.println("Marka: " + marka);
        System.out.println("Model: " + model);
        System.out.println("Stan: " + stan);
        System.out.println("Cena: " + cena + " zł");
        System.out.println("Rok produkcji: " + rokProdukcji);
        System.out.println("Przebieg: " + przebieg + " km");
        System.out.println("Pojemność silnika: " + pojemnoscSilnika + " cm³");
    }

    @Override
    public int compareTo(Vehicle other) {
        return this.marka.compareTo(other.marka);
    }

    public String getMarka(){
        return marka;
    }

    public String getModel(){
        return model;
    }

    public ItemCondition getStan(){
        return stan;
    }
    public double getCena(){
        return cena;
    }
    public int getRokProdukcji(){
        return rokProdukcji;
    }
    public double getPrzebieg(){
        return przebieg;
    }
    public double  getPojemnoscSilnika(){
        return pojemnoscSilnika;
    }

}