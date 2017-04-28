package com.pack.java.dto.generator;
import com.sungard.pipeline.model.dto.IDTO;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class temp implements IDTO {


private static final long serialVersionUID = 1L;


private Integer currencyId;
private String displayValueDesc;
private Integer displayValue;
public Integer getCurrencyId() {
	return currencyId;
}
public void setCurrencyId(Integer currencyId) {
 this.currencyId = currencyId;
}
public String getDisplayValueDesc() {
	return displayValueDesc;
}
public void setDisplayValueDesc(String displayValueDesc) {
 this.displayValueDesc = displayValueDesc;
}
public Integer getDisplayValue() {
	return displayValue;
}
public void setDisplayValue(Integer displayValue) {
 this.displayValue = displayValue;
}
public static String getFindMethod() {	
 return "FindAvailableCurrencies";	}

public static String getTransColumns() {	
 return "DISPLAYVALUE";	}

public static Map<String, String> loadAttributeMap() {
		Map<String, String> columnFieldMap = new HashMap<String, String>();
columnFieldMap.put("CURRENCY_ID","currencyId");
columnFieldMap.put("DISPLAYVALUE_DESC","displayValueDesc");
columnFieldMap.put("DISPLAYVALUE","displayValue");
return columnFieldMap;
	}
}
