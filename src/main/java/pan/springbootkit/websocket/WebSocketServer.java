package pan.springbootkit.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * WebSocketServer
 * 	var wsuri = "ws://localhost:8080/websocket/666";
 *
 * Created by panzhangbao on 2019-03-05 10:34:42
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Slf4j
@ServerEndpoint("/websocket/{clientId}")
@Component
public class WebSocketServer {

	/**
	 * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	 */
	private static int onlineCount = 0;

	/**
	 * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	 */
	public static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

	/**
	 * 与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	private Session session;

	/**
	 * 接收clientId
	 */
	private String clientId = "";

	/**
	 *
	 *
	 * @param session
	 * @param clientId
	 */

	/**
	 * 连接建立成功调用的方法
	 *
	 * @param session 会话
	 * @param clientId 每个客户端连接者的 id
	 * @return void
	 * @date 2019-09-18 17:24
	 * @author panzhangbao
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("clientId") String clientId) {
		this.session = session;
		this.clientId = clientId;
		// 加入 set 中
		webSocketSet.add(this);

		// 在线数加1
		addOnlineCount();

		log.info("有新窗口开始监听:" + clientId + ",当前在线人数为" + getOnlineCount());
	}

	/**
	 * 连接关闭调用的方法
	 *
	 * @param
	 * @return void
	 * @date 2019-09-18 17:24
	 * @author panzhangbao
	 */
	@OnClose
	public void onClose() {
		// 从 set 中删除
		webSocketSet.remove(this);

		// 在线数减 1
		subOnlineCount();

		log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message 客户端发送过来的消息
	 * @param session 会话
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		log.info("收到来自窗口" + clientId + "的信息:" + message);

		// 群发消息
		for (WebSocketServer item : webSocketSet) {
			try {
				if (item.session.getId().equals(session.getId())) {
					// 这里为回复同样的信息
					item.sendMessage(message);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 出错
	 *
	 * @param session 会话
	 * @param error 报错
	 * @return void
	 * @date 2019-09-18 17:31
	 * @author panzhangbao
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		log.error("webSocket 发生错误\n" + error.getMessage());
	}

	/**
	 * 实现服务器主动推送
	 *
	 * @param message
	 * @return void
	 * @date 2019-09-18 17:32
	 * @author panzhangbao
	 */
	public void sendMessage(String message) throws IOException {
		log.info("服务器主动推送消息，推送内容:" + message);

		this.session.getBasicRemote().sendText(message);
	}

	/**
	 * 群发自定义消息
	 * */
	private static void sendInfo(String message, @PathParam("clientId") String clientId) {
		log.info("主动推送消息到窗口" + clientId + "，推送内容:" + message);

		for (WebSocketServer item : webSocketSet) {
			try {
				// 这里可以设定只推送给这个 clientId 的，为 null 则全部推送
				if(clientId == null) {
					item.sendMessage(message);
					continue;
				}

				if(item.clientId.equals(clientId)){
					item.sendMessage(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}
	public static synchronized void addOnlineCount() {
		WebSocketServer.onlineCount++;
	}
	public static synchronized void subOnlineCount() {
		WebSocketServer.onlineCount--;
	}
}
