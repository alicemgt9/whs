package com.jhb.whs.common;

import lombok.Data;

@Data
public class Result {

    private int code; //编码 200/400
    private String msg; //成功、失败
    private Long total;//总记录数
    private Object data;//数据


    public static Result fail(){
        return result(400,"失败",0L,null);
    }

    //当操作成功且没有任何参数时候
    public static Result suc(){
        return result(200,"成功",0L,null);
    }

    //当操作成功且要返回单个对象的时候
    public static Result suc(Object data){
        return result(200,"成功",0L,data);
    }

    //当操作成功且要返回一整个数组的时候
    public static Result suc(Object data,Long total){
        return result(200,"成功",total,data);
    }

    private static Result result(int code,String msg,Long total,Object data){
        Result res = new Result();

        res.setCode(code);
        res.setMsg(msg);
        res.setData(data);
        res.setTotal(total);

        return res;
    }
}
