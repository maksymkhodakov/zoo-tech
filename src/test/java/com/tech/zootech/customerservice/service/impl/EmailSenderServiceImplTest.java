package com.tech.zootech.customerservice.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Ignore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {EmailSenderServiceImpl.class, String.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class EmailSenderServiceImplTest {
    @Autowired
    private EmailSenderServiceImpl emailSenderServiceImpl;

    @MockBean
    private JavaMailSender javaMailSender;

    /**
     * Method under test: {@link EmailSenderServiceImpl#send(String, String, String, String)}
     */
    @Test
    public void testSend() throws MailException {
        doNothing().when(javaMailSender).send((SimpleMailMessage) any());
        emailSenderServiceImpl.send("jane.doe@example.org", "alice.liddell@example.org", "Hello from the Dreaming Spires",
                "Text");
        verify(javaMailSender).send((SimpleMailMessage) any());
    }

    /**
     * Method under test: {@link EmailSenderServiceImpl#sendWithAttach(String, String, String, String, String, InputStreamSource)}
     */
    @Test
    public void testSendWithAttach() throws MessagingException, IOException, MailException {
        doNothing().when(javaMailSender).send((MimeMessage) any());
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        InputStreamSource inputStreamSource = mock(InputStreamSource.class);
        when(inputStreamSource.getInputStream()).thenReturn(new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8")));
        emailSenderServiceImpl.sendWithAttach("jane.doe@example.org", "alice.liddell@example.org",
                "Hello from the Dreaming Spires", "Text", "Attach Name", inputStreamSource);
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send((MimeMessage) any());
        verify(inputStreamSource).getInputStream();
    }

    /**
     * Method under test: {@link EmailSenderServiceImpl#sendWithAttach(String, String, String, String, String, InputStreamSource)}
     */
    @Test
    public void testSendWithAttach2() throws MessagingException, IOException, MailException {
        doNothing().when(javaMailSender).send((MimeMessage) any());
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        InputStreamSource inputStreamSource = mock(InputStreamSource.class);
        when(inputStreamSource.getInputStream()).thenThrow(new IOException());
        assertThrows(IOException.class, () -> emailSenderServiceImpl.sendWithAttach("jane.doe@example.org",
                "alice.liddell@example.org", "Hello from the Dreaming Spires", "Text", "Attach Name", inputStreamSource));
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send((MimeMessage) any());
        verify(inputStreamSource).getInputStream();
    }



    /**
     * Method under test: {@link EmailSenderServiceImpl#sendWithAttach(String, String, String, String, String, InputStreamSource)}
     */
    @Test
    public void testSendWithAttach4() throws MessagingException, IOException, MailException {
        MimeMessage mimeMessage = mock(MimeMessage.class);
        doNothing().when(mimeMessage).setRecipient((Message.RecipientType) any(), (Address) any());
        doNothing().when(mimeMessage).setContent((Multipart) any());
        doNothing().when(mimeMessage).setFrom((Address) any());
        doNothing().when(mimeMessage).setSubject((String) any());
        doNothing().when(javaMailSender).send((MimeMessage) any());
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        InputStreamSource inputStreamSource = mock(InputStreamSource.class);
        when(inputStreamSource.getInputStream()).thenReturn(new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8")));
        emailSenderServiceImpl.sendWithAttach("jane.doe@example.org", "alice.liddell@example.org",
                "Hello from the Dreaming Spires", "Text", "Attach Name", inputStreamSource);
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send((MimeMessage) any());
        verify(mimeMessage).setRecipient((Message.RecipientType) any(), (Address) any());
        verify(mimeMessage).setContent((Multipart) any());
        verify(mimeMessage).setFrom((Address) any());
        verify(mimeMessage).setSubject((String) any());
        verify(inputStreamSource).getInputStream();
    }


    /**
     * Method under test: {@link EmailSenderServiceImpl#sendWithAttach(String, String, String, String, String, InputStreamSource)}
     */
    @Test
    public void testSendWithAttach6() throws MessagingException, IOException, MailException {
        MimeMessage mimeMessage = mock(MimeMessage.class);
        doNothing().when(mimeMessage).setRecipient((Message.RecipientType) any(), (Address) any());
        doNothing().when(mimeMessage).setContent((Multipart) any());
        doNothing().when(mimeMessage).setFrom((Address) any());
        doNothing().when(mimeMessage).setSubject((String) any());
        doNothing().when(javaMailSender).send((MimeMessage) any());
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        InputStreamSource inputStreamSource = mock(InputStreamSource.class);
        when(inputStreamSource.getInputStream()).thenReturn(new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8")));
        emailSenderServiceImpl.sendWithAttach("From", "alice.liddell@example.org", "Hello from the Dreaming Spires",
                "Text", "Attach Name", inputStreamSource);
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send((MimeMessage) any());
        verify(mimeMessage).setRecipient((Message.RecipientType) any(), (Address) any());
        verify(mimeMessage).setContent((Multipart) any());
        verify(mimeMessage).setFrom((Address) any());
        verify(mimeMessage).setSubject((String) any());
        verify(inputStreamSource).getInputStream();
    }


    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link EmailSenderServiceImpl#EmailSenderServiceImpl(JavaMailSender)}
     *   <li>{@link EmailSenderServiceImpl#setServerRedirectEmail(String)}
     * </ul>
     */
    @Test
    public void testConstructor() {
        (new EmailSenderServiceImpl(new JavaMailSenderImpl())).setServerRedirectEmail("jane.doe@example.org");
        assertEquals("jane.doe@example.org", EmailSenderServiceImpl.serverRedirectEmail);
    }
}

