public class Main {
    public static void main(String[] args) {
        BookInventoryManager manager = new BookInventoryManager();
        Book book = new Book("Clean Code");
        manager.addBook(book, 2);
        // a record gets accessors, equals, hashCode and toString for free
        System.out.println(book);
        System.out.println(book.title());
    }
}
