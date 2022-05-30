package org.serratec.java02backend.projeto07.service;

import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.serratec.java02backend.projeto07.dto.ServicoDto;
import org.serratec.java02backend.projeto07.exception.EmailException;
import org.serratec.java02backend.projeto07.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	JavaMailSender emailSender;
	
	@Autowired
	CarroRepository carroRepository;
	
	@Value("${spring.mail.username}")
	private String userName;
	
	@Value("${spring.mail.password}")
	private String password;
	
	@Value("${spring.mail.host}")
	private String host;
	
	private final String emailRemetente = "";
	
	public JavaMailSender javaMailSender() {
		
		JavaMailSenderImpl enviarEmail = new JavaMailSenderImpl();
		Properties prop = new Properties();
		
		enviarEmail.setHost(host);
		enviarEmail.setPort(465);
		enviarEmail.setUsername(userName);
		enviarEmail.setPassword(password);
		enviarEmail.setProtocol("smtp");
		enviarEmail.setDefaultEncoding("UTF-8");
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.ssl.enable", true);
		enviarEmail.setJavaMailProperties(prop);
		
		return enviarEmail;		
	}
	
	public void sendMessage(String to, String subject, String text) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailRemetente);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}
	
	public void enviarEmail(ServicoDto dto) throws MessagingException, EmailException {
		this.emailSender = javaMailSender();
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		String data = dto.getData().toString();
		String descricao = dto.getDescricao();
		String valor = String.format("R$ %.2f", dto.getValor());
		String email = carroRepository.findById(dto.getIdCarro())
				.get()
				.getCliente()
				.getEmail();
		
		try {
			helper.setFrom(userName);
			helper.setTo(email);
			helper.setSubject("Borracharia Pneu Vazio - Nota de Serviço");
			
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("<html lang=\"en\">\r\n"
					+ "\r\n"
					+ "<body>\r\n"
					+ "<h3>Seu carro já está pronto!</h3>"
					+ "<a>Segue abaixo a sua nota de serviço:</a>"
					+ "<br></br>"
					+ "<a>========================================</a>"
					+ "<br></br>"
					+ "<a>Data: </a>" + data
					+ "<br></br>"
					+ "<a>Descrição: </a>" + descricao
					+ "<br></br>"
					+ "<a>Valor: </a>" + valor
					+ "<br></br>"
					+ "<a>========================================</a>"
					+ "</body>\r\n"
					+ "\r\n"
					+ "</html>");
			
			helper.setText(sBuilder.toString(), true);
			
			emailSender.send(message);
		} catch(Exception e) {
			throw new EmailException("Não foi possível enviar o email!");
		}
	}

}