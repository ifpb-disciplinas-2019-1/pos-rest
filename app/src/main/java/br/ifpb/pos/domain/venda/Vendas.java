package br.ifpb.pos.domain.venda;

import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 09/08/2019, 11:02:29
 */
public interface Vendas {

    
    public Venda nova();

    public List<Venda> todas();

    public Venda localizarCom(String uuid);

    public Venda atualizar(Venda venda);
}
