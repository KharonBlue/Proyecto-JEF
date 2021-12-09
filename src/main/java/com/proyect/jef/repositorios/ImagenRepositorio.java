package com.proyect.jef.repositorios;

import com.proyect.jef.entidades.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @author Kharon estudio Web
@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, String> {


}
