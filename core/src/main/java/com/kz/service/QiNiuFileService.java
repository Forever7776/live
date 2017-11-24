package com.kz.service;

import com.kz.entity.QiNiuFile;
import com.kz.persistence.QiNiuFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kz on 2017-11-20.
 *
 */
@Service
@Transactional
public class QiNiuFileService {

    @Autowired
    private QiNiuFileMapper qiniuFileMapper;

    public void save(QiNiuFile qiNiuFile){
        qiniuFileMapper.insert(qiNiuFile);
    }
}
