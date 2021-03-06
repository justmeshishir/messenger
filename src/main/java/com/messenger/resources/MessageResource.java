package com.messenger.resources;

import com.messenger.model.Message;
import com.messenger.resources.beans.MessageFilterBeans;
import com.messenger.service.MessageService;
import com.sun.media.jfxmedia.Media;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("messages")
//@Consumes(MediaType.APPLICATION_JSON) //Accepts JSON Data
//@Produces(MediaType.APPLICATION_JSON) //Returns JSON Data
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML}) //Returns JSON and XML data as per accept header Data
@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML}) //Receives JSON and XML data as per accept header Data


public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessage(@BeanParam MessageFilterBeans filterBeans){
        if(filterBeans.getYear() > 0){
            return messageService.getAllMessageForYear(filterBeans.getYear());
        }
        if(filterBeans.getStart() >= 0 && filterBeans.getSize() > 0){
            return messageService.getAllMessagesPaginated(filterBeans.getStart(),filterBeans.getSize());
        }
        return messageService.getAllMessages();
    }


    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
        Message newMessage = messageService.addMessage(message);
        String newId = String.valueOf(newMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri)
                        .entity(newMessage)
                        .build();
    }

    @PUT
    @Path("{messageId}")
    public Message updateMessage(@PathParam("messageId") long id, Message message) {
        message.setId(id);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("{messageId}")
    public void removeMessage(@PathParam("messageId") long id){
        messageService.removeMessage(id);
    }


    @GET
    @Path("{messageId}")
    public Message getMessage(@PathParam("messageId") long id){
        return messageService.getMessage(id);
    }


    @Path("/{messageId}/comments")
    public CommentResource getCommentResource(){
        return new CommentResource();
    }
}
