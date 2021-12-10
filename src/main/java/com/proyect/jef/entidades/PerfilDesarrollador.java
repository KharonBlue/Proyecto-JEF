package com.proyect.jef.entidades;

// @author Kharon estudio Web
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name = "Perfil_Desarrollador")
public class PerfilDesarrollador {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "Id_Desarrollador")
    private String id;
     @Column(name = "Nombre")
    private String nombre;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Imagen imagen;
    @Column(name = "Link_Externo")
    private String linkExterno;
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "Perfil_Completo")
    private boolean perfilCompleto;
    @Column(name = "Perfil_Activo")
    private boolean perfilActivo;
}
