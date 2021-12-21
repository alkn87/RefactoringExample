package videostore

class ChildrenMovie : Price() {
    override fun getPrice(rental: Rental): Double {
        var thisAmount = 1.5
        if (rental.daysRented > 3) thisAmount += (rental.daysRented - 3) * 1.5
        return thisAmount
    }
}
