package com.bray.mapper;

import com.bray.model.WyOrder;
import com.bray.model.WyOrderExample;
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

public interface WyOrderMapper {
    @SelectProvider(type=WyOrderSqlProvider.class, method="countByExample")
    int countByExample(WyOrderExample example);

    @DeleteProvider(type=WyOrderSqlProvider.class, method="deleteByExample")
    int deleteByExample(WyOrderExample example);

    @Delete({
        "delete from wy_order",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wy_order (id, name, ",
        "phone, title, price, ",
        "province, city, ",
        "county, address, ",
        "num, number, idnumber, ",
        "qq, wechat, email, ",
        "age)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{phone,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, #{price,jdbcType=DECIMAL}, ",
        "#{province,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, ",
        "#{county,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
        "#{num,jdbcType=INTEGER}, #{number,jdbcType=INTEGER}, #{idnumber,jdbcType=VARCHAR}, ",
        "#{qq,jdbcType=VARCHAR}, #{wechat,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, ",
        "#{age,jdbcType=INTEGER})"
    })
    int insert(WyOrder record);

    @InsertProvider(type=WyOrderSqlProvider.class, method="insertSelective")
    int insertSelective(WyOrder record);

    @SelectProvider(type=WyOrderSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="county", property="county", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="num", property="num", jdbcType=JdbcType.INTEGER),
        @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
        @Result(column="idnumber", property="idnumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="qq", property="qq", jdbcType=JdbcType.VARCHAR),
        @Result(column="wechat", property="wechat", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER)
    })
    List<WyOrder> selectByExample(WyOrderExample example);

    @Select({
        "select",
        "id, name, phone, title, price, province, city, county, address, num, number, ",
        "idnumber, qq, wechat, email, age",
        "from wy_order",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="county", property="county", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="num", property="num", jdbcType=JdbcType.INTEGER),
        @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
        @Result(column="idnumber", property="idnumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="qq", property="qq", jdbcType=JdbcType.VARCHAR),
        @Result(column="wechat", property="wechat", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER)
    })
    WyOrder selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WyOrderSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WyOrder record, @Param("example") WyOrderExample example);

    @UpdateProvider(type=WyOrderSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WyOrder record, @Param("example") WyOrderExample example);

    @UpdateProvider(type=WyOrderSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WyOrder record);

    @Update({
        "update wy_order",
        "set name = #{name,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "price = #{price,jdbcType=DECIMAL},",
          "province = #{province,jdbcType=VARCHAR},",
          "city = #{city,jdbcType=VARCHAR},",
          "county = #{county,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "num = #{num,jdbcType=INTEGER},",
          "number = #{number,jdbcType=INTEGER},",
          "idnumber = #{idnumber,jdbcType=VARCHAR},",
          "qq = #{qq,jdbcType=VARCHAR},",
          "wechat = #{wechat,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "age = #{age,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WyOrder record);
}