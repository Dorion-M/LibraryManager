package junitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.mu.Main;
import edu.mu.book.Book;
import edu.mu.book.Genre;
import edu.mu.book.LibraryManagment;
import edu.mu.book.ReadingStatus;

class LibraryManagementTest {
	 private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();


	 @Test
     public void testLoadLibrary() {
         // Test loading library from a file
         List<Book> library = LibraryManagment.loadLibrary("test_data.csv");
         
         // Assert that the library is not empty
         assertFalse(library.isEmpty());
         
         // Assert that all books are loaded correctly
         assertEquals(11, library.size());
         
         // Test loading library from a non_existing file
         List<Book> emptyLibrary = LibraryManagment.loadLibrary("non_existing_file.csv");
         
         // Assert that an empty list is returned for non_existing file
         assertTrue(emptyLibrary.isEmpty());
     }

     @Test
     public void testShowFavoritedBooks() {
         // Test showing favorited books
     	LibraryManagment libraryManagement = new LibraryManagment();
         
         // Add some books to the library with favorited status
         Book book1 = new Book("Book1", "Author1", 2022, Genre.FICTION, 300, ReadingStatus.READ, true);
         Book book2 = new Book("Book2", "Author2", 2022, Genre.NONFICTION, 250, ReadingStatus.UNREAD, true);
         libraryManagement.addBookToLibrary(book1);
         libraryManagement.addBookToLibrary(book2);
         
         // Add some books to the library without favorited status
         Book book3 = new Book("Book3", "Author3", 2022, Genre.SCIFI, 400, ReadingStatus.INPROGRESS, false);
         Book book4 = new Book("Book4", "Author4", 2022, Genre.MYSTERY, 350, ReadingStatus.READ, false);
         libraryManagement.addBookToLibrary(book3);
         libraryManagement.addBookToLibrary(book4);
         
         // Get favorited books
         List<Book> favorites = libraryManagement.showFavoritedBooks();
         
         // Assert that only favorited books are returned
         assertEquals(2, favorites.size());
         
         // Assert that the favorited books are as expected
         assertEquals("Book1", favorites.get(0).getTitle());
         assertEquals("Book2", favorites.get(1).getTitle());
     }
     
    
     
     
     @Test 
     public void testSortByYear()
     {
    	//This method tests the sort by year functionality of the program and includes test years to make 
    	//sure that the program sorts the years correctly in use.
    	 LibraryManagment myLibraryManagement = new LibraryManagment();
    	    myLibraryManagement.addBookToLibrary(new Book("Book1", "Author1", 2019, Genre.FICTION, 300, ReadingStatus.READ, false));
    	    myLibraryManagement.addBookToLibrary(new Book("Book2", "Author2", 2021, Genre.MYSTERY, 200, ReadingStatus.UNREAD, true));
    	    myLibraryManagement.addBookToLibrary(new Book("Book3", "Author3", 2018, Genre.SCIFI, 250, ReadingStatus.INPROGRESS, false));

    	    List<Book> sortedBooks = myLibraryManagement.sortBooksByYear();
    	    assertNotNull(sortedBooks);
    	    assertTrue(sortedBooks.get(0).getPublicationYear() < sortedBooks.get(1).getPublicationYear());
    	    assertTrue(sortedBooks.get(1).getPublicationYear() < sortedBooks.get(2).getPublicationYear());
     }
     
    @Test 
    public void testSortByGenre()
     {
    	//This method tests the sort by genre functionality of the program and includes test genres to make 
    	//sure that the program sorts the genres alphabetically correctly in use. 
    	LibraryManagment library = new LibraryManagment();
    	    library.addBookToLibrary(new Book("Book1", "Author1", 2019, Genre.THRILLER, 300, ReadingStatus.READ, false));
    	    library.addBookToLibrary(new Book("Book2", "Author2", 2021, Genre.FICTION, 200, ReadingStatus.UNREAD, true));
    	    library.addBookToLibrary(new Book("Book3", "Author3", 2018, Genre.MYSTERY, 250, ReadingStatus.INPROGRESS, false));

    	    List<Book> sortedBooks = library.sortByGenre();
    	    assertNotNull(sortedBooks);
    	    assertTrue(sortedBooks.get(0).getGenre().compareTo(sortedBooks.get(1).getGenre()) <= 0);
    	    assertTrue(sortedBooks.get(1).getGenre().compareTo(sortedBooks.get(2).getGenre()) <= 0);
     }
  

