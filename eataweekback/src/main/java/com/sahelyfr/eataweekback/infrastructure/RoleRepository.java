package com.sahelyfr.eataweekback.infrastructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sahelyfr.eataweekback.domain.enums.RoleEnum;
import com.sahelyfr.eataweekback.infrastructure.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
  Role findByName(RoleEnum name);

}
