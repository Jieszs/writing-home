package com.jiesz.writinghome.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectBox {

    /**
     * 编号
     */
    private Long code;

    /**
     * 描述
     */
    private String desc;

}
