package com.luanvan.ThesisTrack_Backend.recommend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201", "http://localhost:4202"  })
@RequestMapping("api/v1/recommend")
public class RecommendResource {
    @Autowired
    private RecommendService recommendService;
    @PostMapping
    public ResponseEntity<Void> createRecommend(@Valid @RequestBody Recommend recommend) {
        recommendService.createRecommend(recommend);
        return ResponseEntity.ok().build();
    }
    @GetMapping("teacher/{id}")
    public ResponseEntity<List<Recommend>> getByTeacherId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(recommendService.getByTeacherId(id));
    }
    @GetMapping("teacher23/{id}")
    public ResponseEntity<List<Recommend>> getByTeacherId23(@PathVariable Integer id) {
        return ResponseEntity.ok().body(recommendService.getByTeacherId23(id));
    }
    @PatchMapping("")
    public ResponseEntity<Void> updateStatus(@RequestBody RecommendRequestDTO recommedRepository) {
        recommendService.updateStatus(recommedRepository);
        return ResponseEntity.noContent().build();
    }
}
