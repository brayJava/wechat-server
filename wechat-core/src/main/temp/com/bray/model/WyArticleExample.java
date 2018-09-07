package com.bray.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WyArticleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WyArticleExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andBgmUrlIsNull() {
            addCriterion("bgm_url is null");
            return (Criteria) this;
        }

        public Criteria andBgmUrlIsNotNull() {
            addCriterion("bgm_url is not null");
            return (Criteria) this;
        }

        public Criteria andBgmUrlEqualTo(String value) {
            addCriterion("bgm_url =", value, "bgmUrl");
            return (Criteria) this;
        }

        public Criteria andBgmUrlNotEqualTo(String value) {
            addCriterion("bgm_url <>", value, "bgmUrl");
            return (Criteria) this;
        }

        public Criteria andBgmUrlGreaterThan(String value) {
            addCriterion("bgm_url >", value, "bgmUrl");
            return (Criteria) this;
        }

        public Criteria andBgmUrlGreaterThanOrEqualTo(String value) {
            addCriterion("bgm_url >=", value, "bgmUrl");
            return (Criteria) this;
        }

        public Criteria andBgmUrlLessThan(String value) {
            addCriterion("bgm_url <", value, "bgmUrl");
            return (Criteria) this;
        }

        public Criteria andBgmUrlLessThanOrEqualTo(String value) {
            addCriterion("bgm_url <=", value, "bgmUrl");
            return (Criteria) this;
        }

        public Criteria andBgmUrlLike(String value) {
            addCriterion("bgm_url like", value, "bgmUrl");
            return (Criteria) this;
        }

        public Criteria andBgmUrlNotLike(String value) {
            addCriterion("bgm_url not like", value, "bgmUrl");
            return (Criteria) this;
        }

        public Criteria andBgmUrlIn(List<String> values) {
            addCriterion("bgm_url in", values, "bgmUrl");
            return (Criteria) this;
        }

        public Criteria andBgmUrlNotIn(List<String> values) {
            addCriterion("bgm_url not in", values, "bgmUrl");
            return (Criteria) this;
        }

        public Criteria andBgmUrlBetween(String value1, String value2) {
            addCriterion("bgm_url between", value1, value2, "bgmUrl");
            return (Criteria) this;
        }

        public Criteria andBgmUrlNotBetween(String value1, String value2) {
            addCriterion("bgm_url not between", value1, value2, "bgmUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andShareTitleIsNull() {
            addCriterion("share_title is null");
            return (Criteria) this;
        }

        public Criteria andShareTitleIsNotNull() {
            addCriterion("share_title is not null");
            return (Criteria) this;
        }

        public Criteria andShareTitleEqualTo(String value) {
            addCriterion("share_title =", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleNotEqualTo(String value) {
            addCriterion("share_title <>", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleGreaterThan(String value) {
            addCriterion("share_title >", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleGreaterThanOrEqualTo(String value) {
            addCriterion("share_title >=", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleLessThan(String value) {
            addCriterion("share_title <", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleLessThanOrEqualTo(String value) {
            addCriterion("share_title <=", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleLike(String value) {
            addCriterion("share_title like", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleNotLike(String value) {
            addCriterion("share_title not like", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleIn(List<String> values) {
            addCriterion("share_title in", values, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleNotIn(List<String> values) {
            addCriterion("share_title not in", values, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleBetween(String value1, String value2) {
            addCriterion("share_title between", value1, value2, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleNotBetween(String value1, String value2) {
            addCriterion("share_title not between", value1, value2, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareDescribeIsNull() {
            addCriterion("share_describe is null");
            return (Criteria) this;
        }

        public Criteria andShareDescribeIsNotNull() {
            addCriterion("share_describe is not null");
            return (Criteria) this;
        }

        public Criteria andShareDescribeEqualTo(String value) {
            addCriterion("share_describe =", value, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeNotEqualTo(String value) {
            addCriterion("share_describe <>", value, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeGreaterThan(String value) {
            addCriterion("share_describe >", value, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("share_describe >=", value, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeLessThan(String value) {
            addCriterion("share_describe <", value, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeLessThanOrEqualTo(String value) {
            addCriterion("share_describe <=", value, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeLike(String value) {
            addCriterion("share_describe like", value, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeNotLike(String value) {
            addCriterion("share_describe not like", value, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeIn(List<String> values) {
            addCriterion("share_describe in", values, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeNotIn(List<String> values) {
            addCriterion("share_describe not in", values, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeBetween(String value1, String value2) {
            addCriterion("share_describe between", value1, value2, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeNotBetween(String value1, String value2) {
            addCriterion("share_describe not between", value1, value2, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareImgUrlIsNull() {
            addCriterion("share_img_url is null");
            return (Criteria) this;
        }

        public Criteria andShareImgUrlIsNotNull() {
            addCriterion("share_img_url is not null");
            return (Criteria) this;
        }

        public Criteria andShareImgUrlEqualTo(String value) {
            addCriterion("share_img_url =", value, "shareImgUrl");
            return (Criteria) this;
        }

        public Criteria andShareImgUrlNotEqualTo(String value) {
            addCriterion("share_img_url <>", value, "shareImgUrl");
            return (Criteria) this;
        }

        public Criteria andShareImgUrlGreaterThan(String value) {
            addCriterion("share_img_url >", value, "shareImgUrl");
            return (Criteria) this;
        }

        public Criteria andShareImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("share_img_url >=", value, "shareImgUrl");
            return (Criteria) this;
        }

        public Criteria andShareImgUrlLessThan(String value) {
            addCriterion("share_img_url <", value, "shareImgUrl");
            return (Criteria) this;
        }

        public Criteria andShareImgUrlLessThanOrEqualTo(String value) {
            addCriterion("share_img_url <=", value, "shareImgUrl");
            return (Criteria) this;
        }

        public Criteria andShareImgUrlLike(String value) {
            addCriterion("share_img_url like", value, "shareImgUrl");
            return (Criteria) this;
        }

        public Criteria andShareImgUrlNotLike(String value) {
            addCriterion("share_img_url not like", value, "shareImgUrl");
            return (Criteria) this;
        }

        public Criteria andShareImgUrlIn(List<String> values) {
            addCriterion("share_img_url in", values, "shareImgUrl");
            return (Criteria) this;
        }

        public Criteria andShareImgUrlNotIn(List<String> values) {
            addCriterion("share_img_url not in", values, "shareImgUrl");
            return (Criteria) this;
        }

        public Criteria andShareImgUrlBetween(String value1, String value2) {
            addCriterion("share_img_url between", value1, value2, "shareImgUrl");
            return (Criteria) this;
        }

        public Criteria andShareImgUrlNotBetween(String value1, String value2) {
            addCriterion("share_img_url not between", value1, value2, "shareImgUrl");
            return (Criteria) this;
        }

        public Criteria andIsOrderImgIsNull() {
            addCriterion("is_order_img is null");
            return (Criteria) this;
        }

        public Criteria andIsOrderImgIsNotNull() {
            addCriterion("is_order_img is not null");
            return (Criteria) this;
        }

        public Criteria andIsOrderImgEqualTo(Boolean value) {
            addCriterion("is_order_img =", value, "isOrderImg");
            return (Criteria) this;
        }

        public Criteria andIsOrderImgNotEqualTo(Boolean value) {
            addCriterion("is_order_img <>", value, "isOrderImg");
            return (Criteria) this;
        }

        public Criteria andIsOrderImgGreaterThan(Boolean value) {
            addCriterion("is_order_img >", value, "isOrderImg");
            return (Criteria) this;
        }

        public Criteria andIsOrderImgGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_order_img >=", value, "isOrderImg");
            return (Criteria) this;
        }

        public Criteria andIsOrderImgLessThan(Boolean value) {
            addCriterion("is_order_img <", value, "isOrderImg");
            return (Criteria) this;
        }

        public Criteria andIsOrderImgLessThanOrEqualTo(Boolean value) {
            addCriterion("is_order_img <=", value, "isOrderImg");
            return (Criteria) this;
        }

        public Criteria andIsOrderImgIn(List<Boolean> values) {
            addCriterion("is_order_img in", values, "isOrderImg");
            return (Criteria) this;
        }

        public Criteria andIsOrderImgNotIn(List<Boolean> values) {
            addCriterion("is_order_img not in", values, "isOrderImg");
            return (Criteria) this;
        }

        public Criteria andIsOrderImgBetween(Boolean value1, Boolean value2) {
            addCriterion("is_order_img between", value1, value2, "isOrderImg");
            return (Criteria) this;
        }

        public Criteria andIsOrderImgNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_order_img not between", value1, value2, "isOrderImg");
            return (Criteria) this;
        }

        public Criteria andConnectPrimaryDomainIsNull() {
            addCriterion("connect_primary_domain is null");
            return (Criteria) this;
        }

        public Criteria andConnectPrimaryDomainIsNotNull() {
            addCriterion("connect_primary_domain is not null");
            return (Criteria) this;
        }

        public Criteria andConnectPrimaryDomainEqualTo(String value) {
            addCriterion("connect_primary_domain =", value, "connectPrimaryDomain");
            return (Criteria) this;
        }

        public Criteria andConnectPrimaryDomainNotEqualTo(String value) {
            addCriterion("connect_primary_domain <>", value, "connectPrimaryDomain");
            return (Criteria) this;
        }

        public Criteria andConnectPrimaryDomainGreaterThan(String value) {
            addCriterion("connect_primary_domain >", value, "connectPrimaryDomain");
            return (Criteria) this;
        }

        public Criteria andConnectPrimaryDomainGreaterThanOrEqualTo(String value) {
            addCriterion("connect_primary_domain >=", value, "connectPrimaryDomain");
            return (Criteria) this;
        }

        public Criteria andConnectPrimaryDomainLessThan(String value) {
            addCriterion("connect_primary_domain <", value, "connectPrimaryDomain");
            return (Criteria) this;
        }

        public Criteria andConnectPrimaryDomainLessThanOrEqualTo(String value) {
            addCriterion("connect_primary_domain <=", value, "connectPrimaryDomain");
            return (Criteria) this;
        }

        public Criteria andConnectPrimaryDomainLike(String value) {
            addCriterion("connect_primary_domain like", value, "connectPrimaryDomain");
            return (Criteria) this;
        }

        public Criteria andConnectPrimaryDomainNotLike(String value) {
            addCriterion("connect_primary_domain not like", value, "connectPrimaryDomain");
            return (Criteria) this;
        }

        public Criteria andConnectPrimaryDomainIn(List<String> values) {
            addCriterion("connect_primary_domain in", values, "connectPrimaryDomain");
            return (Criteria) this;
        }

        public Criteria andConnectPrimaryDomainNotIn(List<String> values) {
            addCriterion("connect_primary_domain not in", values, "connectPrimaryDomain");
            return (Criteria) this;
        }

        public Criteria andConnectPrimaryDomainBetween(String value1, String value2) {
            addCriterion("connect_primary_domain between", value1, value2, "connectPrimaryDomain");
            return (Criteria) this;
        }

        public Criteria andConnectPrimaryDomainNotBetween(String value1, String value2) {
            addCriterion("connect_primary_domain not between", value1, value2, "connectPrimaryDomain");
            return (Criteria) this;
        }

        public Criteria andConnectCommonDomainIsNull() {
            addCriterion("connect_common_domain is null");
            return (Criteria) this;
        }

        public Criteria andConnectCommonDomainIsNotNull() {
            addCriterion("connect_common_domain is not null");
            return (Criteria) this;
        }

        public Criteria andConnectCommonDomainEqualTo(String value) {
            addCriterion("connect_common_domain =", value, "connectCommonDomain");
            return (Criteria) this;
        }

        public Criteria andConnectCommonDomainNotEqualTo(String value) {
            addCriterion("connect_common_domain <>", value, "connectCommonDomain");
            return (Criteria) this;
        }

        public Criteria andConnectCommonDomainGreaterThan(String value) {
            addCriterion("connect_common_domain >", value, "connectCommonDomain");
            return (Criteria) this;
        }

        public Criteria andConnectCommonDomainGreaterThanOrEqualTo(String value) {
            addCriterion("connect_common_domain >=", value, "connectCommonDomain");
            return (Criteria) this;
        }

        public Criteria andConnectCommonDomainLessThan(String value) {
            addCriterion("connect_common_domain <", value, "connectCommonDomain");
            return (Criteria) this;
        }

        public Criteria andConnectCommonDomainLessThanOrEqualTo(String value) {
            addCriterion("connect_common_domain <=", value, "connectCommonDomain");
            return (Criteria) this;
        }

        public Criteria andConnectCommonDomainLike(String value) {
            addCriterion("connect_common_domain like", value, "connectCommonDomain");
            return (Criteria) this;
        }

        public Criteria andConnectCommonDomainNotLike(String value) {
            addCriterion("connect_common_domain not like", value, "connectCommonDomain");
            return (Criteria) this;
        }

        public Criteria andConnectCommonDomainIn(List<String> values) {
            addCriterion("connect_common_domain in", values, "connectCommonDomain");
            return (Criteria) this;
        }

        public Criteria andConnectCommonDomainNotIn(List<String> values) {
            addCriterion("connect_common_domain not in", values, "connectCommonDomain");
            return (Criteria) this;
        }

        public Criteria andConnectCommonDomainBetween(String value1, String value2) {
            addCriterion("connect_common_domain between", value1, value2, "connectCommonDomain");
            return (Criteria) this;
        }

        public Criteria andConnectCommonDomainNotBetween(String value1, String value2) {
            addCriterion("connect_common_domain not between", value1, value2, "connectCommonDomain");
            return (Criteria) this;
        }

        public Criteria andIsPublishIsNull() {
            addCriterion("is_publish is null");
            return (Criteria) this;
        }

        public Criteria andIsPublishIsNotNull() {
            addCriterion("is_publish is not null");
            return (Criteria) this;
        }

        public Criteria andIsPublishEqualTo(Boolean value) {
            addCriterion("is_publish =", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishNotEqualTo(Boolean value) {
            addCriterion("is_publish <>", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishGreaterThan(Boolean value) {
            addCriterion("is_publish >", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_publish >=", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishLessThan(Boolean value) {
            addCriterion("is_publish <", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishLessThanOrEqualTo(Boolean value) {
            addCriterion("is_publish <=", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishIn(List<Boolean> values) {
            addCriterion("is_publish in", values, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishNotIn(List<Boolean> values) {
            addCriterion("is_publish not in", values, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishBetween(Boolean value1, Boolean value2) {
            addCriterion("is_publish between", value1, value2, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_publish not between", value1, value2, "isPublish");
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