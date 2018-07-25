package com.wjt.zam.modules.act.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.github.pagehelper.PageInfo;

@Alias(value="Leave")
public class Leave extends PageInfo<Leave>{
	
	private static final long serialVersionUID = 2781691041651811388L;

	/**
	 * id 
	 */
    private Long id;
    
    /**
	 * 请假天数 
	 */
    private Long days;
    
    /**
	 * 请假内容 
	 */
    private String content;
    
    /**
	 * 请假时间 
	 */
    private Date leaveTime;
    
    /**
   	 * 备注 
   	 */
    private String remark;
    
    /**
   	 * 请假人id 
   	 */
    private Long userid;
    
    /**
   	 * 请假天数范围
   	 */
    private String leaveTimeFromTo;
    
    /**
   	 * 请假天数开始范围
   	 */
    private String leaveTimeFrom;
    
    /**
   	 * 请假天数结束范围
   	 */
    private String leaveTimeTo;
    
    /**
     * 流程状态 0 初始录入  1 开始审批   2审批完成
     */
    private Integer state;
    /**
     * 状态名称
     */
    private String stateName;
    /**
     * 批准信息
     */
    private String comment;
    /**
     * 按钮信息
     */
    private String outcome;
    /**
     * 任务id
     */
    private String taskId;
    
    public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getLeaveTimeFrom() {
		return leaveTimeFrom;
	}

	public void setLeaveTimeFrom(String leaveTimeFrom) {
		this.leaveTimeFrom = leaveTimeFrom;
	}

	public String getLeaveTimeTo() {
		return leaveTimeTo;
	}

	public void setLeaveTimeTo(String leaveTimeTo) {
		this.leaveTimeTo = leaveTimeTo;
	}

	public String getLeaveTimeFromTo() {
		return leaveTimeFromTo;
	}

	public void setLeaveTimeFromTo(String leaveTimeFromTo) {
		this.leaveTimeFromTo = leaveTimeFromTo;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}