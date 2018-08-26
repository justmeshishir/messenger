package com.messenger.resources;

import com.messenger.model.Message;
import com.messenger.service.MessageService;
import com.sun.media.jfxmedia.Media;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("messages")
@Consumes(MediaType.APPLICATION_JSON) //Accepts JSON Data
@Produces(MediaType.APPLICATION_JSON) //Returns JSON Data
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessage(@QueryParam("year") int year,
                                    @QueryParam("start") int start,
                                    @QueryParam("size") int size){
        if(year > 0){
            return messageService.getAllMessageForYear(year);
        }
        if(start >= 0 && size > 0){
            return messageService.getAllMessagesPaginated(start,size);
        }
        return messageService.getAllMessages();
    }


    @POST
    public Message addMessage(Message message){
        return messageService.addMessage(message);
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
}
