package persistence;

import entity.sysUser;
import entity.sysUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface sysUserMapper {
    int countByExample(sysUserExample example);

    int deleteByExample(sysUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(sysUser record);

    int insertSelective(sysUser record);

    List<sysUser> selectByExample(sysUserExample example);

    sysUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") sysUser record, @Param("example") sysUserExample example);

    int updateByExample(@Param("record") sysUser record, @Param("example") sysUserExample example);

    int updateByPrimaryKeySelective(sysUser record);

    int updateByPrimaryKey(sysUser record);
}