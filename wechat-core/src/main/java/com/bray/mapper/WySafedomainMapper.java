package com.bray.mapper;

import com.bray.model.WySafedomain;
import com.bray.model.WySafedomainExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface WySafedomainMapper {
    @SelectProvider(type=WySafedomainSqlProvider.class, method="countByExample")
    int countByExample(WySafedomainExample example);

    @DeleteProvider(type=WySafedomainSqlProvider.class, method="deleteByExample")
    int deleteByExample(WySafedomainExample example);

    @Delete({
        "delete from wy_safedomain",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wy_safedomain (safe_url, create_time, ",
        "update_time)",
        "values (#{safeUrl,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(WySafedomain record);

    @InsertProvider(type=WySafedomainSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(WySafedomain record);

    @SelectProvider(type=WySafedomainSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="safe_url", property="safeUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<WySafedomain> selectByExample(WySafedomainExample example);

    @Select({
        "select",
        "id, safe_url, create_time, update_time",
        "from wy_safedomain",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="safe_url", property="safeUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    WySafedomain selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WySafedomainSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WySafedomain record, @Param("example") WySafedomainExample example);

    @UpdateProvider(type=WySafedomainSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WySafedomain record, @Param("example") WySafedomainExample example);

    @UpdateProvider(type=WySafedomainSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WySafedomain record);

    @Update({
        "update wy_safedomain",
        "set safe_url = #{safeUrl,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WySafedomain record);
}