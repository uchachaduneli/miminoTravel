package ge.mimino.travel.service;


import ge.mimino.travel.dao.MailDAO;
import ge.mimino.travel.dto.EmailDTO;
import ge.mimino.travel.model.Email;
import ge.mimino.travel.model.Users;
import ge.mimino.travel.request.MailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

/**
 * @author ucha
 */
@Service
public class MailService {

    private String userName;
    private String password;
    private String sendingHost;
    private int sendingPort;
    private String from;
    private String to;
    private String subject;
    private String text;
    private String receivingHost;

    @Autowired
    private MailDAO mailDAO;


    public List<EmailDTO> getEmails(int start, int limit, MailRequest srchRequest) throws ParseException {
        return EmailDTO.parseToList(mailDAO.getEMails(start, limit, srchRequest));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Email save(MailRequest request) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Email obj = new Email();
        obj.setFrom(request.getFrom());
        obj.setTo(request.getTo());
        obj.setSubject(request.getSubject());
        obj.setContent(request.getContent());
        obj.setAttachments(request.getAttachments());
        obj.setSendDate(new Timestamp(sdf.parse(request.getSendDate()).getTime()));
        obj.setReceiveDate(new Timestamp(sdf.parse(request.getReceiveDate()).getTime()));
        obj.setUser((Users) mailDAO.find(Users.class, request.getUserId()));
//
        if (request.getId() != null) {
            obj.setId(request.getId());
            obj = (Email) mailDAO.update(obj);
        } else {
            obj = (Email) mailDAO.create(obj);
        }

        return obj;
    }


//    *************************************************** Email Syncronization ****************************************************************

    public void setAccountDetails(String userName, String password) {

        this.userName = userName;//sender's email can also use as User Name
        this.password = password;

    }

    public void sendGmail(String from, String to, String subject, String text) {

        // This will send mail from -->sender@gmail.com to -->receiver@gmail.com

        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;

        // For a Gmail account--sending mails-- host and port shold be as follows

        this.sendingHost = "smtp.gmail.com";
        this.sendingPort = 465;

        Properties props = new Properties();

        props.put("mail.smtp.host", this.sendingHost);
        props.put("mail.smtp.port", String.valueOf(this.sendingPort));
        props.put("mail.smtp.user", this.userName);
        props.put("mail.smtp.password", this.password);

        props.put("mail.smtp.auth", "true");

        Session session1 = Session.getDefaultInstance(props);

        Message simpleMessage = new MimeMessage(session1);

        //MIME stands for Multipurpose Internet Mail Extensions

        InternetAddress fromAddress = null;
        InternetAddress toAddress = null;

        try {

            fromAddress = new InternetAddress(this.from);
            toAddress = new InternetAddress(this.to);

        } catch (AddressException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(null, "Sending email to: " + to + " failed !!!", "Falied to Send!!!", JOptionPane.ERROR_MESSAGE);

        }

        try {

            simpleMessage.setFrom(fromAddress);

            simpleMessage.setRecipient(Message.RecipientType.TO, toAddress);

            // to add CC or BCC use
            // simpleMessage.setRecipient(RecipientType.CC, new InternetAddress("CC_Recipient@any_mail.com"));
            // simpleMessage.setRecipient(RecipientType.BCC, new InternetAddress("CBC_Recipient@any_mail.com"));

            simpleMessage.setSubject(this.subject);

            simpleMessage.setText(this.text);

            //sometimes Transport.send(simpleMessage); is used, but for gmail it's different

            Transport transport = session1.getTransport("smtps");

            transport.connect(this.sendingHost, sendingPort, this.userName, this.password);

            transport.sendMessage(simpleMessage, simpleMessage.getAllRecipients());

            transport.close();

            System.out.println("Mail sent ");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    private String getTextFromMimeMultipart(
            MimeMultipart mimeMultipart) throws MessagingException, IOException {
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
            }
        }
        return result;
    }

