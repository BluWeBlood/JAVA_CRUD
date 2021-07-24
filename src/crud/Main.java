package crud;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");
		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;
		
		List<Article> articles = new ArrayList<>();
		
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
				int id = lastArticleId + 1;
				lastArticleId = id;
				String regDate = Util.getNowDateStr();
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				System.out.printf("%d번째 글이 생성되었습니다\n",id);
				Article article = new Article(id,regDate, title,body);
				articles.add(article);
				
			} else if (command.equals("article list")){
				if (articles.size()==0) {
					System.out.println("게시글이 없습니다.");
					continue;
				} 
				System.out.println("번호  |  제목");
				for(int i=0;i<articles.size();i++) {
					Article article = articles.get(i);
					System.out.printf("%d  |  %s\n",article.id ,article.title);
				}
			} else if (command.startsWith("article detail ")){
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				
//				boolean foundArticle = false;
				Article foundArticle = null;
				
				for (int i =0;i<articles.size();i++) {
					Article article = articles.get(i);
					
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				} 
				
					System.out.printf("번호 : %d\n", foundArticle.id);
					System.out.printf("제목 : %s\n", foundArticle.regDate);
					System.out.printf("제목 : %s\n", foundArticle.title);
					System.out.printf("내용 : %s\n", foundArticle.body);
					
			} else if (command.startsWith("article modify ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				Article foundArticle = null;
				
				for (int i=0;i<articles.size();i++) {
					Article article = articles.get(i);
					
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}
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
				
//				Article foundArticle = null;
				int foundIndex = -1;
				
				for (int i =0;i<articles.size();i++) {
					Article article = articles.get(i);
					
					if (article.id == id) {
						foundIndex = i;
						break;
					}
				}
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
	} 
}
class Article {
	int id;
	String title;
	String body;
	String regDate;
	Article (int id,String regDate, String title,String body) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
	}
}