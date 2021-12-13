package videostore;

public class Movie {
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDREN = 2;

    private String title;

    private Price price;

    public Movie(String title, int priceCode) {
        this.title = title;
        setPrice(priceCode);
    }

    public String getTitle() {
        return title;
    }
    protected void setPrice(int priceCode) {
        switch (priceCode) {
            case REGULAR:
                price = new RegularPrice();
                break;
            case NEW_RELEASE:
                price = new NewReleasePrice();
                break;
            case CHILDREN:
                price = new ChildrensPrice();
                break;
            default:
                throw new IllegalArgumentException("Wrong Price Code");
        }
    }

    protected double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }

    protected int getFrequentRenterPoints(int daysRented) {
        return price.getFrequentRenterPoints(daysRented);
    }
}
