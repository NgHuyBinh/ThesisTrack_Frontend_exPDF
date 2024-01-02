package com.luanvan.ThesisTrack_Backend.recommend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;

@Service
public class RecommendService {
    @Autowired
    private RecommedRepository recommedRepository;

    public void createRecommend(Recommend recommend) {
        recommedRepository.save(recommend);
    }

    public List<Recommend> getByTeacherId(Integer id) {
        return recommedRepository.findByTeacher1Id(id);
    }

    public List<Recommend> getByTeacherId23(Integer id) {
        return recommedRepository.findByTeacher2_IdOrTeacher3_Id(id, id);
    }

    public void updateStatus(RecommendRequestDTO recommendRequestDTO) {
        Recommend recommend = recommedRepository.findById(recommendRequestDTO.getRecommendId())
                .orElseThrow(() -> new NotFoundException("Recommendation not found"));

        if (recommend.getTeacher2().getId().equals(recommendRequestDTO.getTeacherId())) {
            // Update status2
            recommend.setStatus2(recommendRequestDTO.getStatus());
        } else if (recommend.getTeacher3().getId().equals(recommendRequestDTO.getTeacherId())) {
            // Update status3
            recommend.setStatus3(recommendRequestDTO.getStatus());
        } else {
            throw new NotFoundException("Teacher not found in the recommendation");
        }

        recommedRepository.save(recommend);
    }
}