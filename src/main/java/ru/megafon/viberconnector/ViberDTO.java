package ru.megafon.viberconnector;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ViberDTO {
    @JsonProperty("auth_token")
    private String auth_token;

    @JsonProperty("receiver")
    private String receiver;

    @JsonProperty("sender")
    private SenderDTO sender;

    @JsonProperty("type")
    private String type;

    @JsonProperty("text")
    private String text;

    public ViberDTO() {
    }

    public ViberDTO(String auth_token, String receiver, SenderDTO sender, String type, String text) {
        this.auth_token = auth_token;
        this.receiver = receiver;
        this.sender = sender;
        this.type = type;
        this.text = text;
    }

    public ViberDTO(String receiver, SenderDTO sender, String type, String text) {
        this.receiver = receiver;
        this.sender = sender;
        this.type = type;
        this.text = text;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public SenderDTO getSender() {
        return sender;
    }

    public void setSender(SenderDTO sender) {
        this.sender = sender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

    @Override
    public String toString() {
        return "ViberDTO{" +
                "auth_token='" + auth_token + '\'' +
                ", receiver='" + receiver + '\'' +
                ", sender='" + sender + '\'' +
                ", type='" + type + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
