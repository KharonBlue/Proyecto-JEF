package com.proyect.jef.entidades;

 // @author Kharon estudio Web
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name = "Tablero")
public class Tablero {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "Id_Tablero")
    private String id;
    @OneToMany(mappedBy = "tablero")
    private List<PerfilDesarrollador> perfiles;
}
