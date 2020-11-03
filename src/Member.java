
public class Member {
	private int id;
	private String loginId;
	private String loginPw;
	private String nickname;
	private String regDate;
	private int clickHitNum;

	
	public Member() {

	}

	public Member(int id, String loginId, String loginPw, String nickname) {
		this.id = id;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.nickname = nickname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return loginPw;
	}

	public void setPassword(String loginPw) {
		this.loginPw = loginPw;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPw() {
		return loginPw;
	}

	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getClickHitNum() {
		return clickHitNum;
	}

	public void setClickHitNum(int clickHitNum) {
		this.clickHitNum = clickHitNum;
	}

}
