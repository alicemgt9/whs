package com.jhb.whs.common;

import lombok.Data;

import java.util.HashMap;

@Data
public class QueryPageParam {
    private static int Page_Size = 20;
    private static int Page_Num = 1;

    private int pageSize = Page_Size;
    private int pageNum = Page_Num;

    private HashMap param = new HashMap();

    public void printPage(){
        System.out.println("pageSize" + pageSize + " pageNum" + pageNum);
    }
}
