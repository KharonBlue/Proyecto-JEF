package com.proyect.jef.servicios;

import com.proyect.jef.entidades.Imagen;
import com.proyect.jef.entidades.PerfilDesarrollador;
import com.proyect.jef.excepciones.ErrorJef;
import com.proyect.jef.repositorios.PerfilDesarrolladorRepositorio;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// @author Kharon estudio Web
@Service
public class PerfilDesarrolladorServicio {

    @Autowired
    private PerfilDesarrolladorRepositorio perfilDesarrollador;
    @Autowired
    private ImagenServicio imgServicio;

    public PerfilDesarrollador crearPerfilDesarrollador(String nombre, String linkExterno, String descripcion, MultipartFile archivo) throws ErrorJef, IOException {
        validarDatosCreacion(nombre, linkExterno);
        PerfilDesarrollador perfil = new PerfilDesarrollador();
        perfil.setNombre(nombre);
        perfil.setLinkExterno(linkExterno);
        if (descripcion == null || descripcion.length() < 15 || descripcion.isEmpty())
        {
            perfil.setPerfilCompleto(false);
        } else
        {
            perfil.setPerfilCompleto(true);
        }
        Imagen img = imgServicio.guardarImagen(archivo);
        perfil.setImagen(img);
        perfil.setPerfilActivo(true);
        return perfilDesarrollador.save(perfil);
    }

    public void validarDatosCreacion(String nombre, String linkExterno) throws ErrorJef {
        if (nombre.isEmpty() || nombre.length() <= 0 || nombre == null)
        {
            throw new ErrorJef("El campo nombre es obligatorio");
        }
        if (nombre.length() <= 2)
        {
            throw new ErrorJef("La longitud del campo nombre no puede ser menor a 3");
        }
        if (linkExterno.isEmpty() || linkExterno.length() <= 10 || linkExterno == null)
        {
            throw new ErrorJef("El campo link externo es necesario para que las empresar puedan obtener más información");
        }
    }

    public PerfilDesarrollador actualizarPerfilDesarrollador(String idPerfil, String nombre, String linkExterno, String descripcion, MultipartFile archivo) throws ErrorJef, IOException {
        if (idPerfil != null)
        {
            Optional<PerfilDesarrollador> resultado = perfilDesarrollador.findById(idPerfil);
            if (resultado.isPresent())
            {
                PerfilDesarrollador perfil = resultado.get();

                validarDatosCreacion(nombre, linkExterno);
                perfil.setNombre(nombre);
                perfil.setLinkExterno(linkExterno);
                if (descripcion == null || descripcion.length() < 15 || descripcion.isEmpty())
                {
                    perfil.setPerfilCompleto(false);
                } else
                {
                    perfil.setPerfilCompleto(true);
                }

                String idImagen = null;
                if (perfil.getImagen() != null)
                {
                    idImagen = perfil.getImagen().getId();
                }
                Imagen imagen = imgServicio.actualizarImagen(archivo, idImagen);
                perfil.setImagen(imagen);

                perfil.setPerfilActivo(true);
                return perfilDesarrollador.save(perfil);

            } else
            {
                throw new ErrorJef("No se encontro el perfil ingresado");
            }
        } else
        {
            return null;
        }
    }
    
    public void marcarInactivo(String idPerfil) throws ErrorJef{
        if(idPerfil!=null){
            Optional<PerfilDesarrollador> resultado = perfilDesarrollador.findById(idPerfil);
            if(resultado.isPresent()){
                PerfilDesarrollador perfil = resultado.get();
                perfil.setPerfilActivo(false);
            } else {
                throw new ErrorJef("No se encontro el perfil ingresado");
            }
        }
    }
    
    public void eliminarPerfilDesarrollador(String idPerfil) throws ErrorJef{
        if(idPerfil!=null){
            Optional<PerfilDesarrollador> resultado = perfilDesarrollador.findById(idPerfil);
            if(resultado.isPresent()){
                PerfilDesarrollador perfil = resultado.get();
                perfilDesarrollador.delete(perfil);
            } else {
                throw new ErrorJef("No se encontro el perfil ingresado");
            }
        }
    }
    
    public PerfilDesarrollador buscarPorId(String idPerfil) throws ErrorJef{
        return perfilDesarrollador.findById(idPerfil).get();
    }
    
    
    public Collection<PerfilDesarrollador> buscarTodos() throws ErrorJef{
        return perfilDesarrollador.findAll();
    }
}
