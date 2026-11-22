package com.exemplo.restaurante.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cozinheiros")
@PrimaryKeyJoinColumn(name = "funcionario_id")
public class Cozinheiro extends Funcionario {

    @NotBlank
    @Column(nullable = false)
    private String especialidade;

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
