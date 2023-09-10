package chatting.chat.services;

import chatting.chat.dtos.MessageResponseDto;
import chatting.chat.utils.MessageIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class EnterRoomService {
    @Autowired
    private SimpMessagingTemplate template;

    public void enterRoom(String type,
                          Long roomId,
                          Long userId) {
        template.convertAndSend(
                "/subscription/chat/room/" + roomId,
                new MessageResponseDto(
                        MessageIdGenerator.generateId(),
                        type,
                        "사용자 " + userId + " 님이 "
                                + "채팅방 " + roomId + "에 입장하셨습니다."
                )
        );
    }
}