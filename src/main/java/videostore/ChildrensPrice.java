package videostore;

public class ChildrensPrice extends Price{

    @Override
    int getPriceCode() {
        return Movie.CHILDREN;
    }
}
