package com.sywu.SpringSchedulerTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
@Component("scheduledTasks")
public class ScheduledTasks01 {
	private final JdbcTemplate jdbcTemplate;
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks01.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    public ScheduledTasks01(DataSource dataSource){
    	System.out.println("ScheduledTasks01 construct created...");
    	this.jdbcTemplate=new JdbcTemplate(dataSource);
    }

    /*
     * report current time
     */
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
	}

	/**
	 * report table data
	 */
	public void reportTableData() {
			List<Map<String, Object>> rs=jdbcTemplate.queryForList("select * from DATAFLOW_APP");
			log.info("report DATAFLOW_APP table data:");
			for (int i = 0; i < rs.size(); i++) {
				Map<String, Object> m=rs.get(i);
				System.out.println("name:"+m.get("NAME"));
			}
	}
}
