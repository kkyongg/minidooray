package com.nhnacademy.taskapi.service;

import com.nhnacademy.taskapi.domain.dto.tag.TagIdNameDto;
import com.nhnacademy.taskapi.entity.Tag;
import com.nhnacademy.taskapi.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    public Tag findById(long id) {
        Optional<Tag> savedTag = tagRepository.findById(id);

        return savedTag.get();
    }

    public List<TagIdNameDto> findALlTagIdNameByTaskId(long taskId) {
        List<TagIdNameDto> tagIdNameDtoList = tagRepository.findAllTagIdNameByTaskId(taskId);
        return tagIdNameDtoList;
    }

    public List<TagIdNameDto> findALlTagIdNameByProjectId(long projectId) {
        List<TagIdNameDto> tagIdNameDtoList = tagRepository.findAllTagIdNameByProjectId_Id(projectId);
        return tagIdNameDtoList;
    }



    public Tag saveTag(Tag tag){
        Tag savedTag = tagRepository.save(tag);
        return savedTag;
    }

    public void deleteTagById(long tagId) {
        tagRepository.deleteById(tagId);

    }
}
