package quantumimplosion.iro.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Parser {

	private static final Map<String, List<String>> tableToHeadersMap;
	static
	{
		Map<String, List<String>> temp = new HashMap<String, List<String>>();
		ArrayList<String> headers = new ArrayList<String>(Arrays.asList("system_id", "from", "to", "nodeUpSinceMostRecent", "nodeCountOffline", "nodeCountMissing"));
		temp.put("node_information", headers);
		temp.put("system", Arrays.asList("system_id", "system_companyName", "system_model", "updated", "system_install_date"));
		tableToHeadersMap = Collections.unmodifiableMap(temp);
	}
	private DataInserter dataInserter;

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
			for (String table : tableToHeadersMap.keySet())
			{
				parseData(record, table, invalidColumns, headerMap);
			}
		}
	}

	private void parseData(CSVRecord record, String tableName, Set<Integer> invalidColumns, Map<String, Integer>headerMap)
	{
		List<String> headers = tableToHeadersMap.get(tableName);
		List<Object> values = new ArrayList<Object>();
		List<Integer> argTypes = DataInserter.tableToArgTypeMap.get(tableName);
		for (int i = 0; i < headers.size(); i++)
		{
			String header = headers.get(i);
			if (invalidColumns.contains(headerMap.get(header)))
			{
				values.add(null);
			}
			else
			{
				if (argTypes.get(i) == DataInserter.STRINGTYPE)
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

}
