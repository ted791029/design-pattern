package com.ted.app.util;

public class ExceptionUtil {
    public static String getExceptionFormatMsg(String msg) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        // stackTrace[0] 是 getStackTrace 自己
        // stackTrace[1] 是 someMethod 自己
        // stackTrace[2] 是呼叫 someMethod 的上一層

        if(stackTrace.length < 3){
            throw  new RuntimeException("StackTraceElementUtil錯誤");
        }
        StackTraceElement element = stackTrace[2];
        return "類別: " + element.getClassName() + " 操作: " + element.getMethodName() + " " + msg;
    }
}
