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
    @ApiModelProperty(value = "节点A端，甲方名")
    private String partyAName;

    @ApiModelProperty(value = "节点B端，乙方名")
    private String partyBName;

    @ApiModelProperty(value = "节点权重")
    private double linkWeight;

}
