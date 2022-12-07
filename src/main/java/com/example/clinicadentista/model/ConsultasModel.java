package com.example.clinicadentista.model;


import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Consultas")
public class ConsultasModel {

    @Id
    @SequenceGenerator(name = "consulta_sequence" , sequenceName = "consulta_sequence" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "consulta_sequence")
    private int idConsulta;
    private Date dataConsulta;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "dentista_id")
    private DentistaModel dentistaModel;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "paciente_id")
    private PacienteModel pacienteModel;

    public ConsultasModel() {
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }
}

