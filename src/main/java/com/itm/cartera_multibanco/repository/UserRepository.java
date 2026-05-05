package com.itm.cartera_multibanco.repository;

import com.itm.cartera_multibanco.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByCedula(String cedula);

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}