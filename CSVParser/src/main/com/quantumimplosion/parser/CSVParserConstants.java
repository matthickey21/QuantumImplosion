package com.quantumimplosion.parser;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVParserConstants {

	public static final int STRINGTYPE = 1;
	public static final int DOUBLETYPE = 2;
	
	public static final Map<String, List<Integer>> tableToArgTypeMap;
	static {
		Map<String, List<Integer>> temp = new HashMap<String, List<Integer>>();
		temp.put("Node Information", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE));
		temp.put("System", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE));
		temp.put("File I/O", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE));
		temp.put("Bandwith", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE));
//		temp.put("deduplication", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE));
		temp.put("Storage Usage", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE));
		temp.put("CPU Usage", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE));
		tableToArgTypeMap = Collections.unmodifiableMap(temp);
	}
	
	public static final Map<String, String> tableToStatementMap;
	static
	{
		Map<String, String> temp2 = new HashMap<String, String>();
		temp2.put("Node Information", "INSERT INTO \"Node Information\" (system_id, \"from\", \"to\", \"nodeUpSinceMostRecent\", \"nodeCountOffline\", \"nodeCountMissing\") VALUES (?, ?, ?, ?, ?, ?);");
		temp2.put("System", "INSERT INTO System (system_id, system_companyName, system_model, updated, system_install_date, capacity_total_sizeTiB, capacity_total_freePct, capacity_byType_fc_sizeTiB, capacity_byType_nl_sizeTiB, capacity_byType_ssd_sizeTiB, virtualCapacity_byType_tdvv_vvCount, virtualCapacity_byType_tdvv_sizeTiB, capacity_total_dedupeRatio) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		temp2.put("FILE I/O", "INSERT INTO \"File I/O\" (system_id, \"from\", \"to\", totalWriteIOsHistVlun, totalWriteIOsHistPortTargets, delAcks, delAcksPct, writesGt16s, writesGtXmsPct, writes1msPct, readsGtXmsPct, readsXmsPct, totalsGtXmsPct, totalsXmsPct, portReadAvgIOSizeKB, portWriteAvgIOSizeKB, portTotalAvgIOSizeKB) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		temp2.put("Bandwith", "INSERT INTO Bandwith (system_id, \"from\", \"to\", portReadBandwidthMBPS, portWriteBandwidthMBPS, portTotalBandwidthMBPS) VALUES (?, ?, ?, ?, ?, ?);");
//		temp2.put("deduplication", "INSERT INTO deduplication(system_id, \"from\", \"to\", ddsSizeUsedTiB , ddsSizeUsedTiBPrevious) VALUES (?, ?, ?, ?, ?);");
		temp2.put("Storage Usage", "INSERT INTO \"Storage Usage\" (system_id, \"from\", \"to\", vvCountHistVlun, vvCountHistVlunPrevious) VALUES (?, ?, ?, ?, ?);");
		temp2.put("CPU Usage", "INSERT INTO \"CPU Usage\" (system_id, \"from\", \"to\", cpuLatestSysAvgPct, cpuLatestUserAvgPct, cpuLatestTotalAvgPct, cpuLatestSysMaxPct, cpuLatestUserMaxPct, cpuLatestTotalMaxPct) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");

		tableToStatementMap = Collections.unmodifiableMap(temp2);
	}

	public static final Map<String, List<String>> tableToHeadersMap;
	static
	{
		Map<String, List<String>> temp3 = new HashMap<String, List<String>>();
		temp3.put("Node Information", Arrays.asList("systemId", "from", "to", "nodeUpSinceMostRecent", "nodeCountOffline", "nodeCountMissing"));
		temp3.put("System", Arrays.asList("systemId", "system_companyName", "system_model", "updated", "system_install_date", "capacity_total_sizeTiB", "capacity_total_freePct", "capacity_byType_fc_sizeTiB", "capacity_byType_nl_sizeTiB", "capacity_byType_ssd_sizeTiB", "virtualCapacity_byType_tdvv_vvCount", "virtualCapacity_byType_tdvv_sizeTiB", "capacity_total_dedupeRatio"));
		temp3.put("File I/O", Arrays.asList("systemId", "from", "to", "totalWriteIOsHistVlun", "totalWriteIOsHistPortTargets", "delAcks", "delAcksPct", "writesGt16s", "writesGt32msPct", "writes1msPct", "readsGt32msPct", "reads32msPct", "totalsGt32msPct", "totals32msPct", "portReadAvgIOSizeKB", "portWriteAvgIOSizeKB", "portTotalAvgIOSizeKB"));
		temp3.put("Bandwith", Arrays.asList("systemId", "from", "to", "portReadBandwidthMBPS", "portWriteBandwidthMBPS", "portTotalBandwidthMBPS"));
//		temp3.put("deduplication", Arrays.asList("systemId", "from", "to", "ddsSizeUsedTiB", "ddsSizeUsedTiBPrevious"));
		temp3.put("Storage Usage", Arrays.asList("systemId", "from", "to", "vvCountHistVlun", "vvCountHistVlunPrevious"));
		temp3.put("CPU Usage", Arrays.asList("systemId", "from", "to", "cpuLatestSysAvgPct", "cpuLatestUserAvgPct", "cpuLatestTotalAvgPct", "cpuLatestSysMaxPct", "cpuLatestUserMaxPct", "cpuLatestTotalMaxPct"));
		tableToHeadersMap = Collections.unmodifiableMap(temp3);
	}
}
