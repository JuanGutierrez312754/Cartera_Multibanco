package com.itm.cartera_multibanco.dao;

import com.itm.cartera_multibanco.model.Banco;
import java.util.List;

public interface BancoDao {
    List<Banco> findAll();
    Banco findById(Integer id);
}