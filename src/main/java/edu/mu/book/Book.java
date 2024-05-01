package edu.mu.book;



public class Book {
	
	protected String ISBN;
	protected String title;
	protected String author;
	protected int publicationYear;
	protected Genre genre;
	protected int pageCount;
	protected ReadingStatus ReadingStatus;
	
	
	public Book(String ISBN, String title, String author, int publicationYear, Genre genre, int pageCount, ReadingStatus ReadingStatus) {
		super();
		this.ISBN = ISBN;
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
		this.genre = genre;
		this.pageCount = pageCount;
		this.ReadingStatus = ReadingStatus;
	}
	
	public Book(Book copy) {
		super();
		this.ISBN = copy.ISBN;
		this.title = copy.title;
		this.author = copy.author;
		this.publicationYear = copy.publicationYear;
		this.genre = copy.genre;
		this.pageCount = copy.pageCount;
		this.ReadingStatus = copy.ReadingStatus;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public ReadingStatus getReadingStatus() {
		return ReadingStatus;
	}

	public void setReadingStatus(ReadingStatus readingStatus) {
		ReadingStatus = readingStatus;
	}

	@Override
	public String toString() {
		return "book [ISBN=" + ISBN + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear
				+ ", genre=" + genre + ", pageCount=" + pageCount + ", ReadingStatus=" + ReadingStatus + "]";
	}
	
	
	public String toCsvString() {
	    return String.join(",", 
	            ISBN,
	            title,
	            author,
	            Integer.toString(publicationYear),
	            genre.toString(), 
	            Integer.toString(pageCount),
	            ReadingStatus.toString()  
	    );
	}
	

}
