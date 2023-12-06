package com.luanvan.ThesisTrack_Backend.pdfexport;

import com.itextpdf.text.*;

import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.luanvan.ThesisTrack_Backend.markteacher.Mark;
import com.luanvan.ThesisTrack_Backend.markteacher.MarkService;

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

import java.io.ByteArrayOutputStream;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201", "http://localhost:4202" })
@RestController
@RequestMapping("api/v1/pdfmarks")
public class PdfMarkStudent {

    private static Logger logger = LoggerFactory.getLogger(PdfResource.class);
    private final MarkService markService;

    @Autowired
    public PdfMarkStudent(MarkService markService) {
        this.markService = markService;
    }

    // xuất thẳng file pdf luôn
    @GetMapping("/marks")
    public ResponseEntity<byte[]> generateMarkStudentPdf() {
        String fontPath = "src/main/resources/times.ttf";
        List<Mark> marks = markService.getAllMarks();
        logger.info("lisst regoter: " + marks);
        logger.info("lisst length: " + marks.size());

        // Document document = new Document();

        Document document = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            document.open();

            // Tạo font và định dạng
            BaseFont customBaseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font titleFont = new Font(customBaseFont, 18, Font.NORMAL, BaseColor.BLACK);
            Font contentFont = new Font(customBaseFont, 12, Font.NORMAL, BaseColor.DARK_GRAY);

            // Định dạng tiêu đề
            Paragraph title = new Paragraph("DANH SÁCH SINH VIÊN KÊT QUẢ SINH VIÊN", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Định dạng nội dung
            PdfPTable table = new PdfPTable(15);
            table.setWidthPercentage(100);

            // Độ rộng tương đối của từng cột (tổng của độ rộng này cần phải bằng 100%)
            float[] columnWidths = { 0.7f, 1.5f, 2f, 2f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f };
            table.setWidths(columnWidths);

            // Tiêu đề cho từng cột
            PdfPCell cell1 = new PdfPCell(new Phrase("STT", contentFont));
            PdfPCell cell15 = new PdfPCell(new Phrase("MSSV", contentFont));
            PdfPCell cell2 = new PdfPCell(new Phrase("SV", contentFont));
            PdfPCell cell3 = new PdfPCell(new Phrase("GV", contentFont));
            PdfPCell cell5 = new PdfPCell(new Phrase("Hình thức QBC", contentFont));
            PdfPCell cell6 = new PdfPCell(new Phrase("Tóm tắt", contentFont));
            PdfPCell cell7 = new PdfPCell(new Phrase("Giới thiệu", contentFont));
            PdfPCell cell8 = new PdfPCell(new Phrase("Mô tả", contentFont));
            PdfPCell cell9 = new PdfPCell(new Phrase("Thiêt kế", contentFont));
            PdfPCell cell10 = new PdfPCell(new Phrase("Kiểm thử", contentFont));
            PdfPCell cell11 = new PdfPCell(new Phrase("Kết luận", contentFont));
            PdfPCell cell12 = new PdfPCell(new Phrase("Trình bày", contentFont));
            PdfPCell cell13 = new PdfPCell(new Phrase("Trả lời", contentFont));
            PdfPCell cell14 = new PdfPCell(new Phrase("Điểm tổng", contentFont));
            PdfPCell cell4 = new PdfPCell(new Phrase("Ngày chấm", contentFont));
            // PdfPCell cell6 = new PdfPCell(new Phrase("Tên GVHD", contentFont));

            // Đặt kích thước và định dạng cho cột
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            // cell6.setHorizontalAlignment(Element.ALIGN_CENTER);

            table.addCell(cell1);
            table.addCell(cell15);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell5);
            table.addCell(cell6);
            table.addCell(cell7);
            table.addCell(cell8);
            table.addCell(cell9);
            table.addCell(cell10);
            table.addCell(cell11);
            table.addCell(cell12);
            table.addCell(cell13);
            table.addCell(cell14);
            table.addCell(cell4);
            // table.addCell(cell6);

            // Thêm dữ liệu từ danh sách các đăng ký đề tài
            for (Mark mark : marks) {

                PdfPCell cellCode = new PdfPCell(new Phrase(String.valueOf(mark.getId()), contentFont));
                PdfPCell cellMSSV = new PdfPCell(new Phrase(mark.getStudent().getNumberStudent(), contentFont));
                PdfPCell cellSV = new PdfPCell(new Phrase(mark.getStudent().getName(), contentFont));
                PdfPCell cellGV = new PdfPCell(new Phrase(mark.getTeacher().getName(), contentFont));

                PdfPCell cellMark11 = new PdfPCell(new Phrase(String.valueOf(mark.getMark11()), contentFont));
                PdfPCell cellMark12 = new PdfPCell(new Phrase(String.valueOf(mark.getMark12()), contentFont));
                PdfPCell cellMark13 = new PdfPCell(new Phrase(String.valueOf(mark.getMark13()), contentFont));
                PdfPCell cellMark14 = new PdfPCell(new Phrase(String.valueOf(mark.getMark14()), contentFont));
                PdfPCell cellMark15 = new PdfPCell(new Phrase(String.valueOf(mark.getMark15()), contentFont));

                PdfPCell cellMark2 = new PdfPCell(new Phrase(String.valueOf(mark.getMark2()), contentFont));
                PdfPCell cellMark21 = new PdfPCell(new Phrase(String.valueOf(mark.getMark21()), contentFont));
                PdfPCell cellMark22 = new PdfPCell(new Phrase(String.valueOf(mark.getMark22()), contentFont));
                PdfPCell cellMark23 = new PdfPCell(new Phrase(String.valueOf(mark.getMark23()), contentFont));
                PdfPCell cellSumMark = new PdfPCell(new Phrase(String.valueOf(mark.getSumMark()), contentFont));

                PdfPCell cellDay = new PdfPCell(new Phrase(String.valueOf(mark.getSendDate()), contentFont));

                cellCode.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellMSSV.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellSV.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellGV.setHorizontalAlignment(Element.ALIGN_CENTER);

                cellMark11.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellMark12.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellMark13.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellMark14.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellMark15.setHorizontalAlignment(Element.ALIGN_CENTER);

                cellMark2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellMark21.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellMark22.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellMark23.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellSumMark.setHorizontalAlignment(Element.ALIGN_CENTER);

                cellDay.setHorizontalAlignment(Element.ALIGN_CENTER);
                // cellTeacher.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(cellCode);
                table.addCell(cellMSSV);
                table.addCell(cellSV);
                table.addCell(cellGV);

                table.addCell(cellMark11);
                table.addCell(cellMark12);
                table.addCell(cellMark13);
                table.addCell(cellMark14);
                table.addCell(cellMark15);

                table.addCell(cellMark2);
                table.addCell(cellMark21);
                table.addCell(cellMark22);
                table.addCell(cellMark23);
                table.addCell(cellSumMark);
                
                table.addCell(cellDay);
                // table.addCell(cellTeacher);
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
