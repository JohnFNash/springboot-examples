package com.johnfnash.learn.service;

public interface MailService {

	/**
	 * 发送简单文本邮件
	 * @param to
	 * @param subject
	 * @param content
	 */
	public void sendSimpleMail(String to, String subject, String content);
	
	/**
	 * 发送html格式邮件
	 * @param to
	 * @param subject
	 * @param content
	 */
	public void sendHtmlMail(String to, String subject, String content);
	
	/**
	 * 发送带附件的邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @param filePath
	 */
	public void sendAttachmentsMail(String to, String subject, String content, String filePath);
	
	/**
	 * 发送带静态资源的邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @param rscPath 静态资源路径
	 * @param rscId  the content ID to use. Can be referenced in HTML source via src="cid:myId" expressions.
	 */
	public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
	
}
