package crud.dao;

import java.util.ArrayList;
import java.util.List;

import crud.dto.Article;

public class ArticleDao {
	public List<Article> articles;

	public ArticleDao() {
		articles = new ArrayList<>();
	}
}
