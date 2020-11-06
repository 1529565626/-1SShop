package com.ssk.shop.facade;

import com.alibaba.fastjson.JSONObject;
import com.ssk.exception.ServiceException;
import com.ssk.shop.dto.LoginMan;
import com.ssk.utils.ObjectHelper;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author: ssk
 * @Date: 2020/11/4 23:05
 */
public abstract class BaseAccountInfoFacade {

    /**
     * 获取当前账户id
     * @return
     * @throws ServiceException
     */
    public String getThisAccountId()throws ServiceException {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (ObjectHelper.isNotEmpty(principal) && principal.equals("anonymousUser")){
                return null;
            }else {
                LoginMan thisMan = (LoginMan) principal;
                return thisMan.getId();
            }
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 获取当前账号信息
     * @return
     * @throws ServiceException
     */
    public Object getThisAccount(Object roleType)throws ServiceException{
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (ObjectHelper.isNotEmpty(principal) && principal.equals("anonymousUser")){
                return null;
            }else {
                LoginMan thisMan = (LoginMan) principal;
                String thisAccountInfo = JSONObject.toJSONString(thisMan.getManMsg());
                return JSONObject.parseObject(thisAccountInfo,roleType.getClass());
            }
        }catch (Exception e){
            throw new ServiceException("账号信息获取失败");
        }
    }
}
