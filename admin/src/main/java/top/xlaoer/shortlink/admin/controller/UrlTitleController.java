package top.xlaoer.shortlink.admin.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.xlaoer.shortlink.admin.common.convention.result.Result;
import top.xlaoer.shortlink.admin.remote.dto.ShortLinkRemoteService;

/**
 * URL 标题控制层
 */
@RestController
@RequiredArgsConstructor
public class UrlTitleController {

    /**
     * 后续重构为 SpringCloud Feign 调用
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * 根据URL获取对应网站的标题
     */
    @GetMapping("/api/short-link/admin/v1/title")
    public Result<String> getTitleByUrl(@RequestParam("url") String url) {
        return shortLinkRemoteService.getTitleByUrl(url);
    }
}
