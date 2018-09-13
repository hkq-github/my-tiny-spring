package com.hkq.mtspring.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
	
	private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();
	
	public PropertyValues() {
	}
	
	public List<PropertyValue> getPropertyValueList() {
		return propertyValueList;
	}

	/**
	 * 添加PropertyValue, 若已经存在则覆盖
	 */
	public void addPropertyValue(PropertyValue value) {
		// TO-DO 实现若已经存在则覆盖
		propertyValueList.add(value);
	}
}
