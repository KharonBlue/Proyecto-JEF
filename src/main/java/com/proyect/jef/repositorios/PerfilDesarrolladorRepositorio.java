package com.proyect.jef.repositorios;

import com.proyect.jef.entidades.PerfilDesarrollador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


 // @author Kharon estudio Web

@Repository
public interface PerfilDesarrolladorRepositorio extends JpaRepository<PerfilDesarrollador, String>{

    @Query("SELECT c WHERE PerfilDesarrollador c LIKE :nombre")
    public PerfilDesarrollador findByNombre(String nombre);

}
