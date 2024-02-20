package top.xlaoer.shortlink.project.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import top.xlaoer.shortlink.project.dao.entity.ShortLinkDO;

/**
 * 短链接分页请求参数
 */
@Data
public class ShortLinkPageReqDTO extends Page<ShortLinkDO> {

    /**
     * 分组标识
     */
    private String gid;
}