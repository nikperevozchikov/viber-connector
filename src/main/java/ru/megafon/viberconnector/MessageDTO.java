package ru.megafon.viberconnector;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

public class MessageDTO {

    @JsonProperty("Id")
    private String id;

    @JsonProperty("RoomJid")
    @Nullable
    private String roomJid;

    @JsonProperty("SenderJID")
    @Nullable
    private String senderJID;

    @JsonProperty("Text")
    private String text;

    @JsonProperty("NickName")
    private String nickName;

    @JsonProperty("Source")
    private String source;

    @JsonProperty("chatId")
    @Nullable
    private String chatId;

    @JsonProperty("isBot")
    private boolean isBot;

    @JsonProperty("nextAction")
    @Nullable
    private String nextAction;


    public MessageDTO() {
    }

    public MessageDTO(String text) {
        this.text = text;
    }

    public MessageDTO(String id, String text, String nickName, @Nullable String chatId) {
        this.id = id;
        this.text = text;
        this.nickName = nickName;
        this.chatId = chatId;
    }

    public MessageDTO(String id, String text, String nickName) {
        this.id = id;
        this.text = text;
        this.nickName = nickName;
    }

    public MessageDTO(String id, String text, String nickName, String source, boolean isBot, @Nullable String nextAction) {
        this.id = id;
        this.text = text;
        this.nickName = nickName;
        this.source = source;
        this.isBot = isBot;
        this.nextAction = nextAction;
    }

    public MessageDTO(String id, @Nullable String roomJid, @Nullable String senderJID, String text, String nickName, String source, boolean isBot, @Nullable String nextAction) {
        this.id = id;
        this.roomJid = roomJid;
        this.senderJID = senderJID;
        this.text = text;
        this.nickName = nickName;
        this.source = source;
        this.isBot = isBot;
        this.nextAction = nextAction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Nullable
    public String getRoomJid() {
        return roomJid;
    }

    public void setRoomJid(@Nullable String roomJid) {
        this.roomJid = roomJid;
    }

    @Nullable
    public String getSenderJID() {
        return senderJID;
    }

    public void setSenderJID(@Nullable String senderJID) {
        this.senderJID = senderJID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Nullable
    public String getChatId() {
        return chatId;
    }

    public void setChatId(@Nullable String chatId) {
        this.chatId = chatId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isBot() {
        return isBot;
    }

    public void setBot(boolean bot) {
        isBot = bot;
    }

    @Nullable
    public String getNextAction() {
        return nextAction;
    }

    public void setNextAction(@Nullable String nextAction) {
        this.nextAction = nextAction;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "id='" + id + '\'' +
                ", roomJid='" + roomJid + '\'' +
                ", senderJID='" + senderJID + '\'' +
                ", text='" + text + '\'' +
                ", nickName='" + nickName + '\'' +
                ", source='" + source + '\'' +
                ", chatId='" + chatId + '\'' +
                ", isBot=" + isBot +
                ", nextAction='" + nextAction + '\'' +
                '}';
    }
}