    @Test 
    public void testSortBooksByAuthor() 
    {
    	//This method tests the sort by authors functionality of the program and includes test authors to make 
    	//sure that the program sorts the authors correctly in use.
    	LibraryManagment library = new LibraryManagment();
        library.addBookToLibrary(new Book("Book1", "Paul", 2019, Genre.FICTION, 300, ReadingStatus.READ, false));
        library.addBookToLibrary(new Book("Book2", "Dorion", 2021, Genre.MYSTERY, 200, ReadingStatus.UNREAD, true));
        library.addBookToLibrary(new Book("Book3", "Tosin", 2018, Genre.SCIFI, 250, ReadingStatus.INPROGRESS, false));

        List<Book> sortedBooks = library.sortBooksByAuthor();
        assertNotNull(sortedBooks);
        assertTrue(sortedBooks.get(0).getAuthor().compareTo(sortedBooks.get(1).getAuthor()) <= 0);
        assertTrue(sortedBooks.get(1).getAuthor().compareTo(sortedBooks.get(2).getAuthor()) <= 0);
    }
    
    @Test 
    public void testPrintBooks() 
    {
        //This programs tests the printing function of the program to check if the methods correctly prints the test
    	// case that is given.
    	LibraryManagment library = new LibraryManagment();
        library.addBookToLibrary(new Book("Book1", "Author1", 2020, Genre.FICTION, 300, ReadingStatus.READ, true));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        library.printBooks(library.getPersonalLibrary());

    }
    
    @Test
    public void testSortByFavorites_Positive() {
        // This method tests that the function for sort by favorites only returns books that are favorited.
    	LibraryManagment library = new LibraryManagment();
        library.addBookToLibrary(new Book("Book1", "Author1", 2020, Genre.FICTION, 200, ReadingStatus.READ, true));
        library.addBookToLibrary(new Book("Book2", "Author2", 2021, Genre.NONFICTION, 150, ReadingStatus.INPROGRESS, true));
        library.addBookToLibrary(new Book("Book3", "Author3", 2022, Genre.SCIFI, 300, ReadingStatus.UNREAD, true));
        library.addBookToLibrary(new Book("Book4", "Author4", 2023, Genre.THRILLER, 400, ReadingStatus.UNREAD, true));

        List<Book> sortedBooks = library.sortBooksByFavorites();
        boolean allFavorited = sortedBooks.stream().allMatch(Book::getFavoritedStatus);
        assertTrue(allFavorited);
    }

    @Test
    public void testSortByFavorites_Negative() {
        // This method tests that the function for sort by favorites only returns books that are favorited.
    	LibraryManagment library = new LibraryManagment();
        library.addBookToLibrary(new Book("Book1", "Author1", 2020, Genre.FICTION, 200, ReadingStatus.READ, true));
        library.addBookToLibrary(new Book("Book2", "Author2", 2021, Genre.NONFICTION, 150, ReadingStatus.INPROGRESS, false)); // Non-favorited book
        library.addBookToLibrary(new Book("Book3", "Author3", 2022, Genre.SCIFI, 300, ReadingStatus.UNREAD, true));
        library.addBookToLibrary(new Book("Book4", "Author4", 2023, Genre.THRILLER, 400, ReadingStatus.UNREAD, true));

        List<Book> sortedBooks = library.sortBooksByFavorites();
        boolean allFavorited = sortedBooks.stream().allMatch(Book::getFavoritedStatus);
        assertFalse(allFavorited); // Assert that not all books are favorited
    }


   
    
