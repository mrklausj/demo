package com.example.demo;

import dataservice.PersonDataService;
import datasource.InMemDB;
import models.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/person")
public class PersonResource {
    @Context
    private PersonDataService context;

    @GET
    //@Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersons() {
        return context.getAllPersons();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") int persId) {
        Person p = context.getPerson(persId);
        return Response.status(Response.Status.OK)
                .entity(p)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(Person pers) {
        context.addPerson(pers);
        return Response.status(Response.Status.OK)
                .entity(pers)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePerson(@PathParam("id") int persId, Person pers) {
        context.updatePerson(persId, pers);
        return Response.status(Response.Status.OK)
                .entity(persId)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("id") int persId) {
        context.deletePerson(persId);
        return Response.status(Response.Status.OK)
                .build();
    }
}