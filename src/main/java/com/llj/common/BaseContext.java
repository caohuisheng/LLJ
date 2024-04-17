package com.llj.common;

/**
 * 获取和设置当前用户id
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static Long getCurrentId(){
        //return threadLocal.get();
        return 10001L;
    }

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }
}
