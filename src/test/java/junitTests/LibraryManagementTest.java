package junitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.mu.book.Book;
import edu.mu.book.Genre;
import edu.mu.book.LibraryManagment;
import edu.mu.book.ReadingStatus;

class LibraryManagementTest {

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
     
  
     
     
     
     
 }


