package br.ifpb.pos.application;

import br.ifpb.pos.domain.cliente.Cliente;
import br.ifpb.pos.domain.cliente.Clientes;
import br.ifpb.pos.domain.venda.Venda;
import br.ifpb.pos.domain.venda.Vendas;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 09/08/2019, 11:05:18
 */
@Stateless
public class ServiceDeVendas {

    @Inject
    private Vendas vendas;

    @Inject
    private Clientes clientes;

    public Venda novaVenda() {
        return this.vendas.nova();
    }

    public List<Venda> vendas() {
        return this.vendas.todas();
    }

    public Venda localizaVendaPorUUID(String uuid) {
        return this.vendas.localizarCom(uuid);
    }

    public Venda adicionarClienteAVenda(String uuid,String cpf) {
        Venda venda = this.vendas.localizarCom(uuid);
        Cliente cliente = this.clientes.localizarPorCpf(cpf);
        venda.setCliente(cliente);
        return this.vendas.atualizar(venda);
    }
}
