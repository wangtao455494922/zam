/**  
* <p>Description: </p>   
* @author wjt  create
* @date 2018年8月29日  
* @version 1.0  

*/  
package com.wjt.zam.modules.sol.service;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;

import com.wjt.zam.modules.sol.model.Good;

/**  
* <p>Description: 商品--业务层</p>  
* @author wjt  create
* @date 2018年8月29日 下午5:53:55
*/
public interface IGoodService {

	/**  
	 * <p>Description:根据条件查询 </p>  
	 * @param good 商品实体
	 * @return  
	 * @author wjt  create
	 * @throws IOException 
	 * @throws SolrServerException 
	 * @date 2018年8月29日 下午5:56:46
	 */
	void findList(Good good) throws SolrServerException, IOException;

}
