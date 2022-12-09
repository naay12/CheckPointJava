package com.projectodonto.checkpointjava.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "idDentista")
    private Dentista idDentista;
    @OneToOne
    @JoinColumn(name = "idPaciente")
    private Paciente idPaciente;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date data;

    @DateTimeFormat(pattern="hh:mm:ss")
    private Time horario;

    public Consulta(Dentista idDentista, Paciente idPaciente, Date data, Time horario) {
        this.idDentista = idDentista;
        this.idPaciente = idPaciente;
        this.data = data;
        this.horario = horario;
    }

}
