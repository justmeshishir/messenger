package com.messenger.resources;

import com.messenger.model.Message;
import com.messenger.service.MessageService;
import com.sun.media.jfxmedia.Media;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("messages")
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getMessage(){
        return messageService.getAllMessages();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON) //Accepts JSON Data
    @Produces(MediaType.APPLICATION_JSON) //Returns JSON Data
    public Message addMessage(Message message){
        return messageService.addMessage(message);
    }

    @PUT
    @Path("{messageId}")
    @Consumes(MediaType.APPLICATION_JSON) //Accepts JSON Data
    @Produces(MediaType.APPLICATION_JSON) //Returns JSON Data
    public Message updateMessage(@PathParam("messageId") long id, Message message) {
        message.setId(id);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void removeMessage(@PathParam("messageId") long id){
        messageService.removeMessage(id);
    }


    @GET
    @Path("{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Message getMessage(@PathParam("messageId") long id){
        return messageService.getMessage(id);
    }
}
