package top.xlaoer.shortlink.project.dto.req;

import lombok.Data;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.xlaoer.shortlink.project.dao.entity.ShortLinkDO;

import java.util.List;

/**
 * 回收站短链接分页请求参数
 */
@Data
public class ShortLinkRecycleBinPageReqDTO extends Page<ShortLinkDO> {

    /**
     * 分组标识
     */
    private List<String> gidList;
}