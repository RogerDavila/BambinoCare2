package com.bambinocare.api.bambinocareAPI.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	public MailSender emailSender;

	@Autowired
	public JavaMailSender javaMailSender;

	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);

			emailSender.send(message);
		} catch (MailException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void sendSimpleMessage(String to, String cc, String subject, String text) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setCc(cc);
			message.setSubject(subject);
			message.setText(text);

			emailSender.send(message);
		} catch (MailException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void sendMessageWithAttachment(String to, String subject, String text, String attachmentFilename)
			throws MessagingException {
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		String pathToAttachment = "/static/img/mail/" + attachmentFilename;
		
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(text, true);
		helper.addInline(attachmentFilename, new ClassPathResource(pathToAttachment));
		
		javaMailSender.send(message);
	}

	@Override
	public void sendHTMLMessage(String to, String subject, String text) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text,true);
		
		javaMailSender.send(message);
	}

}
