package com.ght.learn.ffs.dto.sys;

import framework.bean.dto.BaseDto;

public class RoleDto extends BaseDto {

	private static final long serialVersionUID = -6787441837505684327L;

	private Long recId;

	/* 角色中文名 */
	private String nameCn;

	/* 角色英文名 */
	private String nameEn;

	private String name;

	public Long getRecId() {
		return recId;
	}

	public void setRecId(Long recId) {
		this.recId = recId;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
