package com.exemplo.restaurante.service;

import com.exemplo.restaurante.model.ItemPedido;
import com.exemplo.restaurante.repository.ItemPedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService extends AbstractCrudService<ItemPedido> {

    public ItemPedidoService(ItemPedidoRepository repository) {
        super(repository, "Item do pedido");
    }

    @Override
    protected void definirId(ItemPedido entidade, Long id) {
        entidade.setId(id);
    }
}
