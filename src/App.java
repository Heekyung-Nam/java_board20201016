import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class App {
	ArticleDao articleDao = new ArticleDao();
	ReplyDao replyDao = new ReplyDao();
	MemberDao memberDao = new MemberDao();
	Member loginedMember = null;
	Scanner sc = new Scanner(System.in);

	public void start() {

		int no = 4;
		int read_num = 0;
		Member wrighter = null;
		while (true) {
			if (loginedMember == null) {
				System.out.print("명령어 입력 : ");
			} else {
				System.out.println(
						"명령어 입력[" + loginedMember.getLoginId() + "(" + loginedMember.getNickname() + ")" + "] : ");
			}

			String cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				System.out.println("종료");
				break;
			}

			if (cmd.equals("article add")) {
				Article a = new Article();

				if (isLogin()) {
					continue;
				}

				a.setId(no);
				no++;
				System.out.println("게시물 제목을 입력해주세요 :");
				String title = sc.nextLine();
				a.setTitle(title);

				System.out.println("게시물 내용을 입력해주세요 :");
				String body = sc.nextLine();
				a.setBody(body);
				a.setNickname(loginedMember.getNickname());
				wrighter = loginedMember;

				articleDao.insertArticle(a);
				System.out.println("게시물이 등록되었습니다.");

			}

			if (cmd.equals("article list")) {
				ArrayList<Article> articles = articleDao.getArticles();

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					System.out.println("번호 : " + article.getId());
					System.out.println("제목 : " + article.getTitle());
					System.out.println("작성자 : " + article.getNickname());
					System.out.println("조회수 : " + read_num);
					System.out.println("===================");
				}
			}

			if (cmd.equals("article update")) {

				System.out.println("수정할 게시물 선택 : ");
				int targetId = Integer.parseInt(sc.nextLine());
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("없는 게시물입니다.");
				} else {
					System.out.println("게시물 제목을 입력해주세요 :");
					String newTitle = sc.nextLine();

					System.out.println("게시물 내용을 입력해주세요 :");
					String newBody = sc.nextLine();

					target.setTitle(newTitle);
					target.setBody(newBody);
					break;
				}
			}

			if (cmd.equals("article delete")) {
				System.out.println("삭제할 게시물 선택 : ");
				int targetId = Integer.parseInt(sc.nextLine());
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					articleDao.removeArticle(target);
				}
			}

			if (cmd.equals("article read")) {
				System.out.println("상세보기 할 게시물 선택 : ");
				int targetId = Integer.parseInt(sc.nextLine());
				Article target = articleDao.getArticleById(targetId);

				Member clickHitMember = loginedMember;
				clickHitMember.setClickHitNum(0);

				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					target.setHit(target.getHit() + 1);
					printArticle(target);
					while (true) {
						System.out.println("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) :");
						int readCmd = Integer.parseInt(sc.nextLine());
						Article str = new Article();
						if (readCmd == 1) {
							Reply r = new Reply();

							System.out.println("댓글 내용을 입력해주세요:");
							String body = sc.nextLine();
							r.setParentId(target.getId());
							r.setBody(body);
							r.setNickname(loginedMember.getNickname());

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
							ArrayList articles = articleDao.getArticles();

							if (!isLogin()) {
								continue;

							} else {
								clickHitMember.setClickHitNum(clickHitMember.getClickHitNum() + 1);
								target.setHit(target.getHit() + 1);
							}

							if (clickHitMember.getClickHitNum() == 2) {
								clickHitMember.setClickHitNum(clickHitMember.getClickHitNum() - 1);
								target.setHit(target.getHit() - 1);
							}

							printArticles(articles);

						} else if (readCmd == 3) {
							System.out.println("수정 기능");

							if (!loginedMember.getLoginId().equals(wrighter.getLoginId())) {
								if (!isLogin()) {
									continue;
								}
								System.out.println("자신의 게시물만 수정/삭제 할 수 있습니다.");
							} else {
								ArrayList article = articleDao.getArticles();
								updateArticle(target);
								printArticles(article);
								continue;
							}

						} else if (readCmd == 4) {
							System.out.println("삭제 기능");
						} else if (readCmd == 5) {
							break;
						}
					}

				}
			}
			if (cmd.equals("article search")) {
				System.out.print("검색항목을 선택해주세요(1. 제목, 2. 내용, 3. 제목 + 내용, 4. 작성자) : ");
				int flag = Integer.parseInt(sc.nextLine());
				System.out.print("검색 키워드를 입력해주세요.");
				String keyword = sc.nextLine();
				ArrayList<Article> searchedArticles = articleDao.getSearchedArticlesByFlag(flag, keyword);
				searchedArticles = articleDao.getSearchedArticlesByFlag(flag, keyword);
				memberDao.getMembers();
				printArticles(searchedArticles);

			}
			if (cmd.equals("member signup")) {
				Member m = new Member();
				System.out.println("==== 회원 가입을 진행합니다 ====");
				System.out.print("아이디를 입력해주세요 : ");
				String id = sc.nextLine();
				System.out.print("비밀번호를 입력해주세요 : ");
				String password = sc.nextLine();
				System.out.print("닉네임을 입력해주세요 : ");
				String nickname = sc.nextLine();
				m.setLoginId(id);
				m.setLoginPw(password);
				m.setNickname(nickname);
				memberDao.insertMember(m);
				System.out.println("==== 회원가입이 완료되었습니다. ====");
			}
			if (cmd.equals("member signin")) {
				System.out.println("아이디 :");
				String id = sc.nextLine();

				System.out.println("비밀번호 :");
				String pw = sc.nextLine();

				Member member = memberDao.getMemberByLoginIdAndLoginPw(id, pw);
				if (member == null) {
					System.out.println("비밀번호를 틀렸거나 잘못된 회원정보입니다.");
				} else {
					loginedMember = member;
					System.out.println(loginedMember.getNickname() + "님 안녕하세요!!");
				}
			}
			if (cmd.equals("help")) {
				System.out.println(
						"article [add: 게시물 추가 / list : 게시물 목록 조회 / read : 게시물 조회 / search : 검색] / member [signup : 회원가입 / signin : 로그인 / findpass : 비밀번호 찾기 / findid : 아이디 찾기 / logout : 로그아웃 / myinfo : 나의 정보 확인및 수정]");
			}
			if (cmd.equals("member logout")) {
				if (isLogin()) {
					continue;
				} else {
					System.out.println("로그아웃 되었습니다.");
					loginedMember = null;
				}
			}
			if (cmd.equals("article sort")) {

				System.out.println("정렬 대상을 선택해주세요. (like : 좋아요,  hit : 조회수) :");
				String sortType = sc.nextLine();
				System.out.println("정렬 방법을 선택해주세요. (asc : 오름차순,  desc : 내림차순) :");
				String sortOrder = sc.nextLine();
				MyComparator comp = new MyComparator();
				comp.sortOrder = sortOrder;
				// 조회수로 오름차순
				ArrayList<Article> articles = articleDao.getArticles();
				Collections.sort(articles, comp);
				printArticles(articles);

			}
			if (cmd.equals("article page")) {
				ArrayList<Article> articles = articleDao.getArticles();

				int itemPerPage = 3;
				int itemStartNo = 0;
				int itemEndNo = itemPerPage;
				int currentPage = 1;
				int lastPage = (int) Math.ceil((double) articles.size() / itemPerPage);
				int pagePerBlock = 5;
				int currentBlockLocation = (int) Math.ceil((double) currentPage / pagePerBlock);
				int endBlockNo = currentBlockLocation * pagePerBlock + 1;
				int startBlockNo = endBlockNo - pagePerBlock;

				for (int j = itemStartNo; j < articles.size() && j < itemEndNo; j++) {
					Article article = articles.get(j);
					System.out.println("번호 : " + article.getId());
					System.out.println("제목 : " + article.getTitle());
					System.out.println("등록날짜 : " + article.getRegDate());
					System.out.println("작성자 : " + article.getNickname());
					System.out.println("조회수 : " + article.getHit());
					System.out.println("===================");
				}
				itemStartNo =+ itemPerPage;
				System.out.println("\\\\\\"+itemStartNo+"\\\\\\");
				itemEndNo = itemStartNo + itemPerPage;
System.out.println("\\\\\\"+itemEndNo+"\\\\\\");
				for (int i = startBlockNo; i < endBlockNo; i++) {
					if (currentPage == i) {
						System.out.print("[" + i + "] ");
					} else {
						System.out.print(i + " ");
					}
				}
				endBlockNo = +pagePerBlock;

				while (true) {
					System.out.println("prev : 이전, next : 다음, go : 선택, back : 뒤로가기");
					String pageCmd = sc.nextLine();
					if (pageCmd.equals("back")) {
						break;
					} else if (pageCmd.equals("next")) {
						System.out.println("==="+itemStartNo+"===");
						System.out.println("==="+itemEndNo+"===");
						if(itemEndNo > articles.size()) {
							continue;
						}
						for (int j = itemStartNo;j < itemEndNo; j++) {
							System.out.println("test2");  
							Article article = articles.get(j);

							if (articles.isEmpty()) {
								System.out.println("게시물이 없습니다.");
							}

							if (j == articles.size()) {
								break;
							}
							currentPage++;
							System.out.println("번호 : " + article.getId());
							System.out.println("제목 : " + article.getTitle());
							System.out.println("등록날짜 : " + article.getRegDate());
							System.out.println("작성자 : " + article.getNickname());
							System.out.println("조회수 : " + article.getHit());
							System.out.println("===================");
						}
						itemStartNo =+ itemPerPage;
						itemEndNo =+ itemPerPage;
						for (int i = startBlockNo; i < endBlockNo && i < lastPage; i++) {
							if (currentPage == i) {
								System.out.print("[" + i + "] ");
							} else {
								System.out.print(i + " ");
							}
						}
						endBlockNo = +pagePerBlock;
					} else if (pageCmd.equals("prev")) {
						if(itemStartNo < 0) {
							itemStartNo = 0;
						}
						itemStartNo -= itemPerPage;
						for (int j = itemStartNo; j < articles.size() && j < itemStartNo + itemPerPage; j++) {
							Article article = articles.get(j);
							System.out.println("번호 : " + article.getId());
							System.out.println("제목 : " + article.getTitle());
							System.out.println("등록날짜 : " + article.getRegDate());
							System.out.println("작성자 : " + article.getNickname());
							System.out.println("조회수 : " + article.getHit());
							System.out.println("===================");
						}
					}
				}
			}
		}
	}

	private void printArticles(ArrayList<Article> articleList) {
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

	private void printReplies(ArrayList<Reply> replyList) {
		for (int i = 0; i < replyList.size(); i++) {
			Reply reply = replyList.get(i);
			System.out.println("내용 : " + reply.getBody());
			System.out.println("작성자 : " + reply.getNickname());
			System.out.println("등록날짜 : " + reply.getRegDate());
			System.out.println("===================");
		}
	}

	private void printArticle(Article target) {
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

	private boolean isLogin() {
		if (loginedMember == null) {
			System.out.println("로그인이 필요한 기능입니다.");
			return true;
		}
		return false;
	}

	private Article updateArticle(Article target) {
		if (target == null) {
			System.out.println("없는 게시물입니다.");
		} else {
			System.out.println("게시물 제목을 입력해주세요 :");
			String newTitle = sc.nextLine();

			System.out.println("게시물 내용을 입력해주세요 :");
			String newBody = sc.nextLine();

			target.setTitle(newTitle);
			target.setBody(newBody);
		}
		return target;
	}

	class MyComparator implements Comparator<Article> {

		String sortOrder = "asc";
		String sortType = "hit";

		@Override
		public int compare(Article o1, Article o2) {
			int c1 = o1.getHit();
			int c2 = o2.getHit();

			if (sortOrder.equals("asc")) {
				if (c1 > c2) {
					return 1;
				}

				return -1;
			} else {
				if (c1 < c2) {
					return 1;
				}

				return -1;
			}
		}

	}
}
