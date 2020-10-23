
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Ex_exam1 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ArticleDao dao = new ArticleDao();

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

				dao.insertArticle(a);
				System.out.println("게시물이 등록되었습니다.");
			}

			if (cmd.equals("list")) {
				ArrayList<Article> articles = dao.getArticles();

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					System.out.println("번호 : " + article.getId());
					System.out.println("제목 : " + article.getTitle());
					System.out.println("작성자 : 익명");
					System.out.println("조회수 : " + read_num);
					System.out.println("===================");
				}
				read_num++;

			}

			if (cmd.equals("update")) {
			
				System.out.println("수정할 게시물 선택 : ");
				int targetId = sc.nextInt();
				Article target = dao.getArticleById(targetId);
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
				Article target = dao.getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					articles.remove(target);

				}
			}

			if (cmd.equals("read")) {
				System.out.println("읽을 게시물 선택 : ");
				int targetId = sc.nextInt();
				Article target = dao.getArticleById(targetId);
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
			if (cmd.equals("search")) {
				ArrayList<Article> articles = dao.getArticles();

				System.out.println("명령어를 입력해주세요.");
				String search_cmd = sc.next();
				for (int i = 0; i < articles.size(); i++) {
					if (articles.get(i).getTitle().contains("serch_cmd")) {
						System.out.println("==== " + articles.get(i) + " ====");
						System.out.println("번호 : " + target.getId());
						System.out.println("제목 : " + target.getTitle());
						System.out.println("내용 : " + target.getBody());
						System.out.println("===============");
					}
				}
			}
		}

	}

}