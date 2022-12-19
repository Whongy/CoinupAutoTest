package com.course.model;

import lombok.Data;

/**
 * @Description:
 * @Author KHAN
 * @Date 2022/11/8$ 15:54$
 */
@Data
public class OrderCreateCase {

    private int contractId;
    private int positionType;
    private String side;
    private int leverageLevel;
    private String open;
    private String price;
    private int volume;
    private String isConditionOrder;
    private int  type;
    //private String expected;


}
