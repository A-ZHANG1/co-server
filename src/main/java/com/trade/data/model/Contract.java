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

    @ApiModelProperty(value = "出卖公司名")
    private String partyAName;

    @ApiModelProperty(value = "买受公司名")
    private String partyBName;

    @ApiModelProperty(value = "合同金额（万元）")
    private double amount;

    @ApiModelProperty(value = "合同开始时间")
    private double startTime;

    @ApiModelProperty(value = "合同结束时间")
    private double endTime;

    @ApiModelProperty(value = "合同地点")
    private double location;


    public Contract(String partyAName, String partyBName, double amount) {
        this.partyAName = partyAName;
        this.partyBName = partyBName;
        this.amount = amount;
    }

    public Contract(String partyAName, String partyBName, double amount, double startTime, double endTime, double location) {
        this.partyAName = partyAName;
        this.partyBName = partyBName;
        this.amount = amount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }
}
