package com.wjt.zam.modules.sol.impl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjt.zam.common.utils.CheckUtils;
import com.wjt.zam.modules.sol.model.Good;
import com.wjt.zam.modules.sol.service.IGoodService;

@Service(value="goodServiceImpl")
@Transactional
public class GoodServiceImpl implements IGoodService {
	@Autowired
	private HttpSolrClient httpSolrClient;

	@Override
	public void findList(Good paramGood) throws SolrServerException, IOException {
		//用SolrParams的ModifiableSolrParams这个子类,其他子类没有set方法.
		ModifiableSolrParams params = new ModifiableSolrParams();
		//查询所有的field
		if (StringUtils.isBlank(paramGood.getName())) {
			params.set(CommonParams.Q, "*:*");
		}else{
			params.set(CommonParams.Q, "good_name:"+paramGood.getName());
		}
		//精确查询(价格)
		String price = paramGood.getPrice();
		if (StringUtils.isNotBlank(price)) {
			String[] priceArray = price.split("-");
			params.set(CommonParams.FQ, "good_price:["+priceArray[0]+" TO "+priceArray[1]+"]");
		}
		//精确查询(种类名称)
		if (StringUtils.isNotBlank(paramGood.getCatalogName())) {
			params.set(CommonParams.FQ, "good_catalog_name:"+paramGood.getCatalogName());
		}
		//排序
		if ("1".equals(paramGood.getSort())) {
			params.set(CommonParams.SORT, "good_price ASC");
		}
		//页数
		params.set(CommonParams.START, paramGood.getStart());
		//页码
		params.set(CommonParams.ROWS, paramGood.getRows());
		
		//设置高亮
		params.set("hl", true);
		//高亮字段
		params.set("hl.fl", "good_name");
		//高亮前缀
		params.set("hl.simple.pre", "<span style='color:red'>");
		//高亮后缀
		params.set("hl.simple.post", "</span>");
		
		//返回结果集
		List<Good> goods = new LinkedList<>();
		QueryResponse queryResponse = httpSolrClient.query(params);
		Map<String, Map<String, List<String>>> highlightingMap = queryResponse.getHighlighting();
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		
		//总记录数
		paramGood.setTotalSize((int) solrDocumentList.getNumFound());
		//计算总页数
		paramGood.setTotalPage();
		
		for (SolrDocument solrDocument : solrDocumentList) {
			Good resultGood = new Good();
			resultGood.setId(solrDocument.get("good_id").toString());
			resultGood.setPicture(solrDocument.get("good_picture").toString());
			resultGood.setPrice(solrDocument.get("good_price").toString());
			if (StringUtils.isBlank(paramGood.getName())) {
				resultGood.setName(solrDocument.get("good_name").toString());
			}else{
				//设置高亮   
				Map<String, List<String>> map = highlightingMap.get(solrDocument.get("id"));
				List<String> list = map.get("good_name");
				if (CheckUtils.listIsNotNull(list)) {
					resultGood.setName(list.get(0));
				}
			}
			goods.add(resultGood);
		}
		paramGood.setGoodList(goods);
	}

}
