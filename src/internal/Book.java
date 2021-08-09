package internal;

import java.util.Date;

public class Book {
	
	private final String title, author;
	private final int pages, id;
	private int readPages;
	private Date start, finish;
	private boolean isFinished;
	private static int nextId = 0;

	public Book(String title, String author, int pages, int status) {
		
		this.title = title;
		this.author = author;
		this.pages = pages;
		start = new Date();
		readPages = 0;
		isFinished = false;
		id = nextId;
		nextId++;
		
	}
	
	//Getters
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public int getPages() {
		return pages;
	}
	public int getReadPages() {
		return readPages;
	}
	public int getId() {
		return id;
	}
	public Date getStart() {
		return start;
	}
	public Date getFinish() {
		return finish;
	}

	//Setters
	public void setReadPages(int newReadPages) {
		
		if (newReadPages > 0 && newReadPages <= pages) {
			
			readPages = newReadPages;
			
			if (readPages == pages) {
				
				setFinished(true);
			}
		}
	}
	
	public void setFinished(boolean set) {
		
		isFinished = set;
		if (isFinished) {
			
			finish = new Date();
		} else {
			
			finish = null;
		}
	}
}
