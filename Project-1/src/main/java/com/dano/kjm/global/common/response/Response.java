package com.dano.kjm.global.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Response.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.04
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private int status;
    private T data;
    private String message;

    private Response(){}
    public Response (Builder builder){
        this.status = builder.status;
        this.data = (T) builder.data;
        this.message = builder.message;
    }

    public static Builder status(HttpStatus status){
        return new Builder(status);
    }
    @Getter
    public static class Builder<T>{
        private int status;
        private T data;
        private String message;

        public Builder(HttpStatus status){
            this.status = status.value();
        }

        public Builder<T> data(T data){
            this.data = data;
            return this;
        }

        public Builder message(String message){
            this.message = message;
            return this;
        }

        public ResponseEntity<Response> create(){
            Response<T> response = new Response<>(this);
            return ResponseEntity.status(response.status)
                    .body(response);
        }
    }
}
