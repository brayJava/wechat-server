package com.bray.mapper;

import com.bray.model.WySubImg;
import com.bray.model.WySubImgExample;
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

public interface WySubImgMapper {
    @SelectProvider(type=WySubImgSqlProvider.class, method="countByExample")
    int countByExample(WySubImgExample example);

    @DeleteProvider(type=WySubImgSqlProvider.class, method="deleteByExample")
    int deleteByExample(WySubImgExample example);

    @Delete({
        "delete from wy_sub_img",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wy_sub_img (img_id, bg_url, ",
        "create_date, update_date)",
        "values (#{imgId,jdbcType=INTEGER}, #{bgUrl,jdbcType=VARCHAR}, ",
        "#{createDate,jdbcType=TIMESTAMP}, #{updateDate,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(WySubImg record);

    @InsertProvider(type=WySubImgSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(WySubImg record);

    @SelectProvider(type=WySubImgSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="img_id", property="imgId", jdbcType=JdbcType.INTEGER),
        @Result(column="bg_url", property="bgUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_date", property="updateDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<WySubImg> selectByExample(WySubImgExample example);

    @Select({
        "select",
        "id, img_id, bg_url, create_date, update_date",
        "from wy_sub_img",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="img_id", property="imgId", jdbcType=JdbcType.INTEGER),
        @Result(column="bg_url", property="bgUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_date", property="updateDate", jdbcType=JdbcType.TIMESTAMP)
    })
    WySubImg selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WySubImgSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WySubImg record, @Param("example") WySubImgExample example);

    @UpdateProvider(type=WySubImgSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WySubImg record, @Param("example") WySubImgExample example);

    @UpdateProvider(type=WySubImgSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WySubImg record);

    @Update({
        "update wy_sub_img",
        "set img_id = #{imgId,jdbcType=INTEGER},",
          "bg_url = #{bgUrl,jdbcType=VARCHAR},",
          "create_date = #{createDate,jdbcType=TIMESTAMP},",
          "update_date = #{updateDate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WySubImg record);
}