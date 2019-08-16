package br.ifpb.pos.api;

import br.ifpb.pos.application.ServiceDeVendas;
import br.ifpb.pos.domain.venda.Venda;
import br.ifpb.pos.domain.venda.VendaValue;
import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 09/08/2019, 11:06:35
 */
@Stateless
@Path("vendas")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class ResourceVendas {

    @Inject
    private ServiceDeVendas service;

    @POST
    public Response novaVenda(@Context UriInfo uriInfo) {
        Venda venda = this.service.novaVenda();
        URI uri = uriInfo.getAbsolutePathBuilder()
            .path(venda.getCodigo())
            .build();

        return Response.created(uri)
            .entity(venda)
            .build();
    }

    @GET
    public Response todasAsVendas() {
        List<Venda> clientes = this.service.vendas();

        GenericEntity<List<Venda>> entity = new GenericEntity<List<Venda>>(
            clientes) {
        };
        return Response.ok()
            .entity(entity)
            .build();
    }

    @GET
    @Path("{uuid}")
    public Response porUUID(@PathParam("uuid") String uuid,@Context UriInfo uriInfo) {
        Venda venda = this.service.localizaVendaPorUUID(uuid);

        //VendaValue vendaValue = new VendaValue(venda,uriInfo);
        return Response.ok()
            .entity(
                venda.parse(uriInfo)
            ).build();
    }

    @PUT
    @Path("{uuid}/cliente/{cpf}")
    public Response adicionarCliente(
        @PathParam("uuid") String uuid,
        @PathParam("cpf") String cpf) {
        Venda venda = this.service.adicionarClienteAVenda(uuid,cpf);
        return Response.ok()
            .entity(venda)
            .build();
    }

    @DELETE
    @Path("{uuid}")
    public Response cancelar(@PathParam("uuid") String uuid) {
        this.service.cancelarVenda(uuid);
        return Response.noContent()
            .build();
    }

//    TODO: GET /finalizar -> criar um status: CRIADA, ANDAMENTO, FINALIZADA
//    TODO: PUT
//    @PUT
//    @Path("{uuid}/produto/{codigoProduto}")
//    public Response adicionarProduto(
//        @PathParam("uuid") String uuid,
//        @PathParam("codigoProduto") String codigoProduto) {
//        
//        return Response.ok()
//            .build();
//    }
}
