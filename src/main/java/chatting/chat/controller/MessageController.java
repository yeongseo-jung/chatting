package chatting.chat.controller;

import chatting.chat.dtos.MessageRequestDto;
import chatting.chat.services.ConvertAndSendMessageService;
import chatting.chat.services.EnterRoomService;
import chatting.chat.services.QuitRoomService;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    private final EnterRoomService enterRoomService;
    private final QuitRoomService quitRoomService;
    private final ConvertAndSendMessageService convertAndSendMessageService;

    public MessageController(EnterRoomService enterRoomService,
                             QuitRoomService quitRoomService,
                             ConvertAndSendMessageService convertAndSendMessageService) {
        this.enterRoomService = enterRoomService;
        this.quitRoomService = quitRoomService;
        this.convertAndSendMessageService = convertAndSendMessageService;
    }

    @MessageMapping("/chat/enter")
    public void enter(MessageRequestDto messageRequestDto) {
        enterRoomService.enterRoom(
                messageRequestDto.getType(),
                messageRequestDto.getRoomId(),
                messageRequestDto.getUserId()
        );
    }

    @MessageMapping("/chat/quit")
    public void quit(MessageRequestDto messageRequestDto) {
        quitRoomService.quitRoom(
                messageRequestDto.getType(),
                messageRequestDto.getRoomId(),
                messageRequestDto.getUserId()
        );
    }

    @MessageMapping("/chat/message")
    public void message(MessageRequestDto messageRequestDto) {
        convertAndSendMessageService.convertAndSendMessage(
                messageRequestDto.getType(),
                messageRequestDto.getRoomId(),
                messageRequestDto.getUserId(),
                messageRequestDto.getMessage()
        );
    }

    @MessageExceptionHandler
    public String exception() {
        return "Error has occurred.";
    }
}