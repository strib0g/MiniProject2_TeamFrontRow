
public enum UserColumns {
	PIN("member_pin"),
	NAME("member_name"),
	PHONE("member_phone"),
	EMAIL("member_email"),
	ADRESS("member_adress");
	
	private String column;
	
	UserColumns(String column)
	{
		this.column = column;
	}
	
	public String getColumn()
	{
		return column;
	}
}
