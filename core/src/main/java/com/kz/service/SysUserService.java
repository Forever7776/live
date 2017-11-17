package com.kz.service;

import com.kz.entity.SysUser;
import com.kz.entity.SysUserExample;
import com.kz.persistence.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 0 on 2017-11-17.
 */
@Service
@Transactional
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public List<SysUser> getAll(){
        return sysUserMapper.selectByExample(new SysUserExample());
    }
}
