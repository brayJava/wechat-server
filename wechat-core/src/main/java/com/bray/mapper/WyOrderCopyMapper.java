package com.bray.mapper;

import com.bray.model.WyOrderCopy;
import com.bray.model.WyOrderCopyExample;
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

public interface WyOrderCopyMapper {
    @SelectProvider(type=WyOrderCopySqlProvider.class, method="countByExample")
    int countByExample(WyOrderCopyExample example);

    @DeleteProvider(type=WyOrderCopySqlProvider.class, method="deleteByExample")
    int deleteByExample(WyOrderCopyExample example);

    @Delete({
        "delete from wy_order_copy",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wy_order_copy (name, phone, ",
        "title, size, price, ",
        "province, city, ",
        "county, address, ",
        "num, number, message, ",
        "idnumber, qq, wechat, ",
        "email, age, status, ",
        "create_time, update_time)",
        "values (#{name,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, ",
        "#{title,jdbcType=VARCHAR}, #{size,jdbcType=VARCHAR}, #{price,jdbcType=DECIMAL}, ",
        "#{province,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, ",
        "#{county,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
        "#{num,jdbcType=INTEGER}, #{number,jdbcType=INTEGER}, #{message,jdbcType=VARCHAR}, ",
        "#{idnumber,jdbcType=VARCHAR}, #{qq,jdbcType=VARCHAR}, #{wechat,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(WyOrderCopy record);

    @InsertProvider(type=WyOrderCopySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(WyOrderCopy record);

    @SelectProvider(type=WyOrderCopySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="size", property="size", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="county", property="county", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="num", property="num", jdbcType=JdbcType.INTEGER),
        @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
        @Result(column="message", property="message", jdbcType=JdbcType.VARCHAR),
        @Result(column="idnumber", property="idnumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="qq", property="qq", jdbcType=JdbcType.VARCHAR),
        @Result(column="wechat", property="wechat", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<WyOrderCopy> selectByExample(WyOrderCopyExample example);

    @Select({
        "select",
        "id, name, phone, title, size, price, province, city, county, address, num, number, ",
        "message, idnumber, qq, wechat, email, age, status, create_time, update_time",
        "from wy_order_copy",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="size", property="size", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="county", property="county", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="num", property="num", jdbcType=JdbcType.INTEGER),
        @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
        @Result(column="message", property="message", jdbcType=JdbcType.VARCHAR),
        @Result(column="idnumber", property="idnumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="qq", property="qq", jdbcType=JdbcType.VARCHAR),
        @Result(column="wechat", property="wechat", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    WyOrderCopy selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WyOrderCopySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WyOrderCopy record, @Param("example") WyOrderCopyExample example);

    @UpdateProvider(type=WyOrderCopySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WyOrderCopy record, @Param("example") WyOrderCopyExample example);

    @UpdateProvider(type=WyOrderCopySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WyOrderCopy record);

    @Update({
        "update wy_order_copy",
        "set name = #{name,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "size = #{size,jdbcType=VARCHAR},",
          "price = #{price,jdbcType=DECIMAL},",
          "province = #{province,jdbcType=VARCHAR},",
          "city = #{city,jdbcType=VARCHAR},",
          "county = #{county,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "num = #{num,jdbcType=INTEGER},",
          "number = #{number,jdbcType=INTEGER},",
          "message = #{message,jdbcType=VARCHAR},",
          "idnumber = #{idnumber,jdbcType=VARCHAR},",
          "qq = #{qq,jdbcType=VARCHAR},",
          "wechat = #{wechat,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "age = #{age,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WyOrderCopy record);
}