package com.udacity.jwdnd.spring_security_basics.mapper;

import com.udacity.jwdnd.spring_security_basics.model.ChatForm;
import com.udacity.jwdnd.spring_security_basics.model.ChatMessage;
import org.apache.ibatis.annotations.*;

import java.util.List; //I added to resolve problem with List "cannot resolve symbol"

@Mapper
public interface MessageMapper {

    @Select("SELECT * FROM MESSAGES")
    List<ChatMessage> getAllMessages();

    @Insert("INSERT INTO MESSAGES (username, messagetext) VALUES(#{username}, #{messageText})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    int addMessage(ChatMessage chatMessage);  //performs a simple insert

}

//NEED TO CHANGE THIS TO MessageMapper
//adding this class from Lesson 5: 10.  Exercise.
// will have to be adapted to this solution

//RENAMED to MessageMapper

/*  NO LONGER NEEDED. Changed from in-memory table
@Mapper
public interface DeliveryMapper {
    @Select("SELECT * FROM Message WHERE id = #{id}")  //selecting deliveries with a specific id
    ChatForm findMessage(Integer id);

    //only "VALUES" needs to be specified, because myBatis can figure out that this comes from the Delivery obj.
    @Insert("INSERT INTO Message (orderId, time) VALUES(#{orderId}, #{time})")  //create a new row in the
    @Options(useGeneratedKeys = true, keyProperty = "id")  //delivery table. id for delivery itself (id) is generated,so
    Integer insert(ChatMessage message);  //it doesn't need to be provided.  id for delivery (id) is generated and
    //returned from the method

    @Delete("DELETE FROM Message WHERE id = #{id}")
    void delete(Integer id);

}
*/