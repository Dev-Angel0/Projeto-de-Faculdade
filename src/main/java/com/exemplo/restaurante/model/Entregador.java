package com.exemplo.restaurante.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "entregadores")
@PrimaryKeyJoinColumn(name = "funcionario_id")
@Getter
@Setter
public class Entregador extends Funcionario {

    @NotBlank
    @Column(nullable = false)
    private String veiculo;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String placa;

    @JsonIgnore
    @OneToMany(mappedBy = "entregador", fetch = FetchType.LAZY)
    private List<Pedido> pedidos = new ArrayList<>();

}
