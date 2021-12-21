package videostore

class Movie(val title: String, var priceCode: Int) {

    companion object {
        const val REGULAR = 0
        const val NEW_RELEASE = 1
        const val CHILDREN = 2

        val regularMovie = RegularMovie()
        val childrenMovie = ChildrenMovie()
        val newReleaseMovie = NewReleaseMovie()
    }
}
