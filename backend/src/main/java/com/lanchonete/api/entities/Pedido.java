package com.lanchonete.api.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itensPedido;

    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name = "lanche_id")
    private Lanche lanche;

    public Pedido() {
    }

    public Pedido(Long id, Double valorTotal, Lanche lanche) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.lanche = lanche;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Lanche getLanche() {
        return lanche;
    }

    public void setLanche(Lanche lanche) {
        this.lanche = lanche;
    }
}
