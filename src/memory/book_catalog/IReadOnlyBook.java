package memory.book_catalog;

public interface IReadOnlyBook {
    int getId();

    String getTitle();

    String getAuthor();

    Price getPrice();

    String toString();
}
