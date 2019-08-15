/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.pos.application;

import br.ifpb.pos.domain.produto.Produto;
import br.ifpb.pos.domain.produto.Produtos;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import javax.ejb.Stateless;

/**
 *
 * @author Tyathian
 */
@Stateless
public class ServiceDeProdutos {
    
    @Inject
    private Produtos produto;
    
    public void criarNovoCliente(int codigo, String descricao, double valor) {
        Objects.requireNonNull(descricao,"Descrição precisa ser preenchido");
        Produto p = new Produto(
                codigo, descricao, valor
        );
        produto.novo(p);
    }

    public List<Produto> todos() {
        return this.produto.todosProdutos();
    }
    
    public List<Produto> pesquisarPorPreco(double valor) {
        return this.produto.pesquisarPorPreco(valor);
    }

    public Produto localizaPorDescricao(String descricao) {
        return this.produto.localizarPorDescricao(descricao);
    }
    
}
