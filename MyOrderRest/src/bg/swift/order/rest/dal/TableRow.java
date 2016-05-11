package bg.swift.order.rest.dal;

import java.util.HashMap;
import java.util.Map;

public class TableRow {
	
	Map<String, Object> data = new HashMap<>();
	
	public TableRow(Map<String, Object> data) {
		super();
		this.data = data;
	}

	public Object getValue(String columnName) {
		return data.get(columnName);
	}
}
