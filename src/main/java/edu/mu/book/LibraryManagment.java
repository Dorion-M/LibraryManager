package edu.mu.book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
                    System.out.println("Book added to library: " + book.getTitle());
                }
            }
            System.out.println("Library data saved successfully.");
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

    public List<Book> findBooksByTitleAndAuthor(String title, String author) {
        return personalLibrary.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public void addBooksToLibrary(List<Book> books) {
        personalLibrary.addAll(books);
    }

    public static List<Book> loadLibrary(String filename) {
        List<Book> library = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String ISBN = parts[0].trim();
                    String title = parts[1].trim();
                    String author = parts[2].trim();
                    int publicationYear = Integer.parseInt(parts[3].trim());
                    Genre genre = Genre.valueOf(parts[4].trim());
                    int pageCount = Integer.parseInt(parts[5].trim());
                    ReadingStatus readingStatus = ReadingStatus.valueOf(parts[6].trim());
                    Book book = new Book(ISBN, title, author, publicationYear, genre, pageCount, readingStatus);
                    library.add(book);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading library data: " + e.getMessage());
        }
        return library;
    }
    
    
}
