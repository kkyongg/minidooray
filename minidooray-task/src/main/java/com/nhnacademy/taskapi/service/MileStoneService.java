package com.nhnacademy.taskapi.service;

import com.nhnacademy.taskapi.domain.dto.milestone.MileStoneIdStatusDto;
import com.nhnacademy.taskapi.entity.MileStone;
import com.nhnacademy.taskapi.entity.Tag;
import com.nhnacademy.taskapi.repository.MileStoneRepository;
import com.nhnacademy.taskapi.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MileStoneService {

    private final MileStoneRepository mileStoneRepository;

    public MileStone findById(long id) {
        Optional<MileStone> savedMileStone = mileStoneRepository.findById(id);

        return savedMileStone.get();

    }

    public List<MileStoneIdStatusDto> findAllMileStoneIdStatusByProjectId(long projectId){
        List<MileStoneIdStatusDto> mileStoneIdStatusDtoList = mileStoneRepository.findAllMileStoneIdStatusByProjectId_Id(projectId);

        return mileStoneIdStatusDtoList;
    }

    public MileStoneIdStatusDto findMileStoneByTaskId(long taskId) {
        Optional<MileStoneIdStatusDto> mileStoneDto = mileStoneRepository.findMileStoneIdStatusByTaskId(taskId);
        return mileStoneDto.get();
    }

    public MileStone saveMileStone(MileStone mileStone) {
        MileStone savedMileStone = mileStoneRepository.save(mileStone);
        return savedMileStone;
    }


    public void deleteById(long mileStoneId) {
        mileStoneRepository.deleteById(mileStoneId);

    }
}
