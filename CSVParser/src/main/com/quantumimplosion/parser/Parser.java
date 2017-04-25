package com.quantumimplosion.parser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Parser {

	private DataInserter dataInserter;

	public Parser()
	{
		dataInserter = new DataInserter();
	}

	public void parseSummary(String summaryFilePath)
	{
		File csvData = new File(summaryFilePath);
		CSVParser parser;
		try {
			parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.DEFAULT);
		} catch (IOException e) {
			//Do something useful
			e.printStackTrace();
			return;
		}
		for (CSVRecord record : parser)
		{

			parsePerformance(record.get(0));
		}
	}

	public void parsePerformance(String serialNum)
	{
		File csvData = new File(serialNum + "-perform.csv");
		CSVParser parser;
		try {
			parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.DEFAULT);
		} catch (IOException e) {
			//Do something useful
			e.printStackTrace();
			return;
		}
		Map<String, Integer> headerMap = parser.getHeaderMap();
		for (CSVRecord record: parser)
		{
			Set<Integer> invalidColumns = Validator.validatePerformanceRecord(record);
			for (String table : CSVParserConstants.tableToHeadersMap.keySet())
			{
				parseData(record, table, invalidColumns, headerMap);
			}
		}
	}

	private void parseData(CSVRecord record, String tableName, Set<Integer> invalidColumns, Map<String, Integer>headerMap)
	{
		List<String> headers = CSVParserConstants.tableToHeadersMap.get(tableName);
		List<Object> values = new ArrayList<Object>();
		List<Integer> argTypes = CSVParserConstants.tableToArgTypeMap.get(tableName);
		for (int i = 0; i < headers.size(); i++)
		{
			String header = headers.get(i);
			if (invalidColumns.contains(headerMap.get(header)))
			{
				values.add(null);
			}
			else
			{
				if (argTypes.get(i) == CSVParserConstants.STRINGTYPE)
				{
					values.add(record.get(header));
				}
				else
				{
					values.add(Double.parseDouble(record.get(header)));
				}
			}
		}
		dataInserter.insertData(tableName, values);
	}

	//Input: list of summary files
	public static void main(String[] args)
	{
		Parser p = new Parser();
		for (String arg : args)
		{
			p.parseSummary(arg);
		}

	}

}
