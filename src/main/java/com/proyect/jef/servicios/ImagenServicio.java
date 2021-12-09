package com.proyect.jef.servicios;

import com.proyect.jef.entidades.Imagen;
import com.proyect.jef.excepciones.ErrorJef;
import com.proyect.jef.repositorios.ImagenRepositorio;
import java.io.IOException;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// @author Kharon estudio Web
@Service
public class ImagenServicio {

    @Autowired
    private ImagenRepositorio imgRepositorio;

    @Transactional
    public Imagen guardarImagen(MultipartFile archivo) throws ErrorJef, IOException {
        if (archivo != null)
        {
            Imagen img = new Imagen();
            img.setNombre(archivo.getName());
            img.setMime(archivo.getContentType());
            img.setContenido(archivo.getBytes());
            return imgRepositorio.save(img);
        } else
        {
            return null;
        }
    }

    @Transactional
    public Imagen actualizarImagen(MultipartFile archivo, String idImg) throws ErrorJef, IOException {
        if (archivo != null)
        {
            Imagen img = new Imagen();
            if (idImg != null)
            {
                Optional<Imagen> resultado = imgRepositorio.findById(idImg);
                if (resultado.isPresent())
                {
                    img = resultado.get();
                    img.setNombre(archivo.getName());
                    img.setMime(archivo.getContentType());
                    img.setContenido(archivo.getBytes());
                    return imgRepositorio.save(img);
                }
            }
        }
        return null;
    }

    @Transactional
    public void eliminarFoto(String idImg) throws ErrorJef {
        if (idImg != null)
        {
            Optional<Imagen> resultado = imgRepositorio.findById(idImg);
            if (resultado.isPresent())
            {
                imgRepositorio.delete(resultado.get());
            }
        } else
        {
            throw new ErrorJef("No se encontro el ID de la imagen a eliminar");
        }
    }

    @Transactional
    public Imagen buscarImagen(String idImg) throws ErrorJef {
        if (idImg != null)
        {
            return imgRepositorio.findById(idImg).get();
        } else
        {
            return null;
        }
    }
}
