package com.lanchonete.api.services;

import com.lanchonete.api.entities.ItemPedido;
import com.lanchonete.api.entities.Pedido;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromocaoService {

    private static final double DESCONTO_PROMOCAO = 10;

    public static double getDescontoPromocao(Pedido pedido) {
        double preco = pedido.getValorTotal();

        double valorDaQuantidade = verificaMuitaCarneMuitoQueijo(pedido.getItensPedido());

        if (verificaSeTemAlfaceSemBacon(pedido.getItensPedido())) {
            preco = preco * (1 - (DESCONTO_PROMOCAO / 100));
        }

        return preco - valorDaQuantidade;
    }

    private static double verificaMuitaCarneMuitoQueijo(List<ItemPedido> itensPedido) {


        List<ItemPedido> lancheComQueijo = itensPedido
                .stream()
                .filter(lanche -> lanche.getIngrediente().getNome().equals("QUEIJO"))
                .collect(Collectors.toList());

        List<ItemPedido> lancheComCarne = itensPedido
                .stream()
                .filter(lanche -> lanche.getIngrediente().getNome().equals("HAMBURGUER"))
                .collect(Collectors.toList());

        double descontoTotal = 0;

        if (lancheComQueijo.size() >= 3) {
            int desconto = lancheComQueijo.size() / 3;

            double valorDesconto = lancheComQueijo.get(0).getPrecoIngrediente() * desconto;
            descontoTotal += valorDesconto;
        }

        if (lancheComCarne.size() >= 3) {
            int desconto = lancheComCarne.size() / 3;

            double valorDesconto = lancheComCarne.get(0).getPrecoIngrediente() * desconto;
            descontoTotal += valorDesconto;
        }

        return descontoTotal;
    }

    private static boolean verificaSeTemAlfaceSemBacon(List<ItemPedido> itensPedido) {
        return itensPedido.stream().anyMatch(lanche -> lanche.getIngrediente().getNome().equals("ALFACE")) &&
                itensPedido.stream().noneMatch(lanche -> lanche.getIngrediente().getNome().equals("BACON"));
    }
}

