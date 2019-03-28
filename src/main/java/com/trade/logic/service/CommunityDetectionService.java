package com.trade.logic.service;

import com.trade.data.model.Company;
import com.trade.web.response.GeneralResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by Wayne.A.Z on 2019/3/22.
 */
public interface CommunityDetectionService {

  GeneralResponse executeBMLPA(int type , double threshold);

  GeneralResponse<Map<Company, List<Double>>> roughCoreByCapital();

  GeneralResponse<Map<Company, Map<Double, Double>>> propagation(double threshold);
}
