package com.pack.java;

import java.util.Date;

public class RollUpCategoryAttributes
{
	//Attributes to confirm roll up
	private Integer priceComponentId;
	private Integer priceStructureId;
	
	private Integer rollUpCategoryId;
	private String rollUpCategoryName;
	
	private Integer currency;
	private Integer currenyUnit;
	private Integer quantityGranularity;
	
	private Integer calculationMethod;
	private Integer buySellIndicator;
	private Integer expRevIndicator;
	private Integer billableQuantityFormula;
	private Integer billableQuantityDefinition;
	private Integer billableQuantityPriceComponent;

	private Integer tierQuantityUnit;
	private Integer tierMethod;
	private Integer tierType;
	private Integer tierLevel;
	private Integer tierStartQuantity;
	private Integer tierEndQuantity;
	private Integer tierQuantityFormula;
	
	private Date priceStructureBeginDate;
	private Date priceStructureEndDate;
	
	public RollUpCategoryAttributes() {
		// TODO Auto-generated constructor stub
	}
	
	public RollUpCategoryAttributes(Integer priceComponentId ,Integer priceStructureId, Integer rollUpCategoryId, String rollUpCategoryName ,Integer currency,Integer currenyUnit,Integer quantityGranularity,Integer calculationMethod,Integer buySellIndicator,
	                      Integer expRevIndicator,Integer billableQuantityFormula,Integer billableQuantityDefinition,Integer billableQuantityPriceComponent,
	                      Integer tierQuantityUnit,Integer tierMethod,Integer tierType,Integer tierLevel,Integer tierStartQuantity,Integer tierEndQuantity,
	                      Integer tierQuantityFormula,Date priceStructureBeginDate,Date priceStructureEndDate)
	{
		this.priceComponentId = priceComponentId;
		this.priceStructureId = priceStructureId;
		this.rollUpCategoryId = rollUpCategoryId;
		this.rollUpCategoryName = rollUpCategoryName;
		this.currency = currency;
		this.currenyUnit = currenyUnit;
		this.quantityGranularity = quantityGranularity;
		this.calculationMethod = calculationMethod;
		this.buySellIndicator = buySellIndicator;
		this.expRevIndicator = expRevIndicator;
		this.billableQuantityFormula = billableQuantityFormula;
		this.billableQuantityDefinition = billableQuantityDefinition;
		this.billableQuantityPriceComponent = billableQuantityPriceComponent;
		this.tierQuantityUnit = tierQuantityUnit;
		this.tierMethod = tierMethod;
		this.tierType = tierType;
		this.tierLevel = tierLevel;
		this.tierStartQuantity = tierStartQuantity;
		this.tierEndQuantity = tierEndQuantity;
		this.tierQuantityFormula = tierQuantityFormula;
		this.priceStructureBeginDate = priceStructureBeginDate;
		this.priceStructureEndDate = priceStructureEndDate;
	}
	
	public Integer getPriceComponentId()
	{
		return priceComponentId;
	}

	public void setPriceComponentId(Integer priceComponentId)
	{
		this.priceComponentId = priceComponentId;
	}

	public Integer getPriceStructureId()
	{
		return priceStructureId;
	}

	public void setPriceStructureId(Integer priceStructureId)
	{
		this.priceStructureId = priceStructureId;
	}

	public Integer getRollUpCategoryId()
	{
		return rollUpCategoryId;
	}

	public void setRollUpCategoryId(Integer rollUpCategoryId)
	{
		this.rollUpCategoryId = rollUpCategoryId;
	}

	public String getRollUpCategoryName()
	{
		return rollUpCategoryName;
	}

	public void setRollUpCategoryName(String rollUpCategoryName)
	{
		this.rollUpCategoryName = rollUpCategoryName;
	}

	public Integer getCurrency()
	{
		return currency;
	}

	public void setCurrency(Integer currency)
	{
		this.currency = currency;
	}

