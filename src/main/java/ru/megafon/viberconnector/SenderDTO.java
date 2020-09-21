package ru.megafon.viberconnector;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

public class SenderDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("avatar")
    @Nullable
    private String avatar;

    public SenderDTO() {
    }

    public SenderDTO(String name, @Nullable String avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(@Nullable String avatar) {
        this.avatar = avatar;
    }
}
