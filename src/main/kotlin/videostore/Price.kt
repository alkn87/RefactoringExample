package videostore

abstract class Price {
    abstract fun getPrice(rental: Rental): Double
}
