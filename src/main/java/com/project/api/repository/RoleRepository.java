package com.project.api.repository;

import com.project.api.models.ERole;
import com.project.api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);

  boolean existsByName(ERole eRole);
}
