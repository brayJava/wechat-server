package com.bray.mapper;

import com.bray.model.WyUserRole;
import com.bray.model.WyUserRoleExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface WyUserRoleMapper {
    @SelectProvider(type=WyUserRoleSqlProvider.class, method="countByExample")
    int countByExample(WyUserRoleExample example);

    @DeleteProvider(type=WyUserRoleSqlProvider.class, method="deleteByExample")
    int deleteByExample(WyUserRoleExample example);

    @Delete({
        "delete from wy_user_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wy_user_role (id, user_id, ",
        "role_id, create_time, ",
        "update_time)",
        "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{roleId,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(WyUserRole record);

    @InsertProvider(type=WyUserRoleSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertSelective(WyUserRole record);

    @SelectProvider(type=WyUserRoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<WyUserRole> selectByExample(WyUserRoleExample example);

    @Select({
        "select",
        "id, user_id, role_id, create_time, update_time",
        "from wy_user_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    WyUserRole selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WyUserRoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WyUserRole record, @Param("example") WyUserRoleExample example);

    @UpdateProvider(type=WyUserRoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WyUserRole record, @Param("example") WyUserRoleExample example);

    @UpdateProvider(type=WyUserRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WyUserRole record);

    @Update({
        "update wy_user_role",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "role_id = #{roleId,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WyUserRole record);
}