package memory.book_catalog;

public class Main {

    public static void main(String[] args) {

        BookCollection bc = new BookCollection();

        //get price of book called Tom Jones in EUR
        System.out.println(bc.findBookByName("Tom Jones").getPrice());
        System.out.println(bc.findBookByName("Tom Jones").getPrice().convert("EUR"));
        System.out.println(bc.findBookByName("Tom Jones").getPrice());

    }
}
