package com.projectodonto.checkpointjava.model.dto;

import com.projectodonto.checkpointjava.model.Dentista;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistaDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private String matricula;

    public DentistaDTO(Dentista dentista) {
        this.id = dentista.getId();
        this.nome = dentista.getNome();
        this.sobrenome = dentista.getSobrenome();
        this.matricula = dentista.getMatricula();
    }
}
