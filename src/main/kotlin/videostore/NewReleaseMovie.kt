package videostore

class NewReleaseMovie : Price() {
    override fun getPrice(rental: Rental): Double {
        return (rental.daysRented * 3).toDouble()
    }
}
