package zb.blog.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zb.blog.model.ArticleStat;
import zb.blog.service.StatService;

import java.util.List;

/**
 * Created by zhmt on 2017/6/22.
 */
@RestController()
public class StatController {

    @Autowired
    private StatService statService;

    @GetMapping("/stat/article/getArticleStat")
    public ArticleStat getArticleStat(String blogUid) {
        if(StringUtils.isBlank(blogUid)) {
            throw  new RuntimeException("blogUid cant be null.");
        }
        return statService.getArticleStat(blogUid);
    }
    @GetMapping("/stat/article/getArticleStatByIdList")
    public List<ArticleStat> getByIdList(List<String> blogUidList) {
        return statService.getByIdList(blogUidList);
    }
}
