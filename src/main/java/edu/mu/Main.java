package edu.mu;

import java.util.Scanner;
import java.util.List;

import edu.mu.book.LibraryManagment;
import edu.mu.book.Book;
import edu.mu.book.Genre;
import edu.mu.book.ReadingStatus;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LibraryManagment myLibrary = new LibraryManagment();
    private static final String CSV_FILENAME = "library_data.csv";

    public static void main(String[] args) {
        loadLibraryFromCSV(); // Load library data from CSV file

        boolean exit = false;
        while (!exit) {
            System.out.println("\n==== Library Management System ====");
            System.out.println("1. Add a Book");
            System.out.println("2. Remove a Book");
            System.out.println("3. View Full Library");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        System.out.print("Enter publication year: ");
        int publicationYear = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter genre (FICTION, NONFICTION, SCIFI, etc.): ");
        Genre genre = Genre.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Enter page count: ");
        int pageCount = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter reading status (UNREAD, READ, INPROGRESS): ");
        ReadingStatus readingStatus = ReadingStatus.valueOf(scanner.nextLine().toUpperCase());

        Book book = new Book(null, title, author, publicationYear, genre, pageCount, readingStatus);
        myLibrary.addBookToLibrary(book);
        System.out.println("Book added successfully.");
    }

    private static void removeBook() {
        System.out.println("==== Remove Options ====");
        System.out.println("1. Remove a Single Book");
        System.out.println("2. Remove All Books by Title and Author");
        System.out.println("3. Cancel");
        System.out.print("Enter your choice: ");
        int removeOption = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (removeOption) {
            case 1:
                removeSingleBook();
                break;
            case 2:
                removeAllBooks();
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

    private static void removeAllBooks() {
        System.out.print("Enter title of the books to remove: ");
        String title = scanner.nextLine();
        System.out.print("Enter author of the books to remove: ");
        String author = scanner.nextLine();
        List<Book> booksToRemove = myLibrary.findBooksByTitleAndAuthor(title, author);
        if (booksToRemove.isEmpty()) {
            System.out.println("Books not found.");
        } else {
            for (Book book : booksToRemove) {
                myLibrary.removeBookFromLibrary(book);
            }
            System.out.println("All books with title \"" + title + "\" and author \"" + author + "\" removed successfully.");
        }
    }

    private static void viewLibrary() {
        System.out.println("\n	Full Library:");
        System.out.println("	-------------");
        List<Book> library = myLibrary.getPersonalLibrary();
        for (Book book : library) {
            System.out.println("	* "+book.getTitle() + " by " + book.getAuthor());
        }
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
