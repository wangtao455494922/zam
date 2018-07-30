package com.wjt.zam.modules.mon.controller;

import java.util.LinkedList;
import java.util.List;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.wjt.zam.common.Constant;
import com.wjt.zam.common.base.BaseController;

/**  

* <p>Description: 会话管理</p>  
* @author wjt  add
* @date 2018年7月27日  

*/ 
@Controller
@RequestMapping(value="/session")
public class SessionController extends BaseController<com.wjt.zam.modules.mon.model.Session> {
	@Autowired
    private SessionDAO sessionDAO;
	
	@GetMapping(value="/index")
	public String index(Model model){
		return "modules/session/index";
	}
	
	/**  
	 * table数据初始化
	 */
	@PostMapping("/tableRender")
	@ResponseBody
	public Object resourceTableRender(com.wjt.zam.modules.mon.model.Session session){
		List<com.wjt.zam.modules.mon.model.Session> zamSessions = new LinkedList<com.wjt.zam.modules.mon.model.Session>();
		PageHelper.startPage(session.getPageNum(),session.getPageSize());
		List<Session> sessions = (List<Session>) sessionDAO.getActiveSessions();
		for (Session shiroSession : sessions) {
			com.wjt.zam.modules.mon.model.Session zamSession = new com.wjt.zam.modules.mon.model.Session();
			zamSession.setId(shiroSession.getId());
			zamSession.setHost(shiroSession.getHost());
			zamSession.setLastAccessTime(shiroSession.getLastAccessTime());
			//用户名
			PrincipalCollection principalCollection = (PrincipalCollection) shiroSession.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			zamSession.setName(principalCollection==null?"":(String)principalCollection.getPrimaryPrincipal());
			//是否强制退出
			boolean isForceLogout = shiroSession.getAttribute(Constant.SESSION_FORCE_LOGOUT_KEY)!=null;
			zamSession.setForceLogout(isForceLogout==true?"是":"否");
			zamSessions.add(zamSession);
		}
		return layuiTableRender(zamSessions);
	}
	
	/**  
	 * 强制退出
	 */
	@PostMapping("/forceLogout")
	@ResponseBody
	public Object forceLogout(String id){
		Session session = sessionDAO.readSession(id);
		if(session != null) {
            session.setAttribute(Constant.SESSION_FORCE_LOGOUT_KEY, Boolean.TRUE);
        }
		return renderSuccess(Constant.FORCE_LOGOUT_SUCCESS);
	}
	
}
