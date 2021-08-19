package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@ModelAttribute
	public ArticleForm setUpForm() {
		return new ArticleForm();
	}
	
	@ModelAttribute
	public CommentForm setUpForm2() {
		return new CommentForm();
	}
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	
	/**
	 * 記事一覧を取得し一覧画面にフォワード.
	 * @param model モデル
	 * @return 記事一覧情報
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		for (Article article : articleList) {
			int articleId = article.getId();
			List<Comment> commentList = commentRepository.findByArticleId(articleId);
			article.setCommentList(commentList);
		}
		
		model.addAttribute("articleList", articleList);
		return "article/list";
	}
	
	/**
	 * 記事を追加.
	 * @param form フォーム
	 * @return 追加された記事情報
	 */
	@RequestMapping("/insertArticle")
	public String insertArticle(ArticleForm form) {
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		
		articleRepository.insert(article);
		return "redirect:/article";
	}
	
	@RequestMapping("/insertComment")
	public String insertComment(CommentForm form) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		comment.setArticleId(Integer.parseInt(form.getArticleId()));
		
		commentRepository.insert(comment);
		return "redirect:/article";
	}
	
	@RequestMapping("/deleteArticle")
	public String deleteArticle(int articleId) {
		commentRepository.deleteByArticleId(articleId);		
		articleRepository.deleteById(articleId);
		
		return "redirect:/article";
	}

}
