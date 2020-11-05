import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemberDao {

	private ArrayList<Member> members;
	private int no = 4;
	private int hitNum = 0;

	public MemberDao() {
		members = new ArrayList<>();
		Member m1 = new Member(1, "hong123", "h123", "홍길동");
		Member m2 = new Member(2, "mm111", "mm1122", "둘리");
		Member m3 = new Member(3, "ee1212", "eekk1122", "고길동");

		members.add(m1);
		members.add(m2);
		members.add(m3);
	}

	public ArrayList<Member> getMembers() {
		return members;
	}

	public void insertMember(Member m) {
		m.setId(no);
		no++;
		m.setRegDate(getCurrentDate());

		members.add(m);
	}

	private static String getCurrentDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		Date time = new Date();
		String time1 = format1.format(time);

		return time1;
	}

	public Member getMemberByLoginIdAndLoginPw(String id, String pw) {

		for (int i = 0; i < members.size(); i++) {
			Member m = members.get(i);
			if (m.getLoginId().equals(id) && m.getLoginPw().equals(pw)) {
				return m;
			}
		}

		return null;

	}
	
}
