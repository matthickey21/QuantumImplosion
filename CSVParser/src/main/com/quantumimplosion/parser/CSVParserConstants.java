package com.quantumimplosion.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVParserConstants {

	public static final int STRINGTYPE = 1;
	public static final int DOUBLETYPE = 2;
	public static final Map<String, String> tableToStatementMap;
	public static final Map<String, List<Integer>> tableToArgTypeMap;
	static
	{
		Map<String, String> temp = new HashMap<String, String>();
		temp.put("node_information", "INSERT INTO node_information (system_id, from, to, nodeUpSinceMostRecent, nodeCountOffline, nodeCountMissing) VALUES ('%s', '%s', '%s', '%s', %f, %f);");
		temp.put("system", "INSERT INTO system (system_id, system_companyName, system_model, updated, system_install_date) VALUES ('%s', '%s', '%s', '%s', '%s');");
		temp.put("file_io", "INSERT INTO file_io (system_id, from, to, totalWriteIOsHistVlun, totalWriteIOsHistPortTargets, delAcks, delAcksPct, writesGt16s, writesGtXmsPct, writes1msPct, readsGtXmsPct, readsXmsPct, totalsGtXmsPct, totalsXmsPct, portReadAvgIOSizeKB, portWriteAvgIOSizeKB, portTotalAvgIOSizeKB) VALUES ('%s', '%s', '%s', %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f);");
		temp.put("bandwidth", "INSERT INTO bandwidth (system_id, from, to, portReadBandwidthMBPS, portWriteBandwidthMBPS, portTotalBandwidthMBPS) VALUES ('%s', '%s', '%s', %f, %f, %f);");
		temp.put("deduplication", "INSERT INTO deduplication(system_id, from, to, ddsSizeUsedTiB , ddsSizeUsedTiBPrevious, capacity_total_dedupeRatio) VALUES ('%s', '%s', '%s', %f, %f, %f);");
		temp.put("storage_usage", "INSERT INTO storage_usage (system_id, from, to, capacity_total_sizeTiB, capacity_total_freePct, capacity_byType_fc_sizeTiB, capacity_byType_nl_sizeTiB, capacity_byType_ssd_sizeTiB, virtualCapacity_byType_tdvv_vvCount, virtualCapacity_byType_tdvv_sizeTiB, vvCountHistVlun, vvCountHistVlunPrevious) VALUES ('%s', '%s', '%s', %f, %f, %f, %f, %f, %f, %f, %f, %f);");
		temp.put("cpu_usage", "INSERT INTO cpu_usage (system_id, from, to, cpuLatestSysAvgPct, cpuLatestUserAvgPct, cpuLatestTotalAvgPct, cpuLatestSysMaxPct, cpuLatestUserMaxPct, cpuLatestTotalMaxPct) VALUES ('%s', '%s', '%s', %f, %f, %f, %f, %f, %f);");

		Map<String, List<Integer>> temp2 = new HashMap<String, List<Integer>>();
		temp2.put("node_information", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE));
		temp2.put("system", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, STRINGTYPE, STRINGTYPE));
		temp2.put("file_io", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE));
		temp2.put("bandwidth", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE));
		temp2.put("deduplication", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE));
		temp2.put("storage_usage", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE));
		temp2.put("cpu_usage", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE));
		tableToArgTypeMap = Collections.unmodifiableMap(temp2);
		tableToStatementMap = Collections.unmodifiableMap(temp);
	}
	
	public static final Map<String, List<String>> tableToHeadersMap;
	static
	{
		Map<String, List<String>> temp = new HashMap<String, List<String>>();
		ArrayList<String> headers = new ArrayList<String>(Arrays.asList("system_id", "from", "to", "nodeUpSinceMostRecent", "nodeCountOffline", "nodeCountMissing"));
		temp.put("node_information", headers);
		temp.put("system", Arrays.asList("system_id", "system_companyName", "system_model", "updated", "system_install_date"));
		tableToHeadersMap = Collections.unmodifiableMap(temp);
	}
}
