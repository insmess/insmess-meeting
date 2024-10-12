package com.insmess.meeting.websocket;


import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * 用不到了，后面万一用到websocket可直接修改。
 */
@ServerEndpoint("/wav/save")
@Component
@Slf4j
public class WavHandler {
    private OutputStream out;

    @SneakyThrows
    @OnOpen
    public void onOpen(Session session) {
        out = new FileOutputStream("d:/test/" + UUID.randomUUID().toString() + ".ogg");
        session.setMaxBinaryMessageBufferSize(1024 * 1024);

    }

    @SneakyThrows
    @OnClose
    public void onClose() {
        out.close();
    }

    @SneakyThrows
    @OnMessage
    public void onMessage(byte[] bArr, Session session) {
        out.write(bArr);
        System.out.println(Arrays.toString(bArr));
    }
}
