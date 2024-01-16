package top.xlaoer.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.xlaoer.shortlink.admin.common.convention.result.Result;
import top.xlaoer.shortlink.admin.dto.resp.UserRespDTO;
import top.xlaoer.shortlink.admin.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/shortlink/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username")String username){
        UserRespDTO result = userService.getUserByUsername(username);
        if(result==null){
            return new Result<UserRespDTO>().setCode("-1").setMessage("用户查询为空");
        }else{
            return new Result<UserRespDTO>().setCode("0").setData(result);
        }

    }
}
