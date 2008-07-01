package org.csstudio.nams.configurator.beans.filters;

import org.csstudio.nams.common.material.regelwerk.Operator;
import org.csstudio.nams.common.material.regelwerk.SuggestedProcessVariableType;
import org.csstudio.nams.configurator.beans.AbstractConfigurationBean;

public class PVFilterConditionBean extends
		AbstractConfigurationBean<PVFilterConditionBean> implements AddOnBean {

	private SuggestedProcessVariableType suggestedType;
	private String channelName;
	private Operator operator;
	private String compareValue;
	
	public enum PropertyNames{
		suggestedType, channelName, operator, compareValue;
	}

	public SuggestedProcessVariableType getSuggestedType() {
		return suggestedType;
	}

	public void setSuggestedType(SuggestedProcessVariableType suggestedType) {
		this.suggestedType = suggestedType;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getCompareValue() {
		return compareValue;
	}

	public void setCompareValue(String compareValue) {
		this.compareValue = compareValue;
	}

	@Override
	public PVFilterConditionBean getClone() {
		PVFilterConditionBean bean = new PVFilterConditionBean();
		bean.setChannelName(channelName);
		bean.setCompareValue(compareValue);
		bean.setOperator(operator);
		bean.setSuggestedType(suggestedType);
		return bean;
	}

	@Override
	public void updateState(PVFilterConditionBean bean) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Unimplemented method");
	}

	public String getDisplayName() {
		return channelName + " " + operator.toString() + " " + compareValue
				+ " " + suggestedType;
	}

	public int getID() {
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((channelName == null) ? 0 : channelName.hashCode());
		result = prime * result
				+ ((compareValue == null) ? 0 : compareValue.hashCode());
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
		result = prime * result
				+ ((suggestedType == null) ? 0 : suggestedType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PVFilterConditionBean other = (PVFilterConditionBean) obj;
		if (channelName == null) {
			if (other.channelName != null)
				return false;
		} else if (!channelName.equals(other.channelName))
			return false;
		if (compareValue == null) {
			if (other.compareValue != null)
				return false;
		} else if (!compareValue.equals(other.compareValue))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (suggestedType == null) {
			if (other.suggestedType != null)
				return false;
		} else if (!suggestedType.equals(other.suggestedType))
			return false;
		return true;
	}

}
