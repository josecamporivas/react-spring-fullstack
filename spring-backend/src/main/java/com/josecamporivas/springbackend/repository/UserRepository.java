package com.josecamporivas.springbackend.repository;

import com.josecamporivas.springbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
