package com.csd.study.distribute.lock;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderCodeGenerator {

    // 自增长序列
    private int i = 0;

    public String getOrderCode(){
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-");
        return sdf.format(now) + ++i;
    }
}
