package collection;
import java.util.ArrayList;


class Book {
    private int bookId;
    private String bookName;
    private String authorName;

    public Book(int bookId, String bookName, String authorName) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String toString() {
        return "Book ID: " + bookId + ", Book Name: " + bookName + ", Author Name: " + authorName;
    }
}


public class BookDetails {
	    public static void main(String[] args) {
	        ArrayList<Book> bookList = new ArrayList<>();

	        bookList.add(new Book(1, "Mahabharat", "ARJUN"));
	        bookList.add(new Book(2, "Ramayan", "HANUMAN"));
	        bookList.add(new Book(3, "Radha krishna" ," RAM"));

	        for (Book book : bookList) {
	            System.out.println(book);
	        }
	    }
	}
