package com.bray.mapper;

import com.bray.model.WyDomain;
import com.bray.model.WyDomainExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface WyDomainMapper {
    @SelectProvider(type=WyDomainSqlProvider.class, method="countByExample")
    int countByExample(WyDomainExample example);

    @DeleteProvider(type=WyDomainSqlProvider.class, method="deleteByExample")
    int deleteByExample(WyDomainExample example);

    @Delete({
        "delete from wy_domain",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wy_domain (id, domain, ",
        "domain_from, type, ",
        "status, create_time, ",
        "update_time)",
        "values (#{id,jdbcType=INTEGER}, #{domain,jdbcType=VARCHAR}, ",
        "#{domainFrom,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(WyDomain record);

    @InsertProvider(type=WyDomainSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertSelective(WyDomain record);

    @SelectProvider(type=WyDomainSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="domain", property="domain", jdbcType=JdbcType.VARCHAR),
        @Result(column="domain_from", property="domainFrom", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<WyDomain> selectByExample(WyDomainExample example);

    @Select({
        "select",
        "id, domain, domain_from, type, status, create_time, update_time",
        "from wy_domain",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="domain", property="domain", jdbcType=JdbcType.VARCHAR),
        @Result(column="domain_from", property="domainFrom", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    WyDomain selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WyDomainSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WyDomain record, @Param("example") WyDomainExample example);

    @UpdateProvider(type=WyDomainSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WyDomain record, @Param("example") WyDomainExample example);

    @UpdateProvider(type=WyDomainSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WyDomain record);

    @Update({
        "update wy_domain",
        "set domain = #{domain,jdbcType=VARCHAR},",
          "domain_from = #{domainFrom,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WyDomain record);

}



