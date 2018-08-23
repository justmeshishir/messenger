package com.messenger.service;

import com.messenger.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageService {

    public List<Message> getAllMessages(){
        Message m1 = new Message(1L,"Hello Bailey", "ccr");
        Message m2 = new Message(2L,"Hello LF", "ccr");
        List<Message> list = new ArrayList<>();
        list.add(m1);
        list.add(m2);
        return list;
    }
}
