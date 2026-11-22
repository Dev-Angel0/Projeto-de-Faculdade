package com.exemplo.restaurante.controller;

import com.exemplo.restaurante.model.Endereco;
import com.exemplo.restaurante.service.EnderecoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController extends AbstractCrudController<Endereco> {

    public EnderecoController(EnderecoService service) {
        super(service);
    }
}
