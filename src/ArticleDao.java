import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ArticleDao {

	private ArrayList<Article> articles;
	private int no = 4;

	public ArticleDao() {
		articles = new ArrayList<>();
		Article a1 = new Article(1, "안녕하세요", "내용1", 1, getCurrentDate(), 100);
		Article a2 = new Article(2, "반갑습니다.", "내용2", 2, getCurrentDate(), 30);
		Article a3 = new Article(3, "안녕", "내용3", 3, getCurrentDate(), 50);

		articles.add(a1);
		articles.add(a2);
		articles.add(a3);

		for(int i = 1; i <= 50 ; i++) {
			Article a4 = new Article();
			a4.setId(i);
			a4.setTitle("제목" + i);
			a4.setBody("내용" + i);
			a4.setMid(1);

			articles.add(a4);
		}
	}

	}

	public ArrayList<Article> getSearchedArticlesByFlag(int flag, String keyword) {

		ArrayList<Article> searchedArticles = new ArrayList<>();

		
		for(int i = 0; i <articles.size(); i++) {
			Article article = articles.get(i);
			String str = article.getPropertiesByFlag(flag);
			if(str.contains(keyword)) {
				searchedArticles.add(article);
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

	private String getCurrentDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		Date time = new Date();
		String time1 = format1.format(time);

		return time1;
	}

	// Article 버전
	public Article getArticleById(int targetId) {
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
