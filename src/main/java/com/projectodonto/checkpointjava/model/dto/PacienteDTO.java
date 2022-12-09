package com.projectodonto.checkpointjava.model.dto;

import com.projectodonto.checkpointjava.model.Endereco;
import com.projectodonto.checkpointjava.model.Paciente;
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
public class PacienteDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private Endereco endereco;

    public PacienteDTO(Paciente paciente) {
        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.sobrenome = paciente.getSobrenome();
        this.cpf = paciente.getCpf();
        this.endereco = paciente.getEndereco();
    }
}
