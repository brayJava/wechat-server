package com.bray.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.bray.model.WyOrder;
import com.bray.model.WyOrderExample.Criteria;
import com.bray.model.WyOrderExample.Criterion;
import com.bray.model.WyOrderExample;
import java.util.List;
import java.util.Map;

public class WyOrderSqlProvider {

    public String countByExample(WyOrderExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("wy_order");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(WyOrderExample example) {
        BEGIN();
        DELETE_FROM("wy_order");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(WyOrder record) {
        BEGIN();
        INSERT_INTO("wy_order");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            VALUES("phone", "#{phone,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getSize() != null) {
            VALUES("size", "#{size,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            VALUES("price", "#{price,jdbcType=DECIMAL}");
        }
        
        if (record.getProvince() != null) {
            VALUES("province", "#{province,jdbcType=VARCHAR}");
        }
        
        if (record.getCity() != null) {
            VALUES("city", "#{city,jdbcType=VARCHAR}");
        }
        
        if (record.getCounty() != null) {
            VALUES("county", "#{county,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            VALUES("address", "#{address,jdbcType=VARCHAR}");
        }
        
        if (record.getNum() != null) {
            VALUES("num", "#{num,jdbcType=INTEGER}");
        }
        
        if (record.getNumber() != null) {
            VALUES("number", "#{number,jdbcType=INTEGER}");
        }
        
        if (record.getMessage() != null) {
            VALUES("message", "#{message,jdbcType=VARCHAR}");
        }
        
        if (record.getIdnumber() != null) {
            VALUES("idnumber", "#{idnumber,jdbcType=VARCHAR}");
        }
        
        if (record.getQq() != null) {
            VALUES("qq", "#{qq,jdbcType=VARCHAR}");
        }
        
        if (record.getWechat() != null) {
            VALUES("wechat", "#{wechat,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            VALUES("email", "#{email,jdbcType=VARCHAR}");
        }
        
        if (record.getAge() != null) {
            VALUES("age", "#{age,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            VALUES("status", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String selectByExample(WyOrderExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("name");
        SELECT("phone");
        SELECT("title");
        SELECT("size");
        SELECT("price");
        SELECT("province");
        SELECT("city");
        SELECT("county");
        SELECT("address");
        SELECT("num");
        SELECT("number");
        SELECT("message");
        SELECT("idnumber");
        SELECT("qq");
        SELECT("wechat");
        SELECT("email");
        SELECT("age");
        SELECT("status");
        SELECT("create_time");
        SELECT("update_time");
        FROM("wy_order");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        WyOrder record = (WyOrder) parameter.get("record");
        WyOrderExample example = (WyOrderExample) parameter.get("example");
        
        BEGIN();
        UPDATE("wy_order");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            SET("name = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            SET("phone = #{record.phone,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            SET("title = #{record.title,jdbcType=VARCHAR}");
        }
        
        if (record.getSize() != null) {
            SET("size = #{record.size,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            SET("price = #{record.price,jdbcType=DECIMAL}");
        }
        
        if (record.getProvince() != null) {
            SET("province = #{record.province,jdbcType=VARCHAR}");
        }
        
        if (record.getCity() != null) {
            SET("city = #{record.city,jdbcType=VARCHAR}");
        }
        
        if (record.getCounty() != null) {
            SET("county = #{record.county,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            SET("address = #{record.address,jdbcType=VARCHAR}");
        }
        
        if (record.getNum() != null) {
            SET("num = #{record.num,jdbcType=INTEGER}");
        }
        
        if (record.getNumber() != null) {
            SET("number = #{record.number,jdbcType=INTEGER}");
        }
        
        if (record.getMessage() != null) {
            SET("message = #{record.message,jdbcType=VARCHAR}");
        }
        
        if (record.getIdnumber() != null) {
            SET("idnumber = #{record.idnumber,jdbcType=VARCHAR}");
        }
        
        if (record.getQq() != null) {
            SET("qq = #{record.qq,jdbcType=VARCHAR}");
        }
        
        if (record.getWechat() != null) {
            SET("wechat = #{record.wechat,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            SET("email = #{record.email,jdbcType=VARCHAR}");
        }
        
        if (record.getAge() != null) {
            SET("age = #{record.age,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            SET("status = #{record.status,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("wy_order");
        
        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("name = #{record.name,jdbcType=VARCHAR}");
        SET("phone = #{record.phone,jdbcType=VARCHAR}");
        SET("title = #{record.title,jdbcType=VARCHAR}");
        SET("size = #{record.size,jdbcType=VARCHAR}");
        SET("price = #{record.price,jdbcType=DECIMAL}");
        SET("province = #{record.province,jdbcType=VARCHAR}");
        SET("city = #{record.city,jdbcType=VARCHAR}");
        SET("county = #{record.county,jdbcType=VARCHAR}");
        SET("address = #{record.address,jdbcType=VARCHAR}");
        SET("num = #{record.num,jdbcType=INTEGER}");
        SET("number = #{record.number,jdbcType=INTEGER}");
        SET("message = #{record.message,jdbcType=VARCHAR}");
        SET("idnumber = #{record.idnumber,jdbcType=VARCHAR}");
        SET("qq = #{record.qq,jdbcType=VARCHAR}");
        SET("wechat = #{record.wechat,jdbcType=VARCHAR}");
        SET("email = #{record.email,jdbcType=VARCHAR}");
        SET("age = #{record.age,jdbcType=INTEGER}");
        SET("status = #{record.status,jdbcType=INTEGER}");
        SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        
        WyOrderExample example = (WyOrderExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(WyOrder record) {
        BEGIN();
        UPDATE("wy_order");
        
        if (record.getName() != null) {
            SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            SET("phone = #{phone,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getSize() != null) {
            SET("size = #{size,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            SET("price = #{price,jdbcType=DECIMAL}");
        }
        
        if (record.getProvince() != null) {
            SET("province = #{province,jdbcType=VARCHAR}");
        }
        
        if (record.getCity() != null) {
            SET("city = #{city,jdbcType=VARCHAR}");
        }
        
        if (record.getCounty() != null) {
            SET("county = #{county,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            SET("address = #{address,jdbcType=VARCHAR}");
        }
        
        if (record.getNum() != null) {
            SET("num = #{num,jdbcType=INTEGER}");
        }
        
        if (record.getNumber() != null) {
            SET("number = #{number,jdbcType=INTEGER}");
        }
        
        if (record.getMessage() != null) {
            SET("message = #{message,jdbcType=VARCHAR}");
        }
        
        if (record.getIdnumber() != null) {
            SET("idnumber = #{idnumber,jdbcType=VARCHAR}");
        }
        
        if (record.getQq() != null) {
            SET("qq = #{qq,jdbcType=VARCHAR}");
        }
        
        if (record.getWechat() != null) {
            SET("wechat = #{wechat,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            SET("email = #{email,jdbcType=VARCHAR}");
        }
        
        if (record.getAge() != null) {
            SET("age = #{age,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            SET("status = #{status,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }

    protected void applyWhere(WyOrderExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}