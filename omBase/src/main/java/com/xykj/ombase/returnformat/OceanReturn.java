package com.xykj.ombase.returnformat;


public class OceanReturn {

    private static final Integer SUCCESS = 0;
    private static final Integer ERROR = -1;

    public static Result successResult(String message,Object data){
        return Result.builder()
                .code(SUCCESS)
                .msg(message)
                .data(data)
                .build();
    }

    public static Result errorResult(String message,Object data){
        return Result.builder()
                .code(ERROR)
                .msg(message)
                .data(data)
                .build();
    }

}
