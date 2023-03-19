package com.example.lab6websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketCalculaterHandler extends TextWebSocketHandler {
    private WebSocketSession webSocketSession;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        session.sendMessage(new TextMessage("Connection Established"));
//        if(webSocketSession.isOpen()) {
//            try {
//                webSocketSession.sendMessage(new TextMessage("Message from server!"));
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);

        String input = message.getPayload();
        int num1 =0 , num2 =0 , result = 0;
        String operator="";
        try{
            if(input.indexOf('+') >-1 ){
                num1 = Integer.parseInt(input.substring(0,input.indexOf('+')));
                num2 = Integer.parseInt(input.substring(input.indexOf('+')+1));
                operator = input.substring(input.indexOf('+'),input.indexOf('+')+1);
            }else if(input.indexOf('-') >-1 ){
                num1 = Integer.parseInt(input.substring(0,input.indexOf('-')));
                num2 = Integer.parseInt(input.substring(input.indexOf('-')+1));
                operator = input.substring(input.indexOf('-'),input.indexOf('-')+1);
            }else if(input.indexOf('*') >-1 ){
                num1 = Integer.parseInt(input.substring(0,input.indexOf('*')));
                num2 = Integer.parseInt(input.substring(input.indexOf('*')+1));
                operator = input.substring(input.indexOf('*'),input.indexOf('*')+1);
            }else if(input.indexOf('/') >-1 ){
                num1 = Integer.parseInt(input.substring(0,input.indexOf('/')));
                num2 = Integer.parseInt(input.substring(input.indexOf('/')+1));
                operator = input.substring(input.indexOf('/'),input.indexOf('/')+1);
            }

            result = operateResult(operator, num1, num2);
            session.sendMessage(new TextMessage("The result is: "+String.valueOf(result)));

        }catch (Exception ex){
            session.sendMessage(new TextMessage("Invalid Expression"));
        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        session.sendMessage(new TextMessage("Connection Closed"));
    }

    private int operateResult(String operator, int prevNumber, int currentNum) {
        int result = 0;
        switch (operator) {
            case "+":
                result = prevNumber + currentNum;
                break;
            case "-":
                result = prevNumber - currentNum;
                break;
            case "*":
                result = prevNumber * currentNum;
                break;
            case "/":
                result = prevNumber / currentNum;
                break;
        }
        return result;
    }
}
