package com.example.medicamento1.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Paciente")
public class PacienteModel {

    @Id
    @SequenceGenerator(name = "paciente_sequence" , sequenceName = "paciente_sequence" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "paciente_sequence")
    private Integer id;
    private String nome;
    private String sobrenome;
    private String endereco;
    private String RG;
    private Date dataAlta;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "dentista_id")
    private DentistaModel dentistaModel;

    public PacienteModel() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public Date getDataAlta() {
        return dataAlta;
    }

    public void setDataAlta(Date dataAlta) {
        this.dataAlta = dataAlta;
    }
}
