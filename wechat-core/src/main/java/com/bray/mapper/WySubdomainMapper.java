package com.bray.mapper;

import com.bray.model.WySubdomain;
import com.bray.model.WySubdomainExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface WySubdomainMapper {
    @SelectProvider(type=WySubdomainSqlProvider.class, method="countByExample")
    int countByExample(WySubdomainExample example);

    @DeleteProvider(type=WySubdomainSqlProvider.class, method="deleteByExample")
    int deleteByExample(WySubdomainExample example);

    @Delete({
        "delete from wy_subdomain",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wy_subdomain (id, domain_id, ",
        "sub_domain, type, ",
        "create_time, update_time)",
        "values (#{id,jdbcType=INTEGER}, #{domainId,jdbcType=INTEGER}, ",
        "#{subDomain,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(WySubdomain record);

    @InsertProvider(type=WySubdomainSqlProvider.class, method="insertSelective")
    int insertSelective(WySubdomain record);

    @SelectProvider(type=WySubdomainSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="domain_id", property="domainId", jdbcType=JdbcType.INTEGER),
        @Result(column="sub_domain", property="subDomain", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<WySubdomain> selectByExample(WySubdomainExample example);

    @Select({
        "select",
        "id, domain_id, sub_domain, type, create_time, update_time",
        "from wy_subdomain",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="domain_id", property="domainId", jdbcType=JdbcType.INTEGER),
        @Result(column="sub_domain", property="subDomain", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    WySubdomain selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WySubdomainSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WySubdomain record, @Param("example") WySubdomainExample example);

    @UpdateProvider(type=WySubdomainSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WySubdomain record, @Param("example") WySubdomainExample example);

    @UpdateProvider(type=WySubdomainSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WySubdomain record);

    @Update({
        "update wy_subdomain",
        "set domain_id = #{domainId,jdbcType=INTEGER},",
          "sub_domain = #{subDomain,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WySubdomain record);
}