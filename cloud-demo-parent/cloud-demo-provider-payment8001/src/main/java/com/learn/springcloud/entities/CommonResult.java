package com.learn.springcloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String msg;
    private T data;

    /**
     * 可能存在没有数据的返回结果
     * @param code
     * @param msg
     */
    public CommonResult(Integer code,String msg){
        this(code,msg,null);
    }
}
