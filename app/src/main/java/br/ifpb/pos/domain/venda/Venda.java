package br.ifpb.pos.domain.venda;

import br.ifpb.pos.domain.cliente.Cliente;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 09/08/2019, 10:55:34
 */
@Entity
public class Venda implements Serializable {

    @Id
    private String codigo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date criadaEm;
    private BigDecimal total;

    @ManyToOne
    private Cliente cliente;

    //private List<Produtos> produtos;
    public Venda() {
        this.criadaEm = Date.from(Instant.now());
        this.codigo = UUID.randomUUID().toString();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getCriadaEm() {
        return criadaEm;
    }

    public void setCriadaEm(Date criadaEm) {
        this.criadaEm = criadaEm;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
