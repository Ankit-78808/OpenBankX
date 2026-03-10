// UserRepository.java
package com.cts.openbankx.repository;

import com.cts.openbankx.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }