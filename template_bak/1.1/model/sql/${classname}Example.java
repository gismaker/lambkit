##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.model.sql;

import com.jfinal.kit.StrKit;
import com.lambkit.common.service.ServiceKit;
import com.lambkit.db.sql.IQuery;
import com.lambkit.db.sql.QueryParas;
import $!{basepackage}.service.${classname}Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

#parse("/template/java_author.include")
public class ${classname}Example implements IQuery, Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public ${classname}Example() {
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
    	${classname}Service service = ServiceKit.inject(${classname}Service.class);
    	s.append(service.getTableName());
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

#set($valueType = "String")##
#foreach($column in $columns)##
        public Criteria and${column.upperName}IsNull() {
            addCriterion("${column.name} is null");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}IsNotNull() {
            addCriterion("${column.name} is not null");
            return (Criteria) this;
        }

 #if(${column.javaType}=="java.lang.String")##       
        public Criteria and${column.upperName}Like(${column.javaType} value) {
            addCriterion("${column.name} like", value, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}NotLike(${column.javaType} value) {
            addCriterion("${column.name} not like", value, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }
#end##
#if(${column.javaType}=="java.lang.Integer" || ${column.javaType}=="java.lang.Short" || 
${column.javaType}=="java.lang.Long" || ${column.javaType}=="java.lang.Float" || 
${column.javaType}=="java.lang.Double" || ${column.javaType}=="java.math.BigDecimal")##
#set($valueType = "Float")##
#elseif(${column.javaType}=="java.sql.Date" || ${column.javaType}=="java.sql.Time" ||
${column.javaType}=="java.sql.Timestamp" || ${column.javaType}=="java.util.Calendar")##
#set($valueType = "DateTime")##
#else##
#set($valueType = "String")##
#end##
        public Criteria and${column.upperName}EqualTo(${column.javaType} value) {
            addCriterion("${column.name} =", value, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}NotEqualTo(${column.javaType} value) {
            addCriterion("${column.name} <>", value, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}GreaterThan(${column.javaType} value) {
            addCriterion("${column.name} >", value, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}GreaterThanOrEqualTo(${column.javaType} value) {
            addCriterion("${column.name} >=", value, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}LessThan(${column.javaType} value) {
            addCriterion("${column.name} <", value, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}LessThanOrEqualTo(${column.javaType} value) {
            addCriterion("${column.name} <=", value, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}Between(${column.javaType} value1, ${column.javaType} value2) {
            addCriterion("${column.name} between", value1, value2, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}NotBetween(${column.javaType} value1, ${column.javaType} value2) {
            addCriterion("${column.name} not between", value1, value2, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }
        
        public Criteria and${column.upperName}In(List<${column.javaType}> values) {
            addCriterion("${column.name} in", values, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}NotIn(List<${column.javaType}> values) {
            addCriterion("${column.name} not in", values, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }
#end##
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