package crud.dto;

//참조 하기 위해 모두 public 으로 바꿔줘야한다.
public class Article {
	public int id;
	public int hit;
	public String title;
	public String body;
	public String regDate;
	public Article (int id,String regDate, String title,String body) {
		this(id,regDate,title,body,0);
	}
	public Article (int id,String regDate, String title,String body, int hit) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.hit = hit;
	}
	public void increaseHit() {
		hit++;
	}
}