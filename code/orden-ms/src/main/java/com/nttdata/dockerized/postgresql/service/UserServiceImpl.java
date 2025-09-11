package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.exceptions.BadRequestException;
import com.nttdata.dockerized.postgresql.exceptions.InternalServerErrorException;
import com.nttdata.dockerized.postgresql.exceptions.ResourceNotFoundException;
import com.nttdata.dockerized.postgresql.model.entity.User;
import com.nttdata.dockerized.postgresql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> listAll() {
        //return (List<User>) userRepository.findAll();
        try {
            return (List<User>) userRepository.findAll();
        } catch (DataAccessException e) {
            throw new InternalServerErrorException("Error al listar usuarios");
        }
    }

    @Override
    public User findById(Long id) {
        //return userRepository.findById(id).orElse(null);
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuario con id " + id + " no encontrado")
        );
    }

    @Override
    public User save(User user) {
        //user.setActive(Boolean.TRUE);
        //return userRepository.save(user);
        if (user.getName() == null || user.getEmail() == null) {
            throw new BadRequestException("El nombre y el email son obligatorios");
        }
        user.setActive(Boolean.TRUE);
        try {
            return userRepository.save(user);
        } catch (DataAccessException e) {
            throw new InternalServerErrorException("Error al guardar el usuario");
        }
    }

    @Override
    public User update(Long id, User user) {
        User userSaved = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con id " + id + " no encontrado"));

        if (user.getName() == null || user.getEmail() == null) {
            throw new BadRequestException("El nombre y el email son obligatorios para actualizar");
        }

        userSaved.setName(user.getName());
        userSaved.setEmail(user.getEmail());

        try {
            return userRepository.save(userSaved);
        } catch (DataAccessException e) {
            throw new InternalServerErrorException("Error al actualizar el usuario");
        }
    }

    @Override
    public void delete(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con id " + id + " no encontrado"));

        try {
            userRepository.delete(user);
        } catch (DataAccessException e) {
            throw new InternalServerErrorException("Error al eliminar el usuario");
        }

    }


}
