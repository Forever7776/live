package com.kz.service.qiniu;

import com.kz.entity.QiNiuFile;
import com.kz.persistence.QiNiuFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kz on 2017-11-20.
 *
 */
@Service
public class QiNiuFileService {

    @Autowired
    private QiNiuFileMapper qiniuFileMapper;

    public void save(QiNiuFile qiNiuFile){
        qiniuFileMapper.insertSelective(qiNiuFile);
    }
}
