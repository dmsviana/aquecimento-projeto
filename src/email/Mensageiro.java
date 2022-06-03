package email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;

public class Mensageiro {

	private static String remetente = "myseriespoo@gmail.com";
	private static String senha = "pooifpb2022";
	private static String assunto = "Programação do dia";
	private static String mensagem = "Olá, você recebeu essa mensagem porque solicitou as informações da programação do dia de hoje. Confira abaixo: ";
	
	
	public static String enviarProgramacaoDeHoje(String destinatario) throws Exception {
	
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator(remetente, senha));
		email.setSSLOnConnect(true);

		try {
			email.setFrom(remetente);
			email.setSubject(assunto);
			email.setMsg(mensagem);
			email.addTo(destinatario);

			EmailAttachment anexo = new EmailAttachment();
			anexo.setPath(
					"C:\\Users\\marce\\Dropbox\\IFPB\\AquecimentoProjeto 2.0\\AquecimentoProjeto\\relatorios-hoje.pdf");
			anexo.setName("relatorio-programacao.pdf");
			email.attach(anexo);

			email.send();
			return "Email enviado com sucesso!";

		} catch (Exception e) {
			return "Falha ao enviar email!";
		}
	}

}
