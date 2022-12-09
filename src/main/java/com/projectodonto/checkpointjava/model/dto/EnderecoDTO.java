package com.projectodonto.checkpointjava.model.dto;

import com.projectodonto.checkpointjava.model.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoDTO {
    private Long id;
    private String cidade;
    private String rua;
    private String numero;


    public EnderecoDTO(Endereco endereco) {
        this.id = endereco.getId();
        this.cidade = endereco.getCidade();
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
    }


}
