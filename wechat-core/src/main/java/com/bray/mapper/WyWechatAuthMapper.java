package com.bray.mapper;

import com.bray.model.WyWechatAuth;
import com.bray.model.WyWechatAuthExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface WyWechatAuthMapper {
    @SelectProvider(type=WyWechatAuthSqlProvider.class, method="countByExample")
    int countByExample(WyWechatAuthExample example);

    @DeleteProvider(type=WyWechatAuthSqlProvider.class, method="deleteByExample")
    int deleteByExample(WyWechatAuthExample example);

    @Delete({
        "delete from wy_wechat_auth",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wy_wechat_auth (id, weixin_id, ",
        "weixin_secret, weixin_cache, ",
        "weixin_name, bind_domains, ",
        "debug, status, create_time, ",
        "update_time)",
        "values (#{id,jdbcType=INTEGER}, #{weixinId,jdbcType=VARCHAR}, ",
        "#{weixinSecret,jdbcType=VARCHAR}, #{weixinCache,jdbcType=VARCHAR}, ",
        "#{weixinName,jdbcType=VARCHAR}, #{bindDomains,jdbcType=VARCHAR}, ",
        "#{debug,jdbcType=BIT}, #{status,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(WyWechatAuth record);

    @InsertProvider(type=WyWechatAuthSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertSelective(WyWechatAuth record);

    @SelectProvider(type=WyWechatAuthSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="weixin_id", property="weixinId", jdbcType=JdbcType.VARCHAR),
        @Result(column="weixin_secret", property="weixinSecret", jdbcType=JdbcType.VARCHAR),
        @Result(column="weixin_cache", property="weixinCache", jdbcType=JdbcType.VARCHAR),
        @Result(column="weixin_name", property="weixinName", jdbcType=JdbcType.VARCHAR),
        @Result(column="bind_domains", property="bindDomains", jdbcType=JdbcType.VARCHAR),
        @Result(column="debug", property="debug", jdbcType=JdbcType.BIT),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<WyWechatAuth> selectByExample(WyWechatAuthExample example);

    @Select({
        "select",
        "id, weixin_id, weixin_secret, weixin_cache, weixin_name, bind_domains, debug, ",
        "status, create_time, update_time",
        "from wy_wechat_auth",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="weixin_id", property="weixinId", jdbcType=JdbcType.VARCHAR),
        @Result(column="weixin_secret", property="weixinSecret", jdbcType=JdbcType.VARCHAR),
        @Result(column="weixin_cache", property="weixinCache", jdbcType=JdbcType.VARCHAR),
        @Result(column="weixin_name", property="weixinName", jdbcType=JdbcType.VARCHAR),
        @Result(column="bind_domains", property="bindDomains", jdbcType=JdbcType.VARCHAR),
        @Result(column="debug", property="debug", jdbcType=JdbcType.BIT),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    WyWechatAuth selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WyWechatAuthSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WyWechatAuth record, @Param("example") WyWechatAuthExample example);

    @UpdateProvider(type=WyWechatAuthSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WyWechatAuth record, @Param("example") WyWechatAuthExample example);

    @UpdateProvider(type=WyWechatAuthSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WyWechatAuth record);

    @Update({
        "update wy_wechat_auth",
        "set weixin_id = #{weixinId,jdbcType=VARCHAR},",
          "weixin_secret = #{weixinSecret,jdbcType=VARCHAR},",
          "weixin_cache = #{weixinCache,jdbcType=VARCHAR},",
          "weixin_name = #{weixinName,jdbcType=VARCHAR},",
          "bind_domains = #{bindDomains,jdbcType=VARCHAR},",
          "debug = #{debug,jdbcType=BIT},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WyWechatAuth record);
}