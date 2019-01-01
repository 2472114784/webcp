package com.game.cp.model.entity.openResult.repository;

import com.game.cp.model.entity.openResult.entity.OpenResultEntityForTJSSC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 添加注解
public interface OpenResultRepositoryForTJSSC extends JpaRepository<OpenResultEntityForTJSSC, Long> {

}
