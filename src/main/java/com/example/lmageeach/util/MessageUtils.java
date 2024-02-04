package com.example.lmageeach.util;

import com.alibaba.fastjson.JSON;
import com.example.lmageeach.model.ResultMessage;

public class MessageUtils {
    public static String getMessage(boolean isSystemMessage,String fromName,Object message){
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSystemMessage(isSystemMessage);
        resultMessage.setMessage(message);
        if (fromName != null){
            resultMessage.setFromName(fromName);
        }
        return JSON.toJSONString(resultMessage);
    }
}
