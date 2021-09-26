/*
 *    Copyright 2009-2021 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.session.Configuration;

/**
 * An actual SQL String got from an {@link SqlSource} after having processed any dynamic content.
 * The SQL may have SQL placeholders "?" and an list (ordered) of an parameter mappings
 * with the additional information for each parameter (at least the property name of the input object to read
 * the value from).
 * <p>
 * Can also have additional parameters that are created by the dynamic language (for loops, bind...).
 *
 * @author Clinton Begin
 *
 * 处理任何动态内容后从 {@link SqlSource} 获得 SQL 字符串。
 * SQL 可能有 SQL 占位符 “？” 和一个有序的附带参数附加信息的参数映射列表（至少可以通过对象的属性名获取值）。
 * 也可以有由动态语言创建的附加参数（for 循环、绑定...）。
 */
public class BoundSql {

  private final String sql;
  private final List<ParameterMapping> parameterMappings;
  private final Object parameterObject;
  private final Map<String, Object> additionalParameters;
  private final MetaObject metaParameters;

  public BoundSql(Configuration configuration, String sql, List<ParameterMapping> parameterMappings, Object parameterObject) {
    this.sql = sql;
    this.parameterMappings = parameterMappings;
    this.parameterObject = parameterObject;
    this.additionalParameters = new HashMap<>();
    this.metaParameters = configuration.newMetaObject(additionalParameters);
  }

  // 获取 sql 语句（String）
  public String getSql() {
    return sql;
  }

  // 获取参数映射表
  public List<ParameterMapping> getParameterMappings() {
    return parameterMappings;
  }

  // 获取参数对象
  public Object getParameterObject() {
    return parameterObject;
  }

  // 判断是否附加参数
  public boolean hasAdditionalParameter(String name) {
    String paramName = new PropertyTokenizer(name).getName();
    return additionalParameters.containsKey(paramName);
  }

  // 设定附加参数
  public void setAdditionalParameter(String name, Object value) {
    metaParameters.setValue(name, value);
  }

  // 获取附加参数
  public Object getAdditionalParameter(String name) {
    return metaParameters.getValue(name);
  }
}
