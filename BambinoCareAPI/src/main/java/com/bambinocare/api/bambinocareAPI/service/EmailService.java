package com.bambinocare.api.bambinocareAPI.service;

import javax.mail.MessagingException;

public interface EmailService {

	void sendSimpleMessage(String to, String subject, String text);
	
	void sendSimpleMessage(String to, String cc, String subject, String text);

	void sendMessageWithAttachment(String to, String subject, String text, String attachmentFilename) throws MessagingException;
	
	void sendHTMLMessage(String to, String subject, String text) throws MessagingException;
	
}
