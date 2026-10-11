public class Main {
    public static void main(String[] args) {
        BookInventoryManager manager = new BookInventoryManager();
        Book book = new Book("Clean Code");
        manager.addBook(book, 2);
        System.out.println(book.getTitle());
    }
}
