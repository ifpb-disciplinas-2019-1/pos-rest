package br.ifpb.pos.domain.cliente;

import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 08/08/2019, 08:21:25
 */
public interface Clientes {

    public void novo(Cliente cliente);

    public Cliente localizarPorCpf(String cpf);

    public List<Cliente> todos();

    public void remover(Cliente cliente);

    public void atualizar(Cliente resposta);
}
