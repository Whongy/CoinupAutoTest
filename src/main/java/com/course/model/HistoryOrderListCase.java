package com.course.model;

import lombok.Data;

/**
 * @Description:
 * @Author KHAN
 * @Date 2022/11/4$ 15:50$
 */
@Data
public class HistoryOrderListCase {
        private int contractId;
        private String securityInfo;
        private String expected;

        @Override
        public String toString() {
                return "HistoryOrderListCase{" +
                        "contractId=" + contractId +
                        ", securityInfo='" + securityInfo + '\'' +
                        ", expected='" + expected + '\'' +
                        '}';
        }
}
