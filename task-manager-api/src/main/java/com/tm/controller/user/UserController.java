package com.tm.controller.user;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tm.TaskManagerApiApplication;
import com.tm.consts.CtrlConst;
<<<<<<< HEAD
import com.tm.controller.framework.TmRestBaseController;
=======
import com.tm.controller.framework.BaseRestController;
>>>>>>> origin/develop

import ch.qos.logback.classic.Logger;

/**
 * 利用者のCRUD操作を行うコントローラクラスです.
 */
@RestController
@RequestMapping(CtrlConst.URI_API_VERSION)
<<<<<<< HEAD
public class UserController extends TmRestBaseController {
=======
public class UserController extends BaseRestController {
>>>>>>> origin/develop

	/** ロガーインスタンス */
	Logger logger = (Logger) LoggerFactory.getLogger(TaskManagerApiApplication.class);

}
