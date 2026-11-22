package com.exemplo.restaurante.controller;

import com.exemplo.restaurante.model.Entregador;
import com.exemplo.restaurante.service.EntregadorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entregadores")
public class EntregadorController extends AbstractCrudController<Entregador> {

    public EntregadorController(EntregadorService service) {
        super(service);
    }
}
