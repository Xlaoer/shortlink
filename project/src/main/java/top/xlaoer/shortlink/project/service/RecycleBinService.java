package top.xlaoer.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.xlaoer.shortlink.project.dao.entity.ShortLinkDO;
import top.xlaoer.shortlink.project.dto.req.RecycleBinSaveReqDTO;

/**
 * 回收站管理接口层
 */
public interface RecycleBinService extends IService<ShortLinkDO> {

    /**
     * 保存回收站
     *
     * @param requestParam 请求参数
     */
    void saveRecycleBin(RecycleBinSaveReqDTO requestParam);
}
