package com.xc.common.entity;

import lombok.Data;

@Data
public class ErrorMessage {

	private String moduleName;

	private String description;

	private String errorMsg;

	private int errorCode;
}
