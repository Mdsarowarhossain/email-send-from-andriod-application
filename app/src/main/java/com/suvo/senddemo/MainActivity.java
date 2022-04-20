package com.suvo.senddemo;

import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import javax.mail.MessagingException;

import javax.mail.Session;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;

import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;

import java.util.Properties;

public class MainActivity extends AppCompatActivity {
	EditText email, message;
	Button send;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		email = findViewById(R.id.email);

		message = findViewById(R.id.message);
		send = findViewById(R.id.send);
		send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				final String username = "sarowarshuvo002@gmail.com";
				final String password = "nkqfmliwugizbfgz";
				String messagetosend = message.getText().toString();
					//	String fromt = form.getText().toString();
					Properties properties = new Properties();
					properties.put("mail.smtp.auth", "true");
					properties.put("mail.smtp.starttls.enable", "true");
					properties.put("mail.smtp.host", "smtp.gmail.com");
					//properties.put("mail.smtp.port","143");
					properties.put("mail.smtp.port", "587");
					Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
							@Override
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(username, password);
							}

						});

					//	properties.put("mail.smtp.port", "587");

					try {
						Message message = new MimeMessage(session);
						message.setFrom(new InternetAddress(username));
						message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getText().toString()));

						message.setSubject("this is email");
						message.setText(messagetosend);

						Transport.send(message);
						Toast.makeText(getApplicationContext(), "send", Toast.LENGTH_SHORT).show();

					} catch (MessagingException e) {
						throw new RuntimeException(e);
					}

			}

		});
StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
StrictMode.setThreadPolicy(policy);
	}
}