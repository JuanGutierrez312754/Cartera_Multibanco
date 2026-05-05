package com.itm.cartera_multibanco.repository;

import com.itm.cartera_multibanco.model.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoRepository extends JpaRepository<Banco, Integer> {

    boolean existsByNombre(String nombre);
}