package com.kz.service;

import com.kz.entity.SysFile;
import com.kz.persistence.SysFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kz on 2017-11-27.
 * 文件
 */
@Service
@Transactional
public class SysFileService {

    @Autowired
    private SysFileMapper sysFileMapper;

    public void save(SysFile file) {
        sysFileMapper.insertSelective(file);
    }
}