    public void readGmail() {

        /*this will print subject of all messages in the inbox of sender@gmail.com*/

        this.receivingHost = "imap.yandex.ru";//for imap protocol

        Properties props2 = System.getProperties();

        props2.setProperty("mail.store.protocol", "imaps");
        props2.put("mail.debug", "false");
        props2.put("mail.store.protocol", "imaps");
        props2.put("mail.imap.ssl.enable", "true");
        props2.put("mail.imap.port", "993");
        // I used imaps protocol here

        Session session2 = Session.getDefaultInstance(props2, null);

        try {

            Store store = session2.getStore("imaps");

            store.connect(this.receivingHost, this.userName, this.password);

            Folder folder = store.getFolder("INBOX");//get inbox
            System.out.println("has new messages: " + folder.hasNewMessages() + "\n \n");

            folder.open(Folder.READ_ONLY);//open folder only to read

            Message message[] = folder.getMessages();

            for (int i = 0; i < message.length; i++) {

                //print subjects of all mails in the inbox

                System.out.println("getSubject " + message[i].getSubject());
                System.out.println("getAllRecipients ");
                for (Address address : message[i].getAllRecipients()) {
                    System.out.println(((InternetAddress) address).getAddress());
                }
                System.out.println(" ------------------------- ");
                System.out.println("getMessageNumber " + message[i].getMessageNumber());
                System.out.println("getFrom ");
                System.out.println(message[i].getFrom() != null ? ((InternetAddress) message[i].getFrom()[0]).getAddress() : "");
                System.out.println("getSentDate " + message[i].getSentDate());
                System.out.println("getReceivedDate " + message[i].getReceivedDate());
                //get Content
                if (message[i].isMimeType("text/plain")) {
                    System.out.println("getContent " + message[i].getContent().toString());
                } else if (message[i].isMimeType("multipart/*")) {
                    MimeMultipart mimeMultipart = (MimeMultipart) message[i].getContent();
                    System.out.println("getContent " + getTextFromMimeMultipart(mimeMultipart));
                }
                System.out.println("\n \n *************** \n \n");

                //anything else you want

            }

            //close connections

            folder.close(true);

            store.close();

        } catch (Exception e) {

            System.out.println(e.toString());

        }

    }

    public void downloadEmailAttachments() {

        this.receivingHost = "imap.yandex.ru";//for imap protocol
        Properties props2 = System.getProperties();

        props2.setProperty("mail.store.protocol", "imaps");
        props2.put("mail.debug", "false");
        props2.put("mail.store.protocol", "imaps");
        props2.put("mail.imap.ssl.enable", "true");
        props2.put("mail.imap.port", "993");

        Session session2 = Session.getDefaultInstance(props2, null);

        try {

            Store store = session2.getStore("imaps");

            store.connect(this.receivingHost, this.userName, this.password);

            Folder folder = store.getFolder("INBOX");//get inbox
            System.out.println("has new messages: " + folder.hasNewMessages() + "\n \n");

            folder.open(Folder.READ_ONLY);//open folder only to read

            Message arrayMessages[] = folder.getMessages();

            for (int i = 0; i < arrayMessages.length; i++) {
                Message message = arrayMessages[i];
                Address[] fromAddress = message.getFrom();
                String from = fromAddress[0].toString();
                String subject = message.getSubject();
                String sentDate = message.getSentDate().toString();
                String receiveDate = message.getReceivedDate().toString();

                String contentType = message.getContentType();
                String messageContent = "";

                // store attachment file name, separated by comma
                String attachFiles = "";

                if (contentType.contains("multipart")) {
                    // content may contain attachments
                    Multipart multiPart = (Multipart) message.getContent();
                    int numberOfParts = multiPart.getCount();
                    for (int partCount = 0; partCount < numberOfParts; partCount++) {
                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                            // this part is attachment
                            String fileName = part.getFileName();
                            attachFiles += fileName + ", ";
                            part.saveFile("C:\\Users\\ME\\IdeaProjects\\MyMailer\\src\\main\\resources" + File.separator + fileName);
                        } else {
                            // this part may be the message content
                            if (arrayMessages[i].isMimeType("text/plain")) {
                                messageContent = arrayMessages[i].getContent().toString();
                            } else if (arrayMessages[i].isMimeType("multipart/*")) {
                                MimeMultipart mimeMultipart = (MimeMultipart) arrayMessages[i].getContent();
                                messageContent = getTextFromMimeMultipart(mimeMultipart);
                            }
//                            messageContent = part.getContent().toString();
                        }
                    }

                    if (attachFiles.length() > 1) {
                        attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
                    }
                } else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
                    Object content = message.getContent();
                    if (content != null) {
                        messageContent = content.toString();
                    }
                }

//				print out details of each message
                System.out.println("Message #" + arrayMessages[i].getMessageNumber() + ":");
                System.out.println("\t From: " + from);
                System.out.println("\t Subject: " + subject);
                System.out.println("\t Sent Date: " + sentDate);
                System.out.println("\t Receive Date: " + receiveDate);
                System.out.println("\t Message: " + messageContent);
                System.out.println("\t Attachments: " + attachFiles);
            }

            // disconnect
            folder.close(false);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider for pop3.");
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

//        String mailFrom = new String("miminotest@gmail.com");
//        String mailTo = new String("miminotest@gmail.com");
        String senderPassword = new String("1qaz@WSX1qaz");
        String senderUserName = new String("miminotest@yandex.ru");
//        String mailSubject = new String("Testing Mail");
//        String mailText = new String("Have an Nice Day ...........!!!");
        MailService newGmailClient = new MailService();
        newGmailClient.setAccountDetails(senderUserName, senderPassword);

//        newGmailClient.sendGmail(mailFrom, mailTo, mailSubject, mailText);

//        newGmailClient.readGmail();
        newGmailClient.downloadEmailAttachments();

    }
}