package com.udacity.jwdnd.spring_security_basics.model;

public class ChatMessage {
    private String username;
    private String message;
    private String messageId;  //I added trying to resolve browser crash. It did get me past the crash, but now only
    //user name shows and not message put out by user.

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getMessageText(){  //changed to getMessageText from getMessage. trying to resolve crash in browser
        return message;
    }  //changed from getMessage to getMessageText

    public void setMessageText(String message){//changed to setMessageText to try to resolve problem in MessageService.java
        this.message = message;
    }

    public void setMessageId(String messageId) { //I added trying to resolve browser crash. It did get me past the
        this.messageId = messageId;             //crash, but now only user name shows and not message put out by user
    }                                          //all fixed now too.  I ended up starting over and pasting in classes
                                              //one by one into this, the spring_security_basics project, and not using
     //the review project.  I also, was not getting the correct login screen, the nice one from spring.  it was a very
    //basic looking one.  starting over fixed all those issues.
}
