package com.bray.mapper;

import com.bray.model.WyOrderLog;
import com.bray.model.WyOrderLogExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface WyOrderLogMapper {
    @SelectProvider(type=WyOrderLogSqlProvider.class, method="countByExample")
    int countByExample(WyOrderLogExample example);

    @DeleteProvider(type=WyOrderLogSqlProvider.class, method="deleteByExample")
    int deleteByExample(WyOrderLogExample example);

    @Delete({
        "delete from wy_order_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wy_order_log (id, type, ",
        "create_time, update_time)",
        "values (#{id,jdbcType=INTEGER}, #{type,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(WyOrderLog record);

    @InsertProvider(type=WyOrderLogSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertSelective(WyOrderLog record);

    @SelectProvider(type=WyOrderLogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<WyOrderLog> selectByExample(WyOrderLogExample example);

    @Select({
        "select",
        "id, type, create_time, update_time",
        "from wy_order_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    WyOrderLog selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WyOrderLogSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WyOrderLog record, @Param("example") WyOrderLogExample example);

    @UpdateProvider(type=WyOrderLogSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WyOrderLog record, @Param("example") WyOrderLogExample example);

    @UpdateProvider(type=WyOrderLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WyOrderLog record);

    @Update({
        "update wy_order_log",
        "set type = #{type,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WyOrderLog record);
}