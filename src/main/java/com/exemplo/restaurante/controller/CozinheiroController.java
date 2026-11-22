package com.exemplo.restaurante.controller;

import com.exemplo.restaurante.model.Cozinheiro;
import com.exemplo.restaurante.service.CozinheiroService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cozinheiros")
public class CozinheiroController extends AbstractCrudController<Cozinheiro> {

    public CozinheiroController(CozinheiroService service) {
        super(service);
    }
}
