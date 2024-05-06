package junitTests;

import static org.junit.Assert.*;
import org.junit.*;

import edu.mu.Main;


import java.io.*;
import java.util.*;

public class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testAddNewBookPositive() {
        // Test adding a new book with valid input
        ByteArrayInputStream in = new ByteArrayInputStream("BookTitle\nAuthorName\n2022\nFICTION\n300\nREAD\n".getBytes());
        System.setIn(in);

        Main main = new Main();
        main.addNewBook();
        assertEquals("Enter The Title of The Book: Enter author name: Enter publication year: Enter genre (FICTION, NONFICTION, SCIFI, MYSTERY, THRILLER, FANTASY): Enter page count: Enter reading status (UNREAD, READ, INPROGRESS): Book added successfully.\n"
        		+ "", outContent.toString());
    }
   
      
}
