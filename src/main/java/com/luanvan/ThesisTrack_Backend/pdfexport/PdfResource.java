package com.luanvan.ThesisTrack_Backend.pdfexport;

// import com.bezkoder.spring.security.jwt.entity.DonViThucTap;
// import com.bezkoder.spring.security.jwt.entity.Khoa;
// import com.bezkoder.spring.security.jwt.entity.Lop;
// import com.bezkoder.spring.security.jwt.services.DonViThucTapService;
// import com.bezkoder.spring.security.jwt.services.KhoaService;
// import com.bezkoder.spring.security.jwt.services.LopService;
import com.itextpdf.text.*;
// import com.itextpdf.text.pdf.BaseFont;
// import com.itextpdf.text.pdf.PdfPCell;
// import com.itextpdf.text.pdf.PdfPTable;
// import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
// import com.luanvan.ThesisTrack_Backend.registertopic.RegisiterResource;
import com.luanvan.ThesisTrack_Backend.registertopic.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.ThesisTrack_Backend.registertopic.RegisterTopic;

import jakarta.servlet.http.HttpServletResponse;

// import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
@RestController
@RequestMapping("api/v1/pdf")
public class PdfResource {
    private final RegisterService registerService;


    @Autowired
    public PdfResource(RegisterService registerService){
        this.registerService = registerService;
    }
    
    @GetMapping("/registertopic")
    public void displayRegisterTopicPdf(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf; charset=UTF-8");
        String fontPath = "src/main/resources/times.ttf";
        List<RegisterTopic> registerTopics = registerService.getAllRegister();

        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // PdfWriter writer = PdfWriter.getInstance(document, baos);
        PdfWriter.getInstance(document, baos);
        document.open();

        // Tạo font và định dạng
        BaseFont customBaseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font titleFont = new Font(customBaseFont, 18, Font.NORMAL, BaseColor.BLACK);
        Font contentFont = new Font(customBaseFont, 12, Font.NORMAL, BaseColor.DARK_GRAY);


        // Định dạng tiêu đề
        Paragraph title = new Paragraph("DANH SÁCH SINH VIÊN ĐĂNG KÝ ĐỀ TÀI", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Định dạng nội dung
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        // Tiêu đề cho từng cột
        PdfPCell cell1 = new PdfPCell(new Phrase("STT", contentFont));
        PdfPCell cell2 = new PdfPCell(new Phrase("Tên sinh viên", contentFont));
        PdfPCell cell3 = new PdfPCell(new Phrase("Đề tài sinh viên", contentFont));
        PdfPCell cell4 = new PdfPCell(new Phrase("Đề tài giảng viên", contentFont));
        PdfPCell cell5 = new PdfPCell(new Phrase("Giảng viên hướng dẫn", contentFont));
        // Đặt kích thước và định dạng cho cột
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);

        // Thêm dữ liệu từ danh sách các khoa
        for (RegisterTopic registerTopic : registerTopics) {
            PdfPCell cellSTT = new PdfPCell(new Phrase(String.valueOf(registerTopic.getId()), contentFont));
            PdfPCell cellNameStudent = new PdfPCell(new Phrase(registerTopic.getStudent().getName(), contentFont));
            PdfPCell cellTopicName = new PdfPCell(new Phrase(registerTopic.getTopicName(), contentFont));
            PdfPCell cellTopic = new PdfPCell(new Phrase(registerTopic.getTopic().getName(), contentFont));
            PdfPCell cellTeacher = new PdfPCell(new Phrase(registerTopic.getTopic().getTeacher().getName(), contentFont));


            cellSTT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellNameStudent.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTopicName.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTopic.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTeacher.setHorizontalAlignment(Element.ALIGN_CENTER);


            table.addCell(cellSTT);
            table.addCell(cellNameStudent);
            table.addCell(cellTopicName);
            table.addCell(cellTopic);
            table.addCell(cellTeacher);
        }
        document.add(new Paragraph("\n"));
        document.add(table);

        document.close();

        byte[] pdfBytes = baos.toByteArray();
        response.setContentLength(pdfBytes.length);
        OutputStream os = response.getOutputStream();
        os.write(pdfBytes);
        os.close();
    }

}

