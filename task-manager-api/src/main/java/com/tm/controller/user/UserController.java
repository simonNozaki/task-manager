package com.tm.controller.user;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tm.TaskManagerApiApplication;
import com.tm.consts.CtrlConst;
import com.tm.controller.framework.TmRestBaseController;

import ch.qos.logback.classic.Logger;

/**
 * 利用者のCRUD操作を行うコントローラクラスです.
 */
@RestController
@RequestMapping(CtrlConst.URI_API_VERSION)
public class UserController extends TmRestBaseController {

	/** ロガーインスタンス */
	Logger logger = (Logger) LoggerFactory.getLogger(TaskManagerApiApplication.class);

}
