package com.lanchonete.api.dto;

import com.lanchonete.api.entities.Ingrediente;
import com.lanchonete.api.entities.Lanche;
import com.lanchonete.api.entities.Pedido;

import java.io.Serializable;
import java.util.List;

public class PedidoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Double valorTotal;
    private LancheDTO lanche;

    public PedidoDTO() {
    }

    public PedidoDTO(Long id, Double valorTotal) {
        this.id = id;
        this.valorTotal = valorTotal;
    }

    public PedidoDTO(Pedido entity) {
        this.id = entity.getId();
        this.valorTotal = entity.getValorTotal();
    }

    public PedidoDTO(Pedido entity, Lanche lanche, List<Ingrediente> ingredientes) {
        this(entity);
        this.lanche = new LancheDTO(lanche, ingredientes);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LancheDTO getLanche() {
        return lanche;
    }

    public void setLanche(LancheDTO lanche) {
        this.lanche = lanche;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
