package com.proyect.jef.controladores;

 // @author Kharon estudio Web
import com.proyect.jef.excepciones.ErrorJef;
import com.proyect.jef.servicios.PerfilDesarrolladorServicio;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/")
public class PerfilControlador {

    @Autowired
    private PerfilDesarrolladorServicio servicioPerfil;
    
    @GetMapping("/creacionPerfil")
    public String creacionDesarrollador(){
        return "CrearPerfil.html";
    }
    
    @PostMapping("/creacionPerfil")
    public String crearDesarrollador(@RequestParam String nombre,@RequestParam String link,@RequestParam  String descripcion,@RequestParam(required = false)  MultipartFile archivo){
        try
        {
            servicioPerfil.crearPerfilDesarrollador(nombre, link, descripcion, archivo);
            return "Index.html";
        } catch (ErrorJef ex)
        {
            System.out.println(ex.getMessage());
            return "CrearPerfil.html";
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
            return "CrearPerfil.html";
        }
    }
    
}
