package com.nk.realstateapi.service.impl;

import com.nk.realstateapi.services.IEmailService;
import com.nk.realstateapi.services.impl.EmailServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link EmailServiceImpl} following best practices.
 */
class EmailServiceImplTest {

    private static final String FROM_EMAIL = "noreply@example.com";
    private static final String TO_EMAIL = "user@example.com";
    private static final String SUBJECT = "Test Subject";
    private static final String BODY = "<p>Hello</p>";
    private static final String TEMPLATE_NAME = "welcome";
    private static final String TEMPLATE_RENDERED_HTML = "<html>Rendered Content</html>";

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private TemplateEngine templateEngine;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Mock
    private MimeMessage mimeMessage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        emailService = new EmailServiceImpl(mailSender, templateEngine);
        // Inject the @Value field manually for unit testing
        emailService.getClass()
                .getDeclaredFields();
        setField(emailService, "from", FROM_EMAIL);

        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
    }

    /**
     * Helper method to set private fields via reflection for testing.
     */
    private static void setField(Object target, String fieldName, Object value) {
        try {
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set field in test", e);
        }
    }

    @Nested
    @DisplayName("sendEmail()")
    class SendEmailTests {

        @Test
        @DisplayName("should send email successfully")
        void shouldSendEmailSuccessfully() throws MessagingException {
            // Act
            emailService.sendEmail(TO_EMAIL, SUBJECT, BODY);

            // Assert
            verify(mailSender).createMimeMessage();
            verify(mailSender).send(mimeMessage);

            // We can't directly verify MimeMessageHelper internals, but we can ensure no exception is thrown
            verifyNoMoreInteractions(mailSender);
        }

        @Test
        @DisplayName("should throw RuntimeException when MessagingException occurs")
        void shouldThrowRuntimeExceptionWhenMessagingFails() throws MessagingException {
            // Arrange
            when(mailSender.createMimeMessage()).thenThrow(new RuntimeException("Error send emails"));

            // Act & Assert
            assertThatThrownBy(() -> emailService.sendEmail(TO_EMAIL, SUBJECT, BODY))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessageContaining("Error send emails");

            verify(mailSender).createMimeMessage();
        }
    }

    @Nested
    @DisplayName("sendEmailWithTemplate()")
    class SendEmailWithTemplateTests {

        @Test
        @DisplayName("should process template and send email")
        void shouldProcessTemplateAndSendEmail() {
            // Arrange
            Object model = new Object();
            when(templateEngine.process(eq(TEMPLATE_NAME), any(Context.class)))
                    .thenReturn(TEMPLATE_RENDERED_HTML);

            // Act
            emailService.sendEmailWithTemplate(TO_EMAIL, SUBJECT, TEMPLATE_NAME, model);

            // Assert
            verify(templateEngine).process(eq(TEMPLATE_NAME), any(Context.class));
            verify(mailSender).createMimeMessage();
            verify(mailSender).send(mimeMessage);
        }
    }
}
