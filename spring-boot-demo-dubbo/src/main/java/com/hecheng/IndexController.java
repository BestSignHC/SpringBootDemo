package com.hecheng;

import cn.bestsign.delta.ms.user.dto.api.ApiPersonRegDTO;
import cn.bestsign.delta.ms.user.service.api.IApiAccountService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Reference
    private IApiAccountService iApiAccountService;

    @RequestMapping(value = "/testRegUser")
    public void test() {
        ApiPersonRegDTO apiPersonRegDTO = new ApiPersonRegDTO();

        String account = System.currentTimeMillis() + "@qq.com";
        System.out.println("account :" + account);

        apiPersonRegDTO.setAccount(account);
        apiPersonRegDTO.setName("杜晓杰");
        apiPersonRegDTO.setDeveloperId("112");
        apiPersonRegDTO.setNoticeMail(account);
        apiPersonRegDTO.setNoticeMobile("18258433333");
        iApiAccountService.doPersonReg(apiPersonRegDTO);
    }
}
