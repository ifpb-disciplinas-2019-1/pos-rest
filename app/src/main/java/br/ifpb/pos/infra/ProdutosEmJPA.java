package br.ifpb.pos.infra;

import br.ifpb.pos.domain.produto.Produtos;
import br.ifpb.pos.domain.produto.Produto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JuliermeH
 */
@Stateless
public class ProdutosEmJPA implements Produtos {
    
     @PersistenceContext
    private EntityManager em;
    
     @Override
    public void novo(Produto produto){
        em.persist(produto);
    }

     @Override
    public Produto localizarPorDescricao(String descricao){
        return em.find(Produto.class,descricao);
    }

     @Override
    public List<Produto> todosProdutos() {
        return em.createQuery("FROM Produto p",Produto.class)
            .getResultList();
    }
    
     @Override
    public List<Produto> pesquisarPorPreco(double preco){
        return em.createQuery("FROM Produto p WHERE preco = " + preco,Produto.class)
            .getResultList();
    }
    
}
