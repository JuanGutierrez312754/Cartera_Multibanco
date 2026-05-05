package com.itm.cartera_multibanco.service.impl;

import com.itm.cartera_multibanco.model.Banco;
import com.itm.cartera_multibanco.repository.BancoRepository;
import com.itm.cartera_multibanco.service.BancoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BancoServiceImpl implements BancoService {

    private final BancoRepository bancoRepository;

    public BancoServiceImpl(BancoRepository bancoRepository) {
        this.bancoRepository = bancoRepository;
    }

    @Override
    public List<Banco> listarTodo() {
        return bancoRepository.findAll();
    }

    @Override
    public Banco buscarPorId(Integer id) {
        return bancoRepository.findById(id).orElse(null);
    }

    @Override
    public Banco crearBanco(Banco banco) {
        return bancoRepository.save(banco);
    }

    @Override
    public Banco actualizarBanco(Integer id, Banco datosNuevos) {
        Optional<Banco> bancoOptional = bancoRepository.findById(id);

        if (bancoOptional.isEmpty()) {
            return null;
        }

        Banco bancoActual = bancoOptional.get();
        bancoActual.setNombre(datosNuevos.getNombre());

        return bancoRepository.save(bancoActual);
    }

    @Override
    public String eliminarBanco(Integer id) {
        if (!bancoRepository.existsById(id)) {
            return "Error: El banco no existe.";
        }

        bancoRepository.deleteById(id);
        return "Banco eliminado con éxito.";
    }
}