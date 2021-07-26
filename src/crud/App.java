package crud;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import crud.dto.Article;
import crud.dto.Util.Util;

public class App {
	private static List<Article> articles;
	
	public App() {
		articles = new ArrayList<>();
	}
	
	public void start() {
		System.out.println("==프로그램 시작==");
		makeTestData();
		Scanner sc = new Scanner(System.in);
//		List<Article> articles = new ArrayList<>();
		
		while(true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine().trim();
			
			if(command.length()==0) {
				continue;
			}
			if (command.equals("system exit")) {
				System.out.println("==프로그램 종료==");
				break;
			}
			if(command.equals("article write")) {
				int id = articles.size() + 1;
				String regDate = Util.getNowDateStr();
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				System.out.printf("%d번째 글이 생성되었습니다\n",id);
				Article article = new Article(id,regDate, title,body, 0);
				articles.add(article);
				
			} else if (command.startsWith("article list")){
				String searchKeyword = command.substring("article list".length()).trim();
				
				List<Article> forListArticles = articles;
				
				if (searchKeyword.length() > 0) {
					forListArticles = new ArrayList<>();
					
					for(Article article : articles) {
						if(article.title.contains(searchKeyword)) {
							forListArticles.add(article);
						}
					}
				}
				
				if (articles.size()==0) {
					System.out.println("게시글이 없습니다.");
					continue;
				} 
				System.out.println("번호  |  조회수  |  제목");
//				for(int i=0;i<articles.size();i++) {
//					Article article = articles.get(i);
				for(int i=0;i<forListArticles.size();i++) {
					Article article = forListArticles.get(i);
					System.out.printf("%4d|%4d|%4s\n",article.id ,article.hit, article.title);
				}
			} else if (command.startsWith("article detail ")){
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
					
				Article foundArticle = getArticlebyId(id);
				
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				} 
				
				foundArticle.increaseHit();
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("제목 : %s\n", foundArticle.regDate);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("조회수 : %d\n", foundArticle.hit);
					
			} else if (command.startsWith("article modify ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = getArticlebyId(id);
				
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				foundArticle.title = title;
				foundArticle.body = body;
				System.out.printf("%d번째 글이 수정되었습니다.\n",id);
				
			} else if (command.startsWith("article delete ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				
				int foundIndex = getArticleIndexbyId(id);
				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				} 
				articles.remove(foundIndex);
				System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
			} else {
				System.out.printf("%s는 존재하지 않는 명령어입니다.\n",command);
			}
		} 
		sc.close();
	}
	private int getArticleIndexbyId(int id) {
		int i = 0;
		
		for(Article article:articles) {
			if(article.id==id) {
				return i;
			}
			i++;
		}
		return i;
	}	
	private Article getArticlebyId(int id) {
//		for (int i =0;i<articles.size();i++) {
//			Article article = articles.get(i);
//			
//			if (article.id == id) {
//				return article;
//			}
//		}
		int index = getArticleIndexbyId(id);
		if(index != -1) {
			return articles.get(index);
		}
		return null;
	}
	private static void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");

		articles.add(new Article(1, Util.getNowDateStr(), "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getNowDateStr(), "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getNowDateStr(), "제목3", "내용3", 33));
	} 
}