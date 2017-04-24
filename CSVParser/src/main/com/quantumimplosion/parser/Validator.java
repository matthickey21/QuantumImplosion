package com.quantumimplosion.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVRecord;

public class Validator {

	private static final ArrayList<Class<?>> expectedPerformanceTypes;
	static {
		ArrayList<Class<?>> temp = new ArrayList<Class<?>>();
		//Hardcoded column types
		arrayListAddAll(temp, Double.class, String.class, String.class, String.class, String.class, Double.class,
				Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class,
				Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class,
				Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class,
				Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class,
				Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class,
				Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class,
				Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class,
				Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class,
				Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class,
				Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class,
				Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class,
				Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class,
				Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class,
				Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, String.class,
				Double.class, Double.class, Double.class);
		expectedPerformanceTypes = temp;
	}

	private static void arrayListAddAll(ArrayList list, Object... objects) {
		for (Object o : objects) {
			list.add(o);
		}
	}
	
	public static Set<Integer> validateSummaryRecord(CSVRecord record)
	{
		return null;
	}
	
	public static Set<Integer> validatePerformanceRecord(CSVRecord record)
	{
		Set<Integer> invalidColumns = new HashSet<Integer>();
		for (int i = 0; i < record.size(); i++)
		{
			if (expectedPerformanceTypes.get(i) == Double.class)
			{
				try
				{
					Integer.parseInt(record.get(i));
				}
				catch (NumberFormatException nfe)
				{
					invalidColumns.add(i);
				}
			}
		}
		return invalidColumns;
	}
}
