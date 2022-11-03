package org.acme;

import com.blazebit.persistence.view.EntityViewManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@ApplicationScoped
@Path("/gifts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GiftResource {
    @Inject
    EntityManager entityManager;
    @Inject
    EntityViewManager entityViewManager;
    @Inject
    SantaClausService santaClausService;

    @POST
    @Transactional
    public Response createGift(GiftUpdateView view) {
        entityViewManager.save(entityManager, view);
        return Response.created(URI.create("/gifts/" + view.getId())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<GiftView> getGifts() {
        return santaClausService.findAllGifts();
    }
}
