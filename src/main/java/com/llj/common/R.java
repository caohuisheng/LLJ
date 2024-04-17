package com.llj.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class R<T> {
    private String code;
    private String msg;
    private T data;

    public static <T> R<T> success(T data){
        return new R<T>("200","",data);
    }

    public static <T> R<T> success(String msg){
        return new R<T>("200",msg,null);
    }

    public static <T> R<T> error(String msg){
        return new R<T>("500",msg,null);
    }
}
