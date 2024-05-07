import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.mu.book.Book;
import edu.mu.book.Genre;
import edu.mu.book.ReadingStatus;

class BookTest {

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
    
    
    
    
}
