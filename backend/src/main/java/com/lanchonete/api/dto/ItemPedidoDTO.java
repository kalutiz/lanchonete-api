package com.lanchonete.api.dto;

import com.lanchonete.api.entities.ItemPedido;

import java.io.Serializable;

public class ItemPedidoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private PedidoDTO pedido;
    private IngredienteDTO ingrediente;
    private Double precoIngrediente;

    public ItemPedidoDTO() {
    }

    public ItemPedidoDTO(Long id, Double precoIngrediente) {
        this.id = id;
        this.precoIngrediente = precoIngrediente;
    }

    public ItemPedidoDTO(ItemPedido entity) {
        this.id = entity.getId();
        this.precoIngrediente = entity.getPrecoIngrediente();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PedidoDTO getPedido() {
        return pedido;
    }

    public void setPedido(PedidoDTO pedido) {
        this.pedido = pedido;
    }

    public IngredienteDTO getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(IngredienteDTO ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Double getPrecoIngrediente() {
        return precoIngrediente;
    }

    public void setPrecoIngrediente(Double precoIngrediente) {
        this.precoIngrediente = precoIngrediente;
    }
}
