package com.nhnacademy.taskapi.repository;

import com.nhnacademy.taskapi.domain.dto.tag.TagIdNameDto;
import com.nhnacademy.taskapi.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {


    @Query("select ta.id as id, ta.name as name from Tag ta join TaskTag tt on ta.id = tt.tagId.id where tt.taskId.id = :taskId")
    List<TagIdNameDto> findAllTagIdNameByTaskId(long taskId);

    List<TagIdNameDto> findAllTagIdNameByProjectId_Id(long projectId);
}
