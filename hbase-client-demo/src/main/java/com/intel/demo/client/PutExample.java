package com.intel.demo.client;

import org.apache.log4j.Logger;

// cc PutExample Example application inserting data into HBase
// vv PutExample
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
// ^^ PutExample

import com.intel.demo.util.HBaseHelper;

// vv PutExample


import java.io.IOException;

public class PutExample {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PutExample.class);

  public static void main(String[] args) throws IOException {
    Configuration conf = HBaseConfiguration.create(); // co PutExample-1-CreateConf Create the required configuration.

    // ^^ PutExample
    HBaseHelper helper = HBaseHelper.getHelper(conf);
    helper.dropTable("testtable");
    logger.info("Drop table:testtable");
    helper.createTable("testtable", "colfam1");
    logger.info("create table:testtable");
    // vv PutExample
    HTable table = new HTable(conf, "testtable"); // co PutExample-2-NewTable Instantiate a new client.
    int counter = 1;
    while(true){
	    Put put = new Put(Bytes.toBytes("row"+counter++)); // co PutExample-3-NewPut Create put with specific row.
	
	    put.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"),
	      Bytes.toBytes("val1")); // co PutExample-4-AddCol1 Add a column, whose name is "colfam1:qual1", to the put.
	    put.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual2"),
	      Bytes.toBytes("val2")); // co PutExample-4-AddCol2 Add another column, whose name is "colfam1:qual2", to the put.
	    logger.info(counter);
	    table.put(put); // co PutExample-5-DoPut Store row with column into the HBase table.
    }
  }
}
// ^^ PutExample
