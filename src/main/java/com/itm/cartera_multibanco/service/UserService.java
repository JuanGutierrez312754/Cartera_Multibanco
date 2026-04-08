package com.itm.cartera_multibanco.service;

import com.itm.cartera_multibanco.dao.UserDao;
import com.itm.cartera_multibanco.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> listarTodo() {
        return userDao.findAll();
    }

    public String crearUsuario(User usuario) {

        if (userDao.existsByCedula(usuario.getCedula())) {
            return "Error: La cédula ya está registrada.";
        }
        
        userDao.save(usuario);
        return "Usuario creado con éxito.";
    }

    public User buscarPorCedula(String cedula) {
        return userDao.findByCedula(cedula);
    }

    // 4. Actualizar un usuario existente
    public String actualizarUsuario(String cedula, User datosNuevos) {
        if (!userDao.existsByCedula(cedula)) {
            return "Error: El usuario con cédula " + cedula + " no existe.";
        }
        userDao.update(cedula, datosNuevos);
        return "Usuario actualizado correctamente.";
    }

    // 5. Eliminar un usuario
    public String eliminarUsuario(String cedula) {
        if (!userDao.existsByCedula(cedula)) {
            return "Error: No se puede eliminar, el usuario no existe.";
        }
        userDao.delete(cedula);
        return "Usuario eliminado con éxito.";
    }
}