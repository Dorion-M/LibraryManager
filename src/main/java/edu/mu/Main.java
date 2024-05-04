package edu.mu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

import edu.mu.book.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LibraryManagment myLibrary = new LibraryManagment();
    private static final String CSV_FILENAME = "library_data.csv";

    public static void main(String[] args) {
        try {
            loadLibraryFromCSV(); // Load library data from CSV file

            boolean exit = false;
            while (!exit) {
                System.out.println("\n==== Library Management System ====");
                System.out.println("1. Add a Book");
                System.out.println("2. Remove a Book");
                System.out.println("3. View Full Library");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = readIntegerInput();

                switch (choice) {
                    case 1:
                        addBook();
                        break;
                    case 2:
                        removeBook();
                        break;
                    case 3:
                        viewLibrary();
                        break;
                    case 4:
                        saveLibraryToCSV(); // Save library before exiting
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static int readIntegerInput() {
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author name: ");
        String author = scanner.nextLine();

        int publicationYear = 0;
        while (publicationYear <= 0) {
            System.out.print("Enter publication year: ");
            try {
                publicationYear = readIntegerInput();
                if (publicationYear <= 0) {
                    System.out.println("Publication year must be a positive number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
            }
        }

        Genre genre = null;
        while (genre == null) {
            System.out.print("Enter genre (FICTION, NONFICTION, SCIFI, MYSTERY, THRILLER, FANTASY): ");
            try {
                genre = Genre.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Invalid input. Please enter a valid genre.");
            }
        }

        int pageCount = 0;
        while (pageCount <= 0) {
            System.out.print("Enter page count: ");
            try {
                pageCount = readIntegerInput();
                if (pageCount <= 0) {
                    System.out.println("Page count must be a positive number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
            }
        }

        ReadingStatus readingStatus = null;
        while (readingStatus == null) {
            System.out.print("Enter reading status (UNREAD, READ, INPROGRESS): ");
            try {
                readingStatus = ReadingStatus.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Invalid input. Please enter a valid reading status.");
            }
        }

        Book book = new Book(title, author, publicationYear, genre, pageCount, readingStatus);
        myLibrary.addBookToLibrary(book);
        System.out.println("Book added successfully.");
    }

    private static void removeBook() {
        System.out.println("==== Remove Options ====");
        System.out.println("1. Remove a Single Book");
        System.out.println("2. Remove All Books by Author");
        System.out.println("3. Cancel");
        System.out.print("Enter your choice: ");
        int removeOption = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (removeOption) {
            case 1:
                removeSingleBook();
                break;
            case 2:
                System.out.print("Enter the author's name: ");
                String author = scanner.nextLine();
                removeAllBooksByAuthor(author);
                break;
            case 3:
                System.out.println("Cancelled.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
    
    private static void removeSingleBook() {
        System.out.print("Enter title of the book to remove: ");
        String title = scanner.nextLine();
        System.out.print("Enter author of the book to remove: ");
        String author = scanner.nextLine();
        List<Book> booksToRemove = myLibrary.findBooksByTitleAndAuthor(title, author);
        if (booksToRemove.isEmpty()) {
            System.out.println("Book not found.");
        } else if (booksToRemove.size() == 1) {
            myLibrary.removeBookFromLibrary(booksToRemove.get(0));
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Multiple books found with the same title and author. Please specify.");
            // Print list of books and remove the selected one
            for (int i = 0; i < booksToRemove.size(); i++) {
                System.out.println((i + 1) + ". " + booksToRemove.get(i).getTitle() + " by " + booksToRemove.get(i).getAuthor());
            }
            System.out.print("Enter the number corresponding to the book to remove: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline
            if (index >= 0 && index < booksToRemove.size()) {
                myLibrary.removeBookFromLibrary(booksToRemove.get(index));
                System.out.println("Book removed successfully.");
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    private static void removeAllBooksByAuthor(String author) {
        List<Book> booksToRemove = new ArrayList<>();

        // Find books by the specified author
        for (Book book : myLibrary.getPersonalLibrary()) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                booksToRemove.add(book);
            }
        }

        // Remove the found books
        for (Book book : booksToRemove) {
            myLibrary.removeBookFromLibrary(book);
        }

        // Print the result
        if (booksToRemove.isEmpty()) {
            System.out.println("No books found by author: " + author);
        } else {
            System.out.println("All books by author " + author + " removed successfully.");
        }
    }

    private static void viewLibrary() {
        System.out.println("\nFull Library:");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-4s | %-30s | %-30s | %-6s | %-12s | %-6s | %-12s%n", 
                          "No.", "Title", "Author", "Year", "Genre", "Pages", "Status");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        List<Book> library = myLibrary.getPersonalLibrary();
        for (int i = 0; i < library.size(); i++) {
            Book book = library.get(i);
            System.out.printf("%-4d | %-30s | %-30s | %-6d | %-12s | %-6d | %-12s%n", 
                              i + 1, book.getTitle(), book.getAuthor(), book.getPublicationYear(), 
                              book.getGenre(), book.getPageCount(), book.getReadingStatus());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
    }


    private static void saveLibraryToCSV() {
        myLibrary.saveLibraryToCSV(CSV_FILENAME);
        System.out.println("Library data saved to CSV file: " + CSV_FILENAME);
    }

    private static void loadLibraryFromCSV() {
        myLibrary.addBooksToLibrary(LibraryManagment.loadLibrary(CSV_FILENAME));
        System.out.println("Library data loaded from CSV file: " + CSV_FILENAME);
    }
}
