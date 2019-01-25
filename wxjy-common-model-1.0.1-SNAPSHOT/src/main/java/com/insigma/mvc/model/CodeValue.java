//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.insigma.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CodeValue implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code_seq;
	@NotNull(message = "����ֵ����Ϊ��")
	private String code_value;
	@NotNull(message = "�������Ʋ���Ϊ��")
	private String code_name;
	private String id;
	private String name;
	private String isParent;
	private String code_describe;
	private String code_type;
	private String par_code_value;
	private String par_code_name;
	private String code_level;
	private String inino;
	private String code_spelling;
	private String count;
	private String sub_code_name;
	private String sub_code_value;
	private List<CodeValue> children;
	private String filter;

	public CodeValue() {
	}

	public String getCode_seq() {
		return code_seq;
	}

	public void setCode_seq(String code_seq) {
		this.code_seq = code_seq;
	}

	public String getCode_value() {
		return code_value;
	}

	public void setCode_value(String code_value) {
		this.code_value = code_value;
	}

	public String getCode_name() {
		return code_name;
	}

	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getCode_describe() {
		return code_describe;
	}

	public void setCode_describe(String code_describe) {
		this.code_describe = code_describe;
	}

	public String getCode_type() {
		return code_type;
	}

	public void setCode_type(String code_type) {
		this.code_type = code_type;
	}

	public String getPar_code_value() {
		return par_code_value;
	}

	public void setPar_code_value(String par_code_value) {
		this.par_code_value = par_code_value;
	}

	public String getPar_code_name() {
		return par_code_name;
	}

	public void setPar_code_name(String par_code_name) {
		this.par_code_name = par_code_name;
	}

	public String getCode_level() {
		return code_level;
	}

	public void setCode_level(String code_level) {
		this.code_level = code_level;
	}

	public String getInino() {
		return inino;
	}

	public void setInino(String inino) {
		this.inino = inino;
	}

	public String getCode_spelling() {
		return code_spelling;
	}

	public void setCode_spelling(String code_spelling) {
		this.code_spelling = code_spelling;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getSub_code_name() {
		return sub_code_name;
	}

	public void setSub_code_name(String sub_code_name) {
		this.sub_code_name = sub_code_name;
	}

	public String getSub_code_value() {
		return sub_code_value;
	}

	public void setSub_code_value(String sub_code_value) {
		this.sub_code_value = sub_code_value;
	}

	public List<CodeValue> getChildren() {
		return children;
	}

	public void setChildren(List<CodeValue> children) {
		this.children = children;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
}
