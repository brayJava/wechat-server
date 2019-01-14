package com.bray.mapper;

import com.bray.model.WyArticleImg;
import com.bray.model.WyArticleImgExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface WyArticleImgMapper {
    @SelectProvider(type=WyArticleImgSqlProvider.class, method="countByExample")
    int countByExample(WyArticleImgExample example);

    @DeleteProvider(type=WyArticleImgSqlProvider.class, method="deleteByExample")
    int deleteByExample(WyArticleImgExample example);

    @Delete({
        "delete from wy_article_img",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wy_article_img (id, article_id, ",
        "img_path, third_img_path, ",
        "img_url, status, ",
        "create_time, update_time)",
        "values (#{id,jdbcType=INTEGER}, #{articleId,jdbcType=INTEGER}, ",
        "#{imgPath,jdbcType=VARCHAR}, #{thirdImgPath,jdbcType=VARCHAR}, ",
        "#{imgUrl,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(WyArticleImg record);

    @InsertProvider(type=WyArticleImgSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(WyArticleImg record);

    @SelectProvider(type=WyArticleImgSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="article_id", property="articleId", jdbcType=JdbcType.INTEGER),
        @Result(column="img_path", property="imgPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="third_img_path", property="thirdImgPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="img_url", property="imgUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<WyArticleImg> selectByExample(WyArticleImgExample example);

    @Select({
        "select",
        "id, article_id, img_path, third_img_path, img_url, status, create_time, update_time",
        "from wy_article_img",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="article_id", property="articleId", jdbcType=JdbcType.INTEGER),
        @Result(column="img_path", property="imgPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="third_img_path", property="thirdImgPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="img_url", property="imgUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    WyArticleImg selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WyArticleImgSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WyArticleImg record, @Param("example") WyArticleImgExample example);

    @UpdateProvider(type=WyArticleImgSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WyArticleImg record, @Param("example") WyArticleImgExample example);

    @UpdateProvider(type=WyArticleImgSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WyArticleImg record);

    @Update({
        "update wy_article_img",
        "set article_id = #{articleId,jdbcType=INTEGER},",
          "img_path = #{imgPath,jdbcType=VARCHAR},",
          "third_img_path = #{thirdImgPath,jdbcType=VARCHAR},",
          "img_url = #{imgUrl,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WyArticleImg record);
    /**
     * 修改历史文章状态
     * @param status
     * @param articleId
     * @return
     */
    @Update({
            "update wy_article_img ",
            "set status = #{status,jdbcType=INTEGER}  ",
            "where article_id = #{articleId,jdbcType=INTEGER} "
    })
    int updateByArticleId(@Param("status") int status ,@Param("articleId")int articleId);
}