package com.pretz.dyntreeserver.repository;

import com.pretz.dyntreeserver.domain.DynTree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DynTreeRepo extends JpaRepository<DynTree, Long> {
}
