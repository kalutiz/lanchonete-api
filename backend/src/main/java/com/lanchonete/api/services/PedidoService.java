package com.lanchonete.api.services;

import com.lanchonete.api.dto.PedidoDTO;
import com.lanchonete.api.entities.ItemPedido;
import com.lanchonete.api.entities.Lanche;
import com.lanchonete.api.entities.Pedido;
import com.lanchonete.api.repositories.LancheRepository;
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
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ItemPedidoService itemPedidoService;

    @Autowired
    private LancheRepository lancheRepository;

    @Transactional(readOnly = true)
    public Page<PedidoDTO> findAllPaged(Pageable pageable) {
        Page<Pedido> list = repository.findAll(pageable);
        return list.map(x -> new PedidoDTO(x, x.getLanche(), x.getLanche().getIngredientes()));
    }

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long id) {
        Optional<Pedido> obj = repository.findById(id);
        Pedido entity = obj.orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
        return new PedidoDTO(entity, entity.getLanche(), entity.getLanche().getIngredientes());
    }

    @Transactional
    public PedidoDTO insert(PedidoDTO dto) {
        Pedido entity = new Pedido();
        copyDtoToEntity(dto, entity);
        repository.save(entity);

        if (entity.getLanche() != null && entity.getLanche().getId() != null) {
            Lanche lanche = entity.getLanche();
            entity.setLanche(lanche);
            if (!lanche.getIngredientes().isEmpty()) {
                List<ItemPedido> itensPedido = lanche.getIngredientes().stream().map(ingrediente -> {
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setPedido(entity);
                    itemPedido.setIngrediente(ingrediente);
                    itemPedido.setPrecoIngrediente(ingrediente.getPreco());
                    return itemPedido;
                }).collect(Collectors.toList());

                itemPedidoService.insert(itensPedido, entity);
            }
        }

        return new PedidoDTO(entity, entity.getLanche(), entity.getLanche().getIngredientes());
    }

    @Transactional
    public PedidoDTO update(Long id, PedidoDTO dto) {
        try {
            Pedido entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            repository.save(entity);

            List<ItemPedido> itensPedido = entity.getLanche().getIngredientes().stream().map(ingrediente -> {
                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setPedido(entity);
                itemPedido.setIngrediente(ingrediente);
                itemPedido.setPrecoIngrediente(ingrediente.getPreco());
                return itemPedido;
            }).collect(Collectors.toList());

            itemPedidoService.insert(itensPedido, entity);

            return new PedidoDTO(entity, entity.getLanche(), entity.getLanche().getIngredientes());

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("ID não encontrado: " + id);
        }
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

    private void copyDtoToEntity(PedidoDTO dto, Pedido entity) {

        if (dto.getValorTotal() != null) entity.setValorTotal(dto.getValorTotal());

        Optional<Lanche> lanche = lancheRepository.findById(dto.getLanche().getId());
        entity.setLanche(lanche.get());
    }

}
