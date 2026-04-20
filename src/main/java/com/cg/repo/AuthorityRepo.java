package com.cg.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.entity.Authority;

public interface AuthorityRepo extends JpaRepository<Authority, Integer> {

    Optional<Authority> findByRole(String role);
}