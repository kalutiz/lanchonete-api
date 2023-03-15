package com.lanchonete.api.services;

import com.lanchonete.api.dto.ItemPedidoDTO;
import com.lanchonete.api.entities.Ingrediente;
import com.lanchonete.api.entities.ItemPedido;
import com.lanchonete.api.entities.Pedido;
import com.lanchonete.api.repositories.IngredienteRepository;
import com.lanchonete.api.repositories.ItemPedidoRepository;
import com.lanchonete.api.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository repository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private PromocaoService promocaoService;

    @Transactional
    public ItemPedidoDTO insert(ItemPedidoDTO dto) {
        ItemPedido entity = new ItemPedido();
        copyDtoToEntity(dto, entity);

        Ingrediente ingrediente = ingredienteRepository
                .findById(entity.getIngrediente().getId())
                .orElseThrow(EntityNotFoundException::new);

        entity.setIngrediente(ingrediente);
        entity.setPrecoIngrediente(ingrediente.getPreco());
        entity = repository.save(entity);

        Pedido pedido = pedidoRepository.findById(entity.getPedido().getId()).orElseThrow(EntityNotFoundException::new);

        List<ItemPedido> itensPedido = repository.findAllByPedidoId(pedido.getId());

        atualizaPedidoPrecoTotal(pedido, itensPedido);

        return new ItemPedidoDTO(entity);
    }

    @Transactional
    public List<ItemPedido> insert(List<ItemPedido> itensPedido, Pedido pedido) {
        pedido.setItensPedido(repository.saveAll(itensPedido));

        atualizaPedidoPrecoTotal(pedido, itensPedido);

        return itensPedido;
    }

    private void copyDtoToEntity(ItemPedidoDTO dto, ItemPedido entity) {
        entity.setPrecoIngrediente(dto.getPrecoIngrediente());
    }

    @Transactional
    public void atualizaPedidoPrecoTotal(Pedido pedido, List<ItemPedido> itensPedido) {

        double precoTotal = itensPedido
                .stream()
                .map(ItemPedido::getPrecoIngrediente)
                .reduce(0.0, (subtotal, valor) -> subtotal + valor);

        pedido.setValorTotal(precoTotal);

        pedido.setValorTotal(promocaoService.getDescontoPromocao(pedido));

        pedidoRepository.save(pedido);
    }
}
