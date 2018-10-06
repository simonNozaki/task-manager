import * as log4js from 'log4js';

// ロギングの設定
log4js.configure({
    appenders: {
        system : {type : 'dateFile', 'filename': 'logs/system.log', pattern : '-yyyy-MM-dd'},
        access : {type : 'dateFile', filename : 'logs/access.log', pattern : '-yyyy-MM-dd'},
        error : {type : 'dateFile', filename : 'logs/error.log', pattern : '-yyyy-MM-dd'},
        default : {type : 'dateFile', filename : 'logs/default.log', pattern : '-yyyy-MM-dd'}
    },
    categories : {
        access : {appenders : ['access'], level : 'info'},
        system : {appenders : ['system'], level : 'info'},
        error : {appenders : ['error'], level : 'error'},
        default : {appenders : ['default'], level : 'debug'}
    }
});

/**
 * ロガーの基底クラスです.
 */
export class Logger {
    systemLogger: log4js.Logger = log4js.getLogger('system');
    errorLogger: log4js.Logger = log4js.getLogger('error');
    defaultLogger: log4js.Logger = log4js.getLogger('default');
    accessLogger: log4js.Logger = log4js.getLogger('access');
}
