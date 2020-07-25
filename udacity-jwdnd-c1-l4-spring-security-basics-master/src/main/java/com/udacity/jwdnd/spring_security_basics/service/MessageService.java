package com.udacity.jwdnd.spring_security_basics.service;

import com.udacity.jwdnd.spring_security_basics.mapper.MessageMapper;
import com.udacity.jwdnd.spring_security_basics.model.ChatForm;
import com.udacity.jwdnd.spring_security_basics.model.ChatMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service   //this bean depends on the message bean
public class MessageService {

    //changed from an in-memory list to a dependency on a MessageMapper class

    private MessageMapper messageMapper;  //dependency on MessageMapper class

    public MessageService(MessageMapper messageMapper) { this.messageMapper = messageMapper; }

    //private List<ChatMessage> chatMessages;  //store messages in an in-memory list

/*  NO LONGER NEEDED
    private String message;  //"message" is a field

    public MessageService(String message){   //"message" is a constructor parameter
        this.message = message;
    }

    public String uppercase(){
        return this.message.toUpperCase();
    }

    public String lowercase(){
        return this.message.toLowerCase();
    }
*/




    //@PostConstruct is useful for handling initialization logic for a bean


    //This PostConstruct method initializes the in-memory list for the chat messages
    @PostConstruct  //this means Spring will call this bean method right after instantiated this bean and
    public void postConstruct(){   //placing it in the application context.  Also, this logs the bean's creation
        System.out.println("Creating MessageService bean");

        //this.chatMessages = new ArrayList<>();  //no longer needed.  this was in-memory message storage.

    }  //initialization logic like this could be in the constructor, but its generally best practice for
    //initialization logic in a dedicated method


    //When a new message is added with the addMessage method, we check the message type with
    //getMessageType before converting the message appropriately.
    //This operation could be done anywhere, but its a good idea, and best practice to place it in a service.
    //This is where Business Logic is supposed to be placed.
    public void addMessage(ChatForm chatForm) {
        ChatMessage newMessage = new ChatMessage();
        newMessage.setUsername(chatForm.getUsername());
        switch (chatForm.getMessageType()) {  //get the message type and convert based on which message in the switch.
            case "Say":
                newMessage.setMessageText(chatForm.getMessageText());  //changed these from setMessage to
                break;                                                //setMessageText
            case "Shout":
                newMessage.setMessageText(chatForm.getMessageText().toUpperCase());
                break;
            case "Whisper":
                newMessage.setMessageText(chatForm.getMessageText().toLowerCase());
                break;

        }
        messageMapper.addMessage(newMessage);  //call addMessage in MessageMapper to add the new message

        //this.chatMessages.add(newMessage);  //no longer needed.  part of the in-memory message storage
        // add the new message after its been converted to the internal list.
    }

    public List<ChatMessage> getChatMessages() { return messageMapper.getAllMessages(); }


    /*  NO LONGER NEEDED. part of in-memory message storage
    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }
   */

}
