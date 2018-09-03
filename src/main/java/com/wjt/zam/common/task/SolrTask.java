package com.wjt.zam.common.task;

import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Description: 关于solr的定时任务类
 * </p>
 * 
 * @author wjt create
 * @date 2018年9月3日 下午1:10:44
 */
@Component
@Lazy(false)
public class SolrTask {
	@Autowired
	private HttpSolrClient httpSolrClient;

	@Value("${solr.dataimport.path}")
	private String path;
	
	@Scheduled(cron = "${solr.dataimport.time}")
	public void dataimport() {
		try {
			QueryRequest request = new QueryRequest();
			request.setPath(path);
			//默认情况(Get方式),会报错定时任务会正常执行
			request.setMethod(METHOD.POST);
			httpSolrClient.request(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
