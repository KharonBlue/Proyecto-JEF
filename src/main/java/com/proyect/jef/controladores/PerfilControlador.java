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
import org.springframework.web.multipart.MultipartFile;

//
//@Controller
//@RequestMapping("/perfil")
public class PerfilControlador {

//    @Autowired
//    private PerfilDesarrolladorServicio servicioPerfil;
//    
//    @GetMapping("/creaciondesarrollador")
//    public String creacionDesarrollador(){
//        return "Creardesarrollador.html";
//    }
//    
//    @PostMapping("/creaciondesarrollador")
//    public String crearDesarrollador(String nombre, String linkExterno, String descripcion, MultipartFile archivo){
//        try
//        {
//            servicioPerfil.crearPerfilDesarrollador(nombre, linkExterno, descripcion, archivo);
//            return "Perfil.html";
//        } catch (ErrorJef ex)
//        {
//            Logger.getLogger(PerfilControlador.class.getName()).log(Level.SEVERE, null, ex);
//            return "Creardesarrollador.html";
//        } catch (IOException ex)
//        {
//            Logger.getLogger(PerfilControlador.class.getName()).log(Level.SEVERE, null, ex);
//            return "Creardesarrollador.html";
//        }
//    }
//    
}
