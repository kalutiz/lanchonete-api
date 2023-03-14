package com.lanchonete.api.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_item_pedido")
public class ItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;
    private Double precoIngrediente;

    public ItemPedido() {
    }

    public ItemPedido(Long id, Pedido pedido, Ingrediente ingrediente, Double precoIngrediente) {
        this.id = id;
        this.pedido = pedido;
        this.ingrediente = ingrediente;
        this.precoIngrediente = precoIngrediente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Double getPrecoIngrediente() {
        return precoIngrediente;
    }

    public void setPrecoIngrediente(Double precoIngrediente) {
        this.precoIngrediente = precoIngrediente;
    }
}
