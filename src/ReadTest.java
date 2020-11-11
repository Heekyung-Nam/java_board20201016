import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class ReadTest {
	public static void main(String[] args) {

		String jsonStr = "[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
		JSONArray array = (JSONArray) JSONValue.parse(jsonStr);

		System.out.println("The 1st element of array");
		System.out.println(array.get(0));
		System.out.println();

		System.out.println("The 2nd element of array");
		System.out.println(array.get(1));
		System.out.println();

		JSONObject obj2 = (JSONObject) array.get(1);
		System.out.println("Field \"1\"");
		System.out.println(obj2.get("1"));
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("================================");

		JSONObject obj = new JSONObject();
		String jsonText;

		obj.put("name", "foo");
		obj.put("num", new Integer(100));
		obj.put("balance", new Double(1000.21));
		obj.put("is_vip", new Boolean(true));
		jsonText = obj.toString();
		System.out.println();
		System.out.println("================================2");

		JSONObject jobj = (JSONObject) JSONValue.parse(jsonText);
		String str = (String) jobj.get("name");
		long num = (long) jobj.get("num");
		double d1 = (double) jobj.get("balance");
		boolean b1 = (boolean) jobj.get("is_vip");
		
		
		System.out.println(str);
		System.out.println(num);
		System.out.println(d1);
		System.out.println(b1);

		System.out.println();
		System.out.println("================================3");

		System.out.println("Encode a JSON Object - to String");
		System.out.print(jsonText);

		try {
			// 파일 객체 생성
			File file = new File("C:/test/test.txt");
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

			if (file.isFile()) {
				// 쓰기
				bufferedWriter.write("문자열 추가1");
				// 개행문자쓰기
				bufferedWriter.newLine();
				bufferedWriter.write("문자열 추가2");

				bufferedWriter.close();
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}
}
