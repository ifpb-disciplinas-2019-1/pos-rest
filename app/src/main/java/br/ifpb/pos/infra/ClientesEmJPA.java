package br.ifpb.pos.infra;

import br.ifpb.pos.domain.Cliente;
import br.ifpb.pos.domain.Clientes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 08/08/2019, 08:22:52
 */
@Stateless
public class ClientesEmJPA implements Clientes {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void novo(Cliente cliente) {
        em.persist(cliente);
    }

    @Override
    public List<Cliente> todos() {
        return em.createQuery("FROM Cliente c",Cliente.class)
            .getResultList();
    }

    @Override
    public Cliente localizarPorCpf(String cpf) {
        return em.find(Cliente.class,cpf);
    }
}
