package com.bray.mapper;

import com.bray.model.WyRole;
import com.bray.model.WyRoleExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface WyRoleMapper {
    @SelectProvider(type=WyRoleSqlProvider.class, method="countByExample")
    int countByExample(WyRoleExample example);

    @DeleteProvider(type=WyRoleSqlProvider.class, method="deleteByExample")
    int deleteByExample(WyRoleExample example);

    @Delete({
        "delete from wy_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wy_role (id, role, ",
        "create_time, update_time)",
        "values (#{id,jdbcType=INTEGER}, #{role,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(WyRole record);

    @InsertProvider(type=WyRoleSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertSelective(WyRole record);

    @SelectProvider(type=WyRoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<WyRole> selectByExample(WyRoleExample example);

    @Select({
        "select",
        "id, role, create_time, update_time",
        "from wy_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    WyRole selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WyRoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WyRole record, @Param("example") WyRoleExample example);

    @UpdateProvider(type=WyRoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WyRole record, @Param("example") WyRoleExample example);

    @UpdateProvider(type=WyRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WyRole record);

    @Update({
        "update wy_role",
        "set role = #{role,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WyRole record);
}