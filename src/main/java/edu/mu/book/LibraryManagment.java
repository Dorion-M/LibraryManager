package edu.mu.book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryManagment {

    private List<Book> personalLibrary;

    public LibraryManagment() {
        this.personalLibrary = new ArrayList<>();
    }

    public void addBookToLibrary(Book book) {
        personalLibrary.add(book);
    }

    public void removeBookFromLibrary(Book book) {
        personalLibrary.remove(book);
     } 
 
    //selects books based on their reading status to display to the user
    public List<Book> getBooksByStatus(ReadingStatus status) {
        return personalLibrary.stream()
                .filter(book -> book.getReadingStatus() == status)
                .collect(Collectors.toList());
    }

    public void saveLibraryToCSV(String filename) {
        List<Book> existingBooks = loadLibrary(filename); // Load existing books from CSV
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileWriter(filename));
            for (Book book : personalLibrary) {
                // Check if the book already exists in the CSV file
                boolean alreadyExists = existingBooks.stream().anyMatch(existingBook -> existingBook.equals(book));
                if (!alreadyExists) {
                    writer.println(book.toCsvString());
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving library data: " + e.getMessage());
        } finally {
            if (writer != null) {
                writer.close(); // Close the PrintWriter
            }
        }
    }


    public List<Book> getPersonalLibrary() {
        return new ArrayList<>(personalLibrary);
    }
    //Allows user to select a book by entering the Title and Name of the Author
    public List<Book> findBooksByTitleAndAuthor(String title, String author) {
        return personalLibrary.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public void addBooksToLibrary(List<Book> books) {
        personalLibrary.addAll(books);
    }
    // Loads CSV file of the users library
    public static List<Book> loadLibrary(String filename) {
        List<Book> library = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    int publicationYear = Integer.parseInt(parts[2].trim());
                    Genre genre = Genre.valueOf(parts[3].trim());
                    int pageCount = Integer.parseInt(parts[4].trim());
                    ReadingStatus readingStatus = ReadingStatus.valueOf(parts[5].trim());
                    Boolean favoritedStatus = Boolean.parseBoolean(parts[6].trim());
                    Book book = new Book(title, author, publicationYear, genre, pageCount, readingStatus, favoritedStatus);
                    library.add(book);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading library data: " + e.getMessage());
        }
        return library;
    }
    //Displays Users Favorite books
    public List<Book> showFavoritedBooks() {
        List<Book> favorites = new ArrayList<>();
        for (Book book : personalLibrary) {
            if (book.getFavoritedStatus()) {
                favorites.add(book);
            }
        }
        return favorites;
    }
    
    

    //allows user to search for a specified book
    public List<Book> searchBooks(String searchTerm) {
    return personalLibrary.stream()
            .filter(book -> book.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
                           book.getAuthor().toLowerCase().contains(searchTerm.toLowerCase()))
            .collect(Collectors.toList());
}
    // Template to Display users library
    public void printBooks(List<Book> list)
    {
    	
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-4s | %-30s | %-30s | %-6s | %-12s | %-6s | %-12s | %-1s%n", 
                          "No.", "Title", "Author", "Year", "Genre", "Pages", "Status", "Favorited");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        List<Book> booklist = list;
        for (int i = 0; i < booklist.size(); i++) {
            Book book = booklist.get(i);
            System.out.printf("%-4d | %-30s | %-30s | %-6d | %-12s | %-6d | %-12s | %-1s%n", 
                              i + 1, book.getTitle(), book.getAuthor(), book.getPublicationYear(), 
                              book.getGenre(), book.getPageCount(), book.getReadingStatus(), book.getFavoritedStatus());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    // Sorts by Author
    public List<Book> sortBooksByAuthor() {
    	
    	 List<Book> sortedBooks = new ArrayList<>(); 
    	 sortedBooks = personalLibrary;
    	
        Collections.sort(sortedBooks, new Comparator<Book>() {
           
            public int compare(Book b1, Book b2) {
                return b1.getAuthor().compareTo(b2.getAuthor());
            }
        });
        return sortedBooks; 
    }
   // Sorts by Genre
    public List<Book> sortByGenre(){
    	List<Book> sortedGenre = new ArrayList<>();
    	sortedGenre = personalLibrary;
    	
    	Collections.sort(sortedGenre, new Comparator<Book>() {
    		public int compare(Book b1, Book b2) {
    			return b1.getGenre().name().compareTo(b2.getGenre().name());
            }
    	});
    	return sortedGenre;
    		
    }
    // Sorts books by Year Published
    public List<Book> sortBooksByYear() {
    	
   	 List<Book> sortedByYear = new ArrayList<>(); 
   	 sortedByYear = personalLibrary;
   	
       Collections.sort(sortedByYear, new Comparator<Book>() {
          
           public int compare(Book b1, Book b2) {
        	   return Integer.compare(b1.getPublicationYear(), b2.getPublicationYear());
        	   
           }
       });
       	return sortedByYear;
       
       	
    }
    
    //Sorts all favorited books into a seperate view
    public List<Book> sortBooksByFavorites() {
    List<Book> sortedBooks = new ArrayList<>(personalLibrary);
    Collections.sort(sortedBooks, new Comparator<Book>() {
        @Override
        public int compare(Book b1, Book b2) {
        	return Boolean.compare(b2.getFavoritedStatus(), b1.getFavoritedStatus());
        }
    });
    return sortedBooks;
}
    //Sorts books based on page count Descending
   public List<Book> sortBooksByMostPages() {
    List<Book> sortedBooks = new ArrayList<>(personalLibrary);
    Collections.sort(sortedBooks, new Comparator<Book>() {
        @Override
        public int compare(Book b1, Book b2) {
            return Integer.compare(b2.getPageCount(), b1.getPageCount());
        }
    });
    return sortedBooks;
}
   //Sorts books based on page count Ascending
public List<Book> sortBooksByLeastPages() {
    List<Book> sortedBooks = new ArrayList<>(personalLibrary);
    Collections.sort(sortedBooks, new Comparator<Book>() {
        @Override
        public int compare(Book b1, Book b2) {
            return Integer.compare(b1.getPageCount(), b2.getPageCount());
        }
    });
    return sortedBooks;
} 
    
    
    
    
    
    
    
    
}
