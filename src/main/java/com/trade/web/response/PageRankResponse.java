package com.trade.web.response;

import com.trade.data.model.Company;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by Wayne.A.Z on 2019/1/23.
 */

@ApiModel
@Getter
@Setter
@NoArgsConstructor
public class PageRankResponse {
    @ApiModelProperty(value="当前迭代节点权重计算值与上一轮计算值差值的最大值")
    private double diff;

    @ApiModelProperty(value="当前迭代公司节点")
    private List<Company> companies;

    public PageRankResponse(double diff, List<Company> companies) {
        this.diff = diff;
        this.companies = companies;
    }
}
