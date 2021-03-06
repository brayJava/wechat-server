package com.bray.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WyWechatAuthExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WyWechatAuthExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andWeixinIdIsNull() {
            addCriterion("weixin_id is null");
            return (Criteria) this;
        }

        public Criteria andWeixinIdIsNotNull() {
            addCriterion("weixin_id is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinIdEqualTo(String value) {
            addCriterion("weixin_id =", value, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdNotEqualTo(String value) {
            addCriterion("weixin_id <>", value, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdGreaterThan(String value) {
            addCriterion("weixin_id >", value, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdGreaterThanOrEqualTo(String value) {
            addCriterion("weixin_id >=", value, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdLessThan(String value) {
            addCriterion("weixin_id <", value, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdLessThanOrEqualTo(String value) {
            addCriterion("weixin_id <=", value, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdLike(String value) {
            addCriterion("weixin_id like", value, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdNotLike(String value) {
            addCriterion("weixin_id not like", value, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdIn(List<String> values) {
            addCriterion("weixin_id in", values, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdNotIn(List<String> values) {
            addCriterion("weixin_id not in", values, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdBetween(String value1, String value2) {
            addCriterion("weixin_id between", value1, value2, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdNotBetween(String value1, String value2) {
            addCriterion("weixin_id not between", value1, value2, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinSecretIsNull() {
            addCriterion("weixin_secret is null");
            return (Criteria) this;
        }

        public Criteria andWeixinSecretIsNotNull() {
            addCriterion("weixin_secret is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinSecretEqualTo(String value) {
            addCriterion("weixin_secret =", value, "weixinSecret");
            return (Criteria) this;
        }

        public Criteria andWeixinSecretNotEqualTo(String value) {
            addCriterion("weixin_secret <>", value, "weixinSecret");
            return (Criteria) this;
        }

        public Criteria andWeixinSecretGreaterThan(String value) {
            addCriterion("weixin_secret >", value, "weixinSecret");
            return (Criteria) this;
        }

        public Criteria andWeixinSecretGreaterThanOrEqualTo(String value) {
            addCriterion("weixin_secret >=", value, "weixinSecret");
            return (Criteria) this;
        }

        public Criteria andWeixinSecretLessThan(String value) {
            addCriterion("weixin_secret <", value, "weixinSecret");
            return (Criteria) this;
        }

        public Criteria andWeixinSecretLessThanOrEqualTo(String value) {
            addCriterion("weixin_secret <=", value, "weixinSecret");
            return (Criteria) this;
        }

        public Criteria andWeixinSecretLike(String value) {
            addCriterion("weixin_secret like", value, "weixinSecret");
            return (Criteria) this;
        }

        public Criteria andWeixinSecretNotLike(String value) {
            addCriterion("weixin_secret not like", value, "weixinSecret");
            return (Criteria) this;
        }

        public Criteria andWeixinSecretIn(List<String> values) {
            addCriterion("weixin_secret in", values, "weixinSecret");
            return (Criteria) this;
        }

        public Criteria andWeixinSecretNotIn(List<String> values) {
            addCriterion("weixin_secret not in", values, "weixinSecret");
            return (Criteria) this;
        }

        public Criteria andWeixinSecretBetween(String value1, String value2) {
            addCriterion("weixin_secret between", value1, value2, "weixinSecret");
            return (Criteria) this;
        }

        public Criteria andWeixinSecretNotBetween(String value1, String value2) {
            addCriterion("weixin_secret not between", value1, value2, "weixinSecret");
            return (Criteria) this;
        }

        public Criteria andWeixinCacheIsNull() {
            addCriterion("weixin_cache is null");
            return (Criteria) this;
        }

        public Criteria andWeixinCacheIsNotNull() {
            addCriterion("weixin_cache is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinCacheEqualTo(String value) {
            addCriterion("weixin_cache =", value, "weixinCache");
            return (Criteria) this;
        }

        public Criteria andWeixinCacheNotEqualTo(String value) {
            addCriterion("weixin_cache <>", value, "weixinCache");
            return (Criteria) this;
        }

        public Criteria andWeixinCacheGreaterThan(String value) {
            addCriterion("weixin_cache >", value, "weixinCache");
            return (Criteria) this;
        }

        public Criteria andWeixinCacheGreaterThanOrEqualTo(String value) {
            addCriterion("weixin_cache >=", value, "weixinCache");
            return (Criteria) this;
        }

        public Criteria andWeixinCacheLessThan(String value) {
            addCriterion("weixin_cache <", value, "weixinCache");
            return (Criteria) this;
        }

        public Criteria andWeixinCacheLessThanOrEqualTo(String value) {
            addCriterion("weixin_cache <=", value, "weixinCache");
            return (Criteria) this;
        }

        public Criteria andWeixinCacheLike(String value) {
            addCriterion("weixin_cache like", value, "weixinCache");
            return (Criteria) this;
        }

        public Criteria andWeixinCacheNotLike(String value) {
            addCriterion("weixin_cache not like", value, "weixinCache");
            return (Criteria) this;
        }

        public Criteria andWeixinCacheIn(List<String> values) {
            addCriterion("weixin_cache in", values, "weixinCache");
            return (Criteria) this;
        }

        public Criteria andWeixinCacheNotIn(List<String> values) {
            addCriterion("weixin_cache not in", values, "weixinCache");
            return (Criteria) this;
        }

        public Criteria andWeixinCacheBetween(String value1, String value2) {
            addCriterion("weixin_cache between", value1, value2, "weixinCache");
            return (Criteria) this;
        }

        public Criteria andWeixinCacheNotBetween(String value1, String value2) {
            addCriterion("weixin_cache not between", value1, value2, "weixinCache");
            return (Criteria) this;
        }

        public Criteria andWeixinNameIsNull() {
            addCriterion("weixin_name is null");
            return (Criteria) this;
        }

        public Criteria andWeixinNameIsNotNull() {
            addCriterion("weixin_name is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinNameEqualTo(String value) {
            addCriterion("weixin_name =", value, "weixinName");
            return (Criteria) this;
        }

        public Criteria andWeixinNameNotEqualTo(String value) {
            addCriterion("weixin_name <>", value, "weixinName");
            return (Criteria) this;
        }

        public Criteria andWeixinNameGreaterThan(String value) {
            addCriterion("weixin_name >", value, "weixinName");
            return (Criteria) this;
        }

        public Criteria andWeixinNameGreaterThanOrEqualTo(String value) {
            addCriterion("weixin_name >=", value, "weixinName");
            return (Criteria) this;
        }

        public Criteria andWeixinNameLessThan(String value) {
            addCriterion("weixin_name <", value, "weixinName");
            return (Criteria) this;
        }

        public Criteria andWeixinNameLessThanOrEqualTo(String value) {
            addCriterion("weixin_name <=", value, "weixinName");
            return (Criteria) this;
        }

        public Criteria andWeixinNameLike(String value) {
            addCriterion("weixin_name like", value, "weixinName");
            return (Criteria) this;
        }

        public Criteria andWeixinNameNotLike(String value) {
            addCriterion("weixin_name not like", value, "weixinName");
            return (Criteria) this;
        }

        public Criteria andWeixinNameIn(List<String> values) {
            addCriterion("weixin_name in", values, "weixinName");
            return (Criteria) this;
        }

        public Criteria andWeixinNameNotIn(List<String> values) {
            addCriterion("weixin_name not in", values, "weixinName");
            return (Criteria) this;
        }

        public Criteria andWeixinNameBetween(String value1, String value2) {
            addCriterion("weixin_name between", value1, value2, "weixinName");
            return (Criteria) this;
        }

        public Criteria andWeixinNameNotBetween(String value1, String value2) {
            addCriterion("weixin_name not between", value1, value2, "weixinName");
            return (Criteria) this;
        }

        public Criteria andBindDomainsIsNull() {
            addCriterion("bind_domains is null");
            return (Criteria) this;
        }

        public Criteria andBindDomainsIsNotNull() {
            addCriterion("bind_domains is not null");
            return (Criteria) this;
        }

        public Criteria andBindDomainsEqualTo(String value) {
            addCriterion("bind_domains =", value, "bindDomains");
            return (Criteria) this;
        }

        public Criteria andBindDomainsNotEqualTo(String value) {
            addCriterion("bind_domains <>", value, "bindDomains");
            return (Criteria) this;
        }

        public Criteria andBindDomainsGreaterThan(String value) {
            addCriterion("bind_domains >", value, "bindDomains");
            return (Criteria) this;
        }

        public Criteria andBindDomainsGreaterThanOrEqualTo(String value) {
            addCriterion("bind_domains >=", value, "bindDomains");
            return (Criteria) this;
        }

        public Criteria andBindDomainsLessThan(String value) {
            addCriterion("bind_domains <", value, "bindDomains");
            return (Criteria) this;
        }

        public Criteria andBindDomainsLessThanOrEqualTo(String value) {
            addCriterion("bind_domains <=", value, "bindDomains");
            return (Criteria) this;
        }

        public Criteria andBindDomainsLike(String value) {
            addCriterion("bind_domains like", value, "bindDomains");
            return (Criteria) this;
        }

        public Criteria andBindDomainsNotLike(String value) {
            addCriterion("bind_domains not like", value, "bindDomains");
            return (Criteria) this;
        }

        public Criteria andBindDomainsIn(List<String> values) {
            addCriterion("bind_domains in", values, "bindDomains");
            return (Criteria) this;
        }

        public Criteria andBindDomainsNotIn(List<String> values) {
            addCriterion("bind_domains not in", values, "bindDomains");
            return (Criteria) this;
        }

        public Criteria andBindDomainsBetween(String value1, String value2) {
            addCriterion("bind_domains between", value1, value2, "bindDomains");
            return (Criteria) this;
        }

        public Criteria andBindDomainsNotBetween(String value1, String value2) {
            addCriterion("bind_domains not between", value1, value2, "bindDomains");
            return (Criteria) this;
        }

        public Criteria andDebugIsNull() {
            addCriterion("debug is null");
            return (Criteria) this;
        }

        public Criteria andDebugIsNotNull() {
            addCriterion("debug is not null");
            return (Criteria) this;
        }

        public Criteria andDebugEqualTo(Boolean value) {
            addCriterion("debug =", value, "debug");
            return (Criteria) this;
        }

        public Criteria andDebugNotEqualTo(Boolean value) {
            addCriterion("debug <>", value, "debug");
            return (Criteria) this;
        }

        public Criteria andDebugGreaterThan(Boolean value) {
            addCriterion("debug >", value, "debug");
            return (Criteria) this;
        }

        public Criteria andDebugGreaterThanOrEqualTo(Boolean value) {
            addCriterion("debug >=", value, "debug");
            return (Criteria) this;
        }

        public Criteria andDebugLessThan(Boolean value) {
            addCriterion("debug <", value, "debug");
            return (Criteria) this;
        }

        public Criteria andDebugLessThanOrEqualTo(Boolean value) {
            addCriterion("debug <=", value, "debug");
            return (Criteria) this;
        }

        public Criteria andDebugIn(List<Boolean> values) {
            addCriterion("debug in", values, "debug");
            return (Criteria) this;
        }

        public Criteria andDebugNotIn(List<Boolean> values) {
            addCriterion("debug not in", values, "debug");
            return (Criteria) this;
        }

        public Criteria andDebugBetween(Boolean value1, Boolean value2) {
            addCriterion("debug between", value1, value2, "debug");
            return (Criteria) this;
        }

        public Criteria andDebugNotBetween(Boolean value1, Boolean value2) {
            addCriterion("debug not between", value1, value2, "debug");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}