/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.pos.api;

import br.ifpb.pos.application.ServiceDeProdutos;
import br.ifpb.pos.domain.produto.Produto;
import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Tyathian
 */
@Stateless

@Path("produtos")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class ResoucesProdutos {
    
    @Inject
    private ServiceDeProdutos service;
    
    @GET
    public Response todosOsProdutos() {
        List<Produto> produtos = this.service.todos();

        GenericEntity<List<Produto>> entity = new GenericEntity<
                List<Produto>>(
                    produtos) {
        };
        return Response.ok()
            .entity(entity)
            .build();
    }
    
    @GET
    @Path("descricao/{descricao}")
    public Response produtoDescricao(@PathParam("descricao") String descricao) {
        Produto produto = this.service.localizaPorDescricao(descricao);
        return Response.ok()
            .entity(produto)
            .build();
    }
    
    @GET
    @Path("valor/{valor}")
    public Response produtoDescricao(@PathParam("valor") Double valor) {
        List<Produto> produtos = this.service.pesquisarPorPreco(valor);
        
        GenericEntity<List<Produto>> entity = new GenericEntity<
                List<Produto>>(
                    produtos) {
        };
        
        return Response.ok()
            .entity(produtos)
            .build();
    }
    
    @POST
    public Response novo(JsonObject object) {

        this.service.criarNovoCliente(
            object.getInt("codigo"),
            object.getString("descricao"),
            object.getInt("valor")
        );
        String uri = "http://localhost:8080/app/api/produtos/" + object.getInt("codigo");
        return Response.created(
            URI.create(uri)
        ).build();
    }
    
    // TODO: PUT
    // TODO: DELETE
    // TODO: GET pelo codigo
}
