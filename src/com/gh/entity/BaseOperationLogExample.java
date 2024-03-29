package com.gh.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseOperationLogExample {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table GHPORT.BASE_OPERATION_LOG
     *
     * @abatorgenerated Fri Oct 25 14:35:58 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table GHPORT.BASE_OPERATION_LOG
     *
     * @abatorgenerated Fri Oct 25 14:35:58 CST 2013
     */
    protected List oredCriteria;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table GHPORT.BASE_OPERATION_LOG
     *
     * @abatorgenerated Fri Oct 25 14:35:58 CST 2013
     */
    public BaseOperationLogExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table GHPORT.BASE_OPERATION_LOG
     *
     * @abatorgenerated Fri Oct 25 14:35:58 CST 2013
     */
    protected BaseOperationLogExample(BaseOperationLogExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table GHPORT.BASE_OPERATION_LOG
     *
     * @abatorgenerated Fri Oct 25 14:35:58 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table GHPORT.BASE_OPERATION_LOG
     *
     * @abatorgenerated Fri Oct 25 14:35:58 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table GHPORT.BASE_OPERATION_LOG
     *
     * @abatorgenerated Fri Oct 25 14:35:58 CST 2013
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table GHPORT.BASE_OPERATION_LOG
     *
     * @abatorgenerated Fri Oct 25 14:35:58 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table GHPORT.BASE_OPERATION_LOG
     *
     * @abatorgenerated Fri Oct 25 14:35:58 CST 2013
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table GHPORT.BASE_OPERATION_LOG
     *
     * @abatorgenerated Fri Oct 25 14:35:58 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table GHPORT.BASE_OPERATION_LOG
     *
     * @abatorgenerated Fri Oct 25 14:35:58 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table GHPORT.BASE_OPERATION_LOG
     *
     * @abatorgenerated Fri Oct 25 14:35:58 CST 2013
     */
    public static class Criteria {
        protected List criteriaWithoutValue;

        protected List criteriaWithSingleValue;

        protected List criteriaWithListValue;

        protected List criteriaWithBetweenValue;

        protected Criteria() {
            super();
            criteriaWithoutValue = new ArrayList();
            criteriaWithSingleValue = new ArrayList();
            criteriaWithListValue = new ArrayList();
            criteriaWithBetweenValue = new ArrayList();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List list = new ArrayList();
            list.add(value1);
            list.add(value2);
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        public Criteria andFuidIsNull() {
            addCriterion("FUID is null");
            return this;
        }

        public Criteria andFuidIsNotNull() {
            addCriterion("FUID is not null");
            return this;
        }

        public Criteria andFuidEqualTo(String value) {
            addCriterion("FUID =", value, "fuid");
            return this;
        }

        public Criteria andFuidNotEqualTo(String value) {
            addCriterion("FUID <>", value, "fuid");
            return this;
        }

        public Criteria andFuidGreaterThan(String value) {
            addCriterion("FUID >", value, "fuid");
            return this;
        }

        public Criteria andFuidGreaterThanOrEqualTo(String value) {
            addCriterion("FUID >=", value, "fuid");
            return this;
        }

        public Criteria andFuidLessThan(String value) {
            addCriterion("FUID <", value, "fuid");
            return this;
        }

        public Criteria andFuidLessThanOrEqualTo(String value) {
            addCriterion("FUID <=", value, "fuid");
            return this;
        }

        public Criteria andFuidLike(String value) {
            addCriterion("FUID like", value, "fuid");
            return this;
        }

        public Criteria andFuidNotLike(String value) {
            addCriterion("FUID not like", value, "fuid");
            return this;
        }

        public Criteria andFuidIn(List values) {
            addCriterion("FUID in", values, "fuid");
            return this;
        }

        public Criteria andFuidNotIn(List values) {
            addCriterion("FUID not in", values, "fuid");
            return this;
        }

        public Criteria andFuidBetween(String value1, String value2) {
            addCriterion("FUID between", value1, value2, "fuid");
            return this;
        }

        public Criteria andFuidNotBetween(String value1, String value2) {
            addCriterion("FUID not between", value1, value2, "fuid");
            return this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("USERID is null");
            return this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("USERID is not null");
            return this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("USERID =", value, "userid");
            return this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("USERID <>", value, "userid");
            return this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("USERID >", value, "userid");
            return this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("USERID >=", value, "userid");
            return this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("USERID <", value, "userid");
            return this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("USERID <=", value, "userid");
            return this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("USERID like", value, "userid");
            return this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("USERID not like", value, "userid");
            return this;
        }

        public Criteria andUseridIn(List values) {
            addCriterion("USERID in", values, "userid");
            return this;
        }

        public Criteria andUseridNotIn(List values) {
            addCriterion("USERID not in", values, "userid");
            return this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("USERID between", value1, value2, "userid");
            return this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("USERID not between", value1, value2, "userid");
            return this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("USERNAME is null");
            return this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("USERNAME is not null");
            return this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("USERNAME =", value, "username");
            return this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("USERNAME <>", value, "username");
            return this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("USERNAME >", value, "username");
            return this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("USERNAME >=", value, "username");
            return this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("USERNAME <", value, "username");
            return this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("USERNAME <=", value, "username");
            return this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("USERNAME like", value, "username");
            return this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("USERNAME not like", value, "username");
            return this;
        }

        public Criteria andUsernameIn(List values) {
            addCriterion("USERNAME in", values, "username");
            return this;
        }

        public Criteria andUsernameNotIn(List values) {
            addCriterion("USERNAME not in", values, "username");
            return this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("USERNAME between", value1, value2, "username");
            return this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("USERNAME not between", value1, value2, "username");
            return this;
        }

        public Criteria andRealnameIsNull() {
            addCriterion("REALNAME is null");
            return this;
        }

        public Criteria andRealnameIsNotNull() {
            addCriterion("REALNAME is not null");
            return this;
        }

        public Criteria andRealnameEqualTo(String value) {
            addCriterion("REALNAME =", value, "realname");
            return this;
        }

        public Criteria andRealnameNotEqualTo(String value) {
            addCriterion("REALNAME <>", value, "realname");
            return this;
        }

        public Criteria andRealnameGreaterThan(String value) {
            addCriterion("REALNAME >", value, "realname");
            return this;
        }

        public Criteria andRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("REALNAME >=", value, "realname");
            return this;
        }

        public Criteria andRealnameLessThan(String value) {
            addCriterion("REALNAME <", value, "realname");
            return this;
        }

        public Criteria andRealnameLessThanOrEqualTo(String value) {
            addCriterion("REALNAME <=", value, "realname");
            return this;
        }

        public Criteria andRealnameLike(String value) {
            addCriterion("REALNAME like", value, "realname");
            return this;
        }

        public Criteria andRealnameNotLike(String value) {
            addCriterion("REALNAME not like", value, "realname");
            return this;
        }

        public Criteria andRealnameIn(List values) {
            addCriterion("REALNAME in", values, "realname");
            return this;
        }

        public Criteria andRealnameNotIn(List values) {
            addCriterion("REALNAME not in", values, "realname");
            return this;
        }

        public Criteria andRealnameBetween(String value1, String value2) {
            addCriterion("REALNAME between", value1, value2, "realname");
            return this;
        }

        public Criteria andRealnameNotBetween(String value1, String value2) {
            addCriterion("REALNAME not between", value1, value2, "realname");
            return this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("DESCRIPTION is null");
            return this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("DESCRIPTION is not null");
            return this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("DESCRIPTION =", value, "description");
            return this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("DESCRIPTION <>", value, "description");
            return this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("DESCRIPTION >", value, "description");
            return this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION >=", value, "description");
            return this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("DESCRIPTION <", value, "description");
            return this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION <=", value, "description");
            return this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("DESCRIPTION like", value, "description");
            return this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("DESCRIPTION not like", value, "description");
            return this;
        }

        public Criteria andDescriptionIn(List values) {
            addCriterion("DESCRIPTION in", values, "description");
            return this;
        }

        public Criteria andDescriptionNotIn(List values) {
            addCriterion("DESCRIPTION not in", values, "description");
            return this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("DESCRIPTION between", value1, value2, "description");
            return this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("DESCRIPTION not between", value1, value2, "description");
            return this;
        }

        public Criteria andOperatingIsNull() {
            addCriterion("OPERATING is null");
            return this;
        }

        public Criteria andOperatingIsNotNull() {
            addCriterion("OPERATING is not null");
            return this;
        }

        public Criteria andOperatingEqualTo(String value) {
            addCriterion("OPERATING =", value, "operating");
            return this;
        }

        public Criteria andOperatingNotEqualTo(String value) {
            addCriterion("OPERATING <>", value, "operating");
            return this;
        }

        public Criteria andOperatingGreaterThan(String value) {
            addCriterion("OPERATING >", value, "operating");
            return this;
        }

        public Criteria andOperatingGreaterThanOrEqualTo(String value) {
            addCriterion("OPERATING >=", value, "operating");
            return this;
        }

        public Criteria andOperatingLessThan(String value) {
            addCriterion("OPERATING <", value, "operating");
            return this;
        }

        public Criteria andOperatingLessThanOrEqualTo(String value) {
            addCriterion("OPERATING <=", value, "operating");
            return this;
        }

        public Criteria andOperatingLike(String value) {
            addCriterion("OPERATING like", value, "operating");
            return this;
        }

        public Criteria andOperatingNotLike(String value) {
            addCriterion("OPERATING not like", value, "operating");
            return this;
        }

        public Criteria andOperatingIn(List values) {
            addCriterion("OPERATING in", values, "operating");
            return this;
        }

        public Criteria andOperatingNotIn(List values) {
            addCriterion("OPERATING not in", values, "operating");
            return this;
        }

        public Criteria andOperatingBetween(String value1, String value2) {
            addCriterion("OPERATING between", value1, value2, "operating");
            return this;
        }

        public Criteria andOperatingNotBetween(String value1, String value2) {
            addCriterion("OPERATING not between", value1, value2, "operating");
            return this;
        }

        public Criteria andCreatedateIsNull() {
            addCriterion("CREATEDATE is null");
            return this;
        }

        public Criteria andCreatedateIsNotNull() {
            addCriterion("CREATEDATE is not null");
            return this;
        }

        public Criteria andCreatedateEqualTo(Date value) {
            addCriterion("CREATEDATE =", value, "createdate");
            return this;
        }

        public Criteria andCreatedateNotEqualTo(Date value) {
            addCriterion("CREATEDATE <>", value, "createdate");
            return this;
        }

        public Criteria andCreatedateGreaterThan(Date value) {
            addCriterion("CREATEDATE >", value, "createdate");
            return this;
        }

        public Criteria andCreatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATEDATE >=", value, "createdate");
            return this;
        }

        public Criteria andCreatedateLessThan(Date value) {
            addCriterion("CREATEDATE <", value, "createdate");
            return this;
        }

        public Criteria andCreatedateLessThanOrEqualTo(Date value) {
            addCriterion("CREATEDATE <=", value, "createdate");
            return this;
        }

        public Criteria andCreatedateIn(List values) {
            addCriterion("CREATEDATE in", values, "createdate");
            return this;
        }

        public Criteria andCreatedateNotIn(List values) {
            addCriterion("CREATEDATE not in", values, "createdate");
            return this;
        }

        public Criteria andCreatedateBetween(Date value1, Date value2) {
            addCriterion("CREATEDATE between", value1, value2, "createdate");
            return this;
        }

        public Criteria andCreatedateNotBetween(Date value1, Date value2) {
            addCriterion("CREATEDATE not between", value1, value2, "createdate");
            return this;
        }
    }
}