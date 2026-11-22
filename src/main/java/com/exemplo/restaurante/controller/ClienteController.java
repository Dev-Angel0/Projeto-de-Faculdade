package com.exemplo.restaurante.controller;

import com.exemplo.restaurante.model.Cliente;
import com.exemplo.restaurante.service.ClienteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController extends AbstractCrudController<Cliente> {

    public ClienteController(ClienteService service) {
        super(service);
    }
}
