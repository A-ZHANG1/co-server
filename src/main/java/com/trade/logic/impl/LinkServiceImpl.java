package com.trade.logic.impl;

import com.trade.data.mapper.LinkMapper;
import com.trade.data.model.Link;
import com.trade.logic.service.LinkService;
import com.trade.web.response.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Wayne.A.Z on 2019/1/20.
 */
@Service
public class LinkServiceImpl implements LinkService{
    @Autowired
    LinkMapper linkMapper;

    @Override
    public GeneralResponse<List<Link>> getLinks() {
        GeneralResponse<List<Link>> resp =new GeneralResponse<>();
        List<Link> links=linkMapper.getLinks();
        resp.setObj(links);
        return resp;
    }
}
