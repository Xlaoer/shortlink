package top.xlaoer.shortlink.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.xlaoer.shortlink.project.dao.entity.LinkStatsTodayDO;
import top.xlaoer.shortlink.project.dao.mapper.LinkStatsTodayMapper;
import top.xlaoer.shortlink.project.service.LinkStatsTodayService;

/**
 * 短链接今日统计接口实现层
 */
@Service
public class LinkStatsTodayServiceImpl extends ServiceImpl<LinkStatsTodayMapper, LinkStatsTodayDO> implements LinkStatsTodayService {
}