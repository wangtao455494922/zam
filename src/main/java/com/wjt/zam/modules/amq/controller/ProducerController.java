package com.wjt.zam.modules.amq.controller;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjt.zam.common.Constant;
import com.wjt.zam.common.base.BaseController;

/**  
* <p>Description: 生产者 控制器</p>  
* @author wjt  create
* @date 2018年8月16日 下午1:20:36
*/ 
@Controller
@RequestMapping(value="/amq/producer")
public class ProducerController extends BaseController<String> {
	@Autowired
	private JmsTemplate jmsTemplate;
	
	
	/**  
	 * <p>Description: 首页</p>  
	 * @param model
	 * @return  
	 * @author wjt  create
	 * @date 2018年8月16日 下午1:39:48
	 */
	@GetMapping(value = "/index")
	public String index(Model model) {
		return "modules/amq/index";
	}
	
	/**  
	 * <p>Description: 发送信息</p>  
	 * @param sendMessage
	 * @return  
	 * @author wjt  create
	 * @date 2018年8月16日 下午2:22:39
	 */
	@PostMapping("/send")
	@ResponseBody
	public Object send(String sendMessage){
		try {
			jmsTemplate.send(new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage(sendMessage);
				}
			});
			return renderSuccess(Constant.COMMON_SUCCESS);
		} catch (JmsException e) {
			e.printStackTrace();
			return renderSuccess(Constant.COMMON_ERROR);
		}
	}
}
