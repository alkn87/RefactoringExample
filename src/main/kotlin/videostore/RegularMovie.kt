package videostore

class RegularMovie : Price() {
    override fun getPrice(rental: Rental): Double {
        var thisAmount = 2.0
        if (rental.daysRented > 2) {
            thisAmount += (rental.daysRented - 2) * 1.5
        }
        return thisAmount
    }
}
