package com.proyect.jef.repositorios;

import com.proyect.jef.entidades.Tablero;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


 // @author Kharon estudio Web

@Repository
public interface TableroRepositorio extends JpaRepository<Tablero, String>{

     @Query("SELECT c.perfiles.nombre WHERE c.perfiles.nombre LIKE :nombre")
    public Collection<Tablero> findPerfiles(@Param("nombre") String nombre);
}
