package com.bray.mapper;

import com.bray.model.WyUser;
import com.bray.model.WyUserExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface WyUserMapper {
    @SelectProvider(type=WyUserSqlProvider.class, method="countByExample")
    int countByExample(WyUserExample example);

    @DeleteProvider(type=WyUserSqlProvider.class, method="deleteByExample")
    int deleteByExample(WyUserExample example);

    @Delete({
        "delete from wy_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wy_user (id, username, ",
        "password, create_time, ",
        "update_time)",
        "values (#{id,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(WyUser record);

    @InsertProvider(type=WyUserSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertSelective(WyUser record);

    @SelectProvider(type=WyUserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<WyUser> selectByExample(WyUserExample example);

    @Select({
        "select",
        "id, username, password, create_time, update_time",
        "from wy_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    WyUser selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WyUserSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WyUser record, @Param("example") WyUserExample example);

    @UpdateProvider(type=WyUserSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WyUser record, @Param("example") WyUserExample example);

    @UpdateProvider(type=WyUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WyUser record);

    @Update({
        "update wy_user",
        "set username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WyUser record);
}