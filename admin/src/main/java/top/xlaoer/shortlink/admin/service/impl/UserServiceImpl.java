package top.xlaoer.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import top.xlaoer.shortlink.admin.common.convention.exception.ClientException;
import top.xlaoer.shortlink.admin.common.enums.UserErrorCodeEnum;
import top.xlaoer.shortlink.admin.dao.entity.UserDO;
import top.xlaoer.shortlink.admin.dao.mapper.UserMapper;
import top.xlaoer.shortlink.admin.dto.resp.UserRespDTO;
import top.xlaoer.shortlink.admin.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {


    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }
}
