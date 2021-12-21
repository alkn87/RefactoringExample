package videostore

class Customer(private val name: String) {
    private val rentals: MutableList<Rental> = ArrayList()
    private var frequentRenterPoints = 0
    private var totalAmount = 0.0

    fun addRental(rental: Rental) {
        rentals.add(rental)
    }

    fun statement(): String {

        frequentRenterPoints = calculateFrequentRenterPoints(rentals)
        totalAmount = getTotalAmount()

        val customerLine = createCustomerLine()
        val rentalItems = createRentalLines()
        val totalAmountLine = createTotalAmountLine()
        val bonusPointsLine = createBonusPointsLine()

        return customerLine + rentalItems + totalAmountLine + bonusPointsLine
    }

    private fun getTotalAmount(): Double {
        return rentals.stream().mapToDouble {
            calculatePrice(it)
        }.sum()
    }

    private fun calculateFrequentRenterPoints(rentals: List<Rental>): Int {
        // Add one point for each rental and one bonus point for a two-day new release rental
        return rentals.count { it.movie.priceCode == Movie.NEW_RELEASE && it.daysRented > 1 } + rentals.size
    }

    private fun createRentalLines(): String {
        var rentalItemsString = ""
        rentals.forEach { rentalItemsString += "${it.movie.title} ${calculatePrice(it)}\n" }
        return rentalItemsString
    }

    private fun createCustomerLine(): String {
        return "Rental Record for $name\n"
    }

    private fun createTotalAmountLine(): String {
        return "Amount owed is $totalAmount\n"
    }

    private fun createBonusPointsLine(): String {
        return "You earned $frequentRenterPoints frequent renter points"
    }

    private fun calculatePrice(rental: Rental): Double {
        return when (rental.movie.priceCode) {
            Movie.REGULAR -> Movie.regularMovie.getPrice(rental)
            Movie.NEW_RELEASE -> Movie.newReleaseMovie.getPrice(rental)
            Movie.CHILDREN -> Movie.childrenMovie.getPrice(rental)
            else -> 0.0
        }
    }
}
