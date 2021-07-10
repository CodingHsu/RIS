package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.settings.dao.DeviceDao;
import com.bjpowernode.crm.settings.domain.Device;
import com.bjpowernode.crm.settings.service.DeviceService;
import com.bjpowernode.crm.utils.SqlSessionUtil;

import java.util.List;

public class DeviceServiceImpl implements DeviceService {
    private DeviceDao deviceDao = SqlSessionUtil.getSqlSession().getMapper(DeviceDao.class);
    public List<Device> getAll() {
        List<Device> deviceList = deviceDao.getAll();
        return deviceList;
    }
}
