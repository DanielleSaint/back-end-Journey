package com.back_end_Journey.back_end_Journey.repository;

import com.back_end_Journey.back_end_Journey.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iUsuariosRepository extends JpaRepository<Usuarios, Integer> {
    Usuarios findByCorreo(String correo); // ejemplo útil
}
