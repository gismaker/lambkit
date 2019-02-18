/**
 * Copyright (c) 2015-2017, Henry Yang 杨勇 (gismail@foxmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lambkit.module.upms.rpc.model.base;

import com.jfinal.kit.StrKit;
import com.lambkit.db.sql.IQuery;
import com.lambkit.db.sql.QueryParas;
import com.lambkit.module.upms.rpc.query.UpmsLogQuery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-10-26
 * @version 1.0
 * @since 1.0
 * ${tbcnn}
 */
public class UpmsLogExample implements IQuery, Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public UpmsLogExample() {
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
    
    public QueryParas toQueryParas() {
    	StringBuilder str = new StringBuilder();
    	int k=0;
    	for (Criteria criteria : oredCriteria) {
			if(criteria.isValid()) {
				if(StrKit.notBlank(str.toString())) {
					str.append(" or ");
				}
				str.append("(");
	    		int i=0;
				for (Criterion criterion : criteria.criteria) {
					boolean bStr = "String".equals(criterion.getValueType());
					if(criterion.noValue) {
						if(i>0) str.append(" and ");
						str.append(criterion.condition);
					} else if(criterion.singleValue) {
						if(i>0) str.append(" and ");
						str.append(criterion.condition);
						str.append(" ");
						if(bStr) str.append("'");
						str.append(criterion.value);
						if(bStr) str.append("'");
					} else if(criterion.betweenValue) {
						if(i>0) str.append(" and ");
						str.append(criterion.condition);
						str.append(" ");
						if(bStr) str.append("'");
						str.append(criterion.value);
						if(bStr) str.append("'");
						str.append(" and ");
						if(bStr) str.append("'");
						str.append(criterion.secondValue);
						if(bStr) str.append("'");
					} else if(criterion.listValue) {
						if(i>0) str.append(" and ");
						str.append(criterion.condition);
						int j=0;
						for (Object val : (List<?>)criterion.value) {
							if(j==0) str.append(" (");
							else str.append(",");
							j++;
							if(bStr) str.append("'");
							str.append(val);
							if(bStr) str.append("'");
						}
						str.append(")");
					}
					i++;
				}
				str.append(")");
			}
		}
    	String sqlWhere = str.toString();
    	QueryParas p = new QueryParas();
    	p.setSelect("select * ");
    	StringBuilder s = new StringBuilder();
    	s.append(" from ");
    	s.append(UpmsLogQuery.me().getTableName());
    	if(StrKit.notBlank(sqlWhere)) {
    		s.append(" where ");
        	s.append(sqlWhere);
    	}
    	p.setSqlExceptSelect(s.toString());
    	return p;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
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

        protected void addCriterion(String condition, Object value, String property, String typeHandler, String valueType) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value, typeHandler, valueType));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property, String typeHandler, String valueType) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2, typeHandler, valueType));
        }

        public Criteria andLogIdIsNull() {
            addCriterion("log_id is null");
            return (Criteria) this;
        }

        public Criteria andLogIdIsNotNull() {
            addCriterion("log_id is not null");
            return (Criteria) this;
        }

         public Criteria andLogIdEqualTo(java.lang.Integer value) {
            addCriterion("log_id =", value, "logId", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andLogIdNotEqualTo(java.lang.Integer value) {
            addCriterion("log_id <>", value, "logId", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andLogIdGreaterThan(java.lang.Integer value) {
            addCriterion("log_id >", value, "logId", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andLogIdGreaterThanOrEqualTo(java.lang.Integer value) {
            addCriterion("log_id >=", value, "logId", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andLogIdLessThan(java.lang.Integer value) {
            addCriterion("log_id <", value, "logId", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andLogIdLessThanOrEqualTo(java.lang.Integer value) {
            addCriterion("log_id <=", value, "logId", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andLogIdBetween(java.lang.Integer value1, java.lang.Integer value2) {
            addCriterion("log_id between", value1, value2, "logId", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andLogIdNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
            addCriterion("log_id not between", value1, value2, "logId", "java.lang.Integer", "Float");
            return (Criteria) this;
        }
        
        public Criteria andLogIdIn(List<Integer> values) {
            addCriterion("log_id in", values, "logId", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andLogIdNotIn(List<java.lang.Integer> values) {
            addCriterion("log_id not in", values, "logId", "java.lang.Integer", "Float");
            return (Criteria) this;
        }
        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

         public Criteria andDescriptionLike(java.lang.String value) {
            addCriterion("description like", value, "description", "java.lang.String", "Float");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(java.lang.String value) {
            addCriterion("description not like", value, "description", "java.lang.String", "Float");
            return (Criteria) this;
        }
        public Criteria andDescriptionEqualTo(java.lang.String value) {
            addCriterion("description =", value, "description", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(java.lang.String value) {
            addCriterion("description <>", value, "description", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(java.lang.String value) {
            addCriterion("description >", value, "description", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(java.lang.String value) {
            addCriterion("description >=", value, "description", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(java.lang.String value) {
            addCriterion("description <", value, "description", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(java.lang.String value) {
            addCriterion("description <=", value, "description", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("description between", value1, value2, "description", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("description not between", value1, value2, "description", "java.lang.String", "String");
            return (Criteria) this;
        }
        
        public Criteria andDescriptionIn(List<Integer> values) {
            addCriterion("description in", values, "description", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<java.lang.String> values) {
            addCriterion("description not in", values, "description", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

         public Criteria andUsernameLike(java.lang.String value) {
            addCriterion("username like", value, "username", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(java.lang.String value) {
            addCriterion("username not like", value, "username", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andUsernameEqualTo(java.lang.String value) {
            addCriterion("username =", value, "username", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(java.lang.String value) {
            addCriterion("username <>", value, "username", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(java.lang.String value) {
            addCriterion("username >", value, "username", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(java.lang.String value) {
            addCriterion("username >=", value, "username", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(java.lang.String value) {
            addCriterion("username <", value, "username", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(java.lang.String value) {
            addCriterion("username <=", value, "username", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("username between", value1, value2, "username", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("username not between", value1, value2, "username", "java.lang.String", "String");
            return (Criteria) this;
        }
        
        public Criteria andUsernameIn(List<Integer> values) {
            addCriterion("username in", values, "username", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<java.lang.String> values) {
            addCriterion("username not in", values, "username", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

         public Criteria andStartTimeEqualTo(java.lang.Long value) {
            addCriterion("start_time =", value, "startTime", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(java.lang.Long value) {
            addCriterion("start_time <>", value, "startTime", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(java.lang.Long value) {
            addCriterion("start_time >", value, "startTime", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(java.lang.Long value) {
            addCriterion("start_time >=", value, "startTime", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(java.lang.Long value) {
            addCriterion("start_time <", value, "startTime", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(java.lang.Long value) {
            addCriterion("start_time <=", value, "startTime", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(java.lang.Long value1, java.lang.Long value2) {
            addCriterion("start_time between", value1, value2, "startTime", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(java.lang.Long value1, java.lang.Long value2) {
            addCriterion("start_time not between", value1, value2, "startTime", "java.lang.Long", "Float");
            return (Criteria) this;
        }
        
        public Criteria andStartTimeIn(List<Integer> values) {
            addCriterion("start_time in", values, "startTime", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<java.lang.Long> values) {
            addCriterion("start_time not in", values, "startTime", "java.lang.Long", "Float");
            return (Criteria) this;
        }
        public Criteria andSpendTimeIsNull() {
            addCriterion("spend_time is null");
            return (Criteria) this;
        }

        public Criteria andSpendTimeIsNotNull() {
            addCriterion("spend_time is not null");
            return (Criteria) this;
        }

         public Criteria andSpendTimeEqualTo(java.lang.Integer value) {
            addCriterion("spend_time =", value, "spendTime", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andSpendTimeNotEqualTo(java.lang.Integer value) {
            addCriterion("spend_time <>", value, "spendTime", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andSpendTimeGreaterThan(java.lang.Integer value) {
            addCriterion("spend_time >", value, "spendTime", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andSpendTimeGreaterThanOrEqualTo(java.lang.Integer value) {
            addCriterion("spend_time >=", value, "spendTime", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andSpendTimeLessThan(java.lang.Integer value) {
            addCriterion("spend_time <", value, "spendTime", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andSpendTimeLessThanOrEqualTo(java.lang.Integer value) {
            addCriterion("spend_time <=", value, "spendTime", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andSpendTimeBetween(java.lang.Integer value1, java.lang.Integer value2) {
            addCriterion("spend_time between", value1, value2, "spendTime", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andSpendTimeNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
            addCriterion("spend_time not between", value1, value2, "spendTime", "java.lang.Integer", "Float");
            return (Criteria) this;
        }
        
        public Criteria andSpendTimeIn(List<Integer> values) {
            addCriterion("spend_time in", values, "spendTime", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andSpendTimeNotIn(List<java.lang.Integer> values) {
            addCriterion("spend_time not in", values, "spendTime", "java.lang.Integer", "Float");
            return (Criteria) this;
        }
        public Criteria andBasePathIsNull() {
            addCriterion("base_path is null");
            return (Criteria) this;
        }

        public Criteria andBasePathIsNotNull() {
            addCriterion("base_path is not null");
            return (Criteria) this;
        }

         public Criteria andBasePathLike(java.lang.String value) {
            addCriterion("base_path like", value, "basePath", "java.lang.String", "Float");
            return (Criteria) this;
        }

        public Criteria andBasePathNotLike(java.lang.String value) {
            addCriterion("base_path not like", value, "basePath", "java.lang.String", "Float");
            return (Criteria) this;
        }
        public Criteria andBasePathEqualTo(java.lang.String value) {
            addCriterion("base_path =", value, "basePath", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andBasePathNotEqualTo(java.lang.String value) {
            addCriterion("base_path <>", value, "basePath", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andBasePathGreaterThan(java.lang.String value) {
            addCriterion("base_path >", value, "basePath", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andBasePathGreaterThanOrEqualTo(java.lang.String value) {
            addCriterion("base_path >=", value, "basePath", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andBasePathLessThan(java.lang.String value) {
            addCriterion("base_path <", value, "basePath", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andBasePathLessThanOrEqualTo(java.lang.String value) {
            addCriterion("base_path <=", value, "basePath", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andBasePathBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("base_path between", value1, value2, "basePath", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andBasePathNotBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("base_path not between", value1, value2, "basePath", "java.lang.String", "String");
            return (Criteria) this;
        }
        
        public Criteria andBasePathIn(List<Integer> values) {
            addCriterion("base_path in", values, "basePath", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andBasePathNotIn(List<java.lang.String> values) {
            addCriterion("base_path not in", values, "basePath", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andUriIsNull() {
            addCriterion("uri is null");
            return (Criteria) this;
        }

        public Criteria andUriIsNotNull() {
            addCriterion("uri is not null");
            return (Criteria) this;
        }

         public Criteria andUriLike(java.lang.String value) {
            addCriterion("uri like", value, "uri", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUriNotLike(java.lang.String value) {
            addCriterion("uri not like", value, "uri", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andUriEqualTo(java.lang.String value) {
            addCriterion("uri =", value, "uri", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUriNotEqualTo(java.lang.String value) {
            addCriterion("uri <>", value, "uri", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUriGreaterThan(java.lang.String value) {
            addCriterion("uri >", value, "uri", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUriGreaterThanOrEqualTo(java.lang.String value) {
            addCriterion("uri >=", value, "uri", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUriLessThan(java.lang.String value) {
            addCriterion("uri <", value, "uri", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUriLessThanOrEqualTo(java.lang.String value) {
            addCriterion("uri <=", value, "uri", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUriBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("uri between", value1, value2, "uri", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUriNotBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("uri not between", value1, value2, "uri", "java.lang.String", "String");
            return (Criteria) this;
        }
        
        public Criteria andUriIn(List<Integer> values) {
            addCriterion("uri in", values, "uri", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUriNotIn(List<java.lang.String> values) {
            addCriterion("uri not in", values, "uri", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

         public Criteria andUrlLike(java.lang.String value) {
            addCriterion("url like", value, "url", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(java.lang.String value) {
            addCriterion("url not like", value, "url", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andUrlEqualTo(java.lang.String value) {
            addCriterion("url =", value, "url", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(java.lang.String value) {
            addCriterion("url <>", value, "url", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(java.lang.String value) {
            addCriterion("url >", value, "url", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(java.lang.String value) {
            addCriterion("url >=", value, "url", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(java.lang.String value) {
            addCriterion("url <", value, "url", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(java.lang.String value) {
            addCriterion("url <=", value, "url", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("url between", value1, value2, "url", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("url not between", value1, value2, "url", "java.lang.String", "String");
            return (Criteria) this;
        }
        
        public Criteria andUrlIn(List<Integer> values) {
            addCriterion("url in", values, "url", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<java.lang.String> values) {
            addCriterion("url not in", values, "url", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andMethodIsNull() {
            addCriterion("method is null");
            return (Criteria) this;
        }

        public Criteria andMethodIsNotNull() {
            addCriterion("method is not null");
            return (Criteria) this;
        }

         public Criteria andMethodLike(java.lang.String value) {
            addCriterion("method like", value, "method", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andMethodNotLike(java.lang.String value) {
            addCriterion("method not like", value, "method", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andMethodEqualTo(java.lang.String value) {
            addCriterion("method =", value, "method", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andMethodNotEqualTo(java.lang.String value) {
            addCriterion("method <>", value, "method", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThan(java.lang.String value) {
            addCriterion("method >", value, "method", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThanOrEqualTo(java.lang.String value) {
            addCriterion("method >=", value, "method", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andMethodLessThan(java.lang.String value) {
            addCriterion("method <", value, "method", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andMethodLessThanOrEqualTo(java.lang.String value) {
            addCriterion("method <=", value, "method", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andMethodBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("method between", value1, value2, "method", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andMethodNotBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("method not between", value1, value2, "method", "java.lang.String", "String");
            return (Criteria) this;
        }
        
        public Criteria andMethodIn(List<Integer> values) {
            addCriterion("method in", values, "method", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andMethodNotIn(List<java.lang.String> values) {
            addCriterion("method not in", values, "method", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andParameterIsNull() {
            addCriterion("parameter is null");
            return (Criteria) this;
        }

        public Criteria andParameterIsNotNull() {
            addCriterion("parameter is not null");
            return (Criteria) this;
        }

         public Criteria andParameterLike(java.lang.String value) {
            addCriterion("parameter like", value, "parameter", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andParameterNotLike(java.lang.String value) {
            addCriterion("parameter not like", value, "parameter", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andParameterEqualTo(java.lang.String value) {
            addCriterion("parameter =", value, "parameter", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andParameterNotEqualTo(java.lang.String value) {
            addCriterion("parameter <>", value, "parameter", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThan(java.lang.String value) {
            addCriterion("parameter >", value, "parameter", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThanOrEqualTo(java.lang.String value) {
            addCriterion("parameter >=", value, "parameter", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andParameterLessThan(java.lang.String value) {
            addCriterion("parameter <", value, "parameter", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andParameterLessThanOrEqualTo(java.lang.String value) {
            addCriterion("parameter <=", value, "parameter", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andParameterBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("parameter between", value1, value2, "parameter", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andParameterNotBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("parameter not between", value1, value2, "parameter", "java.lang.String", "String");
            return (Criteria) this;
        }
        
        public Criteria andParameterIn(List<Integer> values) {
            addCriterion("parameter in", values, "parameter", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andParameterNotIn(List<java.lang.String> values) {
            addCriterion("parameter not in", values, "parameter", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andUserAgentIsNull() {
            addCriterion("user_agent is null");
            return (Criteria) this;
        }

        public Criteria andUserAgentIsNotNull() {
            addCriterion("user_agent is not null");
            return (Criteria) this;
        }

         public Criteria andUserAgentLike(java.lang.String value) {
            addCriterion("user_agent like", value, "userAgent", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUserAgentNotLike(java.lang.String value) {
            addCriterion("user_agent not like", value, "userAgent", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andUserAgentEqualTo(java.lang.String value) {
            addCriterion("user_agent =", value, "userAgent", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUserAgentNotEqualTo(java.lang.String value) {
            addCriterion("user_agent <>", value, "userAgent", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUserAgentGreaterThan(java.lang.String value) {
            addCriterion("user_agent >", value, "userAgent", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUserAgentGreaterThanOrEqualTo(java.lang.String value) {
            addCriterion("user_agent >=", value, "userAgent", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUserAgentLessThan(java.lang.String value) {
            addCriterion("user_agent <", value, "userAgent", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUserAgentLessThanOrEqualTo(java.lang.String value) {
            addCriterion("user_agent <=", value, "userAgent", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUserAgentBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("user_agent between", value1, value2, "userAgent", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUserAgentNotBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("user_agent not between", value1, value2, "userAgent", "java.lang.String", "String");
            return (Criteria) this;
        }
        
        public Criteria andUserAgentIn(List<Integer> values) {
            addCriterion("user_agent in", values, "userAgent", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andUserAgentNotIn(List<java.lang.String> values) {
            addCriterion("user_agent not in", values, "userAgent", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andIpIsNull() {
            addCriterion("ip is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("ip is not null");
            return (Criteria) this;
        }

         public Criteria andIpLike(java.lang.String value) {
            addCriterion("ip like", value, "ip", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(java.lang.String value) {
            addCriterion("ip not like", value, "ip", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andIpEqualTo(java.lang.String value) {
            addCriterion("ip =", value, "ip", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(java.lang.String value) {
            addCriterion("ip <>", value, "ip", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(java.lang.String value) {
            addCriterion("ip >", value, "ip", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(java.lang.String value) {
            addCriterion("ip >=", value, "ip", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(java.lang.String value) {
            addCriterion("ip <", value, "ip", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(java.lang.String value) {
            addCriterion("ip <=", value, "ip", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andIpBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("ip between", value1, value2, "ip", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("ip not between", value1, value2, "ip", "java.lang.String", "String");
            return (Criteria) this;
        }
        
        public Criteria andIpIn(List<Integer> values) {
            addCriterion("ip in", values, "ip", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<java.lang.String> values) {
            addCriterion("ip not in", values, "ip", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andResultIsNull() {
            addCriterion("result is null");
            return (Criteria) this;
        }

        public Criteria andResultIsNotNull() {
            addCriterion("result is not null");
            return (Criteria) this;
        }

         public Criteria andResultLike(java.lang.String value) {
            addCriterion("result like", value, "result", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andResultNotLike(java.lang.String value) {
            addCriterion("result not like", value, "result", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andResultEqualTo(java.lang.String value) {
            addCriterion("result =", value, "result", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andResultNotEqualTo(java.lang.String value) {
            addCriterion("result <>", value, "result", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThan(java.lang.String value) {
            addCriterion("result >", value, "result", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThanOrEqualTo(java.lang.String value) {
            addCriterion("result >=", value, "result", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andResultLessThan(java.lang.String value) {
            addCriterion("result <", value, "result", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andResultLessThanOrEqualTo(java.lang.String value) {
            addCriterion("result <=", value, "result", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andResultBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("result between", value1, value2, "result", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andResultNotBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("result not between", value1, value2, "result", "java.lang.String", "String");
            return (Criteria) this;
        }
        
        public Criteria andResultIn(List<Integer> values) {
            addCriterion("result in", values, "result", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andResultNotIn(List<java.lang.String> values) {
            addCriterion("result not in", values, "result", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andPermissionsIsNull() {
            addCriterion("permissions is null");
            return (Criteria) this;
        }

        public Criteria andPermissionsIsNotNull() {
            addCriterion("permissions is not null");
            return (Criteria) this;
        }

         public Criteria andPermissionsLike(java.lang.String value) {
            addCriterion("permissions like", value, "permissions", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPermissionsNotLike(java.lang.String value) {
            addCriterion("permissions not like", value, "permissions", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andPermissionsEqualTo(java.lang.String value) {
            addCriterion("permissions =", value, "permissions", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPermissionsNotEqualTo(java.lang.String value) {
            addCriterion("permissions <>", value, "permissions", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPermissionsGreaterThan(java.lang.String value) {
            addCriterion("permissions >", value, "permissions", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPermissionsGreaterThanOrEqualTo(java.lang.String value) {
            addCriterion("permissions >=", value, "permissions", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPermissionsLessThan(java.lang.String value) {
            addCriterion("permissions <", value, "permissions", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPermissionsLessThanOrEqualTo(java.lang.String value) {
            addCriterion("permissions <=", value, "permissions", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPermissionsBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("permissions between", value1, value2, "permissions", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPermissionsNotBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("permissions not between", value1, value2, "permissions", "java.lang.String", "String");
            return (Criteria) this;
        }
        
        public Criteria andPermissionsIn(List<Integer> values) {
            addCriterion("permissions in", values, "permissions", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPermissionsNotIn(List<java.lang.String> values) {
            addCriterion("permissions not in", values, "permissions", "java.lang.String", "String");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;
        
        private String valueType;

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
        
        public String getValueType() {
        	return valueType;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler, String valueType) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
            this.valueType = valueType;
        }
        
        protected Criterion(String condition, Object value, String typeHandler) {
            this(condition, value, typeHandler, "String");
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler, String valueType) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
            this.valueType = valueType;
        }
        
        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
        	this(condition, value, secondValue, typeHandler, "String");
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}