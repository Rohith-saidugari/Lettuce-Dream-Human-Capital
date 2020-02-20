package com.lettucedream.api.responseEntities;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class UserCreated {

    @Getter
    @Setter
    private String userId;

    @Getter
    @Setter
    private String base64EncodedImage;


    public void SetUserCreatedValues(String userId, String base64EncodedImage){
        this.userId=userId;
        this.base64EncodedImage=base64EncodedImage;
    }
}
