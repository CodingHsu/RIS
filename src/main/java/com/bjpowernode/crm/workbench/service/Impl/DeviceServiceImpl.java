package com.bjpowernode.crm.workbench.service.Impl;

import com.bjpowernode.crm.utils.SqlSessionUtil;
import com.bjpowernode.crm.workbench.dao.DeviceDao;
import com.bjpowernode.crm.workbench.domain.Device;
import com.bjpowernode.crm.workbench.service.DeviceService;

public class DeviceServiceImpl implements DeviceService {
    public static void main(String[] args) {
        DeviceDao deviceDao = SqlSessionUtil.getSqlSession().getMapper(DeviceDao.class);
        String id = "11111111111111111111111111111111";
        Device device = deviceDao.test(id);
        String str = device.getName();
        System.out.println(str);
    }
}
