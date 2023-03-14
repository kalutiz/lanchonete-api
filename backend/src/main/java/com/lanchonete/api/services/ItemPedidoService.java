package com.lanchonete.api.services;

import com.lanchonete.api.dto.ItemPedidoDTO;
import com.lanchonete.api.entities.Ingrediente;
import com.lanchonete.api.entities.ItemPedido;
import com.lanchonete.api.entities.Pedido;
import com.lanchonete.api.repositories.IngredienteRepository;
import com.lanchonete.api.repositories.ItemPedidoRepository;
import com.lanchonete.api.repositories.PedidoRepository;
import com.lanchonete.api.services.exceptions.DataBaseException;
import com.lanchonete.api.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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

    @Transactional(readOnly = true)
    public Page<ItemPedidoDTO> findAllPaged(Pageable pageable) {
        Page<ItemPedido> list = repository.findAll(pageable);
        return list.map(x -> new ItemPedidoDTO(x, x.getPedido()));
    }

    @Transactional(readOnly = true)
    public ItemPedidoDTO findById(Long id) {
        Optional<ItemPedido> obj = repository.findById(id);
        ItemPedido entity = obj.orElseThrow(() -> new ResourceNotFoundException("Item não encontrado"));
        return new ItemPedidoDTO(entity, entity.getPedido());
    }

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

        return new ItemPedidoDTO(entity, entity.getPedido());
    }

    @Transactional
    public ItemPedidoDTO update(Long id, ItemPedidoDTO dto) {
        try {
            ItemPedido entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ItemPedidoDTO(entity, entity.getPedido());
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("ID não encontrado: " + id);
        }
    }

    @Transactional
    public List<ItemPedido> insert(List<ItemPedido> itensPedido, Pedido pedido) {
        pedido.setItensPedido(repository.saveAll(itensPedido));

        atualizaPedidoPrecoTotal(pedido, itensPedido);

        return itensPedido;
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("ID não encontrado: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Violação de integridade");
        }
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
