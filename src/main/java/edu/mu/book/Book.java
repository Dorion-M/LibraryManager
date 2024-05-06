package edu.mu.book;



public class Book {
	
	protected String title;
	protected String author;
	protected int publicationYear;
	protected Genre genre;
	protected int pageCount;
	protected ReadingStatus ReadingStatus;
	protected boolean favoritedStatus;
	
	
	public Book(String title, String author, int publicationYear, Genre genre, int pageCount, ReadingStatus ReadingStatus, boolean favoritedStatus) {
		super();
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
		this.genre = genre;
		this.pageCount = pageCount;
		this.ReadingStatus = ReadingStatus;
		this.favoritedStatus = favoritedStatus;
	}
	
	public Book(Book copy) {
		super();
		this.title = copy.title;
		this.author = copy.author;
		this.publicationYear = copy.publicationYear;
		this.genre = copy.genre;
		this.pageCount = copy.pageCount;
		this.ReadingStatus = copy.ReadingStatus;
		this.favoritedStatus = copy.favoritedStatus;
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
	
	public boolean getFavoritedStatus(){
		return favoritedStatus;
	}
	
	public void setfavoritedStatus(Boolean favoritedStatus) {
		this.favoritedStatus = favoritedStatus;
	}
	

	@Override
	public String toString() {
	    return String.format("Title: %-20s Author: %-20s Publication Year: %-6d Genre: %-10s Page Count: %-4d Reading Status: %s Favorite: %c", 
	                         title, author, publicationYear, genre, pageCount, ReadingStatus, favoritedStatus);
	}
	
	
	public String toCsvString() {
	    return String.join(",", 
	            title,
	            author,
	            Integer.toString(publicationYear),
	            genre.toString(), 
	            Integer.toString(pageCount),
	            ReadingStatus.toString(),
	            Boolean.toString(favoritedStatus)
	            
	    );
	}

	public boolean isFavorited() {
		// TODO Auto-generated method stub
		return getFavoritedStatus();
	}
	

}
