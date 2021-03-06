package com.bray.mapper;

import com.bray.model.WyArticle;
import com.bray.model.WyArticleExample;
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

public interface WyArticleMapper {
    @SelectProvider(type=WyArticleSqlProvider.class, method="countByExample")
    int countByExample(WyArticleExample example);

    @DeleteProvider(type=WyArticleSqlProvider.class, method="deleteByExample")
    int deleteByExample(WyArticleExample example);

    @Delete({
        "delete from wy_article",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wy_article (title, bgm_url, ",
        "author, share_title, ",
        "share_describe, share_img_url, ",
        "is_order_img, data_transfer_url, ",
        "goback_url, read_original_url, ",
        "image_goback_url, order_img_url, ",
        "order_copy_url, share_success_fail_url, ",
        "statistical, connect_primary_domain, ",
        "connect_common_domain, is_publish, ",
        "forced_share, no_share_domain, ",
        "user_id, status, ",
        "create_time, update_time, ",
        "cj_content)",
        "values (#{title,jdbcType=VARCHAR}, #{bgmUrl,jdbcType=VARCHAR}, ",
        "#{author,jdbcType=VARCHAR}, #{shareTitle,jdbcType=VARCHAR}, ",
        "#{shareDescribe,jdbcType=VARCHAR}, #{shareImgUrl,jdbcType=VARCHAR}, ",
        "#{isOrderImg,jdbcType=BIT}, #{dataTransferUrl,jdbcType=VARCHAR}, ",
        "#{gobackUrl,jdbcType=VARCHAR}, #{readOriginalUrl,jdbcType=VARCHAR}, ",
        "#{imageGobackUrl,jdbcType=VARCHAR}, #{orderImgUrl,jdbcType=VARCHAR}, ",
        "#{orderCopyUrl,jdbcType=VARCHAR}, #{shareSuccessFailUrl,jdbcType=VARCHAR}, ",
        "#{statistical,jdbcType=VARCHAR}, #{connectPrimaryDomain,jdbcType=VARCHAR}, ",
        "#{connectCommonDomain,jdbcType=VARCHAR}, #{isPublish,jdbcType=BIT}, ",
        "#{forcedShare,jdbcType=BIT}, #{noShareDomain,jdbcType=VARCHAR}, ",
        "#{userId,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{cjContent,jdbcType=LONGVARBINARY})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(WyArticle record);

    @InsertProvider(type=WyArticleSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(WyArticle record);

    @SelectProvider(type=WyArticleSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="bgm_url", property="bgmUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="share_title", property="shareTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="share_describe", property="shareDescribe", jdbcType=JdbcType.VARCHAR),
        @Result(column="share_img_url", property="shareImgUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_order_img", property="isOrderImg", jdbcType=JdbcType.BIT),
        @Result(column="data_transfer_url", property="dataTransferUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="goback_url", property="gobackUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="read_original_url", property="readOriginalUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_goback_url", property="imageGobackUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_img_url", property="orderImgUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_copy_url", property="orderCopyUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="share_success_fail_url", property="shareSuccessFailUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="statistical", property="statistical", jdbcType=JdbcType.VARCHAR),
        @Result(column="connect_primary_domain", property="connectPrimaryDomain", jdbcType=JdbcType.VARCHAR),
        @Result(column="connect_common_domain", property="connectCommonDomain", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_publish", property="isPublish", jdbcType=JdbcType.BIT),
        @Result(column="forced_share", property="forcedShare", jdbcType=JdbcType.BIT),
        @Result(column="no_share_domain", property="noShareDomain", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="cj_content", property="cjContent", jdbcType=JdbcType.LONGVARBINARY)
    })
    List<WyArticle> selectByExampleWithBLOBs(WyArticleExample example);

    @SelectProvider(type=WyArticleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="bgm_url", property="bgmUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="share_title", property="shareTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="share_describe", property="shareDescribe", jdbcType=JdbcType.VARCHAR),
        @Result(column="share_img_url", property="shareImgUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_order_img", property="isOrderImg", jdbcType=JdbcType.BIT),
        @Result(column="data_transfer_url", property="dataTransferUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="goback_url", property="gobackUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="read_original_url", property="readOriginalUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_goback_url", property="imageGobackUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_img_url", property="orderImgUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_copy_url", property="orderCopyUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="share_success_fail_url", property="shareSuccessFailUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="statistical", property="statistical", jdbcType=JdbcType.VARCHAR),
        @Result(column="connect_primary_domain", property="connectPrimaryDomain", jdbcType=JdbcType.VARCHAR),
        @Result(column="connect_common_domain", property="connectCommonDomain", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_publish", property="isPublish", jdbcType=JdbcType.BIT),
        @Result(column="forced_share", property="forcedShare", jdbcType=JdbcType.BIT),
        @Result(column="no_share_domain", property="noShareDomain", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<WyArticle> selectByExample(WyArticleExample example);

    @Select({
        "select",
        "id, title, bgm_url, author, share_title, share_describe, share_img_url, is_order_img, ",
        "data_transfer_url, goback_url, read_original_url, image_goback_url, order_img_url, ",
        "order_copy_url, share_success_fail_url, statistical, connect_primary_domain, ",
        "connect_common_domain, is_publish, forced_share, no_share_domain, user_id, status, ",
        "create_time, update_time, cj_content",
        "from wy_article",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="bgm_url", property="bgmUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="share_title", property="shareTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="share_describe", property="shareDescribe", jdbcType=JdbcType.VARCHAR),
        @Result(column="share_img_url", property="shareImgUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_order_img", property="isOrderImg", jdbcType=JdbcType.BIT),
        @Result(column="data_transfer_url", property="dataTransferUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="goback_url", property="gobackUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="read_original_url", property="readOriginalUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_goback_url", property="imageGobackUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_img_url", property="orderImgUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_copy_url", property="orderCopyUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="share_success_fail_url", property="shareSuccessFailUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="statistical", property="statistical", jdbcType=JdbcType.VARCHAR),
        @Result(column="connect_primary_domain", property="connectPrimaryDomain", jdbcType=JdbcType.VARCHAR),
        @Result(column="connect_common_domain", property="connectCommonDomain", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_publish", property="isPublish", jdbcType=JdbcType.BIT),
        @Result(column="forced_share", property="forcedShare", jdbcType=JdbcType.BIT),
        @Result(column="no_share_domain", property="noShareDomain", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="cj_content", property="cjContent", jdbcType=JdbcType.LONGVARBINARY)
    })
    WyArticle selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WyArticleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WyArticle record, @Param("example") WyArticleExample example);

    @UpdateProvider(type=WyArticleSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") WyArticle record, @Param("example") WyArticleExample example);

    @UpdateProvider(type=WyArticleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WyArticle record, @Param("example") WyArticleExample example);

    @UpdateProvider(type=WyArticleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WyArticle record);

    @Update({
        "update wy_article",
        "set title = #{title,jdbcType=VARCHAR},",
          "bgm_url = #{bgmUrl,jdbcType=VARCHAR},",
          "author = #{author,jdbcType=VARCHAR},",
          "share_title = #{shareTitle,jdbcType=VARCHAR},",
          "share_describe = #{shareDescribe,jdbcType=VARCHAR},",
          "share_img_url = #{shareImgUrl,jdbcType=VARCHAR},",
          "is_order_img = #{isOrderImg,jdbcType=BIT},",
          "data_transfer_url = #{dataTransferUrl,jdbcType=VARCHAR},",
          "goback_url = #{gobackUrl,jdbcType=VARCHAR},",
          "read_original_url = #{readOriginalUrl,jdbcType=VARCHAR},",
          "image_goback_url = #{imageGobackUrl,jdbcType=VARCHAR},",
          "order_img_url = #{orderImgUrl,jdbcType=VARCHAR},",
          "order_copy_url = #{orderCopyUrl,jdbcType=VARCHAR},",
          "share_success_fail_url = #{shareSuccessFailUrl,jdbcType=VARCHAR},",
          "statistical = #{statistical,jdbcType=VARCHAR},",
          "connect_primary_domain = #{connectPrimaryDomain,jdbcType=VARCHAR},",
          "connect_common_domain = #{connectCommonDomain,jdbcType=VARCHAR},",
          "is_publish = #{isPublish,jdbcType=BIT},",
          "forced_share = #{forcedShare,jdbcType=BIT},",
          "no_share_domain = #{noShareDomain,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "cj_content = #{cjContent,jdbcType=LONGVARBINARY}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(WyArticle record);

    @Update({
        "update wy_article",
        "set title = #{title,jdbcType=VARCHAR},",
          "bgm_url = #{bgmUrl,jdbcType=VARCHAR},",
          "author = #{author,jdbcType=VARCHAR},",
          "share_title = #{shareTitle,jdbcType=VARCHAR},",
          "share_describe = #{shareDescribe,jdbcType=VARCHAR},",
          "share_img_url = #{shareImgUrl,jdbcType=VARCHAR},",
          "is_order_img = #{isOrderImg,jdbcType=BIT},",
          "data_transfer_url = #{dataTransferUrl,jdbcType=VARCHAR},",
          "goback_url = #{gobackUrl,jdbcType=VARCHAR},",
          "read_original_url = #{readOriginalUrl,jdbcType=VARCHAR},",
          "image_goback_url = #{imageGobackUrl,jdbcType=VARCHAR},",
          "order_img_url = #{orderImgUrl,jdbcType=VARCHAR},",
          "order_copy_url = #{orderCopyUrl,jdbcType=VARCHAR},",
          "share_success_fail_url = #{shareSuccessFailUrl,jdbcType=VARCHAR},",
          "statistical = #{statistical,jdbcType=VARCHAR},",
          "connect_primary_domain = #{connectPrimaryDomain,jdbcType=VARCHAR},",
          "connect_common_domain = #{connectCommonDomain,jdbcType=VARCHAR},",
          "is_publish = #{isPublish,jdbcType=BIT},",
          "forced_share = #{forcedShare,jdbcType=BIT},",
          "no_share_domain = #{noShareDomain,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WyArticle record);
}