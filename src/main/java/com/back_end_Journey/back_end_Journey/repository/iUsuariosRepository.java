package com.back_end_Journey.back_end_Journey.repository;

import com.back_end_Journey.back_end_Journey.model.usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iUsuariosRepository extends JpaRepository<usuarios, Integer> {
    usuarios findByCorreo(String correo); // ejemplo útil
}