	public Integer getCurrenyUnit()
	{
		return currenyUnit;
	}

	public void setCurrenyUnit(Integer currenyUnit)
	{
		this.currenyUnit = currenyUnit;
	}

	public Integer getQuantityGranularity()
	{
		return quantityGranularity;
	}

	public void setQuantityGranularity(Integer quantityGranularity)
	{
		this.quantityGranularity = quantityGranularity;
	}

	public Integer getCalculationMethod()
	{
		return calculationMethod;
	}

	public void setCalculationMethod(Integer calculationMethod)
	{
		this.calculationMethod = calculationMethod;
	}

	public Integer getBuySellIndicator()
	{
		return buySellIndicator;
	}

	public void setBuySellIndicator(Integer buySellIndicator)
	{
		this.buySellIndicator = buySellIndicator;
	}

	public Integer getExpRevIndicator()
	{
		return expRevIndicator;
	}

	public void setExpRevIndicator(Integer expRevIndicator)
	{
		this.expRevIndicator = expRevIndicator;
	}

	public Integer getBillableQuantityFormula()
	{
		return billableQuantityFormula;
	}

	public void setBillableQuantityFormula(Integer billableQuantityFormula)
	{
		this.billableQuantityFormula = billableQuantityFormula;
	}

	public Integer getBillableQuantityDefinition()
	{
		return billableQuantityDefinition;
	}

	public void setBillableQuantityDefinition(Integer billableQuantityDefinition)
	{
		this.billableQuantityDefinition = billableQuantityDefinition;
	}

	public Integer getBillableQuantityPriceComponent()
	{
		return billableQuantityPriceComponent;
	}

	public void setBillableQuantityPriceComponent(Integer billableQuantityPriceComponent)
	{
		this.billableQuantityPriceComponent = billableQuantityPriceComponent;
	}

	public Integer getTierQuantityUnit()
	{
		return tierQuantityUnit;
	}

	public void setTierQuantityUnit(Integer tierQuantityUnit)
	{
		this.tierQuantityUnit = tierQuantityUnit;
	}

	public Integer getTierMethod()
	{
		return tierMethod;
	}

	public void setTierMethod(Integer tierMethod)
	{
		this.tierMethod = tierMethod;
	}

	public Integer getTierType()
	{
		return tierType;
	}

	public void setTierType(Integer tierType)
	{
		this.tierType = tierType;
	}

	public Integer getTierLevel()
	{
		return tierLevel;
	}

	public void setTierLevel(Integer tierLevel)
	{
		this.tierLevel = tierLevel;
	}

	public Integer getTierStartQuantity()
	{
		return tierStartQuantity;
	}

	public void setTierStartQuantity(Integer tierStartQuantity)
	{
		this.tierStartQuantity = tierStartQuantity;
	}

	public Integer getTierEndQuantity()
	{
		return tierEndQuantity;
	}

	public void setTierEndQuantity(Integer tierEndQuantity)
	{
		this.tierEndQuantity = tierEndQuantity;
	}

	public Integer getTierQuantityFormula()
	{
		return tierQuantityFormula;
	}

	public void setTierQuantityFormula(Integer tierQuantityFormula)
	{
		this.tierQuantityFormula = tierQuantityFormula;
	}

	public Date getPriceStructureBeginDate()
	{
		return priceStructureBeginDate;
	}

	public void setPriceStructureBeginDate(Date priceStructureBeginDate)
	{
		this.priceStructureBeginDate = priceStructureBeginDate;
	}

	public Date getPriceStructureEndDate()
	{
		return priceStructureEndDate;
	}

	public void setPriceStructureEndDate(Date priceStructureEndDate)
	{
		this.priceStructureEndDate = priceStructureEndDate;
	}

	class RollUpCategoryKey 
	{
		Integer priceStructureId;
		Integer rollUpCategoryId;
	}
}