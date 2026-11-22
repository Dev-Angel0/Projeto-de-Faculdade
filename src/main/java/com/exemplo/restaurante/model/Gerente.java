package com.exemplo.restaurante.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "gerentes")
@PrimaryKeyJoinColumn(name = "funcionario_id")
public class Gerente extends Funcionario {

    @NotBlank
    @Column(nullable = false)
    private String setor;

    @NotNull
    @DecimalMin("0.0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal bonusMensal;

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public BigDecimal getBonusMensal() {
        return bonusMensal;
    }

    public void setBonusMensal(BigDecimal bonusMensal) {
        this.bonusMensal = bonusMensal;
    }
}
