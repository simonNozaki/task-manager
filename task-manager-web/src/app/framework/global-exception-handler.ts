import { Logger } from '../config/logger';
import { Injectable, ErrorHandler } from '@angular/core';
import { AppLog } from '../const/app-log';

/**
 * 共通エラーハンドラークラスです.
 */
@Injectable()
export class GlobalExceptionHandler implements ErrorHandler {

  // 共通エラーハンドラーの実装
  handleError(e: any): void {

    /** ロガーインスタンスの初期化 */
    const logger: Logger = new Logger();
    /** 共通処理ロギング */
    logger.errorLogger.error(AppLog.TW_TK_CM_001);
  }
}
