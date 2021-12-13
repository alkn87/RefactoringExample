package videostore;

class Rental {
    private Movie movie;
    private int daysRented;
    private Price price;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
        price = getPrice(movie.getPriceCode());
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    protected double getCharge() {
        double charge = 0.0;
        switch (getMovie().getPriceCode()) {
            case Movie.REGULAR:
                charge += 2;
                if (getDaysRented() > 2)
                    charge += (getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                charge += getDaysRented() * 3;
                break;
            case Movie.CHILDREN:
                charge += 1.5;
                if (getDaysRented() > 3)
                    charge += (getDaysRented() - 3) * 1.5;
                break;
        }
        return charge;
    }

    protected Price getPrice(int priceCode) {
        switch (priceCode) {
            case Movie.REGULAR:
                return new RegularPrice();
            case Movie.NEW_RELEASE:
                return new NewReleasePrice();
            case Movie.CHILDREN:
                return new ChildrensPrice();
            default:
                throw new IllegalArgumentException("Unknown price code");
        }
    }

    protected int getFrequentRenterPoints() {
        int frequentRenterPoints = 0;

        // add frequent renter points
        frequentRenterPoints++;
        // add bonus for a two day new release rental
        if ((getMovie().getPriceCode() == Movie.NEW_RELEASE)  && getDaysRented() > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }
}
