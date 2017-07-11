package com.sywu.SpringSchedulerTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("scheduledTasks")
public class ScheduledTasks {
	String ms;
	DataSource dataSource;
	private Connection conn;

	private static final Log logger = LogFactory.getLog(ScheduledTasks.class);

	public void initConn() {
		try {
			if (null == conn)
				conn = this.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
	}

	public void reportSystemInfo() {
		log.info("ms:" + ms);
	}

	public void printData() {
		initConn();
		try {
			PreparedStatement pre = conn.prepareStatement("select * from DATAFLOW_APP");
			ResultSet rs = pre.executeQuery();
			log.info("report DATAFLOW_APP table data:");
			while (rs.next()) {
				log.info("Name:" + rs.getString("NAME"));
			}
			rs.close();
			pre.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getMs() {
		return ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
