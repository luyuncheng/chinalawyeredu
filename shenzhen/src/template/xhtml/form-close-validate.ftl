<#--
/*
 * $Id: Action.java 502296 2007-02-01 17:33:39Z niallp $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
-->
<#--
START SNIPPET: supported-validators
Only the following validators are supported:
* required validator
* requiredstring validator
* stringlength validator
* regex validator
* email validator
* url validator
* int validator
* double validator
END SNIPPET: supported-validators
修改记录
1、新增alertError方法,js的方式弹出消息
2、不一次性弹出所有的消息,而是输入一个弹出一个。每次都return error,如果有error的话
3、

-->
<#if ((parameters.validate?default(false) == true) && (parameters.performValidation?default(false) == true))>
<script type="text/javascript">
function alertError(field, error){
  alert(error);
  field.focus();
}
    function validateForm_${parameters.id}() {
        form = document.getElementById("${parameters.id}");
     //   clearErrorMessages(form);
     //   clearErrorLabels(form);

        var errors = false;
    <#list parameters.tagNames as tagName>
        <#list tag.getValidators("${tagName}") as validator>
        // field name: ${validator.fieldName}
        // validator name: ${validator.validatorType}
        if (form.elements['${validator.fieldName}']) {
            field = form.elements['${validator.fieldName}'];
            var error = "${validator.getMessage(action)?js_string}";
            <#if validator.validatorType = "required">
            if (field.value == "") {
                alertError(field, error);
            errors = true;
            return !errors;
            }
            <#elseif validator.validatorType = "requiredstring">
            if (field.value != null && (field.value == "" || field.value.replace(/^\s+|\s+$/g,"").length == 0)) {
                alertError(field, error);
            errors = true;
            return !errors;
            }
            <#elseif validator.validatorType = "stringlength">
            if (field.value != null) {
                var value = field.value;
                <#if validator.trim>
                    //trim field value
                    while (value.substring(0,1) == ' ')
                        value = value.substring(1, value.length);
                    while (value.substring(value.length-1, value.length) == ' ')
                        value = value.substring(0, value.length-1);
                </#if>
                if ((${validator.minLength?string} > -1 && value.length < ${validator.minLength?string}) ||
                    (${validator.maxLength?string} > -1 && value.length > ${validator.maxLength?string})) {
                    alertError(field, error);
            errors = true;
            return !errors;
                }
            } 
            <#elseif validator.validatorType = "regex">
            if (field.value != null && !field.value.match("${validator.expression?js_string}")) {
                alertError(field, error);
            errors = true;
            return !errors;
            }
            <#elseif validator.validatorType = "email">
            if (field.value != null && field.value.length > 0 && field.value.match(/\b(^[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\.[A-Za-z0-9-]+)*((\.[A-Za-z0-9]{2,})|(\.[A-Za-z0-9]{2,}\.[A-Za-z0-9]{2,}))$)\b/gi)==null) {
                alertError(field, error);
            errors = true;
            return !errors;
            }
            <#elseif validator.validatorType = "url">
            if (field.value != null && field.value.length > 0 && field.value.match(/(^(ftp|http|https):\/\/(\.[_A-Za-z0-9-]+)*(@?([A-Za-z0-9-])+)?(\.[A-Za-z0-9-]+)*((\.[A-Za-z0-9]{2,})|(\.[A-Za-z0-9]{2,}\.[A-Za-z0-9]{2,}))(:[0-9]+)?([/A-Za-z0-9?#_-]*)?$)/gi)==null) { 
                alertError(field, error);
            errors = true;
            return !errors;
            }
            <#elseif validator.validatorType = "int">
            if (field.value != null) {
                if (<#if validator.min?exists>parseInt(field.value) <
                     ${validator.min?string}<#else>false</#if> ||
                        <#if validator.max?exists>parseInt(field.value) >
                           ${validator.max?string}<#else>false</#if>) {
                    alertError(field, error);
            errors = true;
            return !errors;
                }
            }
            <#elseif validator.validatorType = "double">
            if (field.value != null) {
                var value = parseFloat(field.value);
                if (<#if validator.minInclusive?exists>value < ${validator.minInclusive?string}<#else>false</#if> ||
                        <#if validator.maxInclusive?exists>value > ${validator.maxInclusive?string}<#else>false</#if> ||
                        <#if validator.minExclusive?exists>value <= ${validator.minExclusive?string}<#else>false</#if> ||
                        <#if validator.maxExclusive?exists>value >= ${validator.maxExclusive?string}<#else>false</#if>) {
                    alertError(field, error);
            errors = true;
            return !errors;
                }
            }
            </#if>
        }
        </#list>
    </#list>




        return !errors;
    }
</script>
</#if>
