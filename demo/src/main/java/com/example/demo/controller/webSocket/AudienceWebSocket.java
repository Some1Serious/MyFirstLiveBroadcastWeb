package com.example.demo.controller.webSocket;

import com.example.demo.controller.webSocket.pojo.Audience;
import com.example.demo.controller.webSocket.pojo.Rooms;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@ServerEndpoint("/audience/{roomId}/{userId}")
public class AudienceWebSocket extends Audience {

}
