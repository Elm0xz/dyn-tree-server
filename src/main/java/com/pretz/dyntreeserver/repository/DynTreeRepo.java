package com.pretz.dyntreeserver.repository;

import com.pretz.dyntreeserver.domain.DynTree;
import org.springframework.data.repository.CrudRepository;

public interface DynTreeRepo extends CrudRepository<DynTree, Long> {
}
