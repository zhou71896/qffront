package com.ikkong.platform.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ikkong.core.base.BaseController;
import com.ikkong.core.dao.Blade;
import com.ikkong.core.jfinal.ext.kit.JsonKit;
import com.ikkong.core.toolbox.Record;
import com.ikkong.platform.util.MyDateUtil;
import com.ikkong.system.controller.base.UrlPermissController;
import com.ikkong.platform.model.ActivationCode;
import com.ikkong.platform.service.ActivationCodeService;
import com.ikkong.platform.service.impl.ActivationCodeServiceImpl;

/**
 * Generated by Blade.
 * 2017-11-21 14:13:03
 */
public class ActivationCodeController extends UrlPermissController {
	private static String CODE = "activationCode";
	private static String PERFIX = "activation_code";
	private static String LIST_SOURCE = "ActivationCode.list";
	private static String BASE_PATH = "/platform/activationCode/";
	
	ActivationCodeService service = new ActivationCodeServiceImpl();
	
	public void index() {
		setAttr("code", CODE);
		render(BASE_PATH + "activationCode.html");
	}

	public void add() {
		setAttr("code", CODE);
		render(BASE_PATH + "activationCode_add.html");
	}
	public void generate(){
		setAttr("code",CODE);
		render(BASE_PATH+"activationCode_generate.html");
	}
	public void edit() {
		String id = getPara(0);
		ActivationCode activationCode = service.findById(id);
		setAttr("model", JsonKit.toJson(activationCode));
		setAttr("id", id);
		setAttr("code", CODE);
		render(BASE_PATH + "activationCode_edit.html");
	}

	public void view() {
		String id = getPara(0);
		ActivationCode activationCode = service.findById(id);
		setAttr("model", JsonKit.toJson(activationCode));
		setAttr("id", id);
		setAttr("code", CODE);
		render(BASE_PATH + "activationCode_view.html");
	}

	public void list() {
		Object grid = paginate(LIST_SOURCE);
		renderJson(grid);
	}

	/**
	 * @Author: zry
	 * @Update:
	 * @param;  * @param null
	 * @Description: 保存生成的激活码
	 * @Date: 15:53 2017/11/21 0021
	 */

	public void saveGenerate(){
		int  number=getParaToInt("number");
		String number_desc=getPara("code_desc");
		boolean result=false;
		for(int i=0;i<number;i++){
			ActivationCode activationCode=new ActivationCode();
			String uuIdStr=UUID.randomUUID().toString();
			activationCode.setActivation_code(uuIdStr);
			activationCode.setActivation_device_numbers(0);
			activationCode.setCreate_time(MyDateUtil.getNowDate());
			activationCode.setUpdate_time(MyDateUtil.getNowDate());
			activationCode.setDescs(number_desc);
			activationCode.setDown_load_status(0);
			result=service.save(activationCode);
		}
		if(result){
			renderJson(success(SAVE_SUCCESS_MSG));
		}else{
			renderJson(error(SAVE_SUCCESS_MSG));
		}
	}

	/**
	 * @Author: zry
	 * @Update:
	 * @param;  * @param null
	 * @Description: 下载激活码
	 * @Date: 16:02 2017/11/21 0021
	 */

	public void downLoadCode(){
		//service.find()
		List<ActivationCode> map = Blade.dao().select(LIST_SOURCE, ActivationCode.class, Record.create().set("down_load_status", 0));
		System.out.print("正在下载 map: "+map.size());
	}

	public void save() {
		ActivationCode activationCode = mapping(PERFIX, ActivationCode.class);
		boolean temp = service.save(activationCode);
		if (temp) {
			renderJson(success(SAVE_SUCCESS_MSG));
		} else {
			renderJson(error(SAVE_FAIL_MSG));
		}
	}

	public void update() {
		ActivationCode activationCode = mapping(PERFIX, ActivationCode.class);
		activationCode.setVersion(activationCode.getVersion()+1);
		boolean temp = service.update(activationCode);
		if (temp) {
			renderJson(success(UPDATE_SUCCESS_MSG));
		} else {
			renderJson(error(UPDATE_FAIL_MSG));
		}
	}

	public void remove() {
		String ids = getPara("ids");
		int cnt = service.deleteByIds(ids);
		if (cnt > 0) {
			renderJson(success(DEL_SUCCESS_MSG));
		} else {
			renderJson(error(DEL_FAIL_MSG));
		}
	}
}
