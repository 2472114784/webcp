package com.game.cp.model.entity.openResult.repository;

import com.game.cp.model.entity.openResult.entity.OpenResultEntityForPC28;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 添加注解
public interface OpenResultRepositoryForPc28 extends JpaRepository<OpenResultEntityForPC28, Long> {

}
