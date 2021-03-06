package crud.dto;

//참조 하기 위해 모두 public 으로 바꿔줘야한다.
//public class Article {
//	public int id;
//	public String regDate;
public class Article extends Dto {
		
	public int hit;
	public String title;
	public String body;
	public int memberId; // 작성자의 멤버 id
	
	public Article (int id,String regDate,int memberId, String title,String body) {
		this(id,regDate,memberId,title,body,0);
	}
	public Article (int id,String regDate, int memberId,String title,String body, int hit) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.hit = hit;
		this.memberId = memberId;
	}
	public void increaseHit() {
		hit++;
	}
}