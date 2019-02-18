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
import com.lambkit.module.upms.rpc.query.UpmsUserQuery;

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
public class UpmsUserExample implements IQuery, Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public UpmsUserExample() {
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
    	s.append(UpmsUserQuery.me().getTableName());
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

         public Criteria andUserIdEqualTo(java.lang.Long value) {
            addCriterion("user_id =", value, "userId", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(java.lang.Long value) {
            addCriterion("user_id <>", value, "userId", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(java.lang.Long value) {
            addCriterion("user_id >", value, "userId", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(java.lang.Long value) {
            addCriterion("user_id >=", value, "userId", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(java.lang.Long value) {
            addCriterion("user_id <", value, "userId", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(java.lang.Long value) {
            addCriterion("user_id <=", value, "userId", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(java.lang.Long value1, java.lang.Long value2) {
            addCriterion("user_id between", value1, value2, "userId", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
            addCriterion("user_id not between", value1, value2, "userId", "java.lang.Long", "Float");
            return (Criteria) this;
        }
        
        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<java.lang.Long> values) {
            addCriterion("user_id not in", values, "userId", "java.lang.Long", "Float");
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
            addCriterion("username like", value, "username", "java.lang.String", "Float");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(java.lang.String value) {
            addCriterion("username not like", value, "username", "java.lang.String", "Float");
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
        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

         public Criteria andPasswordLike(java.lang.String value) {
            addCriterion("password like", value, "password", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(java.lang.String value) {
            addCriterion("password not like", value, "password", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andPasswordEqualTo(java.lang.String value) {
            addCriterion("password =", value, "password", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(java.lang.String value) {
            addCriterion("password <>", value, "password", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(java.lang.String value) {
            addCriterion("password >", value, "password", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(java.lang.String value) {
            addCriterion("password >=", value, "password", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(java.lang.String value) {
            addCriterion("password <", value, "password", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(java.lang.String value) {
            addCriterion("password <=", value, "password", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("password between", value1, value2, "password", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("password not between", value1, value2, "password", "java.lang.String", "String");
            return (Criteria) this;
        }
        
        public Criteria andPasswordIn(List<Integer> values) {
            addCriterion("password in", values, "password", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<java.lang.String> values) {
            addCriterion("password not in", values, "password", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andSaltIsNull() {
            addCriterion("salt is null");
            return (Criteria) this;
        }

        public Criteria andSaltIsNotNull() {
            addCriterion("salt is not null");
            return (Criteria) this;
        }

         public Criteria andSaltLike(java.lang.String value) {
            addCriterion("salt like", value, "salt", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andSaltNotLike(java.lang.String value) {
            addCriterion("salt not like", value, "salt", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andSaltEqualTo(java.lang.String value) {
            addCriterion("salt =", value, "salt", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andSaltNotEqualTo(java.lang.String value) {
            addCriterion("salt <>", value, "salt", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThan(java.lang.String value) {
            addCriterion("salt >", value, "salt", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThanOrEqualTo(java.lang.String value) {
            addCriterion("salt >=", value, "salt", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andSaltLessThan(java.lang.String value) {
            addCriterion("salt <", value, "salt", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andSaltLessThanOrEqualTo(java.lang.String value) {
            addCriterion("salt <=", value, "salt", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andSaltBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("salt between", value1, value2, "salt", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andSaltNotBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("salt not between", value1, value2, "salt", "java.lang.String", "String");
            return (Criteria) this;
        }
        
        public Criteria andSaltIn(List<Integer> values) {
            addCriterion("salt in", values, "salt", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andSaltNotIn(List<java.lang.String> values) {
            addCriterion("salt not in", values, "salt", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andRealnameIsNull() {
            addCriterion("realname is null");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNotNull() {
            addCriterion("realname is not null");
            return (Criteria) this;
        }

         public Criteria andRealnameLike(java.lang.String value) {
            addCriterion("realname like", value, "realname", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andRealnameNotLike(java.lang.String value) {
            addCriterion("realname not like", value, "realname", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andRealnameEqualTo(java.lang.String value) {
            addCriterion("realname =", value, "realname", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andRealnameNotEqualTo(java.lang.String value) {
            addCriterion("realname <>", value, "realname", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThan(java.lang.String value) {
            addCriterion("realname >", value, "realname", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThanOrEqualTo(java.lang.String value) {
            addCriterion("realname >=", value, "realname", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThan(java.lang.String value) {
            addCriterion("realname <", value, "realname", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThanOrEqualTo(java.lang.String value) {
            addCriterion("realname <=", value, "realname", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andRealnameBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("realname between", value1, value2, "realname", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andRealnameNotBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("realname not between", value1, value2, "realname", "java.lang.String", "String");
            return (Criteria) this;
        }
        
        public Criteria andRealnameIn(List<Integer> values) {
            addCriterion("realname in", values, "realname", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andRealnameNotIn(List<java.lang.String> values) {
            addCriterion("realname not in", values, "realname", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andAvatarIsNull() {
            addCriterion("avatar is null");
            return (Criteria) this;
        }

        public Criteria andAvatarIsNotNull() {
            addCriterion("avatar is not null");
            return (Criteria) this;
        }

         public Criteria andAvatarLike(java.lang.String value) {
            addCriterion("avatar like", value, "avatar", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andAvatarNotLike(java.lang.String value) {
            addCriterion("avatar not like", value, "avatar", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andAvatarEqualTo(java.lang.String value) {
            addCriterion("avatar =", value, "avatar", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andAvatarNotEqualTo(java.lang.String value) {
            addCriterion("avatar <>", value, "avatar", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThan(java.lang.String value) {
            addCriterion("avatar >", value, "avatar", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThanOrEqualTo(java.lang.String value) {
            addCriterion("avatar >=", value, "avatar", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThan(java.lang.String value) {
            addCriterion("avatar <", value, "avatar", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThanOrEqualTo(java.lang.String value) {
            addCriterion("avatar <=", value, "avatar", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andAvatarBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("avatar between", value1, value2, "avatar", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andAvatarNotBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("avatar not between", value1, value2, "avatar", "java.lang.String", "String");
            return (Criteria) this;
        }
        
        public Criteria andAvatarIn(List<Integer> values) {
            addCriterion("avatar in", values, "avatar", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andAvatarNotIn(List<java.lang.String> values) {
            addCriterion("avatar not in", values, "avatar", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

         public Criteria andPhoneLike(java.lang.String value) {
            addCriterion("phone like", value, "phone", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(java.lang.String value) {
            addCriterion("phone not like", value, "phone", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andPhoneEqualTo(java.lang.String value) {
            addCriterion("phone =", value, "phone", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(java.lang.String value) {
            addCriterion("phone <>", value, "phone", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(java.lang.String value) {
            addCriterion("phone >", value, "phone", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(java.lang.String value) {
            addCriterion("phone >=", value, "phone", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(java.lang.String value) {
            addCriterion("phone <", value, "phone", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(java.lang.String value) {
            addCriterion("phone <=", value, "phone", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("phone between", value1, value2, "phone", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("phone not between", value1, value2, "phone", "java.lang.String", "String");
            return (Criteria) this;
        }
        
        public Criteria andPhoneIn(List<Integer> values) {
            addCriterion("phone in", values, "phone", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<java.lang.String> values) {
            addCriterion("phone not in", values, "phone", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

         public Criteria andEmailLike(java.lang.String value) {
            addCriterion("email like", value, "email", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(java.lang.String value) {
            addCriterion("email not like", value, "email", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andEmailEqualTo(java.lang.String value) {
            addCriterion("email =", value, "email", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(java.lang.String value) {
            addCriterion("email <>", value, "email", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(java.lang.String value) {
            addCriterion("email >", value, "email", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(java.lang.String value) {
            addCriterion("email >=", value, "email", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(java.lang.String value) {
            addCriterion("email <", value, "email", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(java.lang.String value) {
            addCriterion("email <=", value, "email", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("email between", value1, value2, "email", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(java.lang.String value1, java.lang.String value2) {
            addCriterion("email not between", value1, value2, "email", "java.lang.String", "String");
            return (Criteria) this;
        }
        
        public Criteria andEmailIn(List<Integer> values) {
            addCriterion("email in", values, "email", "java.lang.String", "String");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<java.lang.String> values) {
            addCriterion("email not in", values, "email", "java.lang.String", "String");
            return (Criteria) this;
        }
        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

         public Criteria andSexEqualTo(java.lang.Integer value) {
            addCriterion("sex =", value, "sex", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(java.lang.Integer value) {
            addCriterion("sex <>", value, "sex", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(java.lang.Integer value) {
            addCriterion("sex >", value, "sex", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(java.lang.Integer value) {
            addCriterion("sex >=", value, "sex", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(java.lang.Integer value) {
            addCriterion("sex <", value, "sex", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(java.lang.Integer value) {
            addCriterion("sex <=", value, "sex", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andSexBetween(java.lang.Integer value1, java.lang.Integer value2) {
            addCriterion("sex between", value1, value2, "sex", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
            addCriterion("sex not between", value1, value2, "sex", "java.lang.Integer", "Float");
            return (Criteria) this;
        }
        
        public Criteria andSexIn(List<Integer> values) {
            addCriterion("sex in", values, "sex", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<java.lang.Integer> values) {
            addCriterion("sex not in", values, "sex", "java.lang.Integer", "Float");
            return (Criteria) this;
        }
        public Criteria andLockedIsNull() {
            addCriterion("locked is null");
            return (Criteria) this;
        }

        public Criteria andLockedIsNotNull() {
            addCriterion("locked is not null");
            return (Criteria) this;
        }

         public Criteria andLockedEqualTo(java.lang.Integer value) {
            addCriterion("locked =", value, "locked", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andLockedNotEqualTo(java.lang.Integer value) {
            addCriterion("locked <>", value, "locked", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andLockedGreaterThan(java.lang.Integer value) {
            addCriterion("locked >", value, "locked", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andLockedGreaterThanOrEqualTo(java.lang.Integer value) {
            addCriterion("locked >=", value, "locked", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andLockedLessThan(java.lang.Integer value) {
            addCriterion("locked <", value, "locked", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andLockedLessThanOrEqualTo(java.lang.Integer value) {
            addCriterion("locked <=", value, "locked", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andLockedBetween(java.lang.Integer value1, java.lang.Integer value2) {
            addCriterion("locked between", value1, value2, "locked", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andLockedNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
            addCriterion("locked not between", value1, value2, "locked", "java.lang.Integer", "Float");
            return (Criteria) this;
        }
        
        public Criteria andLockedIn(List<Integer> values) {
            addCriterion("locked in", values, "locked", "java.lang.Integer", "Float");
            return (Criteria) this;
        }

        public Criteria andLockedNotIn(List<java.lang.Integer> values) {
            addCriterion("locked not in", values, "locked", "java.lang.Integer", "Float");
            return (Criteria) this;
        }
        public Criteria andCtimeIsNull() {
            addCriterion("ctime is null");
            return (Criteria) this;
        }

        public Criteria andCtimeIsNotNull() {
            addCriterion("ctime is not null");
            return (Criteria) this;
        }

         public Criteria andCtimeEqualTo(java.lang.Long value) {
            addCriterion("ctime =", value, "ctime", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andCtimeNotEqualTo(java.lang.Long value) {
            addCriterion("ctime <>", value, "ctime", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThan(java.lang.Long value) {
            addCriterion("ctime >", value, "ctime", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThanOrEqualTo(java.lang.Long value) {
            addCriterion("ctime >=", value, "ctime", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThan(java.lang.Long value) {
            addCriterion("ctime <", value, "ctime", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThanOrEqualTo(java.lang.Long value) {
            addCriterion("ctime <=", value, "ctime", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andCtimeBetween(java.lang.Long value1, java.lang.Long value2) {
            addCriterion("ctime between", value1, value2, "ctime", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andCtimeNotBetween(java.lang.Long value1, java.lang.Long value2) {
            addCriterion("ctime not between", value1, value2, "ctime", "java.lang.Long", "Float");
            return (Criteria) this;
        }
        
        public Criteria andCtimeIn(List<Integer> values) {
            addCriterion("ctime in", values, "ctime", "java.lang.Long", "Float");
            return (Criteria) this;
        }

        public Criteria andCtimeNotIn(List<java.lang.Long> values) {
            addCriterion("ctime not in", values, "ctime", "java.lang.Long", "Float");
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