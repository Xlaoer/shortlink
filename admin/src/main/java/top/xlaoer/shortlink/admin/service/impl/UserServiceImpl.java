package top.xlaoer.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import top.xlaoer.shortlink.admin.common.convention.exception.ClientException;
import top.xlaoer.shortlink.admin.common.enums.UserErrorCodeEnum;
import top.xlaoer.shortlink.admin.dao.entity.UserDO;
import top.xlaoer.shortlink.admin.dao.mapper.UserMapper;
import top.xlaoer.shortlink.admin.dto.req.UserRegisterReqDTO;
import top.xlaoer.shortlink.admin.dto.resp.UserRespDTO;
import top.xlaoer.shortlink.admin.service.UserService;

import static top.xlaoer.shortlink.admin.common.enums.UserErrorCodeEnum.USER_NAME_EXIST;
import static top.xlaoer.shortlink.admin.common.enums.UserErrorCodeEnum.USER_SAVE_ERROR;

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

    @Override
    public Boolean hasUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        return userDO!=null;
    }

    @Override
    public void register(UserRegisterReqDTO requestParam) {
        if (hasUsername(requestParam.getUsername())) {
            throw new ClientException(USER_NAME_EXIST);
        }
        int inserted = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
        if (inserted < 1) {
            throw new ClientException(USER_SAVE_ERROR);
        }
    }
}
