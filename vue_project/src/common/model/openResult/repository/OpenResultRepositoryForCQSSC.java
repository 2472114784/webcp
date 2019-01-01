package com.game.cp.model.entity.openResult.repository;

import com.game.cp.model.entity.openResult.entity.OpenResultEntityForCQSSC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 添加注解
public interface OpenResultRepositoryForCQSSC extends JpaRepository<OpenResultEntityForCQSSC, Long> {

}
