package com.trade.data.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Wayne.A.Z on 2019/1/19.
 */
@ApiModel
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @ApiModelProperty(value = "公司Id，主键，创建时不传")
    private String companyId;

    @ApiModelProperty(value = "公司名")
    private String companyName;

    @ApiModelProperty(value="注册资本，value")
    private int capital;

    @ApiModelProperty(value="节点权重，symbolSize")
    private int nodeWeight;

    public Company(String companyName, int capital, int nodeWeight) {
        this.companyName = companyName;
        this.capital = capital;
        this.nodeWeight = nodeWeight;
    }

    public Company(String companyName) {
        this.companyName = companyName;
    }
}
