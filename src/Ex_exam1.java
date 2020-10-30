
import java.util.ArrayList;
import java.util.Scanner;

public class Ex_exam1 {
	static ArticleDao articleDao = new ArticleDao();
	static ReplyDao replyDao = new ReplyDao();
	static MemberDao memberDao = new MemberDao();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);


		int no = 4;
		int read_num = 0;
		int signin_flag = 1; 

		while (true) {
			memberDao.getMembers();
			if(signin_flag == 1) {
			System.out.print("명령어 입력 : ");
			} else {
			System.out.print("명령어 입력 : " + "[" + memberDao.getMembers().);
					
			}
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
				a.setNickname("익명");

				articleDao.insertArticle(a);
				System.out.println("게시물이 등록되었습니다.");
			}

			if (cmd.equals("list")) {
				ArrayList<Article> articles = articleDao.getArticles();

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					System.out.println("번호 : " + article.getId());
					System.out.println("제목 : " + article.getTitle());
					System.out.println("작성자 : 익명");
					System.out.println("조회수 : " + read_num);
					System.out.println("===================");
				}
			}

			if (cmd.equals("update")) {

				System.out.println("수정할 게시물 선택 : ");
				int targetId = sc.nextInt();
				Article target = articleDao.getArticleById(targetId);
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
				ArrayList<Article> articles = articleDao.getArticles();

				System.out.println("삭제할 게시물 선택 : ");
				int targetId = sc.nextInt();
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					articleDao.removeArticle(target);

				}
			}

			if (cmd.equals("read")) {
				System.out.println("읽을 게시물 선택 : ");
				int targetId = sc.nextInt();
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					target.setHit(target.getHit() + 1);
					printArticle(target);

				}
				while (true) {
					System.out.println("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) :");
					int readCmd = sc.nextInt();
					Article str = new Article();
					if (readCmd == 1) {
						Reply r = new Reply();

						System.out.println("댓글 내용을 입력해주세요:");
						String body = sc.next();
						r.setParentId(target.getId());
						r.setBody(body);
						r.setNickname("익명");

						replyDao.insertReply(r);
						System.out.println("댓글이 등록되었습니다.");
						printArticle(target);

//						for (int i = 0; i < reple.size(); i++) {
//							System.out.println(reple.get(i).getBody());
//							System.out.println(reple.get(i).getRegDate());
//							System.out.println(reple.get(i).getNickname());
//						}

					} else if (readCmd == 2) {
						System.out.println("좋아요 기능");
					} else if (readCmd == 3) {
						System.out.println("수정 기능");
					} else if (readCmd == 4) {
						System.out.println("삭제 기능");
					} else if (readCmd == 5) {
						break;
					}
				}
			}
			if (cmd.equals("search")) {
				System.out.print("검색항목을 선택해주세요(1. 제목, 2. 내용, 3. 제목 + 내용, 4. 작성자) : ");
				int flag = sc.nextInt();
				System.out.print("검색 키워드를 입력해주세요.");
				String keyword = sc.next();
				ArrayList<Article> searchedArticles = articleDao.getSearchedArticlesByFlag(flag, keyword);
				searchedArticles = articleDao.getSearchedArticlesByFlag(flag, keyword);
				memberDao.getMembers();
				printArticles(searchedArticles);

			}
			if (cmd.equals("signup")) {
				Member m = new Member();
				System.out.println("==== 회원 가입을 진행합니다 ====");
				System.out.print("아이디를 입력해주세요 : ");
				String id = sc.next();
				System.out.print("비밀번호를 입력해주세요 : ");
				String password = sc.next();
				System.out.print("닉네임을 입력해주세요 : ");
				String nickname = sc.next();
				m.setLoginId(id);
				m.setLoginPw(password);
				m.setNickname(nickname);
				memberDao.insertMember(m);
				System.out.println("==== 회원가입이 완료되었습니다. ====");
			}
			if (cmd.equals("signin")) {
				
				memberDao.getMembers();
				showAllofMember(memberDao.getMembers());
				
				System.out.print("아이디 : ");
				String id = sc.next();
				System.out.print("비밀번호 : ");
				String password = sc.next();

				for (int i = 0; i < memberDao.getMembers().size(); i++) {
					if (id.equals(memberDao.getMembers().get(i).getLoginId()) && password.equals(memberDao.getMembers().get(i).getLoginPw())) {
						System.out.println(memberDao.getMembers().get(i).getNickname() + "님 환영합니다!");
						signin_flag = -1;
					} else {
						System.out.println("비밀번호를 틀렸거나 잘못된 회원정보입니다.");
					}
					break;
				}
			}
		}
	}

	private static void printArticles(ArrayList<Article> articleList) {
		for (int i = 0; i < articleList.size(); i++) {
			Article article = articleList.get(i);
			System.out.println("번호 : " + article.getId());
			System.out.println("제목 : " + article.getTitle());
			System.out.println("등록날짜 : " + article.getRegDate());
			System.out.println("작성자 : " + article.getNickname());
			System.out.println("조회수 : " + article.getHit());
			System.out.println("===================");
		}
	}

	private static void printReplies(ArrayList<Reply> replyList) {
		for (int i = 0; i < replyList.size(); i++) {
			Reply reply = replyList.get(i);
			System.out.println("내용 : " + reply.getBody());
			System.out.println("작성자 : " + reply.getNickname());
			System.out.println("등록날짜 : " + reply.getRegDate());
			System.out.println("===================");
		}
	}

	private static void printArticle(Article target) {
		System.out.println("==== " + target.getId() + " ====");
		System.out.println("번호 : " + target.getId());
		System.out.println("제목 : " + target.getTitle());
		System.out.println("내용 : " + target.getBody());
		System.out.println("등록날짜 : " + target.getRegDate());
		System.out.println("조회수 : " + target.getHit());
		System.out.println("===============");
		System.out.println("================댓글==============");

		ArrayList<Reply> replies = replyDao.getRepliesByParentId(target.getId());
		printReplies(replies);
	}

	private static void showAllofMember(ArrayList<Member> members) {
		memberDao.getMembers();
		for (int i = 0; i < members.size(); i++) {
			System.out.println("==== " + members.get(i).getId() + " ====");
			System.out.println("아이디 : " + members.get(i).getLoginId());
			System.out.println("비밀번호 : " + members.get(i).getLoginPw());
			System.out.println("닉네임 : " + members.get(i).getNickname());
			System.out.println("===============");
		}
	}
	
	return memeber;
	
}
