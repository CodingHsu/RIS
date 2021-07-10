package com.bjpowernode.crm.settings.dao;

import com.bjpowernode.crm.settings.domain.Device;

import java.util.List;

public interface DeviceDao {
    Device test(String s);

    List<Device> getAll();
}
