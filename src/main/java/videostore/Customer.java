package videostore;

import java.util.ArrayList;
import java.util.List;

class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        String result = "Rental Record for " + getName() + "\n";

        for (Rental each : rentals) {
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.movie.getCharge(each.getDaysRented())) + "\n";
        }

        //add footer lines
        result += "Amount owed is " + String.valueOf(getTotalAmount()) + "\n";
        result += "You earned " + String.valueOf(getFrequentRenterPoints()) +" frequent renter points";
        return result;
    }

    protected int getFrequentRenterPoints() {
        int frequentRenterPoints = 0;
        for (Rental each : rentals) {
            frequentRenterPoints +=each.getFrequentRenterPoints();
        }
        return frequentRenterPoints;
    }

    protected double getTotalAmount(){
        double totalAmount = 0.0;
        for (Rental each : rentals) {
            totalAmount += each.movie.getCharge(each.getDaysRented());
        }
        return totalAmount;
    }

}