    @Test
    public void testGetBooksByStatus() {
        LibraryManagment libraryManagement = new LibraryManagment();
        Book book1 = new Book("Book1", "Author1", 2022, Genre.FICTION, 300, ReadingStatus.READ, true);
        Book book2 = new Book("Book2", "Author2", 2021, Genre.NONFICTION, 250, ReadingStatus.UNREAD, false);
        Book book3 = new Book("Book3", "Author3", 2020, Genre.MYSTERY, 400, ReadingStatus.INPROGRESS, true);
        libraryManagement.addBookToLibrary(book1);
        libraryManagement.addBookToLibrary(book2);
        libraryManagement.addBookToLibrary(book3);

        List<Book> readBooks = libraryManagement.getBooksByStatus(ReadingStatus.READ);
        assertEquals(1, readBooks.size());
        assertTrue(readBooks.contains(book1));

        List<Book> unreadBooks = libraryManagement.getBooksByStatus(ReadingStatus.UNREAD);
        assertEquals(1, unreadBooks.size());
        assertTrue(unreadBooks.contains(book2));

        List<Book> inProgressBooks = libraryManagement.getBooksByStatus(ReadingStatus.INPROGRESS);
        assertEquals(1, inProgressBooks.size());
        assertTrue(inProgressBooks.contains(book3));
    }
    
@Test
public void testAddBooksToLibrary() {
    LibraryManagment libraryManagement = new LibraryManagment();
    Book book1 = new Book("Book1", "Author1", 2022, Genre.FICTION, 300, ReadingStatus.READ, true);
    Book book2 = new Book("Book2", "Author2", 2021, Genre.NONFICTION, 250, ReadingStatus.UNREAD, false);
    List<Book> booksToAdd = new ArrayList<>();
    booksToAdd.add(book1);
    booksToAdd.add(book2);

    libraryManagement.addBooksToLibrary(booksToAdd);

    List<Book> library = libraryManagement.getPersonalLibrary();
    assertEquals(2, library.size());
    assertTrue(library.contains(book1));
    assertTrue(library.contains(book2));
}
@Test
public void testSearchBooks() {
    LibraryManagment libraryManagement = new LibraryManagment();
    Book book1 = new Book("Book1", "Author1", 2022, Genre.FICTION, 300, ReadingStatus.READ, true);
    Book book2 = new Book("Book2", "Author2", 2021, Genre.NONFICTION, 250, ReadingStatus.UNREAD, false);
    Book book3 = new Book("Book3", "Author3", 2020, Genre.MYSTERY, 400, ReadingStatus.INPROGRESS, true);
    libraryManagement.addBookToLibrary(book1);
    libraryManagement.addBookToLibrary(book2);
    libraryManagement.addBookToLibrary(book3);

    List<Book> searchResults = libraryManagement.searchBooks("Book1");
    assertEquals(1, searchResults.size());
    assertTrue(searchResults.contains(book1));

    searchResults = libraryManagement.searchBooks("Author2");
    assertEquals(1, searchResults.size());
    assertTrue(searchResults.contains(book2));

    searchResults = libraryManagement.searchBooks("NonExistentTerm");
    assertTrue(searchResults.isEmpty());
}
@Test
public void testSortBooksByMostPages() {
    LibraryManagment libraryManagement = new LibraryManagment();
    Book book1 = new Book("Book1", "Author1", 2022, Genre.FICTION, 300, ReadingStatus.READ, true);
    Book book2 = new Book("Book2", "Author2", 2021, Genre.NONFICTION, 250, ReadingStatus.UNREAD, false);
    Book book3 = new Book("Book3", "Author3", 2020, Genre.MYSTERY, 400, ReadingStatus.INPROGRESS, true);
    libraryManagement.addBookToLibrary(book1);
    libraryManagement.addBookToLibrary(book2);
    libraryManagement.addBookToLibrary(book3);

    List<Book> sortedBooks = libraryManagement.sortBooksByMostPages();
    assertEquals(book3, sortedBooks.get(0));
    assertEquals(book1, sortedBooks.get(1));
    assertEquals(book2, sortedBooks.get(2));
}

@Test
public void testSortBooksByLeastPages_Positive() {
    // Create a list of books with varying page counts
    List<Book> library = new ArrayList<>();
    library.add(new Book("Book1", "Author1", 2022, Genre.FICTION, 300, ReadingStatus.READ, true));
    library.add(new Book("Book2", "Author2", 2023, Genre.SCIFI, 250, ReadingStatus.READ, true));
    library.add(new Book("Book3", "Author3", 2021, Genre.MYSTERY, 400, ReadingStatus.READ, true));

    // Create LibraryManagment object with the library
    LibraryManagment libraryManagment = new LibraryManagment();
    
 // Add books to the library management system
    for (Book book : library) {
    	libraryManagment.addBookToLibrary(book);
    }

    // Sort the books by least pages
    List<Book> sortedBooks = libraryManagment.sortBooksByLeastPages();

    // Assert that the books are sorted in ascending order by page count
    assertEquals(250, sortedBooks.get(0).getPageCount());
    assertEquals(300, sortedBooks.get(1).getPageCount());
    assertEquals(400, sortedBooks.get(2).getPageCount());
}

@Test
void testConstructor() {
    Book book = new Book("Title", "Author", 2022, Genre.FICTION, 300, ReadingStatus.READ, true);
    assertEquals("Title", book.getTitle());
    assertEquals("Author", book.getAuthor());
    assertEquals(2022, book.getPublicationYear());
    assertEquals(Genre.FICTION, book.getGenre());
    assertEquals(300, book.getPageCount());
    assertEquals(ReadingStatus.READ, book.getReadingStatus());
    assertTrue(book.getFavoritedStatus());
}

@Test
void testCopyConstructor() {
    Book original = new Book("Title", "Author", 2022, Genre.FICTION, 300, ReadingStatus.READ, true);
    Book copy = new Book(original);
    assertEquals(original.getTitle(), copy.getTitle());
    assertEquals(original.getAuthor(), copy.getAuthor());
    assertEquals(original.getPublicationYear(), copy.getPublicationYear());
    assertEquals(original.getGenre(), copy.getGenre());
    assertEquals(original.getPageCount(), copy.getPageCount());
    assertEquals(original.getReadingStatus(), copy.getReadingStatus());
    assertEquals(original.getFavoritedStatus(), copy.getFavoritedStatus());
}

@Test
void testSetters() {
    Book book = new Book("Title", "Author", 2022, Genre.FICTION, 300, ReadingStatus.READ, true);
    book.setTitle("New Title");
    book.setAuthor("New Author");
    book.setPublicationYear(2021);
    book.setGenre(Genre.MYSTERY);
    book.setPageCount(400);
    book.setReadingStatus(ReadingStatus.UNREAD);
    book.setfavoritedStatus(false);
    assertEquals("New Title", book.getTitle());
    assertEquals("New Author", book.getAuthor());
    assertEquals(2021, book.getPublicationYear());
    assertEquals(Genre.MYSTERY, book.getGenre());
    assertEquals(400, book.getPageCount());
    assertEquals(ReadingStatus.UNREAD, book.getReadingStatus());
    assertFalse(book.getFavoritedStatus());
}

@Test
void testToString() {
    Book book = new Book("Title", "Author", 2022, Genre.FICTION, 300, ReadingStatus.READ, true);
    String expected = "Title: Title                Author: Author                Publication Year: 2022 Genre: FICTION   Page Count: 300 Reading Status: READ Favorite: true";
    assertEquals(expected, book.toString());
}

@Test
void testToCsvString() {
    Book book = new Book("Title", "Author", 2022, Genre.FICTION, 300, ReadingStatus.READ, true);
    String expected = "Title,Author,2022,FICTION,300,READ,true";
    assertEquals(expected, book.toCsvString());
}

@Test
void testIsFavorited() {
    Book favoritedBook = new Book("Title", "Author", 2022, Genre.FICTION, 300, ReadingStatus.READ, true);
    Book notFavoritedBook = new Book("Title", "Author", 2022, Genre.FICTION, 300, ReadingStatus.READ, false);
    assertTrue(favoritedBook.isFavorited());
    assertFalse(notFavoritedBook.isFavorited());
}

@Test
void testSetFavoritedStatus() {
    Book book = new Book("Title", "Author", 2022, Genre.FICTION, 300, ReadingStatus.READ, false);
    assertFalse(book.getFavoritedStatus());
    book.setfavoritedStatus(true);
    assertTrue(book.getFavoritedStatus());
}

@Test
void testIsFavoritedWithSetFavoritedStatus() {
    Book book = new Book("Title", "Author", 2022, Genre.FICTION, 300, ReadingStatus.READ, false);
    assertFalse(book.isFavorited());
    book.setfavoritedStatus(true);
    assertTrue(book.isFavorited());
}

@Test
public void addBook() {
    // Test adding a new book with valid input
    ByteArrayInputStream in = new ByteArrayInputStream("BookTitle\nAuthorName\n2022\nFICTION\n300\nREAD\n".getBytes());
    System.setIn(in);

    Main main = new Main();
    main.addNewBook();
    assertEquals("Enter The Title of The Book: Enter author name: Enter publication year: Enter genre (FICTION, NONFICTION, SCIFI, MYSTERY, THRILLER, FANTASY): Enter page count: Enter reading status (UNREAD, READ, INPROGRESS): Book added successfully.\n"
    		+ "", outContent.toString());
}

}

     


  
     
     
     
    



