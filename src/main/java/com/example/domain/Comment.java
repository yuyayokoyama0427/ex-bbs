package com.example.domain;

/**
 * コメント情報を表すドメイン.
 * 
 * @author yuyayokoyama
 *
 */

public class Comment {
	/** ID */
	private Integer id;
	/** コメント名 */
	private String name;
	/** コメント内容 */
	private String content;
	/** 記事ID */
	private Integer articledId;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getArticledId() {
		return articledId;
	}
	public void setArticledId(Integer articledId) {
		this.articledId = articledId;
	}
	
	
	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", content=" + content + ", articledId=" + articledId + "]";
	}

}
