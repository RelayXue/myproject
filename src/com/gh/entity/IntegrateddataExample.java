package com.gh.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

public class IntegrateddataExample {

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database table integrateddata
	 * @abatorgenerated  Mon May 11 16:23:03 CST 2015
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database table integrateddata
	 * @abatorgenerated  Mon May 11 16:23:03 CST 2015
	 */
	protected List oredCriteria;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table integrateddata
	 * @abatorgenerated  Mon May 11 16:23:03 CST 2015
	 */
	public IntegrateddataExample() {
		oredCriteria = new ArrayList();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table integrateddata
	 * @abatorgenerated  Mon May 11 16:23:03 CST 2015
	 */
	protected IntegrateddataExample(IntegrateddataExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table integrateddata
	 * @abatorgenerated  Mon May 11 16:23:03 CST 2015
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table integrateddata
	 * @abatorgenerated  Mon May 11 16:23:03 CST 2015
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table integrateddata
	 * @abatorgenerated  Mon May 11 16:23:03 CST 2015
	 */
	public List getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table integrateddata
	 * @abatorgenerated  Mon May 11 16:23:03 CST 2015
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table integrateddata
	 * @abatorgenerated  Mon May 11 16:23:03 CST 2015
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table integrateddata
	 * @abatorgenerated  Mon May 11 16:23:03 CST 2015
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table integrateddata
	 * @abatorgenerated  Mon May 11 16:23:03 CST 2015
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to the database table integrateddata
	 * @abatorgenerated  Mon May 11 16:23:03 CST 2015
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
			return criteriaWithoutValue.size() > 0 || criteriaWithSingleValue.size() > 0 || criteriaWithListValue.size() > 0 || criteriaWithBetweenValue.size() > 0;
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

		public Criteria andFullnameIsNull() {
			addCriterion("FULLNAME is null");
			return this;
		}

		public Criteria andFullnameIsNotNull() {
			addCriterion("FULLNAME is not null");
			return this;
		}

		public Criteria andFullnameEqualTo(String value) {
			addCriterion("FULLNAME =", value, "fullname");
			return this;
		}

		public Criteria andFullnameNotEqualTo(String value) {
			addCriterion("FULLNAME <>", value, "fullname");
			return this;
		}

		public Criteria andFullnameGreaterThan(String value) {
			addCriterion("FULLNAME >", value, "fullname");
			return this;
		}

		public Criteria andFullnameGreaterThanOrEqualTo(String value) {
			addCriterion("FULLNAME >=", value, "fullname");
			return this;
		}

		public Criteria andFullnameLessThan(String value) {
			addCriterion("FULLNAME <", value, "fullname");
			return this;
		}

		public Criteria andFullnameLessThanOrEqualTo(String value) {
			addCriterion("FULLNAME <=", value, "fullname");
			return this;
		}

		public Criteria andFullnameLike(String value) {
			addCriterion("FULLNAME like", value, "fullname");
			return this;
		}

		public Criteria andFullnameNotLike(String value) {
			addCriterion("FULLNAME not like", value, "fullname");
			return this;
		}

		public Criteria andFullnameIn(List values) {
			addCriterion("FULLNAME in", values, "fullname");
			return this;
		}

		public Criteria andFullnameNotIn(List values) {
			addCriterion("FULLNAME not in", values, "fullname");
			return this;
		}

		public Criteria andFullnameBetween(String value1, String value2) {
			addCriterion("FULLNAME between", value1, value2, "fullname");
			return this;
		}

		public Criteria andFullnameNotBetween(String value1, String value2) {
			addCriterion("FULLNAME not between", value1, value2, "fullname");
			return this;
		}

		public Criteria andFxIsNull() {
			addCriterion("FX is null");
			return this;
		}

		public Criteria andFxIsNotNull() {
			addCriterion("FX is not null");
			return this;
		}

		public Criteria andFxEqualTo(String value) {
			addCriterion("FX =", value, "fx");
			return this;
		}

		public Criteria andFxNotEqualTo(String value) {
			addCriterion("FX <>", value, "fx");
			return this;
		}

		public Criteria andFxGreaterThan(String value) {
			addCriterion("FX >", value, "fx");
			return this;
		}

		public Criteria andFxGreaterThanOrEqualTo(String value) {
			addCriterion("FX >=", value, "fx");
			return this;
		}

		public Criteria andFxLessThan(String value) {
			addCriterion("FX <", value, "fx");
			return this;
		}

		public Criteria andFxLessThanOrEqualTo(String value) {
			addCriterion("FX <=", value, "fx");
			return this;
		}

		public Criteria andFxLike(String value) {
			addCriterion("FX like", value, "fx");
			return this;
		}

		public Criteria andFxNotLike(String value) {
			addCriterion("FX not like", value, "fx");
			return this;
		}

		public Criteria andFxIn(List values) {
			addCriterion("FX in", values, "fx");
			return this;
		}

		public Criteria andFxNotIn(List values) {
			addCriterion("FX not in", values, "fx");
			return this;
		}

		public Criteria andFxBetween(String value1, String value2) {
			addCriterion("FX between", value1, value2, "fx");
			return this;
		}

		public Criteria andFxNotBetween(String value1, String value2) {
			addCriterion("FX not between", value1, value2, "fx");
			return this;
		}

		public Criteria andFyIsNull() {
			addCriterion("FY is null");
			return this;
		}

		public Criteria andFyIsNotNull() {
			addCriterion("FY is not null");
			return this;
		}

		public Criteria andFyEqualTo(String value) {
			addCriterion("FY =", value, "fy");
			return this;
		}

		public Criteria andFyNotEqualTo(String value) {
			addCriterion("FY <>", value, "fy");
			return this;
		}

		public Criteria andFyGreaterThan(String value) {
			addCriterion("FY >", value, "fy");
			return this;
		}

		public Criteria andFyGreaterThanOrEqualTo(String value) {
			addCriterion("FY >=", value, "fy");
			return this;
		}

		public Criteria andFyLessThan(String value) {
			addCriterion("FY <", value, "fy");
			return this;
		}

		public Criteria andFyLessThanOrEqualTo(String value) {
			addCriterion("FY <=", value, "fy");
			return this;
		}

		public Criteria andFyLike(String value) {
			addCriterion("FY like", value, "fy");
			return this;
		}

		public Criteria andFyNotLike(String value) {
			addCriterion("FY not like", value, "fy");
			return this;
		}

		public Criteria andFyIn(List values) {
			addCriterion("FY in", values, "fy");
			return this;
		}

		public Criteria andFyNotIn(List values) {
			addCriterion("FY not in", values, "fy");
			return this;
		}

		public Criteria andFyBetween(String value1, String value2) {
			addCriterion("FY between", value1, value2, "fy");
			return this;
		}

		public Criteria andFyNotBetween(String value1, String value2) {
			addCriterion("FY not between", value1, value2, "fy");
			return this;
		}

		public Criteria andTypeIsNull() {
			addCriterion("TYPE is null");
			return this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("TYPE is not null");
			return this;
		}

		public Criteria andTypeEqualTo(String value) {
			addCriterion("TYPE =", value, "type");
			return this;
		}

		public Criteria andTypeNotEqualTo(String value) {
			addCriterion("TYPE <>", value, "type");
			return this;
		}

		public Criteria andTypeGreaterThan(String value) {
			addCriterion("TYPE >", value, "type");
			return this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(String value) {
			addCriterion("TYPE >=", value, "type");
			return this;
		}

		public Criteria andTypeLessThan(String value) {
			addCriterion("TYPE <", value, "type");
			return this;
		}

		public Criteria andTypeLessThanOrEqualTo(String value) {
			addCriterion("TYPE <=", value, "type");
			return this;
		}

		public Criteria andTypeLike(String value) {
			addCriterion("TYPE like", value, "type");
			return this;
		}

		public Criteria andTypeNotLike(String value) {
			addCriterion("TYPE not like", value, "type");
			return this;
		}

		public Criteria andTypeIn(List values) {
			addCriterion("TYPE in", values, "type");
			return this;
		}

		public Criteria andTypeNotIn(List values) {
			addCriterion("TYPE not in", values, "type");
			return this;
		}

		public Criteria andTypeBetween(String value1, String value2) {
			addCriterion("TYPE between", value1, value2, "type");
			return this;
		}

		public Criteria andTypeNotBetween(String value1, String value2) {
			addCriterion("TYPE not between", value1, value2, "type");
			return this;
		}

		public Criteria andPhoneIsNull() {
			addCriterion("PHONE is null");
			return this;
		}

		public Criteria andPhoneIsNotNull() {
			addCriterion("PHONE is not null");
			return this;
		}

		public Criteria andPhoneEqualTo(String value) {
			addCriterion("PHONE =", value, "phone");
			return this;
		}

		public Criteria andPhoneNotEqualTo(String value) {
			addCriterion("PHONE <>", value, "phone");
			return this;
		}

		public Criteria andPhoneGreaterThan(String value) {
			addCriterion("PHONE >", value, "phone");
			return this;
		}

		public Criteria andPhoneGreaterThanOrEqualTo(String value) {
			addCriterion("PHONE >=", value, "phone");
			return this;
		}

		public Criteria andPhoneLessThan(String value) {
			addCriterion("PHONE <", value, "phone");
			return this;
		}

		public Criteria andPhoneLessThanOrEqualTo(String value) {
			addCriterion("PHONE <=", value, "phone");
			return this;
		}

		public Criteria andPhoneLike(String value) {
			addCriterion("PHONE like", value, "phone");
			return this;
		}

		public Criteria andPhoneNotLike(String value) {
			addCriterion("PHONE not like", value, "phone");
			return this;
		}

		public Criteria andPhoneIn(List values) {
			addCriterion("PHONE in", values, "phone");
			return this;
		}

		public Criteria andPhoneNotIn(List values) {
			addCriterion("PHONE not in", values, "phone");
			return this;
		}

		public Criteria andPhoneBetween(String value1, String value2) {
			addCriterion("PHONE between", value1, value2, "phone");
			return this;
		}

		public Criteria andPhoneNotBetween(String value1, String value2) {
			addCriterion("PHONE not between", value1, value2, "phone");
			return this;
		}

		public Criteria andAddressIsNull() {
			addCriterion("ADDRESS is null");
			return this;
		}

		public Criteria andAddressIsNotNull() {
			addCriterion("ADDRESS is not null");
			return this;
		}

		public Criteria andAddressEqualTo(String value) {
			addCriterion("ADDRESS =", value, "address");
			return this;
		}

		public Criteria andAddressNotEqualTo(String value) {
			addCriterion("ADDRESS <>", value, "address");
			return this;
		}

		public Criteria andAddressGreaterThan(String value) {
			addCriterion("ADDRESS >", value, "address");
			return this;
		}

		public Criteria andAddressGreaterThanOrEqualTo(String value) {
			addCriterion("ADDRESS >=", value, "address");
			return this;
		}

		public Criteria andAddressLessThan(String value) {
			addCriterion("ADDRESS <", value, "address");
			return this;
		}

		public Criteria andAddressLessThanOrEqualTo(String value) {
			addCriterion("ADDRESS <=", value, "address");
			return this;
		}

		public Criteria andAddressLike(String value) {
			addCriterion("ADDRESS like", value, "address");
			return this;
		}

		public Criteria andAddressNotLike(String value) {
			addCriterion("ADDRESS not like", value, "address");
			return this;
		}

		public Criteria andAddressIn(List values) {
			addCriterion("ADDRESS in", values, "address");
			return this;
		}

		public Criteria andAddressNotIn(List values) {
			addCriterion("ADDRESS not in", values, "address");
			return this;
		}

		public Criteria andAddressBetween(String value1, String value2) {
			addCriterion("ADDRESS between", value1, value2, "address");
			return this;
		}

		public Criteria andAddressNotBetween(String value1, String value2) {
			addCriterion("ADDRESS not between", value1, value2, "address");
			return this;
		}

		public Criteria andDimagesStatusIsNull() {
			addCriterion("DIMAGES_STATUS is null");
			return this;
		}

		public Criteria andDimagesStatusIsNotNull() {
			addCriterion("DIMAGES_STATUS is not null");
			return this;
		}

		public Criteria andDimagesStatusEqualTo(Long value) {
			addCriterion("DIMAGES_STATUS =", value, "dimagesStatus");
			return this;
		}

		public Criteria andDimagesStatusNotEqualTo(Long value) {
			addCriterion("DIMAGES_STATUS <>", value, "dimagesStatus");
			return this;
		}

		public Criteria andDimagesStatusGreaterThan(Long value) {
			addCriterion("DIMAGES_STATUS >", value, "dimagesStatus");
			return this;
		}

		public Criteria andDimagesStatusGreaterThanOrEqualTo(Long value) {
			addCriterion("DIMAGES_STATUS >=", value, "dimagesStatus");
			return this;
		}

		public Criteria andDimagesStatusLessThan(Long value) {
			addCriterion("DIMAGES_STATUS <", value, "dimagesStatus");
			return this;
		}

		public Criteria andDimagesStatusLessThanOrEqualTo(Long value) {
			addCriterion("DIMAGES_STATUS <=", value, "dimagesStatus");
			return this;
		}

		public Criteria andDimagesStatusIn(List values) {
			addCriterion("DIMAGES_STATUS in", values, "dimagesStatus");
			return this;
		}

		public Criteria andDimagesStatusNotIn(List values) {
			addCriterion("DIMAGES_STATUS not in", values, "dimagesStatus");
			return this;
		}

		public Criteria andDimagesStatusBetween(Long value1, Long value2) {
			addCriterion("DIMAGES_STATUS between", value1, value2, "dimagesStatus");
			return this;
		}

		public Criteria andDimagesStatusNotBetween(Long value1, Long value2) {
			addCriterion("DIMAGES_STATUS not between", value1, value2, "dimagesStatus");
			return this;
		}

		public Criteria andSupervisorIsNull() {
			addCriterion("SUPERVISOR is null");
			return this;
		}

		public Criteria andSupervisorIsNotNull() {
			addCriterion("SUPERVISOR is not null");
			return this;
		}

		public Criteria andSupervisorEqualTo(String value) {
			addCriterion("SUPERVISOR =", value, "supervisor");
			return this;
		}

		public Criteria andSupervisorNotEqualTo(String value) {
			addCriterion("SUPERVISOR <>", value, "supervisor");
			return this;
		}

		public Criteria andSupervisorGreaterThan(String value) {
			addCriterion("SUPERVISOR >", value, "supervisor");
			return this;
		}

		public Criteria andSupervisorGreaterThanOrEqualTo(String value) {
			addCriterion("SUPERVISOR >=", value, "supervisor");
			return this;
		}

		public Criteria andSupervisorLessThan(String value) {
			addCriterion("SUPERVISOR <", value, "supervisor");
			return this;
		}

		public Criteria andSupervisorLessThanOrEqualTo(String value) {
			addCriterion("SUPERVISOR <=", value, "supervisor");
			return this;
		}

		public Criteria andSupervisorLike(String value) {
			addCriterion("SUPERVISOR like", value, "supervisor");
			return this;
		}

		public Criteria andSupervisorNotLike(String value) {
			addCriterion("SUPERVISOR not like", value, "supervisor");
			return this;
		}

		public Criteria andSupervisorIn(List values) {
			addCriterion("SUPERVISOR in", values, "supervisor");
			return this;
		}

		public Criteria andSupervisorNotIn(List values) {
			addCriterion("SUPERVISOR not in", values, "supervisor");
			return this;
		}

		public Criteria andSupervisorBetween(String value1, String value2) {
			addCriterion("SUPERVISOR between", value1, value2, "supervisor");
			return this;
		}

		public Criteria andSupervisorNotBetween(String value1, String value2) {
			addCriterion("SUPERVISOR not between", value1, value2, "supervisor");
			return this;
		}

		public Criteria andSupervisorphoneIsNull() {
			addCriterion("SUPERVISORPHONE is null");
			return this;
		}

		public Criteria andSupervisorphoneIsNotNull() {
			addCriterion("SUPERVISORPHONE is not null");
			return this;
		}

		public Criteria andSupervisorphoneEqualTo(String value) {
			addCriterion("SUPERVISORPHONE =", value, "supervisorphone");
			return this;
		}

		public Criteria andSupervisorphoneNotEqualTo(String value) {
			addCriterion("SUPERVISORPHONE <>", value, "supervisorphone");
			return this;
		}

		public Criteria andSupervisorphoneGreaterThan(String value) {
			addCriterion("SUPERVISORPHONE >", value, "supervisorphone");
			return this;
		}

		public Criteria andSupervisorphoneGreaterThanOrEqualTo(String value) {
			addCriterion("SUPERVISORPHONE >=", value, "supervisorphone");
			return this;
		}

		public Criteria andSupervisorphoneLessThan(String value) {
			addCriterion("SUPERVISORPHONE <", value, "supervisorphone");
			return this;
		}

		public Criteria andSupervisorphoneLessThanOrEqualTo(String value) {
			addCriterion("SUPERVISORPHONE <=", value, "supervisorphone");
			return this;
		}

		public Criteria andSupervisorphoneLike(String value) {
			addCriterion("SUPERVISORPHONE like", value, "supervisorphone");
			return this;
		}

		public Criteria andSupervisorphoneNotLike(String value) {
			addCriterion("SUPERVISORPHONE not like", value, "supervisorphone");
			return this;
		}

		public Criteria andSupervisorphoneIn(List values) {
			addCriterion("SUPERVISORPHONE in", values, "supervisorphone");
			return this;
		}

		public Criteria andSupervisorphoneNotIn(List values) {
			addCriterion("SUPERVISORPHONE not in", values, "supervisorphone");
			return this;
		}

		public Criteria andSupervisorphoneBetween(String value1, String value2) {
			addCriterion("SUPERVISORPHONE between", value1, value2, "supervisorphone");
			return this;
		}

		public Criteria andSupervisorphoneNotBetween(String value1, String value2) {
			addCriterion("SUPERVISORPHONE not between", value1, value2, "supervisorphone");
			return this;
		}

		public Criteria andPraiseIsNull() {
			addCriterion("PRAISE is null");
			return this;
		}

		public Criteria andPraiseIsNotNull() {
			addCriterion("PRAISE is not null");
			return this;
		}

		public Criteria andPraiseEqualTo(Integer value) {
			addCriterion("PRAISE =", value, "praise");
			return this;
		}

		public Criteria andPraiseNotEqualTo(Integer value) {
			addCriterion("PRAISE <>", value, "praise");
			return this;
		}

		public Criteria andPraiseGreaterThan(Integer value) {
			addCriterion("PRAISE >", value, "praise");
			return this;
		}

		public Criteria andPraiseGreaterThanOrEqualTo(Integer value) {
			addCriterion("PRAISE >=", value, "praise");
			return this;
		}

		public Criteria andPraiseLessThan(Integer value) {
			addCriterion("PRAISE <", value, "praise");
			return this;
		}

		public Criteria andPraiseLessThanOrEqualTo(Integer value) {
			addCriterion("PRAISE <=", value, "praise");
			return this;
		}

		public Criteria andPraiseIn(List values) {
			addCriterion("PRAISE in", values, "praise");
			return this;
		}

		public Criteria andPraiseNotIn(List values) {
			addCriterion("PRAISE not in", values, "praise");
			return this;
		}

		public Criteria andPraiseBetween(Integer value1, Integer value2) {
			addCriterion("PRAISE between", value1, value2, "praise");
			return this;
		}

		public Criteria andPraiseNotBetween(Integer value1, Integer value2) {
			addCriterion("PRAISE not between", value1, value2, "praise");
			return this;
		}

		public Criteria andConsumptionIsNull() {
			addCriterion("CONSUMPTION is null");
			return this;
		}

		public Criteria andConsumptionIsNotNull() {
			addCriterion("CONSUMPTION is not null");
			return this;
		}

		public Criteria andConsumptionEqualTo(String value) {
			addCriterion("CONSUMPTION =", value, "consumption");
			return this;
		}

		public Criteria andConsumptionNotEqualTo(String value) {
			addCriterion("CONSUMPTION <>", value, "consumption");
			return this;
		}

		public Criteria andConsumptionGreaterThan(String value) {
			addCriterion("CONSUMPTION >", value, "consumption");
			return this;
		}

		public Criteria andConsumptionGreaterThanOrEqualTo(String value) {
			addCriterion("CONSUMPTION >=", value, "consumption");
			return this;
		}

		public Criteria andConsumptionLessThan(String value) {
			addCriterion("CONSUMPTION <", value, "consumption");
			return this;
		}

		public Criteria andConsumptionLessThanOrEqualTo(String value) {
			addCriterion("CONSUMPTION <=", value, "consumption");
			return this;
		}

		public Criteria andConsumptionLike(String value) {
			addCriterion("CONSUMPTION like", value, "consumption");
			return this;
		}

		public Criteria andConsumptionNotLike(String value) {
			addCriterion("CONSUMPTION not like", value, "consumption");
			return this;
		}

		public Criteria andConsumptionIn(List values) {
			addCriterion("CONSUMPTION in", values, "consumption");
			return this;
		}

		public Criteria andConsumptionNotIn(List values) {
			addCriterion("CONSUMPTION not in", values, "consumption");
			return this;
		}

		public Criteria andConsumptionBetween(String value1, String value2) {
			addCriterion("CONSUMPTION between", value1, value2, "consumption");
			return this;
		}

		public Criteria andConsumptionNotBetween(String value1, String value2) {
			addCriterion("CONSUMPTION not between", value1, value2, "consumption");
			return this;
		}

		public Criteria andDistanceIsNull() {
			addCriterion("DISTANCE is null");
			return this;
		}

		public Criteria andDistanceIsNotNull() {
			addCriterion("DISTANCE is not null");
			return this;
		}

		public Criteria andDistanceEqualTo(Long value) {
			addCriterion("DISTANCE =", value, "distance");
			return this;
		}

		public Criteria andDistanceNotEqualTo(Long value) {
			addCriterion("DISTANCE <>", value, "distance");
			return this;
		}

		public Criteria andDistanceGreaterThan(Long value) {
			addCriterion("DISTANCE >", value, "distance");
			return this;
		}

		public Criteria andDistanceGreaterThanOrEqualTo(Long value) {
			addCriterion("DISTANCE >=", value, "distance");
			return this;
		}

		public Criteria andDistanceLessThan(Long value) {
			addCriterion("DISTANCE <", value, "distance");
			return this;
		}

		public Criteria andDistanceLessThanOrEqualTo(Long value) {
			addCriterion("DISTANCE <=", value, "distance");
			return this;
		}

		public Criteria andDistanceIn(List values) {
			addCriterion("DISTANCE in", values, "distance");
			return this;
		}

		public Criteria andDistanceNotIn(List values) {
			addCriterion("DISTANCE not in", values, "distance");
			return this;
		}

		public Criteria andDistanceBetween(Long value1, Long value2) {
			addCriterion("DISTANCE between", value1, value2, "distance");
			return this;
		}

		public Criteria andDistanceNotBetween(Long value1, Long value2) {
			addCriterion("DISTANCE not between", value1, value2, "distance");
			return this;
		}

		public Criteria andPromiseIsNull() {
			addCriterion("PROMISE is null");
			return this;
		}

		public Criteria andPromiseIsNotNull() {
			addCriterion("PROMISE is not null");
			return this;
		}

		public Criteria andPromiseEqualTo(String value) {
			addCriterion("PROMISE =", value, "promise");
			return this;
		}

		public Criteria andPromiseNotEqualTo(String value) {
			addCriterion("PROMISE <>", value, "promise");
			return this;
		}

		public Criteria andPromiseGreaterThan(String value) {
			addCriterion("PROMISE >", value, "promise");
			return this;
		}

		public Criteria andPromiseGreaterThanOrEqualTo(String value) {
			addCriterion("PROMISE >=", value, "promise");
			return this;
		}

		public Criteria andPromiseLessThan(String value) {
			addCriterion("PROMISE <", value, "promise");
			return this;
		}

		public Criteria andPromiseLessThanOrEqualTo(String value) {
			addCriterion("PROMISE <=", value, "promise");
			return this;
		}

		public Criteria andPromiseLike(String value) {
			addCriterion("PROMISE like", value, "promise");
			return this;
		}

		public Criteria andPromiseNotLike(String value) {
			addCriterion("PROMISE not like", value, "promise");
			return this;
		}

		public Criteria andPromiseIn(List values) {
			addCriterion("PROMISE in", values, "promise");
			return this;
		}

		public Criteria andPromiseNotIn(List values) {
			addCriterion("PROMISE not in", values, "promise");
			return this;
		}

		public Criteria andPromiseBetween(String value1, String value2) {
			addCriterion("PROMISE between", value1, value2, "promise");
			return this;
		}

		public Criteria andPromiseNotBetween(String value1, String value2) {
			addCriterion("PROMISE not between", value1, value2, "promise");
			return this;
		}

		public Criteria andQidIsNull() {
			addCriterion("QID is null");
			return this;
		}

		public Criteria andQidIsNotNull() {
			addCriterion("QID is not null");
			return this;
		}

		public Criteria andQidEqualTo(String value) {
			addCriterion("QID =", value, "qid");
			return this;
		}

		public Criteria andQidNotEqualTo(String value) {
			addCriterion("QID <>", value, "qid");
			return this;
		}

		public Criteria andQidGreaterThan(String value) {
			addCriterion("QID >", value, "qid");
			return this;
		}

		public Criteria andQidGreaterThanOrEqualTo(String value) {
			addCriterion("QID >=", value, "qid");
			return this;
		}

		public Criteria andQidLessThan(String value) {
			addCriterion("QID <", value, "qid");
			return this;
		}

		public Criteria andQidLessThanOrEqualTo(String value) {
			addCriterion("QID <=", value, "qid");
			return this;
		}

		public Criteria andQidLike(String value) {
			addCriterion("QID like", value, "qid");
			return this;
		}

		public Criteria andQidNotLike(String value) {
			addCriterion("QID not like", value, "qid");
			return this;
		}

		public Criteria andQidIn(List values) {
			addCriterion("QID in", values, "qid");
			return this;
		}

		public Criteria andQidNotIn(List values) {
			addCriterion("QID not in", values, "qid");
			return this;
		}

		public Criteria andQidBetween(String value1, String value2) {
			addCriterion("QID between", value1, value2, "qid");
			return this;
		}

		public Criteria andQidNotBetween(String value1, String value2) {
			addCriterion("QID not between", value1, value2, "qid");
			return this;
		}

		public Criteria andStarIsNull() {
			addCriterion("STAR is null");
			return this;
		}

		public Criteria andStarIsNotNull() {
			addCriterion("STAR is not null");
			return this;
		}

		public Criteria andStarEqualTo(BigDecimal value) {
			addCriterion("STAR =", value, "star");
			return this;
		}

		public Criteria andStarNotEqualTo(BigDecimal value) {
			addCriterion("STAR <>", value, "star");
			return this;
		}

		public Criteria andStarGreaterThan(BigDecimal value) {
			addCriterion("STAR >", value, "star");
			return this;
		}

		public Criteria andStarGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("STAR >=", value, "star");
			return this;
		}

		public Criteria andStarLessThan(BigDecimal value) {
			addCriterion("STAR <", value, "star");
			return this;
		}

		public Criteria andStarLessThanOrEqualTo(BigDecimal value) {
			addCriterion("STAR <=", value, "star");
			return this;
		}

		public Criteria andStarIn(List values) {
			addCriterion("STAR in", values, "star");
			return this;
		}

		public Criteria andStarNotIn(List values) {
			addCriterion("STAR not in", values, "star");
			return this;
		}

		public Criteria andStarBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("STAR between", value1, value2, "star");
			return this;
		}

		public Criteria andStarNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("STAR not between", value1, value2, "star");
			return this;
		}
	}
}