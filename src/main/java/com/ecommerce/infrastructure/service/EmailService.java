package com.ecommerce.infrastructure.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Service for sending emails.
 */
@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username:noreply@ecommerce.com}")
    private String fromEmail;

    @Value("${app.name:E-Commerce}")
    private String appName;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sends a simple text email.
     */
    @Async
    public void sendSimpleEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            mailSender.send(message);
            log.info("Email sent to: {}", to);
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", to, e.getMessage());
        }
    }

    /**
     * Sends an HTML email.
     */
    @Async
    public void sendHtmlEmail(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            log.info("HTML email sent to: {}", to);
        } catch (MessagingException e) {
            log.error("Failed to send HTML email to {}: {}", to, e.getMessage());
        }
    }

    /**
     * Sends a welcome email to new users.
     */
    @Async
    public void sendWelcomeEmail(String to, String username) {
        String subject = "Welcome to " + appName + "!";
        String content = String.format("""
                <html>
                <body style="font-family: Arial, sans-serif; padding: 20px;">
                    <h1 style="color: #333;">Welcome to %s, %s!</h1>
                    <p>Thank you for creating an account with us.</p>
                    <p>You can now browse our products, add items to your cart, and make purchases.</p>
                    <p>If you have any questions, feel free to contact our support team.</p>
                    <br>
                    <p>Happy shopping!</p>
                    <p>The %s Team</p>
                </body>
                </html>
                """, appName, username, appName);

        sendHtmlEmail(to, subject, content);
    }

    /**
     * Sends an order confirmation email.
     */
    @Async
    public void sendOrderConfirmationEmail(String to, String orderNumber, String total) {
        String subject = "Order Confirmation - " + orderNumber;
        String content = String.format("""
                <html>
                <body style="font-family: Arial, sans-serif; padding: 20px;">
                    <h1 style="color: #333;">Order Confirmed!</h1>
                    <p>Thank you for your order.</p>
                    <div style="background: #f5f5f5; padding: 20px; border-radius: 5px; margin: 20px 0;">
                        <p><strong>Order Number:</strong> %s</p>
                        <p><strong>Total:</strong> %s</p>
                    </div>
                    <p>We'll send you another email when your order ships.</p>
                    <p>You can track your order in your account dashboard.</p>
                    <br>
                    <p>Thank you for shopping with us!</p>
                    <p>The %s Team</p>
                </body>
                </html>
                """, orderNumber, total, appName);

        sendHtmlEmail(to, subject, content);
    }

    /**
     * Sends a shipping notification email.
     */
    @Async
    public void sendShippingNotificationEmail(String to, String orderNumber, String trackingNumber, String carrier) {
        String subject = "Your Order Has Shipped - " + orderNumber;
        String content = String.format("""
                <html>
                <body style="font-family: Arial, sans-serif; padding: 20px;">
                    <h1 style="color: #333;">Your Order Has Shipped!</h1>
                    <p>Great news! Your order is on its way.</p>
                    <div style="background: #f5f5f5; padding: 20px; border-radius: 5px; margin: 20px 0;">
                        <p><strong>Order Number:</strong> %s</p>
                        <p><strong>Carrier:</strong> %s</p>
                        <p><strong>Tracking Number:</strong> %s</p>
                    </div>
                    <p>You can track your package using the tracking number above.</p>
                    <br>
                    <p>Thank you for shopping with us!</p>
                    <p>The %s Team</p>
                </body>
                </html>
                """, orderNumber, carrier, trackingNumber, appName);

        sendHtmlEmail(to, subject, content);
    }

    /**
     * Sends a password reset email.
     */
    @Async
    public void sendPasswordResetEmail(String to, String resetLink) {
        String subject = "Password Reset Request";
        String content = String.format("""
                <html>
                <body style="font-family: Arial, sans-serif; padding: 20px;">
                    <h1 style="color: #333;">Password Reset</h1>
                    <p>You requested to reset your password.</p>
                    <p>Click the button below to reset your password:</p>
                    <p style="margin: 30px 0;">
                        <a href="%s"
                           style="background: #007bff; color: white; padding: 12px 30px;
                                  text-decoration: none; border-radius: 5px;">
                            Reset Password
                        </a>
                    </p>
                    <p>If you didn't request this, please ignore this email.</p>
                    <p>This link will expire in 1 hour.</p>
                    <br>
                    <p>The %s Team</p>
                </body>
                </html>
                """, resetLink, appName);

        sendHtmlEmail(to, subject, content);
    }
}
