package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * commentsテーブルを操作するリポジトリ.
 * 
 * @author yuyayokoyama
 *
 */

@Repository
public class CommentRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) ->{
		
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleId(rs.getInt("article_id"));
		
		return comment;
	};
	
	/**
	 * コメントの全件取得.
	 * @param articleId 記事ID
	 * @return 全てのコメント
	 */
	public  List<Comment> findByArticleId(int articleId){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT id, name, content, article_id FROM comments WHERE article_id = :article_id ORDER BY id DESC;");
		SqlParameterSource param = new MapSqlParameterSource().addValue("article_id", articleId);
		
		List<Comment> commentList = template.query(sql.toString(), param, COMMENT_ROW_MAPPER);
		return commentList;
	}
	
	/**
	 * コメントを追加
	 * @param comment コメント
	 */
	public void insert(Comment comment) {
		System.out.println("コメント" + comment);
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		
		String insertSql = "INSERT INTO comments(name, content, article_id) VALUES(:name, :content, :articleId);";
		template.update(insertSql, param);
	}

}
