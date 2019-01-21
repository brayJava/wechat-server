package com.bray.mapper;

import com.bray.excel.modules.XFOrderModule;
import com.bray.model.WyOrder;
import com.bray.model.WyOrderExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
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
        "phone, title, size, ",
        "price, province, ",
        "city, county, address, ",
        "num, number, message, ",
        "idnumber, qq, wechat, ",
        "email, age, status, ",
        "create_time, update_time)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{phone,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, #{size,jdbcType=VARCHAR}, ",
        "#{price,jdbcType=DECIMAL}, #{province,jdbcType=VARCHAR}, ",
        "#{city,jdbcType=VARCHAR}, #{county,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
        "#{num,jdbcType=INTEGER}, #{number,jdbcType=INTEGER}, #{message,jdbcType=VARCHAR}, ",
        "#{idnumber,jdbcType=VARCHAR}, #{qq,jdbcType=VARCHAR}, #{wechat,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(WyOrder record);

    @InsertProvider(type=WyOrderSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertSelective(WyOrder record);

    @SelectProvider(type=WyOrderSqlProvider.class, method="selectByExample")
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
    List<WyOrder> selectByExample(WyOrderExample example);

    @Select({
        "select",
        "id, name, phone, title, size, price, province, city, county, address, num, number, ",
        "message, idnumber, qq, wechat, email, age, status, create_time, update_time",
        "from wy_order",
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
    int updateByPrimaryKey(WyOrder record);
    @Select({
            " SELECT ",
            " '' as orderId, ",
            " '' as consigneeCompany, ",
            " d.`name` as addressee, ",
            " '' AS phone, ",
            " d.phone AS mobilephone, ",
            " CONCAT(d.province,d.city,d.county,d.address) AS address, ",
            " d.size as content, ",
            "         1 AS num, ",
            " 1 AS bgweight, ",
            " '' AS jfbz, ",
            " '寄付月结' AS yfdffs, ",
            " '顺丰特惠' AS type, ",
            " '' AS jnum, ",
            " d.price AS dsmoney, ",
            "         '' AS bjmoney, ",
            " '' AS packingSpecification, ",
            " '' AS gxhSpecification, ",
            " '' AS qhd, ",
            " '' AS zqj, ",
            " '' AS ysx, ",
            " '' AS dzqs, ",
            " '' AS serverfee, ",
            "  '' AS sendintime, ",
            " '' AS psrq, ",
            " '' AS pssd, ",
            " '' AS kczd ",
            "  FROM ",
            " `wy_order` d; "
    })
    @Results({
            @Result(column="orderId", property="orderId", jdbcType=JdbcType.VARCHAR),
            @Result(column="consigneeCompany", property="consigneeCompany", jdbcType=JdbcType.VARCHAR),
            @Result(column="addressee", property="addressee", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="mobilephone", property="mobilephone", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.DECIMAL),
            @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
            @Result(column="num", property="num", jdbcType=JdbcType.INTEGER),
            @Result(column="bgweight", property="bgweight", jdbcType=JdbcType.INTEGER),
            @Result(column="jfbz", property="jfbz", jdbcType=JdbcType.VARCHAR),
            @Result(column="yfdffs", property="yfdffs", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="jnum", property="jnum", jdbcType=JdbcType.VARCHAR),
            @Result(column="idnumber", property="jnum", jdbcType=JdbcType.VARCHAR),
            @Result(column="dsmoney", property="dsmoney", jdbcType=JdbcType.INTEGER),
            @Result(column="bjmoney", property="bjmoney", jdbcType=JdbcType.VARCHAR),
            @Result(column="packingSpecification", property="packingSpecification", jdbcType=JdbcType.VARCHAR),
            @Result(column="gxhSpecification", property="gxhSpecification", jdbcType=JdbcType.VARCHAR),
            @Result(column="qhd", property="qhd", jdbcType=JdbcType.VARCHAR),
            @Result(column="zqj", property="zqj", jdbcType=JdbcType.VARCHAR),
            @Result(column="ysx", property="ysx", jdbcType=JdbcType.VARCHAR),
            @Result(column="dzqs", property="dzqs", jdbcType=JdbcType.VARCHAR),
            @Result(column="serverfee", property="serverfee", jdbcType=JdbcType.VARCHAR),
            @Result(column="sendintime", property="sendintime", jdbcType=JdbcType.VARCHAR),
            @Result(column="psrq", property="psrq", jdbcType=JdbcType.VARCHAR),
            @Result(column="pssd", property="pssd", jdbcType=JdbcType.VARCHAR),
            @Result(column="kczd", property="kczd", jdbcType=JdbcType.VARCHAR)
    })
    List<XFOrderModule> selectOfOrders();
}