package videostore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CustomerTest {

    private static final Movie NEW_RELEASED_MOVIE = new Movie("Matrix 4.0", Movie.NEW_RELEASE);
    private static final Movie CHILDREN_MOVIE = new Movie("Ice Age 3", Movie.CHILDREN);
    private static final Movie REGULAR_MOVIE = new Movie("Hangover", Movie.REGULAR);

    Customer customer;

    @BeforeEach
    void setup(){
        customer = new Customer("Martin Fowler");
    }

    @Test
    void testStatement_case_Amount_0()  {
        String actual = customer.statement();
        String expected = "Rental Record for Martin Fowler\n"+
        "Amount owed is 0.0\n"+
        "You earned 0 frequent renter points";
        assertEquals(expected, actual);
    }

    @Test
    void testStatement_case_Frequent_0()  {
        String actual = customer.statement();
        String expected = "Rental Record for Martin Fowler\n"+
                "Amount owed is 0.0\n"+
                "You earned 0 frequent renter points";
        assertEquals(expected, actual);
    }

    @Test
    void testStatement_case_Rental_newMovie()  {
        customer.addRental(new Rental(NEW_RELEASED_MOVIE, 1));
        String actual = customer.statement();
        String expected = "Rental Record for Martin Fowler\n" +
                "\tMatrix 4.0\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points";
        assertEquals(expected, actual);
    }

    @Test
    void testStatement_case_Rental_newMovie_daysRented() {
        customer.addRental(new Rental(NEW_RELEASED_MOVIE, 2));
        String actual = customer.statement();
        String expected = "Rental Record for Martin Fowler\n" +
                "\tMatrix 4.0\t6.0\n" +
                "Amount owed is 6.0\n" +
                "You earned 2 frequent renter points";
        assertEquals(expected, actual);
    }

    @Test
    void testStatement_case_Rental_ChildrensMovie_OneDay() {
        customer.addRental(new Rental(CHILDREN_MOVIE, 1));
        String actual = customer.statement();
        String expected = "Rental Record for Martin Fowler\n" +
                "\tIce Age 3\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points";
        assertEquals(expected, actual);
    }

    @Test
    void testStatement_case_Rental_ChildrensMovie_FiveDays() {
        customer.addRental(new Rental(CHILDREN_MOVIE, 5));
        String actual = customer.statement();
        String expected = "Rental Record for Martin Fowler\n" +
                "\tIce Age 3\t4.5\n" +
                "Amount owed is 4.5\n" +
                "You earned 1 frequent renter points";
        assertEquals(expected, actual);
    }

    @Test
    void testStatement_case_Rental_ChildrensMovie_TwoDays() {
        customer.addRental(new Rental(CHILDREN_MOVIE, 2));
        String actual = customer.statement();
        String expected = "Rental Record for Martin Fowler\n" +
                "\tIce Age 3\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points";
        assertEquals(expected, actual);
    }

    @Test
    void testStatement_case_Rental_Regulatory_OneDay() {
        customer.addRental(new Rental(REGULAR_MOVIE, 1));
        String actual = customer.statement();
        String expected = "Rental Record for Martin Fowler\n" +
                "\tHangover\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points";
        assertEquals(expected, actual);
    }

    @Test
    void testStatement_case_Rental_Regulatory_FourDays()  {
        customer.addRental(new Rental(REGULAR_MOVIE, 4));
        String actual = customer.statement();
        String expected = "Rental Record for Martin Fowler\n" +
                "\tHangover\t5.0\n" +
                "Amount owed is 5.0\n" +
                "You earned 1 frequent renter points";
        assertEquals(expected, actual);
    }

    @Test
    void testStatement_case_Rental_Regulatory_TwoDays()  {
        customer.addRental(new Rental(REGULAR_MOVIE, 2));
        String actual = customer.statement();
        String expected = "Rental Record for Martin Fowler\n" +
                "\tHangover\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points";
        assertEquals(expected, actual);
    }

    @Test
    void testStatement_case_Rental_TwoMovies()  {
        customer.addRental(new Rental(REGULAR_MOVIE, 1));
        customer.addRental(new Rental(CHILDREN_MOVIE, 2));
        String actual = customer.statement();
        String expected = "Rental Record for Martin Fowler\n" +
                "\tHangover\t2.0\n" +
                "\tIce Age 3\t1.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 2 frequent renter points";
        assertEquals(expected, actual);
    }
}