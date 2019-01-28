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

        public Criteria andDataTransferUrlIsNull() {
            addCriterion("data_transfer_url is null");
            return (Criteria) this;
        }

        public Criteria andDataTransferUrlIsNotNull() {
            addCriterion("data_transfer_url is not null");
            return (Criteria) this;
        }

        public Criteria andDataTransferUrlEqualTo(String value) {
            addCriterion("data_transfer_url =", value, "dataTransferUrl");
            return (Criteria) this;
        }

        public Criteria andDataTransferUrlNotEqualTo(String value) {
            addCriterion("data_transfer_url <>", value, "dataTransferUrl");
            return (Criteria) this;
        }

        public Criteria andDataTransferUrlGreaterThan(String value) {
            addCriterion("data_transfer_url >", value, "dataTransferUrl");
            return (Criteria) this;
        }

        public Criteria andDataTransferUrlGreaterThanOrEqualTo(String value) {
            addCriterion("data_transfer_url >=", value, "dataTransferUrl");
            return (Criteria) this;
        }

        public Criteria andDataTransferUrlLessThan(String value) {
            addCriterion("data_transfer_url <", value, "dataTransferUrl");
            return (Criteria) this;
        }

        public Criteria andDataTransferUrlLessThanOrEqualTo(String value) {
            addCriterion("data_transfer_url <=", value, "dataTransferUrl");
            return (Criteria) this;
        }

        public Criteria andDataTransferUrlLike(String value) {
            addCriterion("data_transfer_url like", value, "dataTransferUrl");
            return (Criteria) this;
        }

        public Criteria andDataTransferUrlNotLike(String value) {
            addCriterion("data_transfer_url not like", value, "dataTransferUrl");
            return (Criteria) this;
        }

        public Criteria andDataTransferUrlIn(List<String> values) {
            addCriterion("data_transfer_url in", values, "dataTransferUrl");
            return (Criteria) this;
        }

        public Criteria andDataTransferUrlNotIn(List<String> values) {
            addCriterion("data_transfer_url not in", values, "dataTransferUrl");
            return (Criteria) this;
        }

        public Criteria andDataTransferUrlBetween(String value1, String value2) {
            addCriterion("data_transfer_url between", value1, value2, "dataTransferUrl");
            return (Criteria) this;
        }

        public Criteria andDataTransferUrlNotBetween(String value1, String value2) {
            addCriterion("data_transfer_url not between", value1, value2, "dataTransferUrl");
            return (Criteria) this;
        }

        public Criteria andGobackUrlIsNull() {
            addCriterion("goback_url is null");
            return (Criteria) this;
        }

        public Criteria andGobackUrlIsNotNull() {
            addCriterion("goback_url is not null");
            return (Criteria) this;
        }

        public Criteria andGobackUrlEqualTo(String value) {
            addCriterion("goback_url =", value, "gobackUrl");
            return (Criteria) this;
        }

        public Criteria andGobackUrlNotEqualTo(String value) {
            addCriterion("goback_url <>", value, "gobackUrl");
            return (Criteria) this;
        }

        public Criteria andGobackUrlGreaterThan(String value) {
            addCriterion("goback_url >", value, "gobackUrl");
            return (Criteria) this;
        }

        public Criteria andGobackUrlGreaterThanOrEqualTo(String value) {
            addCriterion("goback_url >=", value, "gobackUrl");
            return (Criteria) this;
        }

        public Criteria andGobackUrlLessThan(String value) {
            addCriterion("goback_url <", value, "gobackUrl");
            return (Criteria) this;
        }

        public Criteria andGobackUrlLessThanOrEqualTo(String value) {
            addCriterion("goback_url <=", value, "gobackUrl");
            return (Criteria) this;
        }

        public Criteria andGobackUrlLike(String value) {
            addCriterion("goback_url like", value, "gobackUrl");
            return (Criteria) this;
        }

        public Criteria andGobackUrlNotLike(String value) {
            addCriterion("goback_url not like", value, "gobackUrl");
            return (Criteria) this;
        }

        public Criteria andGobackUrlIn(List<String> values) {
            addCriterion("goback_url in", values, "gobackUrl");
            return (Criteria) this;
        }

        public Criteria andGobackUrlNotIn(List<String> values) {
            addCriterion("goback_url not in", values, "gobackUrl");
            return (Criteria) this;
        }

        public Criteria andGobackUrlBetween(String value1, String value2) {
            addCriterion("goback_url between", value1, value2, "gobackUrl");
            return (Criteria) this;
        }

        public Criteria andGobackUrlNotBetween(String value1, String value2) {
            addCriterion("goback_url not between", value1, value2, "gobackUrl");
            return (Criteria) this;
        }

        public Criteria andReadOriginalUrlIsNull() {
            addCriterion("read_original_url is null");
            return (Criteria) this;
        }

        public Criteria andReadOriginalUrlIsNotNull() {
            addCriterion("read_original_url is not null");
            return (Criteria) this;
        }

        public Criteria andReadOriginalUrlEqualTo(String value) {
            addCriterion("read_original_url =", value, "readOriginalUrl");
            return (Criteria) this;
        }

        public Criteria andReadOriginalUrlNotEqualTo(String value) {
            addCriterion("read_original_url <>", value, "readOriginalUrl");
            return (Criteria) this;
        }

        public Criteria andReadOriginalUrlGreaterThan(String value) {
            addCriterion("read_original_url >", value, "readOriginalUrl");
            return (Criteria) this;
        }

        public Criteria andReadOriginalUrlGreaterThanOrEqualTo(String value) {
            addCriterion("read_original_url >=", value, "readOriginalUrl");
            return (Criteria) this;
        }

        public Criteria andReadOriginalUrlLessThan(String value) {
            addCriterion("read_original_url <", value, "readOriginalUrl");
            return (Criteria) this;
        }

        public Criteria andReadOriginalUrlLessThanOrEqualTo(String value) {
            addCriterion("read_original_url <=", value, "readOriginalUrl");
            return (Criteria) this;
        }

        public Criteria andReadOriginalUrlLike(String value) {
            addCriterion("read_original_url like", value, "readOriginalUrl");
            return (Criteria) this;
        }

        public Criteria andReadOriginalUrlNotLike(String value) {
            addCriterion("read_original_url not like", value, "readOriginalUrl");
            return (Criteria) this;
        }

        public Criteria andReadOriginalUrlIn(List<String> values) {
            addCriterion("read_original_url in", values, "readOriginalUrl");
            return (Criteria) this;
        }

        public Criteria andReadOriginalUrlNotIn(List<String> values) {
            addCriterion("read_original_url not in", values, "readOriginalUrl");
            return (Criteria) this;
        }

        public Criteria andReadOriginalUrlBetween(String value1, String value2) {
            addCriterion("read_original_url between", value1, value2, "readOriginalUrl");
            return (Criteria) this;
        }

        public Criteria andReadOriginalUrlNotBetween(String value1, String value2) {
            addCriterion("read_original_url not between", value1, value2, "readOriginalUrl");
            return (Criteria) this;
        }

        public Criteria andImageGobackUrlIsNull() {
            addCriterion("image_goback_url is null");
            return (Criteria) this;
        }

        public Criteria andImageGobackUrlIsNotNull() {
            addCriterion("image_goback_url is not null");
            return (Criteria) this;
        }

        public Criteria andImageGobackUrlEqualTo(String value) {
            addCriterion("image_goback_url =", value, "imageGobackUrl");
            return (Criteria) this;
        }

        public Criteria andImageGobackUrlNotEqualTo(String value) {
            addCriterion("image_goback_url <>", value, "imageGobackUrl");
            return (Criteria) this;
        }

        public Criteria andImageGobackUrlGreaterThan(String value) {
            addCriterion("image_goback_url >", value, "imageGobackUrl");
            return (Criteria) this;
        }

        public Criteria andImageGobackUrlGreaterThanOrEqualTo(String value) {
            addCriterion("image_goback_url >=", value, "imageGobackUrl");
            return (Criteria) this;
        }

        public Criteria andImageGobackUrlLessThan(String value) {
            addCriterion("image_goback_url <", value, "imageGobackUrl");
            return (Criteria) this;
        }

        public Criteria andImageGobackUrlLessThanOrEqualTo(String value) {
            addCriterion("image_goback_url <=", value, "imageGobackUrl");
            return (Criteria) this;
        }

        public Criteria andImageGobackUrlLike(String value) {
            addCriterion("image_goback_url like", value, "imageGobackUrl");
            return (Criteria) this;
        }

        public Criteria andImageGobackUrlNotLike(String value) {
            addCriterion("image_goback_url not like", value, "imageGobackUrl");
            return (Criteria) this;
        }

        public Criteria andImageGobackUrlIn(List<String> values) {
            addCriterion("image_goback_url in", values, "imageGobackUrl");
            return (Criteria) this;
        }

        public Criteria andImageGobackUrlNotIn(List<String> values) {
            addCriterion("image_goback_url not in", values, "imageGobackUrl");
            return (Criteria) this;
        }

        public Criteria andImageGobackUrlBetween(String value1, String value2) {
            addCriterion("image_goback_url between", value1, value2, "imageGobackUrl");
            return (Criteria) this;
        }

        public Criteria andImageGobackUrlNotBetween(String value1, String value2) {
            addCriterion("image_goback_url not between", value1, value2, "imageGobackUrl");
            return (Criteria) this;
        }

        public Criteria andOrderImgUrlIsNull() {
            addCriterion("order_img_url is null");
            return (Criteria) this;
        }

        public Criteria andOrderImgUrlIsNotNull() {
            addCriterion("order_img_url is not null");
            return (Criteria) this;
        }

        public Criteria andOrderImgUrlEqualTo(String value) {
            addCriterion("order_img_url =", value, "orderImgUrl");
            return (Criteria) this;
        }

        public Criteria andOrderImgUrlNotEqualTo(String value) {
            addCriterion("order_img_url <>", value, "orderImgUrl");
            return (Criteria) this;
        }

        public Criteria andOrderImgUrlGreaterThan(String value) {
            addCriterion("order_img_url >", value, "orderImgUrl");
            return (Criteria) this;
        }

        public Criteria andOrderImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("order_img_url >=", value, "orderImgUrl");
            return (Criteria) this;
        }

        public Criteria andOrderImgUrlLessThan(String value) {
            addCriterion("order_img_url <", value, "orderImgUrl");
            return (Criteria) this;
        }

        public Criteria andOrderImgUrlLessThanOrEqualTo(String value) {
            addCriterion("order_img_url <=", value, "orderImgUrl");
            return (Criteria) this;
        }

        public Criteria andOrderImgUrlLike(String value) {
            addCriterion("order_img_url like", value, "orderImgUrl");
            return (Criteria) this;
        }

        public Criteria andOrderImgUrlNotLike(String value) {
            addCriterion("order_img_url not like", value, "orderImgUrl");
            return (Criteria) this;
        }

        public Criteria andOrderImgUrlIn(List<String> values) {
            addCriterion("order_img_url in", values, "orderImgUrl");
            return (Criteria) this;
        }

        public Criteria andOrderImgUrlNotIn(List<String> values) {
            addCriterion("order_img_url not in", values, "orderImgUrl");
            return (Criteria) this;
        }

        public Criteria andOrderImgUrlBetween(String value1, String value2) {
            addCriterion("order_img_url between", value1, value2, "orderImgUrl");
            return (Criteria) this;
        }

        public Criteria andOrderImgUrlNotBetween(String value1, String value2) {
            addCriterion("order_img_url not between", value1, value2, "orderImgUrl");
            return (Criteria) this;
        }

        public Criteria andOrderCopyUrlIsNull() {
            addCriterion("order_copy_url is null");
            return (Criteria) this;
        }

        public Criteria andOrderCopyUrlIsNotNull() {
            addCriterion("order_copy_url is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCopyUrlEqualTo(String value) {
            addCriterion("order_copy_url =", value, "orderCopyUrl");
            return (Criteria) this;
        }

        public Criteria andOrderCopyUrlNotEqualTo(String value) {
            addCriterion("order_copy_url <>", value, "orderCopyUrl");
            return (Criteria) this;
        }

        public Criteria andOrderCopyUrlGreaterThan(String value) {
            addCriterion("order_copy_url >", value, "orderCopyUrl");
            return (Criteria) this;
        }

        public Criteria andOrderCopyUrlGreaterThanOrEqualTo(String value) {
            addCriterion("order_copy_url >=", value, "orderCopyUrl");
            return (Criteria) this;
        }

        public Criteria andOrderCopyUrlLessThan(String value) {
            addCriterion("order_copy_url <", value, "orderCopyUrl");
            return (Criteria) this;
        }

        public Criteria andOrderCopyUrlLessThanOrEqualTo(String value) {
            addCriterion("order_copy_url <=", value, "orderCopyUrl");
            return (Criteria) this;
        }

        public Criteria andOrderCopyUrlLike(String value) {
            addCriterion("order_copy_url like", value, "orderCopyUrl");
            return (Criteria) this;
        }

        public Criteria andOrderCopyUrlNotLike(String value) {
            addCriterion("order_copy_url not like", value, "orderCopyUrl");
            return (Criteria) this;
        }

        public Criteria andOrderCopyUrlIn(List<String> values) {
            addCriterion("order_copy_url in", values, "orderCopyUrl");
            return (Criteria) this;
        }

        public Criteria andOrderCopyUrlNotIn(List<String> values) {
            addCriterion("order_copy_url not in", values, "orderCopyUrl");
            return (Criteria) this;
        }

        public Criteria andOrderCopyUrlBetween(String value1, String value2) {
            addCriterion("order_copy_url between", value1, value2, "orderCopyUrl");
            return (Criteria) this;
        }

        public Criteria andOrderCopyUrlNotBetween(String value1, String value2) {
            addCriterion("order_copy_url not between", value1, value2, "orderCopyUrl");
            return (Criteria) this;
        }

        public Criteria andShareSuccessFailUrlIsNull() {
            addCriterion("share_success_fail_url is null");
            return (Criteria) this;
        }

        public Criteria andShareSuccessFailUrlIsNotNull() {
            addCriterion("share_success_fail_url is not null");
            return (Criteria) this;
        }

        public Criteria andShareSuccessFailUrlEqualTo(String value) {
            addCriterion("share_success_fail_url =", value, "shareSuccessFailUrl");
            return (Criteria) this;
        }

        public Criteria andShareSuccessFailUrlNotEqualTo(String value) {
            addCriterion("share_success_fail_url <>", value, "shareSuccessFailUrl");
            return (Criteria) this;
        }

        public Criteria andShareSuccessFailUrlGreaterThan(String value) {
            addCriterion("share_success_fail_url >", value, "shareSuccessFailUrl");
            return (Criteria) this;
        }

        public Criteria andShareSuccessFailUrlGreaterThanOrEqualTo(String value) {
            addCriterion("share_success_fail_url >=", value, "shareSuccessFailUrl");
            return (Criteria) this;
        }

        public Criteria andShareSuccessFailUrlLessThan(String value) {
            addCriterion("share_success_fail_url <", value, "shareSuccessFailUrl");
            return (Criteria) this;
        }

        public Criteria andShareSuccessFailUrlLessThanOrEqualTo(String value) {
            addCriterion("share_success_fail_url <=", value, "shareSuccessFailUrl");
            return (Criteria) this;
        }

        public Criteria andShareSuccessFailUrlLike(String value) {
            addCriterion("share_success_fail_url like", value, "shareSuccessFailUrl");
            return (Criteria) this;
        }

        public Criteria andShareSuccessFailUrlNotLike(String value) {
            addCriterion("share_success_fail_url not like", value, "shareSuccessFailUrl");
            return (Criteria) this;
        }

        public Criteria andShareSuccessFailUrlIn(List<String> values) {
            addCriterion("share_success_fail_url in", values, "shareSuccessFailUrl");
            return (Criteria) this;
        }

        public Criteria andShareSuccessFailUrlNotIn(List<String> values) {
            addCriterion("share_success_fail_url not in", values, "shareSuccessFailUrl");
            return (Criteria) this;
        }

        public Criteria andShareSuccessFailUrlBetween(String value1, String value2) {
            addCriterion("share_success_fail_url between", value1, value2, "shareSuccessFailUrl");
            return (Criteria) this;
        }

        public Criteria andShareSuccessFailUrlNotBetween(String value1, String value2) {
            addCriterion("share_success_fail_url not between", value1, value2, "shareSuccessFailUrl");
            return (Criteria) this;
        }

        public Criteria andStatisticalIsNull() {
            addCriterion("statistical is null");
            return (Criteria) this;
        }

        public Criteria andStatisticalIsNotNull() {
            addCriterion("statistical is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticalEqualTo(String value) {
            addCriterion("statistical =", value, "statistical");
            return (Criteria) this;
        }

        public Criteria andStatisticalNotEqualTo(String value) {
            addCriterion("statistical <>", value, "statistical");
            return (Criteria) this;
        }

        public Criteria andStatisticalGreaterThan(String value) {
            addCriterion("statistical >", value, "statistical");
            return (Criteria) this;
        }

        public Criteria andStatisticalGreaterThanOrEqualTo(String value) {
            addCriterion("statistical >=", value, "statistical");
            return (Criteria) this;
        }

        public Criteria andStatisticalLessThan(String value) {
            addCriterion("statistical <", value, "statistical");
            return (Criteria) this;
        }

        public Criteria andStatisticalLessThanOrEqualTo(String value) {
            addCriterion("statistical <=", value, "statistical");
            return (Criteria) this;
        }

        public Criteria andStatisticalLike(String value) {
            addCriterion("statistical like", value, "statistical");
            return (Criteria) this;
        }

        public Criteria andStatisticalNotLike(String value) {
            addCriterion("statistical not like", value, "statistical");
            return (Criteria) this;
        }

        public Criteria andStatisticalIn(List<String> values) {
            addCriterion("statistical in", values, "statistical");
            return (Criteria) this;
        }

        public Criteria andStatisticalNotIn(List<String> values) {
            addCriterion("statistical not in", values, "statistical");
            return (Criteria) this;
        }

        public Criteria andStatisticalBetween(String value1, String value2) {
            addCriterion("statistical between", value1, value2, "statistical");
            return (Criteria) this;
        }

        public Criteria andStatisticalNotBetween(String value1, String value2) {
            addCriterion("statistical not between", value1, value2, "statistical");
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

        public Criteria andForcedShareIsNull() {
            addCriterion("forced_share is null");
            return (Criteria) this;
        }

        public Criteria andForcedShareIsNotNull() {
            addCriterion("forced_share is not null");
            return (Criteria) this;
        }

        public Criteria andForcedShareEqualTo(Boolean value) {
            addCriterion("forced_share =", value, "forcedShare");
            return (Criteria) this;
        }

        public Criteria andForcedShareNotEqualTo(Boolean value) {
            addCriterion("forced_share <>", value, "forcedShare");
            return (Criteria) this;
        }

        public Criteria andForcedShareGreaterThan(Boolean value) {
            addCriterion("forced_share >", value, "forcedShare");
            return (Criteria) this;
        }

        public Criteria andForcedShareGreaterThanOrEqualTo(Boolean value) {
            addCriterion("forced_share >=", value, "forcedShare");
            return (Criteria) this;
        }

        public Criteria andForcedShareLessThan(Boolean value) {
            addCriterion("forced_share <", value, "forcedShare");
            return (Criteria) this;
        }

        public Criteria andForcedShareLessThanOrEqualTo(Boolean value) {
            addCriterion("forced_share <=", value, "forcedShare");
            return (Criteria) this;
        }

        public Criteria andForcedShareIn(List<Boolean> values) {
            addCriterion("forced_share in", values, "forcedShare");
            return (Criteria) this;
        }

        public Criteria andForcedShareNotIn(List<Boolean> values) {
            addCriterion("forced_share not in", values, "forcedShare");
            return (Criteria) this;
        }

        public Criteria andForcedShareBetween(Boolean value1, Boolean value2) {
            addCriterion("forced_share between", value1, value2, "forcedShare");
            return (Criteria) this;
        }

        public Criteria andForcedShareNotBetween(Boolean value1, Boolean value2) {
            addCriterion("forced_share not between", value1, value2, "forcedShare");
            return (Criteria) this;
        }

        public Criteria andNoShareDomainIsNull() {
            addCriterion("no_share_domain is null");
            return (Criteria) this;
        }

        public Criteria andNoShareDomainIsNotNull() {
            addCriterion("no_share_domain is not null");
            return (Criteria) this;
        }

        public Criteria andNoShareDomainEqualTo(String value) {
            addCriterion("no_share_domain =", value, "noShareDomain");
            return (Criteria) this;
        }

        public Criteria andNoShareDomainNotEqualTo(String value) {
            addCriterion("no_share_domain <>", value, "noShareDomain");
            return (Criteria) this;
        }

        public Criteria andNoShareDomainGreaterThan(String value) {
            addCriterion("no_share_domain >", value, "noShareDomain");
            return (Criteria) this;
        }

        public Criteria andNoShareDomainGreaterThanOrEqualTo(String value) {
            addCriterion("no_share_domain >=", value, "noShareDomain");
            return (Criteria) this;
        }

        public Criteria andNoShareDomainLessThan(String value) {
            addCriterion("no_share_domain <", value, "noShareDomain");
            return (Criteria) this;
        }

        public Criteria andNoShareDomainLessThanOrEqualTo(String value) {
            addCriterion("no_share_domain <=", value, "noShareDomain");
            return (Criteria) this;
        }

        public Criteria andNoShareDomainLike(String value) {
            addCriterion("no_share_domain like", value, "noShareDomain");
            return (Criteria) this;
        }

        public Criteria andNoShareDomainNotLike(String value) {
            addCriterion("no_share_domain not like", value, "noShareDomain");
            return (Criteria) this;
        }

        public Criteria andNoShareDomainIn(List<String> values) {
            addCriterion("no_share_domain in", values, "noShareDomain");
            return (Criteria) this;
        }

        public Criteria andNoShareDomainNotIn(List<String> values) {
            addCriterion("no_share_domain not in", values, "noShareDomain");
            return (Criteria) this;
        }

        public Criteria andNoShareDomainBetween(String value1, String value2) {
            addCriterion("no_share_domain between", value1, value2, "noShareDomain");
            return (Criteria) this;
        }

        public Criteria andNoShareDomainNotBetween(String value1, String value2) {
            addCriterion("no_share_domain not between", value1, value2, "noShareDomain");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
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