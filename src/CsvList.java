import java.util.ArrayList;

public class CsvList<E> extends ArrayList<E> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9101516596124626571L;
	public CsvList()
	{
		super();
	}
	public String toCSV()
	{
		String res="";
		for(E e:this)
		{
			res += e + "\n";
		}
		return res;
	}
}