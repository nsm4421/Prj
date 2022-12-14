package com.karma.prj.repository;

import com.karma.prj.model.entity.PostEntity;
import com.karma.prj.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Page<PostEntity> findAll(Pageable pageable);
    Page<PostEntity> findAllByUser(Pageable pageable,UserEntity userEntity);
    @Modifying
    @Query(value = "UPDATE PostEntity entity SET removed_at = NOW() where entity.id = :postId", nativeQuery = true)
    void deleteByPostId(@Param("postId") Long postId);
}
