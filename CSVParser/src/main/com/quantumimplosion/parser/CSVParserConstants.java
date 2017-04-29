package com.quantumimplosion.parser;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVParserConstants {

	public static final int STRINGTYPE = 1;
	public static final int DOUBLETYPE = 2;
	public static final int INTTYPE = 3;
	
	public static final Map<String, List<Integer>> tableToArgTypeMap;
	static {
		Map<String, List<Integer>> temp = new HashMap<String, List<Integer>>();
		temp.put("Node Information", Arrays.asList(INTTYPE, STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE));
		temp.put("System", Arrays.asList(INTTYPE, STRINGTYPE, STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE));
		temp.put("File I/O", Arrays.asList(INTTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE));
		temp.put("Bandwidth", Arrays.asList(INTTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE));
		temp.put("Deduplication", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE));
		temp.put("Storage Usage", Arrays.asList(INTTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE));
		temp.put("CPU Usage", Arrays.asList(INTTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE));
		tableToArgTypeMap = Collections.unmodifiableMap(temp);
	}
	
	public static final Map<String, String> tableToStatementMap;
	static
	{
		Map<String, String> temp2 = new HashMap<String, String>();
		temp2.put("Node Information", "INSERT INTO \"Node Information\" (system_id, \"from\", \"to\", \"nodeUpSinceMostRecent\", \"nodeCountOffline\", \"nodeCountMissing\") VALUES (?, ?, ?, ?, ?, ?);");
		temp2.put("System", "INSERT INTO System (system_id, system_companyName, system_model, updated, system_install_date, capacity_total_sizeTiB, capacity_total_freePct, capacity_byType_fc_sizeTiB, capacity_byType_nl_sizeTiB, capacity_byType_ssd_sizeTiB, virtualCapacity_byType_tdvv_vvCount, virtualCapacity_byType_tdvv_sizeTiB, capacity_total_dedupeRatio) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		temp2.put("File I/O", "INSERT INTO \"File I/O\" (system_id, \"from\", \"to\", totalWriteIOsHistVlun, totalWriteIOsHistPortTargets, delAcks, delAcksPct, writesGt16s, writesGt32msPct, writesGt64msPct, writesGt128msPct, writesGt256msPct, writesGt512msPct, writesGt1024msPct, writesGt2048msPct, writesGt4096msPct, writes0_062msPct, writes0_125msPct, writes0_25msPct, writes0_5msPct, writes1msPct, writes2msPct, writes4msPct, writes8msPct, writes16msPct, writes32msPct, writes64msPct, writes128msPct, writes256msPct, writes512msPct, writes1024msPct, writes2048msPct, writes4096msPct, writes8192msPct, writes16384msPct, writes32768msPct, writes65536msPct, readsGt32msPct, readsGt64msPct, readsGt128msPct, readsGt256msPct, readsGt512msPct, readsGt1024msPct, readsGt2048msPct, readsGt4096msPct, reads0_062msPct, reads0_125msPct, reads0_25msPct, reads0_5msPct, reads1msPct, reads2msPct, reads4msPct, reads8msPct, reads16msPct, reads32msPct, reads64msPct, reads128msPct, reads256msPct, reads512msPct, reads1024msPct, reads2048msPct, reads4096msPct, reads8192msPct, reads16384msPct, reads32768msPct, reads65536msPct, totalsGt32msPct, totalsGt64msPct, totalsGt128msPct, totalsGt256msPct, totalsGt512msPct, totalsGt1024msPct, totalsGt2048msPct, totalsGt4096msPct, totals0_062msPct, totals0_125msPct, totals0_25msPct, totals0_5msPct,  totals1msPct, totals2msPct, totals4msPct, totals8msPct, totals16msPct, totals32msPct, totals64msPct, totals128msPct, totals256msPct, totals512msPct, totals1024msPct, totals2048msPct, totals4096msPct, totals8192msPct, totals16384msPct, totals32768msPct, totals65536msPct, portReadAvgIOSizeKB, portWriteAvgIOSizeKB, portTotalAvgIOSizeKB) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		temp2.put("Bandwidth", "INSERT INTO Bandwidth (system_id, \"from\", \"to\", portReadBandwidthMBPS, portWriteBandwidthMBPS, portTotalBandwidthMBPS) VALUES (?, ?, ?, ?, ?, ?);");
		temp2.put("Deduplication", "INSERT INTO deduplication(system_id, \"from\", \"to\", ddsSizeUsedTiB , ddsSizeUsedTiBPrevious) VALUES (?, ?, ?, ?, ?);");
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
		temp3.put("File I/O", Arrays.asList("systemId", "from", "to", "totalWriteIOsHistVlun", "totalWriteIOsHistPortTargets", "delAcks", "delAcksPct", "writesGt16s", "writesGt32msPct", "writesGt64msPct", "writesGt128msPct", "writesGt256msPct", "writesGt512msPct", "writesGt1024msPct", "writesGt2048msPct", "writesGt4096msPct", "writes0_062msPct", "writes0_125msPct", "writes0_25msPct", "writes0_5msPct", "writes1msPct", "writes2msPct", "writes4msPct", "writes8msPct", "writes16msPct", "writes32msPct", "writes64msPct", "writes128msPct", "writes256msPct", "writes512msPct", "writes1024msPct", "writes2048msPct", "writes4096msPct", "writes8192msPct", "writes16384msPct", "writes32768msPct", "writes65536msPct", "readsGt32msPct", "readsGt64msPct", "readsGt128msPct", "readsGt256msPct", "readsGt512msPct", "readsGt1024msPct", "readsGt2048msPct", "readsGt4096msPct", "reads0_062msPct", "reads0_125msPct", "reads0_25msPct", "reads0_5msPct", "reads1msPct", "reads2msPct", "reads4msPct", "reads8msPct", "reads16msPct", "reads32msPct", "reads64msPct", "reads128msPct", "reads256msPct", "reads512msPct", "reads1024msPct", "reads2048msPct", "reads4096msPct", "reads8192msPct", "reads16384msPct", "reads32768msPct", "reads65536msPct", "totalsGt32msPct", "totalsGt64msPct", "totalsGt128msPct", "totalsGt256msPct", "totalsGt512msPct", "totalsGt1024msPct", "totalsGt2048msPct", "totalsGt4096msPct", "totals0_062msPct", "totals0_125msPct", "totals0_25msPct", "totals0_5msPct", "totals1msPct", "totals2msPct", "totals4msPct", "totals8msPct", "totals16msPct", "totals32msPct", "totals64msPct", "totals128msPct", "totals256msPct", "totals512msPct", "totals1024msPct", "totals2048msPct", "totals4096msPct", "totals8192msPct", "totals16384msPct", "totals32768msPct", "totals65536msPct", "portReadAvgIOSizeKB", "portWriteAvgIOSizeKB", "portTotalAvgIOSizeKB"));
		temp3.put("Bandwidth", Arrays.asList("systemId", "from", "to", "portReadBandwidthMBPS", "portWriteBandwidthMBPS", "portTotalBandwidthMBPS"));
		temp3.put("Deduplication", Arrays.asList("systemId", "from", "to", "ddsSizeUsedTiB", "ddsSizeUsedTiBPrevious"));
		temp3.put("Storage Usage", Arrays.asList("systemId", "from", "to", "vvCountHistVlun", "vvCountHistVlunPrevious"));
		temp3.put("CPU Usage", Arrays.asList("systemId", "from", "to", "cpuLatestSysAvgPct", "cpuLatestUserAvgPct", "cpuLatestTotalAvgPct", "cpuLatestSysMaxPct", "cpuLatestUserMaxPct", "cpuLatestTotalMaxPct"));
		tableToHeadersMap = Collections.unmodifiableMap(temp3);
	}
}
