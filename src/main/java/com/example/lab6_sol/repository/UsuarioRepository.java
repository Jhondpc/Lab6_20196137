package com.example.lab6_sol.repository;

import com.example.lab6_sol.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByRolid(int rol);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value= "INSERT INTO `registro_db`.`usuario` (`nombres`, `apellidos`, `dni`, `edad`, `correo`, `password`, `activo`, `rolid`) VALUES (?1, ?2, ?3, ?4, ?5, 'sfsdf', '1', '5')")
    void guardarEstudiante(String nombre, String apellido, String dni, int edad, String correo);
}
