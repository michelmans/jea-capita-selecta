package nl.anitro.order.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    public static String encode(Object o){
        try{
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e){
            return null;
        }
    }

    public static <T> T decode(String json, Class<T> type){
        try{
            return (T) new ObjectMapper().readValue(json, type);
        } catch (IOException e){
            return null;
        }
    }

}
