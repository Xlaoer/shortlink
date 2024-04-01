package top.xlaoer.shortlink.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.xlaoer.shortlink.admin.common.biz.user.UserContext;
import top.xlaoer.shortlink.admin.common.convention.exception.ServiceException;
import top.xlaoer.shortlink.admin.common.convention.result.Result;
import top.xlaoer.shortlink.admin.dao.entity.GroupDO;
import top.xlaoer.shortlink.admin.dao.mapper.GroupMapper;
import top.xlaoer.shortlink.admin.dto.req.ShortLinkRecycleBinPageReqDTO;
import top.xlaoer.shortlink.admin.remote.dto.ShortLinkActualRemoteService;
import top.xlaoer.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import top.xlaoer.shortlink.admin.service.RecycleBinService;

import java.util.List;

/**
 * URL 回收站接口实现层
 */
@Service
@RequiredArgsConstructor
public class RecycleBinServiceImpl implements RecycleBinService {

    private final GroupMapper groupMapper;

    private final ShortLinkActualRemoteService shortLinkActualRemoteService;


    @Override
    public Result<Page<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getDelFlag, 0);
        List<GroupDO> groupDOList = groupMapper.selectList(queryWrapper);
        if (CollUtil.isEmpty(groupDOList)) {
            throw new ServiceException("用户无分组信息");
        }
        requestParam.setGidList(groupDOList.stream().map(GroupDO::getGid).toList());
        return shortLinkActualRemoteService.pageRecycleBinShortLink(requestParam.getGidList(), requestParam.getCurrent(), requestParam.getSize());
    }
}