package com.ctw.webscoket;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ctw com.ctw.webscoket
 *
 * @author: HaiAng
 * @CreateDate: 2016/06/17 23: 23
 * @Version 1.0
/**
 * 大屏监控服务器推送处理类
 * Created by Administrator on 2016/5/5.
 */
@Component("monitorPollingHandler")
public class MonitorWebsocketHandler  implements WebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(MonitorWebsocketHandler.class);
    private List<WebSocketSession> socketSessions = Collections.synchronizedList(new ArrayList<WebSocketSession>());

   /* @Resource
    private MonitorService monitorService;*/

    @Override
    public void afterConnectionEstablished(WebSocketSession wssession) throws Exception {
        logger.debug("websocket" + wssession.getId() + " connected");
        // 首次连接后，记录连接的客户端
        socketSessions.add(wssession);
        // wssession.sendMessage(new TextMessage("websocket connection initialized successfully!"));
    }

    @Override
    public void handleMessage(WebSocketSession wssession, WebSocketMessage<?> webSocketMessage) throws Exception {
        // 客户端使用websocket.send()时，将由此方法接收消息
    }

    @Override
    public void handleTransportError(WebSocketSession wssession, Throwable throwable) throws Exception {
        logger.debug("websocket[id=" + wssession.getId() + "] throws error", throwable);
        if (wssession.isOpen()) {
            wssession.close();
        }
        socketSessions.remove(wssession);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession wssession, CloseStatus closeStatus) throws Exception {
        logger.debug("websocket" + wssession.getId() + " connection closed......");
        // 连接关闭后，将关闭的客户端从列表移除
        socketSessions.remove(wssession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 处理用户签到事件，查找、组装得到监控数据，并推送到前端页面
     * @param userId 签到的用户ID
     * @param recId 关联的巡检记录ID
     */
    public void onUserSignIn(Integer userId, Integer recId) {
      /*  SignInfo info = monitorService.getSignInfo(userId, recId);
        if (info == null) {
            logger.error("获取签到事件信息失败,无法推送:userId=" + userId + ",recId=" + recId);
            return;
        }
        info.setIsSign(1);
        try {
            String json = JsonUtil.objectToJson(info);
            send2clients(new TextMessage(json));
        } catch (JsonProcessingException e) {
            logger.error("签到信息转换json失败", e);
        }*/
    }

    /**
     * 处理用户签出事件，查找、组装得到监控数据，并推送到前端页面
     * @param userId 签到的用户ID
     * @param recId 关联的巡检记录ID
     */
    public void onUserSignOff(Integer userId, Integer recId) {
      /*  SignInfo info = monitorService.getSignInfo(userId, recId);
        if (info == null) {
            logger.error("获取签出事件信息失败,无法推送:userId=" + userId + ",recId=" + recId);
            return;
        }
        info.setIsSign(2);
        try {
            String json = JsonUtil.objectToJson(info);
            send2clients(new TextMessage(json));
        } catch (JsonProcessingException e) {
            logger.error("签到信息转换json失败", e);
        }*/
    }

    /**
     * 处理用户取消签到事件，查找、组装得到监控数据，并推送到前端页面
     * @param userId 签到的用户ID
     * @param recId 关联的巡检记录ID
     */
    public void onUserSignCancel(Integer userId, Integer recId) {
   /*     logger.info("取消签到事件信息推送:userId=" + userId + ",recId=" + recId);
        SignInfo info = monitorService.getUserInfo(userId, recId);
        if (info == null) {
            logger.error("获取取消签到事件信息失败,无法推送:userId=" + userId + ",recId=" + recId);
            return;
        }

        info.setRecId(recId);
        info.setIsSign(3);
        try {
            String json = JsonUtil.objectToJson(info);
            send2clients(new TextMessage(json));
        } catch (JsonProcessingException e) {
            logger.error("签到信息转换json失败", e);
        }*/
    }

    private void send2clients(TextMessage message) {
        for (WebSocketSession session : socketSessions) {
            try {
                if (session.isOpen()) {
                    logger.debug("sending to " + session.getId() + ": " + message.toString());
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                logger.error("推送给客户端[id=" + session.getId() + "]失败", e);
            }
        }
    }
}
