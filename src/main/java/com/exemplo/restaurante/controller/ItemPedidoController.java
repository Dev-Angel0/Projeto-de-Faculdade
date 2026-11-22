package com.exemplo.restaurante.controller;

import com.exemplo.restaurante.model.ItemPedido;
import com.exemplo.restaurante.service.ItemPedidoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itens-pedido")
public class ItemPedidoController extends AbstractCrudController<ItemPedido> {

    public ItemPedidoController(ItemPedidoService service) {
        super(service);
    }
}
