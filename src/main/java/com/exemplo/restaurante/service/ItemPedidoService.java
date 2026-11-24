package com.exemplo.restaurante.service;

import com.exemplo.restaurante.exception.BusinessRuleException;
import com.exemplo.restaurante.model.ItemPedido;
import com.exemplo.restaurante.model.Pedido;
import com.exemplo.restaurante.model.Produto;
import com.exemplo.restaurante.repository.ItemPedidoRepository;
import com.exemplo.restaurante.repository.PedidoRepository;
import com.exemplo.restaurante.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemPedidoService extends AbstractCrudService<ItemPedido> {

    private final ItemPedidoRepository repository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoService pedidoService;

    public ItemPedidoService(
            ItemPedidoRepository repository,
            PedidoRepository pedidoRepository,
            ProdutoRepository produtoRepository,
            PedidoService pedidoService
    ) {
        super(repository, "Item do pedido");
        this.repository = repository;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoService = pedidoService;
    }

    @Override
    protected void definirId(ItemPedido entidade, Long id) {
        entidade.setId(id);
    }

    @Override
    @Transactional
    public ItemPedido criar(ItemPedido item) {
        Pedido pedido = buscarPedido(requererPedidoId(item.getPedido()));
        Produto produto = buscarProduto(requererProdutoId(item.getProduto()));
        item.setPedido(pedido);
        item.setProduto(produto);
        ItemPedido salvo = repository.saveAndFlush(item);
        pedidoService.recalcularValorTotal(pedido.getId());
        return salvo;
    }

    @Override
    @Transactional
    public ItemPedido atualizar(Long id, ItemPedido dadosAtualizados) {
        ItemPedido item = buscarPorId(id);
        Long pedidoAnteriorId = item.getPedido().getId();
        Pedido novoPedido = buscarPedido(requererPedidoId(dadosAtualizados.getPedido()));
        Produto produto = buscarProduto(requererProdutoId(dadosAtualizados.getProduto()));

        item.setPedido(novoPedido);
        item.setProduto(produto);
        item.setQuantidade(dadosAtualizados.getQuantidade());
        item.setPrecoUnitario(dadosAtualizados.getPrecoUnitario());
        ItemPedido salvo = repository.saveAndFlush(item);

        if (!pedidoAnteriorId.equals(novoPedido.getId())) {
            pedidoService.recalcularValorTotal(pedidoAnteriorId);
        }
        pedidoService.recalcularValorTotal(novoPedido.getId());
        return salvo;
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        ItemPedido item = buscarPorId(id);
        Long pedidoId = item.getPedido().getId();
        repository.delete(item);
        repository.flush();
        pedidoService.recalcularValorTotal(pedidoId);
    }

    private Pedido buscarPedido(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Pedido com id " + id + " não encontrado"));
    }

    private Produto buscarProduto(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Produto com id " + id + " não encontrado"));
    }

    private Long requererPedidoId(Pedido pedido) {
        Long id = pedido == null ? null : pedido.getId();
        if (id == null) {
            throw new BusinessRuleException(
                    "Informe o id do pedido do item");
        }
        return id;
    }

    private Long requererProdutoId(Produto produto) {
        Long id = produto == null ? null : produto.getId();
        if (id == null) {
            throw new BusinessRuleException(
                    "Informe o id do produto do item");
        }
        return id;
    }
}
