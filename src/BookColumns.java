
public enum BookColumns {
	TITLE("book_title"),
	PUBLISHER("book_publisher"),
	AUTHOR("book_author"),
	SHELF("book_shelf"),
	GENRE("book_genre");
	
	private String column;
	
	BookColumns(String column)
	{
		this.column = column;
	}
	
	public String getColumn()
	{
		return column;
	}
}
