import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ArticleDao {

	private static ArrayList<Article> articles;
	private int no = 4;

	public ArticleDao() {
		articles = new ArrayList<>();
		Article a1 = new Article(1, "안녕하세요", "내용1", "바보", getCurrentDate());
		Article a2 = new Article(2, "반갑습니다.", "내용2", "멍청이", getCurrentDate());
		Article a3 = new Article(3, "안녕", "내용3", "말미잘", getCurrentDate());

		articles.add(a1);
		articles.add(a2);
		articles.add(a3);
	}

	public ArrayList<Article> getSearchedArticlesByCmd(int key_num, String keyword) {

		ArrayList<Article> searchedArticles = new ArrayList<>();

		if (key_num == 1) {
			for (int i = 0; i < articles.size(); i++) {
				if (articles.get(i).getTitle().contains(keyword)) {
					searchedArticles.add(articles.get(i));
				} else if (key_num == 2) {
					for (i = 0; i < articles.size(); i++) {
						if (articles.get(i).getBody().contains(keyword)) {
							searchedArticles.add(articles.get(i));
						} else if (key_num == 3) {
							for (i = 0; i < articles.size(); i++) {
								if (articles.get(i).getBody().contains(keyword)
										|| articles.get(i).getTitle().contains(keyword)) {
									searchedArticles.add(articles.get(i));
								} else {
									for (i = 0; i < articles.size(); i++) {
										if (articles.get(i).getNickname().contains(keyword)) {
											searchedArticles.add(articles.get(i));
										}
									}

								}
							}
						}

					}
				}
			}
		}
		return searchedArticles;

	}

	public void insertArticle(Article a) {
		a.setId(no);
		no++;
		a.setRegDate(getCurrentDate());

		articles.add(a);
	}

	public void removeArticle(Article a) {
		articles.remove(a);
	}

	private static String getCurrentDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		Date time = new Date();
		String time1 = format1.format(time);

		return time1;
	}

	// Article 버전
	public static Article getArticleById(int targetId) {
		for (int i = 0; i < articles.size(); i++) {
			int id = articles.get(i).getId();
			if (id == targetId) {
				return articles.get(i);
			}
		}

		return null;
	}

	public ArrayList<Article> getArticles() {
		return articles;
	}
}
