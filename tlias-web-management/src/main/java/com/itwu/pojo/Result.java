package com.itwu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String code;//0失败 1成功
    private String msg;//提示信息
    private Object data;// 数据
    private Integer total;

    public static Result success (){
        Result result = new Result();
        result.code = "1";
        result.msg = "success";
        return result;
    }
    public static Result success (Object object){
        Result result = new Result();
        result.data = object;
        result.code = "1";
        result.msg = "success";
        return result;
    }

    public static Result error (String  msg){
        Result result = new Result();
        result.code = "0";
        result.msg = msg;
        return result;
    }

}
