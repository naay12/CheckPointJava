package com.example.clinicadentista.model;

import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Dentista")
public class DentistaModel {

    @Id
    @SequenceGenerator(name = "dentista_sequence" , sequenceName = "dentista_sequence" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "dentista_sequence")
    private Integer id;
    private String nome;
    private String email;
    private String numMatricula;
    private int atendeConvenio;

    @OneToMany(mappedBy = "dentistaModel" , fetch = FetchType.LAZY)
    private Set<ConsultasModel> consultasModels = new HashSet<>();



    public DentistaModel(Integer id, String nome, String email, String numMatricula, int atendeConvenio) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.numMatricula = numMatricula;
        this.atendeConvenio = atendeConvenio;
    }
    public DentistaModel(String nome, String email, String numMatricula, int atendeConvenio) {
        this.nome = nome;
        this.email = email;
        this.numMatricula = numMatricula;
        this.atendeConvenio = atendeConvenio;
    }


    public DentistaModel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumMatricula() {
        return numMatricula;
    }

    public void setNumMatricula(String numMatricula) {
        this.numMatricula = numMatricula;
    }

    public int getAtendeConvenio() {
        return atendeConvenio;
    }

    public void setAtendeConvenio(int atendeConvenio) {
        this.atendeConvenio = atendeConvenio;
    }
}
