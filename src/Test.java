import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		SimpleDateFormat date_format = new SimpleDateFormat("yyyy.MM.dd");
		Date date = new Date();
		
		String today = date_format.format(date);
		
		System.out.println(today);
		
	}

}
