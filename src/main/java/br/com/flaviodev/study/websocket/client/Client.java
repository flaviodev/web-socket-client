package br.com.flaviodev.study.websocket.client;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;

import br.com.flaviodev.study.websocket.model.Order;
import br.com.flaviodev.study.websocket.model.OutputOrder;


public class Client {

	private final static WebSocketHttpHeaders headers = new WebSocketHttpHeaders();

	public ListenableFuture<StompSession> connect(StompSessionHandlerAdapter handler) {

		Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
		List<Transport> transports = Collections.singletonList(webSocketTransport);

		SockJsClient sockJsClient = new SockJsClient(transports);
		sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());
		
		WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);

		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		
		String url = "ws://localhost:8080/order";
		return stompClient.connect(url, headers,  handler, "localhost", 8080);
	}


	public void sendOrder(StompSession stompSession, Order order) {

		stompSession.send("/app/order", order);
	}

	public static void main(String[] args) throws Exception {
		MySessionHandler handler = new MySessionHandler();
		
		Client orderClient = new Client();
		
		
		ListenableFuture<StompSession> f = orderClient.connect(handler);
		StompSession stompSession = f.get();

		stompSession.subscribe("/topic/orders", handler); 

		orderClient.sendOrder(stompSession, new Order("2", "fulano", "ciclano", new BigDecimal("99.99"), "SENT"));
		Thread.sleep(60000);
		
		stompSession.disconnect();
	}

}

class MySessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        exception.printStackTrace();
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return OutputOrder.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
    	System.out.println("Received: " + payload.toString());
    }			

}
