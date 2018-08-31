package com.wjt.zam.modules.sol.controller;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjt.zam.modules.sol.model.Good;
import com.wjt.zam.modules.sol.service.IGoodService;

/**  
* <p>Description: 商品管理--控制器</p>  
* @author wjt  create
* @date 2018年8月29日 下午5:14:45
*/
@Controller
@RequestMapping(value="/solr/good")
public class GoodController{
	@Autowired
	private IGoodService goodsService;
	
	/**  
	 * <p>Description: 首页</p>  
	 * @param model
	 * @param good 商品实体
	 * @return  跳转页面
	 * @author wjt  create
	 * @throws IOException 
	 * @throws SolrServerException 
	 * @date 2018年8月29日 下午5:24:35
	 */
	@RequestMapping(value = "/index")
	public String index(Model model, Good good) throws SolrServerException, IOException {
		goodsService.findList(good);
		model.addAttribute("good", good);
		return "/modules/good/index";
	}
	
	/**  
	 * <p>Description:查询 </p>  
	 * @param good 商品实体
	 * @return
	 * @throws SolrServerException
	 * @throws IOException  
	 * @author wjt  create
	 * @date 2018年8月31日 下午1:31:06
	 */
	@PostMapping(value = "/list")
	@ResponseBody
	public Good list(Good good) throws SolrServerException, IOException {
				goodsService.findList(good);
		return good;
	}
}
