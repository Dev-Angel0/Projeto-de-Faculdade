package com.exemplo.restaurante.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cozinheiros")
@PrimaryKeyJoinColumn(name = "funcionario_id")
@Getter
@Setter
public class Cozinheiro extends Funcionario {

    @NotBlank
    @Column(nullable = false)
    private String especialidade;

}
