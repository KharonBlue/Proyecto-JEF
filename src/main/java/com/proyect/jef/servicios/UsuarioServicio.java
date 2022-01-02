package com.proyect.jef.servicios;

import com.proyect.jef.entidades.Usuario;
import com.proyect.jef.excepciones.ErrorJef;
import com.proyect.jef.repositorios.UsuarioRepositorio;
import com.proyect.jef.role.RolUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private MailServicio mailServicio;

@Autowired
private UsuarioRepositorio usuarioRepositorio;

@Transactional
    public void registrarUsuario(String username, String password, String mail) throws ErrorJef{
        validarRegistro(username,password,mail);
        Usuario usuario = new Usuario();
        usuario.setRol(RolUsuario.USER);
        usuario.setUsername(username);
        usuario.setMail(mail);
        String encriptada = new BCryptPasswordEncoder().encode(password);
        usuario.setPassword(encriptada);

        mailServicio.enviar("Gracias por registrarte en JEF", "Bienvenido al proyecto JEF", mail);
    }

    private void validarRegistro(String username, String password, String mail) throws ErrorJef {
        if(username.length()<3){
            throw new ErrorJef("La longitud del nombre de usuario no puede ser menor a 3");
        }
        if(mail.contains("@")==false || mail.length()<5){
            throw new ErrorJef("El mail ingresado no es valido");
        }

        if(password.length()<7){
            throw new ErrorJef("La longitud de la clave no puede ser menor a 7");
        }

        int contLetraMay=0;
        int contLetraMin=0;
        int contNumero=0;
        char clave;

        for (int i = 0; i < password.length(); i++) {
            clave = password.charAt(i);
            String passValue = String.valueOf(clave);
            if (passValue.matches("[A-Z]")) {
                contLetraMay++;
            } else if (passValue.matches("[a-z]")) {
                contLetraMin++;
            } else if (passValue.matches("[0-9]")) {
                contNumero++;
            }
        }
        if(contLetraMay<1 || contLetraMin<1 || contNumero<1){
            throw new ErrorJef("La clave debe contener al menos 1 mayuscula, 1 minuscula y 1 número");
        }

    }


    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByMail(mail);
        if (usuario != null) {
            if (usuario.getRol().equals("USER")) {
                ArrayList<GrantedAuthority> permisos = new ArrayList<>();

                GrantedAuthority p1 = new SimpleGrantedAuthority("MODULO_FOTOS");
                permisos.add(p1);
                GrantedAuthority p2 = new SimpleGrantedAuthority("MODULO_INFORMACION");
                permisos.add(p2);
                GrantedAuthority p3 = new SimpleGrantedAuthority("MODULO_EDICION");
                permisos.add(p3);

                User user = new User(usuario.getMail(), usuario.getPassword(), permisos);
                return user;
            } else if (usuario.getRol().equals("ADMIN")) {
                ArrayList<GrantedAuthority> permisos = new ArrayList<>();

                GrantedAuthority p1 = new SimpleGrantedAuthority("MODULO_FOTOS");
                permisos.add(p1);
                GrantedAuthority p2 = new SimpleGrantedAuthority("MODULO_INFORMACION");
                permisos.add(p2);
                GrantedAuthority p3 = new SimpleGrantedAuthority("MODULO_GESTION");
                permisos.add(p3);
                GrantedAuthority p4 = new SimpleGrantedAuthority("MODULO_EDICION");
                permisos.add(p4);

                User user = new User(usuario.getMail(), usuario.getPassword(), permisos);
                return user;
            } else {
                return null;
            }

        } else {
            return null;
        }
    }
}
