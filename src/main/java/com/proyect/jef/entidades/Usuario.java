package com.proyect.jef.entidades;

import com.proyect.jef.role.RolUsuario;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Entity
@Table(name="Usuario")
public class Usuario{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "Id_usuario")
    private String id;

    @Column(unique = true, name = "Username")
    private String username;

    @Column( name = "Password")
    private String password;

    @Column( name = "Mail")
    private String mail;

    @Enumerated(EnumType.STRING)
    private RolUsuario rol;

    private boolean locked=false;
    private boolean unlocked=false;

}
