package com.gh.action.business;

import java.util.Date;
import java.util.List;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.UUIDCreater;
import com.gh.entity.BuNavigation;
import com.gh.service.BuNavigationService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

public class BuNavigationAction extends Action {

	private BuNavigationService buNavigationService;

	private String uid;
	private String id;
	private String skey;

	private List<BuNavigation> buNavigation_list;
	private BuNavigation buNavigation;

	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show() {
		String roleid = (String) request.getSession().getAttribute("roleid");
		com = CompetenceManager.getCom(roleid, "buNavigation");
		// --------------------操作权限控制
		if (!com.getHisSelect()) {
			return "error";
		}
		String SqlWhere = "";
		if (skey != null && skey.length() > 0) {
			SqlWhere += "  fullname like '%" + skey + "%'";
		}
		// --------------------
		pageSize = 10;
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage = buNavigationService.getRecordCount(SqlWhere);
		buNavigation_list = buNavigationService.selectByPage(indexPage, pageSize, SqlWhere, "MODIFYDATE desc");
		return "buNavigation";
	}


	/**
	 * @see 修改及新增
	 * @author xiao
	 */
	public String edit() {
		String username = (String) request.getSession().getAttribute("username");
		String id = buNavigation.getFuid();
		String roleid = (String) request.getSession().getAttribute("roleid");
		if (id != null && id.length() > 0) {
			com = CompetenceManager.getCom(roleid, "buNavigation");
			if (!com.getHisUpdate()) {
				return "error";
			}
			buNavigation.setModifydate(new Date());
			buNavigation.setModifyuserrealname(username);
			buNavigationService.updateSelective(buNavigation);
		} else {
			com = CompetenceManager.getCom(roleid, "buNavigation");
			if (!com.getHisAdd()) {
				return "error";
			}
			buNavigation.setFuid(UUIDCreater.getUUID());
			buNavigation.setCreatedate(new Date());
			buNavigation.setCreateuserrealname(username);
			buNavigation.setModifydate(new Date());
			buNavigation.setModifyuserrealname(username);
			buNavigationService.save(buNavigation);
		}
		return "show";
	}

	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show() {
		buNavigation = buNavigationService.findById(id);
		return "buNavigationEditor";
	}

	/**
	 * @see 删除单条数据或多条
	 * @author xiao
	 */
	public String delete() {
		String roleid = (String) request.getSession().getAttribute("roleid");
		com = CompetenceManager.getCom(roleid, "buNavigation");
		if (!com.getHisDelete()) {
			return "error";
		}
		if (id != null && id.length() > 0) {
			String ids[] = id.split(",");
			if (ids != null && ids.length > 0) {
				for (int a = 0; a < ids.length; a++) {
					buNavigationService.delete(ids[a]);
				}
			} else {
				buNavigationService.delete(id);
			}
		}
		return "show";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSkey() {
		return skey;
	}

	public void setSkey(String skey) {
		this.skey = skey;
	}

	public BuNavigationService getBuNavigationService() {
		return buNavigationService;
	}

	public void setBuNavigationService(BuNavigationService buNavigationService) {
		this.buNavigationService = buNavigationService;
	}

	public List<BuNavigation> getBuNavigation_list() {
		return buNavigation_list;
	}

	public void setBuNavigation_list(List<BuNavigation> buNavigation_list) {
		this.buNavigation_list = buNavigation_list;
	}

	public BuNavigation getBuNavigation() {
		return buNavigation;
	}

	public void setBuNavigation(BuNavigation buNavigation) {
		this.buNavigation = buNavigation;
	}
}
