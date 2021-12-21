package videostore

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CustomerTest {
    private lateinit var customer: Customer

    @BeforeEach
    fun setup() {
        customer = Customer("Martin Fowler")
    }

    @Test
    fun testStatement_case_Amount_0() {
        val actual = customer.statement()
        val expected = """
            Rental Record for Martin Fowler
            Amount owed is 0.0
            You earned 0 frequent renter points
            """.trimIndent()
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun testStatement_case_Frequent_0() {
        val actual = customer.statement()
        val expected = """
            Rental Record for Martin Fowler
            Amount owed is 0.0
            You earned 0 frequent renter points
            """.trimIndent()
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun testStatement_case_Rental_newMovie() {
        customer.addRental(Rental(NEW_RELEASED_MOVIE, 1))
        val actual = customer.statement()
        val expected = """
            Rental Record for Martin Fowler
            Matrix 4.0 3.0
            Amount owed is 3.0
            You earned 1 frequent renter points""".trimIndent()
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun testStatement_case_Rental_newMovie_daysRented() {
        customer.addRental(Rental(NEW_RELEASED_MOVIE, 2))
        val actual = customer.statement()
        val expected = """
            Rental Record for Martin Fowler
            Matrix 4.0 6.0
            Amount owed is 6.0
            You earned 2 frequent renter points""".trimIndent()
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun testStatement_case_Rental_ChildrensMovie_OneDay() {
        customer.addRental(Rental(CHILDREN_MOVIE, 1))
        val actual = customer.statement()
        val expected = """
            Rental Record for Martin Fowler
            Ice Age 3 1.5
            Amount owed is 1.5
            You earned 1 frequent renter points""".trimIndent()
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun testStatement_case_Rental_ChildrensMovie_FiveDays() {
        customer.addRental(Rental(CHILDREN_MOVIE, 5))
        val actual = customer.statement()
        val expected = """
            Rental Record for Martin Fowler
            Ice Age 3 4.5
            Amount owed is 4.5
            You earned 1 frequent renter points""".trimIndent()
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun testStatement_case_Rental_ChildrensMovie_TwoDays() {
        customer.addRental(Rental(CHILDREN_MOVIE, 2))
        val actual = customer.statement()
        val expected = """
            Rental Record for Martin Fowler
            Ice Age 3 1.5
            Amount owed is 1.5
            You earned 1 frequent renter points""".trimIndent()
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun testStatement_case_Rental_Regulatory_OneDay() {
        customer.addRental(Rental(REGULAR_MOVIE, 1))
        val actual = customer.statement()
        val expected = """
            Rental Record for Martin Fowler
            Hangover 2.0
            Amount owed is 2.0
            You earned 1 frequent renter points""".trimIndent()
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun testStatement_case_Rental_Regulatory_FourDays() {
        customer.addRental(Rental(REGULAR_MOVIE, 4))
        val actual = customer.statement()
        val expected = """
            Rental Record for Martin Fowler
            Hangover 5.0
            Amount owed is 5.0
            You earned 1 frequent renter points""".trimIndent()
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun testStatement_case_Rental_Regulatory_TwoDays() {
        customer.addRental(Rental(REGULAR_MOVIE, 2))
        val actual = customer.statement()
        val expected = """
            Rental Record for Martin Fowler
            Hangover 2.0
            Amount owed is 2.0
            You earned 1 frequent renter points""".trimIndent()
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun testStatement_case_Rental_TwoMovies() {
        customer.addRental(Rental(REGULAR_MOVIE, 1))
        customer.addRental(Rental(CHILDREN_MOVIE, 2))
        val actual = customer.statement()
        val expected = """
            Rental Record for Martin Fowler
            Hangover 2.0
            Ice Age 3 1.5
            Amount owed is 3.5
            You earned 2 frequent renter points""".trimIndent()
        Assertions.assertEquals(expected, actual)
    }

    companion object {
        private val NEW_RELEASED_MOVIE = Movie("Matrix 4.0", Movie.NEW_RELEASE)
        private val CHILDREN_MOVIE = Movie("Ice Age 3", Movie.CHILDREN)
        private val REGULAR_MOVIE = Movie("Hangover", Movie.REGULAR)
    }
}
