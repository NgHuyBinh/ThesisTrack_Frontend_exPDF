package com.luanvan.ThesisTrack_Backend.pdfexport;

import com.itextpdf.text.*;

import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.luanvan.ThesisTrack_Backend.registertopic.RegisterService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.ThesisTrack_Backend.registertopic.RegisterTopic;

import java.io.ByteArrayOutputStream;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201", "http://localhost:4202"  })
@RestController
@RequestMapping("api/v1/pdf")
public class PdfResource {

    private  static Logger logger = LoggerFactory.getLogger(PdfResource.class);
    private final RegisterService registerService;

    @Autowired
    public PdfResource(RegisterService registerService) {
        this.registerService = registerService;
    }

    // xuất thẳng file pdf luôn
    @GetMapping("/registertopic")
    public ResponseEntity<byte[]> generateRegisterTopicPdf() {
        String fontPath = "src/main/resources/times.ttf";
        List<RegisterTopic> registerTopics = registerService.getAllRegister();
        logger.info("lisst regoter: " + registerTopics);
        logger.info("lisst length: " + registerTopics.size());

        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            document.open();

            // Tạo font và định dạng
            BaseFont customBaseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font titleFont = new Font(customBaseFont, 18, Font.NORMAL, BaseColor.BLACK);
            Font contentFont = new Font(customBaseFont, 12, Font.NORMAL, BaseColor.DARK_GRAY);

            // Định dạng tiêu đề
            Paragraph title = new Paragraph("DANH SÁCH CHI TIẾT ĐIỂM SINH VIÊN", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Định dạng nội dung
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            // Độ rộng tương đối của từng cột (tổng của độ rộng này cần phải bằng 100%)
            float[] columnWidths = { 0.5f, 1f, 2f, 2f, 2f };
            table.setWidths(columnWidths);

            // Tiêu đề cho từng cột
            PdfPCell cell1 = new PdfPCell(new Phrase("STT", contentFont));
            PdfPCell cell2 = new PdfPCell(new Phrase("Khoa", contentFont));
            PdfPCell cell3 = new PdfPCell(new Phrase("Tên sinh viên", contentFont));
            PdfPCell cell4 = new PdfPCell(new Phrase("Đề tài sinh viên", contentFont));
            PdfPCell cell5 = new PdfPCell(new Phrase("Đề tài giảng viên", contentFont));
//            PdfPCell cell6 = new PdfPCell(new Phrase("Tên GVHD", contentFont));

            // Đặt kích thước và định dạng cho cột
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell6.setHorizontalAlignment(Element.ALIGN_CENTER);

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
//            table.addCell(cell6);

            // Thêm dữ liệu từ danh sách các đăng ký đề tài
            for (RegisterTopic registerTopic : registerTopics) {
                String idTopicName = "_";
                if (registerTopic.getTopic() == null) {
                    idTopicName = "_";
                } else {
                    idTopicName = registerTopic.getTopic().getName();
                }
//                Topic tptest = registerTopic.getTopic();


                PdfPCell cellSTT = new PdfPCell(new Phrase(String.valueOf(registerTopic.getId()), contentFont));
                PdfPCell cellFaculty = new PdfPCell(
                        new Phrase(registerTopic.getStudent().getFaculty().getName(), contentFont));
                PdfPCell cellNameStudent = new PdfPCell(new Phrase(registerTopic.getStudent().getName(), contentFont));

                PdfPCell cellTopicName = new PdfPCell(new Phrase(registerTopic.getTopicName(), contentFont));
                PdfPCell cellTopic = new PdfPCell( new Phrase(idTopicName, contentFont));


                cellSTT.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellFaculty.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellNameStudent.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellTopicName.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellTopic.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cellTeacher.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(cellSTT);
                table.addCell(cellFaculty);
                table.addCell(cellNameStudent);
                table.addCell(cellTopicName);
                table.addCell(cellTopic);
//                table.addCell(cellTeacher);
            }

            document.add(new Paragraph("\n"));
            document.add(table);
            document.close();

            byte[] pdfBytes = baos.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            headers.setContentDispositionFormData("attachment", "register_topic.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
