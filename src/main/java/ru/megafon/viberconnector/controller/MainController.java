package ru.megafon.viberconnector.controller;

import com.viber.bot.event.incoming.IncomingEvent;
import com.viber.bot.event.incoming.IncomingMessageEvent;
import com.viber.bot.message.Message;
import com.viber.bot.message.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.megafon.viberconnector.MessageDTO;
import ru.megafon.viberconnector.SenderDTO;
import ru.megafon.viberconnector.ViberDTO;
import ru.megafon.viberconnector.config.RestProxyConfiguration;

import java.util.HashSet;


@RestController
public class MainController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final String auth_token = "4bccc5dfba27df1d-fefacfa5034e1feb-419e465a81ae****";
    @Autowired
    HashSet<Long> tokenCache;
    @Autowired
    private RestProxyConfiguration restProxyConfiguration;

    @PostMapping(value = "/imr/v1/alice")
    public ResponseEntity<String> incoming(@RequestBody IncomingEvent incoming) {
        log.info("incoming from Viber: " + incoming.toString());
        log.info("Type of event " + incoming.getEvent());
        MessageDTO messageDTO = new MessageDTO();
        if (incoming instanceof IncomingMessageEvent) {
            Message message = ((IncomingMessageEvent) incoming).getMessage();
            Long token = ((IncomingMessageEvent) incoming).getToken();
            log.info("Token: " + token);
            if (!tokenCache.contains(token)) {
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) ((IncomingMessageEvent) incoming).getMessage();
                    String text = textMessage.getText();
                    String id = ((IncomingMessageEvent) incoming).getSender().getId();
                    String nickname = ((IncomingMessageEvent) incoming).getSender().getName();
                    messageDTO.setId(id);
                    messageDTO.setText(text);
                    messageDTO.setNickName(nickname);
                    messageDTO.setBot(false);
                    System.out.println(messageDTO);
                    tokenCache.add(token);
                    new RestTemplate().postForEntity("http://msk***:8082/getMessageFromViber", messageDTO, String.class);
                    log.info("Message from Viber was send to IMR: " + messageDTO);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("{\"success\":true}");
    }

    @PostMapping(value = "/ViberMessage")
    public ResponseEntity<String> getMessageFromImr(@RequestBody MessageDTO messageDTO) throws Exception {
        log.info("RECIEVED MESSAGE FROM IMR" + messageDTO);
        if (messageDTO != null) {
            String url = "https://chatapi.viber.com/pa/send_message";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            ViberDTO viberDTO = new ViberDTO(auth_token, messageDTO.getId(), new SenderDTO("NameSender", null), "text", messageDTO.getText());
            log.info("ViberDTO is " + viberDTO);
            String message = restProxyConfiguration.getRestTemplate().postForEntity(url, viberDTO, String.class).getBody();
            log.info("Message is " + message);
            log.info("RETURN 200 OK");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        log.info("RETURN 404");
        return ResponseEntity.notFound().build();
    }
}


