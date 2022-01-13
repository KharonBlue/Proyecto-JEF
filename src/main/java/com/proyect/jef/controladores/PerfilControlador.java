package com.proyect.jef.controladores;

 // @author Kharon estudio Web
import com.proyect.jef.excepciones.ErrorJef;
import com.proyect.jef.servicios.PerfilDesarrolladorServicio;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class PerfilControlador {

    @Autowired
    private PerfilDesarrolladorServicio servicioPerfil;
    
    @GetMapping
    @RequestMapping("/crear_desarrollador")
    public String creacionDesarrollador(){
        return "CrearPerfilDesarrollador.html";
    }



    @PostMapping
    @RequestMapping("/creacion_desarrollador")
    public String crearDesarrollador(@RequestParam String nombre,@RequestParam String link,@RequestParam  String descripcion,@RequestParam(required = false)  MultipartFile archivo){
        try
        {
            servicioPerfil.crearPerfilDesarrollador(nombre, link, descripcion, archivo);
            return "Index.html";
        } catch (ErrorJef ex)
        {
            System.out.println(ex.getMessage());
            return "redirect:/creacion_desarrollador";
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
            return "redirect:/creacion_desarrollador";
        }
    }

    @PutMapping
    @RequestMapping("/editar_desarrollador")
    public String editarDesarrollador(String idPerfil, @RequestParam(required = false) String nombre,@RequestParam(required = false) String link,@RequestParam(required = false)  String descripcion,@RequestParam(required = false)  MultipartFile archivo) {
        try {
            servicioPerfil.actualizarPerfilDesarrollador(idPerfil, nombre, link, descripcion, archivo);
            return "Index.html";
        } catch (ErrorJef ex) {
            return "redirect:/editar_desarrollador";
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/editar_desarrollador";
        }
    }

    @DeleteMapping
    @RequestMapping("/eliminar_perfil")
    public String borrarPerfil(@RequestParam String idPerfil){
        try {
            servicioPerfil.eliminarPerfilDesarrollador(idPerfil);
            return "Index.html";
        } catch (ErrorJef e) {
            e.printStackTrace();
            return "Index.html";
        }
    }
}


    

