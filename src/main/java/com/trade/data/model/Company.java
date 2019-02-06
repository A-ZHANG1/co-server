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
    private double capital;

    @ApiModelProperty(value="节点权重，symbolSize")
    private double nodeWeight;

    public Company(String companyName, double capital, double nodeWeight) {
        this.companyName = companyName;
        this.capital = capital;
        this.nodeWeight = nodeWeight;
    }

    public Company(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (Double.compare(company.capital, capital) != 0) return false;
        if (Double.compare(company.nodeWeight, nodeWeight) != 0) return false;
        if (companyId != null ? !companyId.equals(company.companyId) : company.companyId != null) return false;
        return companyName != null ? companyName.equals(company.companyName) : company.companyName == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = companyId != null ? companyId.hashCode() : 0;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        temp = Double.doubleToLongBits(capital);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(nodeWeight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
