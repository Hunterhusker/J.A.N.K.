package com.jank;


import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Properties;

public class JankMain {

    public static void main(String[] args) throws IOException, MessagingException {
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
        char[] buffer = new char[1024];
        int numRead = 0;

        while ((numRead = reader.read(buffer)) != -1) {
            fileData.append(buffer, 0, numRead);
        }
        reader.close();

        Properties props = System.getProperties();
        Session session = Session.getInstance(props, null);
        MimeMessage message = new MimeMessage(session, new ByteArrayInputStream(fileData.toString().getBytes()));

        System.out.println(message.getContent());

    }
}
