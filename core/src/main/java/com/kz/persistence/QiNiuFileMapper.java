package com.kz.persistence;

import com.kz.entity.QiNiuFile;
import com.kz.entity.QiNiuFileExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QiNiuFileMapper {
    int countByExample(QiNiuFileExample example);

    int deleteByExample(QiNiuFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(QiNiuFile record);

    int insertSelective(QiNiuFile record);

    List<QiNiuFile> selectByExample(QiNiuFileExample example);

    QiNiuFile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") QiNiuFile record, @Param("example") QiNiuFileExample example);

    int updateByExample(@Param("record") QiNiuFile record, @Param("example") QiNiuFileExample example);

    int updateByPrimaryKeySelective(QiNiuFile record);

    int updateByPrimaryKey(QiNiuFile record);
}