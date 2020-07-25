package com.udacity.jwdnd.spring_security_basics.controller;

import com.udacity.jwdnd.spring_security_basics.model.ChatForm;
//import com.udacity.jwdnd.c1.review.service.AuthenticationService;  from old file. not 2nd time through
import com.udacity.jwdnd.spring_security_basics.service.MessageService;
import org.springframework.security.core.Authentication;  //I added to resolve Authentication "cannot resolve symbol"
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")  //establishes base url that this controller is responsible for. This also means we don't have
//to repeat ourselves in the mappings for each of the controllers methods.
public class ChatController {

    private MessageService messageService;

    public ChatController(MessageService messageService) {this.messageService = messageService; }

    //@GetMapping and @PostMapping are methods for the 2 request types the template, chat.html requires.
    @GetMapping
    public String getChatPage(ChatForm chatForm, Model model) { //respond to the GET request
        //getChatPage is responsible for serving the initial
        //GET request for the template
        //Declaring the ChatForm obj as an arg allows spring to initialize the form backing object for the template
        model.addAttribute("chatMessages", this.messageService.getChatMessages());  //use the MessageService to get chat message
        //this.messageService.getChatMessages() is adding the chat messages
        //stored to the model
        return "chat";
    }

    @PostMapping    //for Final Exercise Solution, added Authentication object for Spring to handle it.
    public String postChatMessage(Authentication authentication, ChatForm chatForm, Model model){  //using spring security
        //with Authentication authentication to retrieve the currently logged in user. This is adding an authentication
        //object. This is the same exact object the service returns when a user is authorized to login.

        //postChatMessage is responsible for receiving a message submission from the form.
        //Declaring the ChatForm obj as an arg which spring will initialize with data from the request.
        chatForm.setUsername(authentication.getName()); //extract the user name and add to chatForm object.
        this.messageService.addMessage(chatForm);  //send the extracted user name and chatForm object to the messageService
                                                  //to get added.
                 //use MessageService to add chat messages
                //pass the entire ChatForm to the messageService.addMessage method
        chatForm.setMessageText(""); //clear the message text of the form
        model.addAttribute("chatMessages", this.messageService.getChatMessages()); //update the list of chat
        //messages in the model
        return "chat";
    }

    @ModelAttribute("allMessageTypes")  //allMessageTypes is the @ModelAttribute name.
    //allows spring to make a value available in the model without repeating ourselves in
    //every controller method.
    public String[] allMessageTypes () {
        return new String[] { "Say", "Shout", "Whisper" };
    }
    //It defines a value and marks it with the appropriate ModelAttribute name.
    //It's almost like a bean declaration except, instead of ending up in the spring application context,
    //it ends up in the spring MVC's model for the template that it's rendering
}
