package com.projectodonto.checkpointjava.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEnderecos")
    private Long id;
    private String estado;
    private String cidade;

    private String bairro;
    private String cep;
    private String rua;
    private String numero;

    public Endereco(String estado, String cidade, String bairro, String cep, String rua, String numero) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
    }

    public Endereco(Long id, String cidade, String rua, String numero) {
        this.id = id;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
    }
}
