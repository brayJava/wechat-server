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

import com.bray.model.WyArticle;
import com.bray.model.WyArticleExample.Criteria;
import com.bray.model.WyArticleExample.Criterion;
import com.bray.model.WyArticleExample;
import java.util.List;
import java.util.Map;

public class WyArticleSqlProvider {

    public String countByExample(WyArticleExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("wy_article");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(WyArticleExample example) {
        BEGIN();
        DELETE_FROM("wy_article");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(WyArticle record) {
        BEGIN();
        INSERT_INTO("wy_article");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getBgmUrl() != null) {
            VALUES("bgm_url", "#{bgmUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthor() != null) {
            VALUES("author", "#{author,jdbcType=VARCHAR}");
        }
        
        if (record.getShareTitle() != null) {
            VALUES("share_title", "#{shareTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getShareDescribe() != null) {
            VALUES("share_describe", "#{shareDescribe,jdbcType=VARCHAR}");
        }
        
        if (record.getIsOrderImg() != null) {
            VALUES("is_order_img", "#{isOrderImg,jdbcType=BIT}");
        }
        
        if (record.getConnectPrimaryDomain() != null) {
            VALUES("connect_primary_domain", "#{connectPrimaryDomain,jdbcType=VARCHAR}");
        }
        
        if (record.getConnectCommonDomain() != null) {
            VALUES("connect_common_domain", "#{connectCommonDomain,jdbcType=VARCHAR}");
        }
        
        if (record.getIsPublish() != null) {
            VALUES("is_publish", "#{isPublish,jdbcType=BIT}");
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

    public String selectByExample(WyArticleExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("title");
        SELECT("bgm_url");
        SELECT("author");
        SELECT("share_title");
        SELECT("share_describe");
        SELECT("is_order_img");
        SELECT("connect_primary_domain");
        SELECT("connect_common_domain");
        SELECT("is_publish");
        SELECT("status");
        SELECT("create_time");
        SELECT("update_time");
        FROM("wy_article");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        WyArticle record = (WyArticle) parameter.get("record");
        WyArticleExample example = (WyArticleExample) parameter.get("example");
        
        BEGIN();
        UPDATE("wy_article");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            SET("title = #{record.title,jdbcType=VARCHAR}");
        }
        
        if (record.getBgmUrl() != null) {
            SET("bgm_url = #{record.bgmUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthor() != null) {
            SET("author = #{record.author,jdbcType=VARCHAR}");
        }
        
        if (record.getShareTitle() != null) {
            SET("share_title = #{record.shareTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getShareDescribe() != null) {
            SET("share_describe = #{record.shareDescribe,jdbcType=VARCHAR}");
        }
        
        if (record.getIsOrderImg() != null) {
            SET("is_order_img = #{record.isOrderImg,jdbcType=BIT}");
        }
        
        if (record.getConnectPrimaryDomain() != null) {
            SET("connect_primary_domain = #{record.connectPrimaryDomain,jdbcType=VARCHAR}");
        }
        
        if (record.getConnectCommonDomain() != null) {
            SET("connect_common_domain = #{record.connectCommonDomain,jdbcType=VARCHAR}");
        }
        
        if (record.getIsPublish() != null) {
            SET("is_publish = #{record.isPublish,jdbcType=BIT}");
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
        UPDATE("wy_article");
        
        SET("id = #{record.id,jdbcType=VARCHAR}");
        SET("title = #{record.title,jdbcType=VARCHAR}");
        SET("bgm_url = #{record.bgmUrl,jdbcType=VARCHAR}");
        SET("author = #{record.author,jdbcType=VARCHAR}");
        SET("share_title = #{record.shareTitle,jdbcType=VARCHAR}");
        SET("share_describe = #{record.shareDescribe,jdbcType=VARCHAR}");
        SET("is_order_img = #{record.isOrderImg,jdbcType=BIT}");
        SET("connect_primary_domain = #{record.connectPrimaryDomain,jdbcType=VARCHAR}");
        SET("connect_common_domain = #{record.connectCommonDomain,jdbcType=VARCHAR}");
        SET("is_publish = #{record.isPublish,jdbcType=BIT}");
        SET("status = #{record.status,jdbcType=INTEGER}");
        SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        
        WyArticleExample example = (WyArticleExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(WyArticle record) {
        BEGIN();
        UPDATE("wy_article");
        
        if (record.getTitle() != null) {
            SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getBgmUrl() != null) {
            SET("bgm_url = #{bgmUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthor() != null) {
            SET("author = #{author,jdbcType=VARCHAR}");
        }
        
        if (record.getShareTitle() != null) {
            SET("share_title = #{shareTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getShareDescribe() != null) {
            SET("share_describe = #{shareDescribe,jdbcType=VARCHAR}");
        }
        
        if (record.getIsOrderImg() != null) {
            SET("is_order_img = #{isOrderImg,jdbcType=BIT}");
        }
        
        if (record.getConnectPrimaryDomain() != null) {
            SET("connect_primary_domain = #{connectPrimaryDomain,jdbcType=VARCHAR}");
        }
        
        if (record.getConnectCommonDomain() != null) {
            SET("connect_common_domain = #{connectCommonDomain,jdbcType=VARCHAR}");
        }
        
        if (record.getIsPublish() != null) {
            SET("is_publish = #{isPublish,jdbcType=BIT}");
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
        
        WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return SQL();
    }

    protected void applyWhere(WyArticleExample example, boolean includeExamplePhrase) {
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