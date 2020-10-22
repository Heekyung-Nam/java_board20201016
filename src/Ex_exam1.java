
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Ex_exam1 {

	static ArrayList<Article> articles;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		articles = new ArrayList<>();

		Article a1 = new Article(1, "제목1", "내용1");
		Article a2 = new Article(2, "제목2", "내용2");
		Article a3 = new Article(3, "제목3", "내용3");

		articles.add(a1);
		articles.add(a2);
		articles.add(a3);

		int no = 4;
		int read_num = 0;

		while (true) {
			System.out.print("명령어 입력 : ");
			String cmd = sc.next();

			if (cmd.equals("exit")) {
				System.out.println("종료");
				break;
			}

			if (cmd.equals("add")) {
				Article a = new Article();

				a.setId(no);
				no++;
				System.out.println("게시물 제목을 입력해주세요 :");
				String title = sc.next();
				a.setTitle(title);

				System.out.println("게시물 내용을 입력해주세요 :");
				String body = sc.next();
				a.setBody(body);
				articles.add(a);
				System.out.println("게시물이 등록되었습니다.");
			}

			if (cmd.equals("list")) {
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					System.out.println("번호 : " + article.getId());
					System.out.println("제목 : " + article.getTitle());
					System.out.println("작성자 : 익명");
					Calendar cal = Calendar.getInstance();
					int year = cal.get(Calendar.YEAR);
					int month = cal.get(Calendar.MONTH);
					int day = cal.get(Calendar.DAY_OF_MONTH);
					
					System.out.println("등록날짜 : " + year + "." + month + "." + day);
					System.out.println("조회수 : " + read_num);
					System.out.println("===================");
				}
				read_num++;

			}

			if (cmd.equals("update")) {

				// index 버전
//									System.out.println("수정할 게시물 선택 : ");
//									int targetId = sc.nextInt();
//									if (targetId == -1) {
//										System.out.println("없는 게시물입니다.");
//									} else {
//										System.out.println("게시물 제목을 입력해주세요 :");
//										String newTitle = sc.next();
				//
//										System.out.println("게시물 내용을 입력해주세요 :");
//										String newBody = sc.next();
				//
//										Article newArticle = new Article();
//										newArticle.setId(targetId);
//										newArticle.setTitle(newTitle);
//										newArticle.setBody(newBody);
				//
//										articles.set(targetId, newArticle);
//										break;
//									}
				System.out.println("수정할 게시물 선택 : ");
				int targetId = sc.nextInt();
				Article target = getArticleById(targetId);
				if (target == null) {
					System.out.println("없는 게시물입니다.");
				} else {
					System.out.println("게시물 제목을 입력해주세요 :");
					String newTitle = sc.next();

					System.out.println("게시물 내용을 입력해주세요 :");
					String newBody = sc.next();

					target.setTitle(newTitle);
					target.setBody(newBody);
					break;
				}
			}

			if (cmd.equals("delete")) {
				System.out.println("삭제할 게시물 선택 : ");
				int targetId = sc.nextInt();
				Article target = getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					articles.remove(target);

				}
			}
			
			if (cmd.equals("read")) {
				
				System.out.println("읽을 게시물 선택 : ");
				int targetId = sc.nextInt();
				Article target = getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					System.out.println("==== " + target.getId() + " ====");
					System.out.println("번호 : " + target.getId());
					System.out.println("제목 : " + target.getTitle());
					System.out.println("내용 : " + target.getBody());
					System.out.println("===============");
				}
			}
		}
	}

	// index 버전
	private static int getArticleIndexById(int targetId) {
		for (int i = 0; i < articles.size(); i++) {
			int id = articles.get(i).getId();
			if (id == targetId) {
				return i;
			}
		}
		return -1;
	}

	// Article 버전
	private static Article getArticleById(int targetId) {
		for (int i = 0; i < articles.size(); i++) {
			int id = articles.get(i).getId();
			if (id == targetId) {
				return articles.get(i);
			}
		}
		return null;
	}

}
