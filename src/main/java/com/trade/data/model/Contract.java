package com.trade.data.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Wayne.A.Z on 2019/1/18.
 */
@ApiModel
@Getter
@Setter
@NoArgsConstructor
public class Contract {
    @ApiModelProperty(value = "合同Id")
    private String contractId;

    @ApiModelProperty(value = "出卖人Id")
    private String partyAId;

    @ApiModelProperty(value = "买受人Id")
    private String partyBId;

    @ApiModelProperty(value = "合同类型.1=采购合同，2=销售合同")
    private String type;

    @ApiModelProperty(value = "合同金额")
    private double amount;

}
