package com.luanvan.ThesisTrack_Backend.email;

import java.time.LocalDate;

import javax.swing.Spring;
import javax.swing.text.html.CSS;
import javax.swing.text.html.HTML;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(Email email, String studentName) {
        MimeMessage message = mailSender.createMimeMessage();
        try {

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("thesistrack2023@gmail.com");
            helper.setTo(email.getToEmail());
            helper.setSubject(email.getSubject());
            // String test = "<p>Chạy lên</p>";
            // Tạo nội dung HTML với các thẻ và CSS tùy chỉnh
            String htmlContent = "<!DOCTYPE html>\r\n" + //
                    "<html>\r\n" + //
                    "\r\n" + //
                    "<head>\r\n" + //
                    "    <style>\r\n" + //
                    "        /* CSS cho phần nội dung email */\r\n" + //
                    "        body {\r\n" + //
                    "            font-family: Arial, sans-serif;\r\n" + //
                    "        }\r\n" + //
                    "\r\n" + //
                    "        .email-container {\r\n" + //
                    "            max-width: 800px;\r\n" + //
                    "            margin: 0 auto;\r\n" + //
                    "            padding: 20px;\r\n" + //
                    "            background-color: #f0f0f0;\r\n" + //
                    "        }\r\n" + //
                    "\r\n" + //
                    "        .header {\r\n" + //
                    "            background-color: #007bff;\r\n" + //
                    "            color: #ffffff;\r\n" + //
                    "            text-align: center;\r\n" + //
                    "            padding: 10px;\r\n" + //
                    "        }\r\n" + //
                    "\r\n" + //
                    "        table {\r\n" + //
                    "            width: 100%;\r\n" + //
                    "            margin-bottom: 20px;\r\n" + //
                    "            border: 1px solid #ddd;\r\n" + //
                    "            border-collapse: collapse;\r\n" + //
                    "            border: none;\r\n" + //
                    "        }\r\n" + //
                    "\r\n" + //
                    "        th,\r\n" + //
                    "        td {\r\n" + //
                    "            padding: 10px;\r\n" + //
                    "        }\r\n" + //
                    "\r\n" + //
                    "        .footer {\r\n" + //
                    "            background-color: #007bff;\r\n" + //
                    "            color: #ffffff;\r\n" + //
                    "            text-align: center;\r\n" + //
                    "            padding: 10px;\r\n" + //
                    "        }\r\n" + //
                    "\r\n" + //
                    "        .title {\r\n" + //
                    "            font-weight: bold;\r\n" + //
                    "        }\r\n" + //
                    "\r\n" + //
                    "        * {\r\n" + //
                    "            margin: 0;\r\n" + //
                    "            padding: 0;\r\n" + //
                    "        }\r\n" + //
                    "    </style>\r\n" + //
                    "</head>\r\n" + //
                    "\r\n" + //
                    "<body>\r\n" + //
                    "    <div class=\"email-container\">\r\n" + //
                    "        <div class=\"header\">\r\n" + //
                    "            <h3 style=\"font-weight: bold\">\r\n" + //
                    "                LỊCH BÁO CÁO LUẬN VĂN CỦA BẠN\r\n" + //
                    "            </h3>\r\n" + //
                    "        </div>\r\n" + //
                    "        <table style=\"background-color: white\">\r\n" + //
                    "            <tr>\r\n" + //
                    "                <td class=\"title\">Mã số sinh viên:</td>\r\n" + //
                    "                <td>[Mã số sinh viên của bạn]</td>\r\n" + //
                    "            </tr>\r\n" + //
                    "            <tr>\r\n" + //
                    "                <td class=\"title\">Tên:</td>\r\n" + //
                    "                <td>[Tên của bạn]</td>\r\n" + //
                    "            </tr>\r\n" + //
                    "            <tr>\r\n" + //
                    "                <td class=\"title\">Ngày sinh:</td>\r\n" + //
                    "                <td>[Ngày sinh của bạn]</td>\r\n" + //
                    "            </tr>\r\n" + //
                    "            <tr>\r\n" + //
                    "                <td class=\"title\">Giới tính:</td>\r\n" + //
                    "                <td>[Giới tính của bạn]</td>\r\n" + //
                    "            </tr>\r\n" + //
                    "            <tr>\r\n" + //
                    "                <td class=\"title\">Lớp học:</td>\r\n" + //
                    "                <td>[Lớp học của bạn]</td>\r\n" + //
                    "            </tr>\r\n" + //
                    "            <tr>\r\n" + //
                    "                <td class=\"title\">Ngành:</td>\r\n" + //
                    "                <td>[Ngành của bạn]</td>\r\n" + //
                    "            </tr>\r\n" + //
                    "            <tr>\r\n" + //
                    "                <td class=\"title\">Email:</td>\r\n" + //
                    "                <td>[Địa chỉ email của bạn]</td>\r\n" + //
                    "            </tr>\r\n" + //
                    "            <tr>\r\n" + //
                    "                <td class=\"title\">Số điện thoại:</td>\r\n" + //
                    "                <td>[Số điện thoại của bạn]</td>\r\n" + //
                    "            </tr>\r\n" + //
                    "            <tr>\r\n" + //
                    "                <td class=\"title\">Phòng báo cáo: </td>\r\n" + //
                    "                <td>phòng</td>\r\n" + //
                    "            </tr>\r\n" + //
                    "            <tr>\r\n" + //
                    "                <td class=\"title\">Ngày báo cáo:</td>\r\n" + //
                    "                <td>ngày</td>\r\n" + //
                    "            </tr>\r\n" + //
                    "            <tr>\r\n" + //
                    "                <td class=\"title\">Giờ báo cáo</td>\r\n" + //
                    "                <td>giờ</td>\r\n" + //
                    "            </tr>\r\n" + //
                    "        </table>\r\n" + //
                    "\r\n" + //
                    "        <div class=\"footer\">\r\n" + //
                    "            <p>LỊCH BÁO CÁO LUẬN VĂN TỐT NGHIỆP TRƯỜNG CÔNG NGHỆ THÔNG TIN VÀ TRUYỀN THÔNG</p>\r\n" + //
                    "            <p>\r\n" + //
                    "                Khu 2, đường 3/2, Phường Xuân Khánh, Q. Ninh Kiều, TP. Cần Thơ, Việt Nam\r\n" + //
                    "            </p>\r\n" + //
                    "            <p>\r\n" + //
                    "                Điện thoại: 84 0292 3 734713 - 0292 3 831301; Fax: 84 0292 3830841; Email: office@cit.ctu.edu.vn\r\n" + //
                    "            </p>\r\n" + //
                    "        </div>\r\n" + //
                    "    </div>\r\n" + //
                    "</body>\r\n" + //
                    "</html>";  

            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
