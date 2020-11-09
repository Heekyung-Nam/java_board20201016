import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		
		/*
		첫번째 과제 - 페이지 5개 출력하기
		ex ) 1 2 3 4 5
		*/
		
	
		
		/*
		두번째 과제 - 현재 페이지 표현하기

		만약 현재 페이지가 3이라면
		ex ) 1 2 [3] 4 5
		만약 현재 페이지가 5이라면
		ex ) 1 2 3 4 [5]
		세번째 과제 - 현재 페이지가 속한 페이지 블럭 출력하기
		*/
		
		
		/*
		만약 현재 페이지가 4라면
		ex ) 1 2 3 [4] 5
		만약 현재 페이지가 7 이라면
		ex ) 6 [7] 8 9 10
		만약 현재 페이지가 13 이라면
		ex ) 11 12 [13] 14 15
		네번째 과제 - 시작페이지와 마지막 페이지(13 이라고 가정)를 이용해 이전/다음 페이지 블록의 유무를 알려주고 현재 페이지가 시작페이지와 마지막 페이지를 못넘어가게 하기

		만약 현재 페이지가 1이라면
		ex ) [1] 2 3 4 5 >>
		만약 현재 페이지가 6이라면
		ex ) << [6] 7 8 9 10 >>
		만약 현재 페이지가 마지막 페이지라면
		ex ) << 11 12 [13]	}*/
		
		ArrayList<Article> articles = new ArrayList<>();
		Article a1 = new Article();
		
		for(int i = 0; i < 50; i++) {
		a1.setId(i+1);
		a1.setTitle("제목 " + (i+1));
		a1.setBody("내용 " + (i+1)); 
		
		articles.add(a1);
		System.out.println(articles.get(i).getId());
		System.out.println(articles.get(i).getTitle());
		System.out.println(articles.get(i).getBody());
		System.out.println("================");
		}
		

		int currentPage = 7;
		int itemNoPerPage = 3;
		int startPageNo = 1;
		int lastPageNo = (int)Math.ceil((double)articles.size() / itemNoPerPage);
		int pagePerBlock = 5;
		
		int currentBlock = (int)Math.ceil((double)currentPage / pagePerBlock);
		int blockEndNo = currentBlock * pagePerBlock +1; 
		int blockStartNo = blockEndNo - pagePerBlock;
				
		
		
		
		for(int i = blockStartNo ; i < blockEndNo && i < lastPageNo; i++) {
			if(i == currentPage) {
				System.out.print("[" + i + "] ");
			} else {
				System.out.print
				(i + " ");
			}
			
						
			
		}
}}
