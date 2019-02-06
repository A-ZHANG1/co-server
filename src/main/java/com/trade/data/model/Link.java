package com.trade.data.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Wayne.A.Z on 2019/1/20.
 */
@ApiModel
@Getter
@Setter
@NoArgsConstructor
public class Link {
    @ApiModelProperty(value = "主键id，创建时不传")
    private int linkId;

    @ApiModelProperty(value = "节点A端，甲方名")
    private String partyAName;

    @ApiModelProperty(value = "节点B端，乙方名")
    private String partyBName;

    @ApiModelProperty(value = "链接权重，合同金额之和，交易次数维度被合并到金额维度")
    private double linkWeight;

    public Link(String partyAName, String partyBName, double linkWeight) {
        this.partyAName = partyAName;
        this.partyBName = partyBName;
        this.linkWeight = linkWeight;
    }
}
