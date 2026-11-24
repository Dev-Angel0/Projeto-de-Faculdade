package com.exemplo.restaurante.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exemplo.restaurante.dto.PedidoRequestDTO;
import com.exemplo.restaurante.dto.PedidoResponseDTO;
import com.exemplo.restaurante.exception.BusinessRuleException;
import com.exemplo.restaurante.model.Cliente;
import com.exemplo.restaurante.model.Endereco;
import com.exemplo.restaurante.model.ItemPedido;
import com.exemplo.restaurante.model.Pedido;
import com.exemplo.restaurante.model.PedidoStatus;
import com.exemplo.restaurante.model.Produto;
import com.exemplo.restaurante.repository.ClienteRepository;
import com.exemplo.restaurante.repository.EnderecoRepository;
import com.exemplo.restaurante.repository.EntregadorRepository;
import com.exemplo.restaurante.repository.ItemPedidoRepository;
import com.exemplo.restaurante.repository.PedidoRepository;
import com.exemplo.restaurante.repository.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final EntregadorRepository entregadorRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> listarTodos() {
        return pedidoRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public PedidoResponseDTO buscarPorId(Long id) {
        return toResponse(buscarPedido(id));
    }

    @Transactional
    public PedidoResponseDTO criar(PedidoRequestDTO request) {
        Pedido pedido = new Pedido();
        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatus(PedidoStatus.RECEBIDO);
        preencherPedido(pedido, request);
        return toResponse(pedidoRepository.save(pedido));
    }

    @Transactional
    public PedidoResponseDTO atualizar(Long id, PedidoRequestDTO request) {
        Pedido pedido = buscarPedido(id);
        preencherPedido(pedido, request);
        if (request.status() != null) {
            pedido.setStatus(request.status());
        }
        return toResponse(pedidoRepository.save(pedido));
    }

    @Transactional
    public void recalcularValorTotal(Long pedidoId) {
        Pedido pedido = buscarPedido(pedidoId);
        BigDecimal valorTotal = itemPedidoRepository.findByPedido_Id(pedidoId).stream()
                .map(item -> item.getPrecoUnitario()
                        .multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        pedido.setValorTotal(valorTotal);
        pedidoRepository.save(pedido);
    }

    @Transactional
    public void excluir(Long id) {
        pedidoRepository.delete(buscarPedido(id));
    }

    private Pedido buscarPedido(Long id) {
        return buscarPorId(pedidoRepository, id, "Pedido");
    }

    private void preencherPedido(Pedido pedido, PedidoRequestDTO request) {
        Cliente cliente = buscarPorId(clienteRepository, request.clienteId(), "Cliente");
        Endereco endereco = buscarPorId(enderecoRepository, request.enderecoId(), "Endereço");
        if (!endereco.getCliente().getId().equals(cliente.getId())) {
            throw new BusinessRuleException(
                    "O endereço informado não pertence ao cliente");
        }

        pedido.setCliente(cliente);
        pedido.setEndereco(endereco);
        pedido.setEntregador(request.entregadorId() == null
                ? null
                : buscarPorId(entregadorRepository, request.entregadorId(), "Entregador"));

        pedido.getItens().clear();
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (PedidoRequestDTO.ItemRequest itemRequest : request.itens()) {
            Produto produto = buscarPorId(
                    produtoRepository, itemRequest.produtoId(), "Produto");

            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemRequest.quantidade());
            item.setPrecoUnitario(produto.getPreco());
            pedido.getItens().add(item);

            valorTotal = valorTotal.add(
                    produto.getPreco().multiply(BigDecimal.valueOf(itemRequest.quantidade())));
        }
        pedido.setValorTotal(valorTotal);
    }

    private PedidoResponseDTO toResponse(Pedido pedido) {
        List<PedidoResponseDTO.ItemResponse> itens = pedido.getItens().stream()
                .map(item -> new PedidoResponseDTO.ItemResponse(
                        item.getProduto().getId(),
                        item.getProduto().getNome(),
                        item.getQuantidade(),
                        item.getPrecoUnitario(),
                        item.getPrecoUnitario().multiply(
                                BigDecimal.valueOf(item.getQuantidade()))))
                .toList();

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getDataHora(),
                pedido.getStatus(),
                pedido.getValorTotal(),
                pedido.getCliente().getId(),
                pedido.getEndereco().getId(),
                pedido.getEntregador() == null ? null : pedido.getEntregador().getId(),
                itens);
    }

    private static <T> T buscarPorId(
            JpaRepository<T, Long> repository, Long id, String nomeEntidade
    ) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        nomeEntidade + " com id " + id + " não encontrado"));
    }
}
