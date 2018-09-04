//
// Built on Sun Aug 19 03:57:28 CEST 2018 by logback-translator
// For more information on configuration files in Groovy
// please see http://logback.qos.ch/manual/groovy.html

// For assistance related to this tool or configuration files
// in general, please contact the logback user mailing list at
//    http://qos.ch/mailman/listinfo/logback-user

// For professional support please see
//   http://www.qos.ch/shop/products/professionalSupport
/**
* 以下パッケージはデフォルトでimportされるので明示する必要はない.
*/
// import ch.qos.logback.core.*;
// import ch.qos.logback.core.encoder.*;
// import ch.qos.logback.core.read.*;
// import ch.qos.logback.core.rolling.*;
// import ch.qos.logback.core.status.*;
// import ch.qos.logback.classic.net.*;
// import ch.qos.logback.classic.encoder.PatternLayoutEncoder;

/**
* logbackの設定ファイル.xmlとgroovyがクラスパス上にある場合、まずgroovyを優先して探索し、設定を反映する.
*/
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.filter.ThresholdFilter;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import java.nio.charset.Charset

import static ch.qos.logback.classic.Level.*;

def ROOT.LEVEL = "debug"
def console.level = "debug"
def logback.log.dir = "./logs"
def logback.hostname = "localhost"
def logback.appname = "task-manager-api"
def defaultCharset = Charset.forName("UTF-8");

/**
* logback.groovyの変更監視周期です.
*/
scan("10 seconds")

/**
* コンソールの標準出力のアペンダーです.
*/
appender("STDOUT", ConsoleAppender) {
  filter(ThresholdFilter) {
    level = "${console.level}"
  }
  encoder(PatternLayoutEncoder) {
    pattern = "%d{yyyy-MM-dd HH:mm:ss} %-5level [%thread] %logger{35} - %msg %n"
  }
}

/**
* INFOレベルログのアペンダーです.
*/
appender("INFO_LOG", RollingFileAppender) {
  file = "${logback.log.dir}/%d{yyyy-MM-dd}_${logback.hostname}_${logback.appname}_trace.log"
  filter(ThresholdFilter) {
    level = INFO
  }
  rollingPolicy(TimeBasedRollingPolicy) {
    fileNamePattern = "${logback.log.dir}/%d{yyyy-MM-dd}_${logback.hostname}_${logback.appname}_trace.log"
    maxHistory = 10
  }
  encoder(PatternLayoutEncoder) {
    pattern = "%d{yyyy-MM-dd HH:mm:ss} %-5level [%thread] %logger{0}: %msg%n"
  }
}

/**
* ERRORレベルログのアペンダーです.
*/
appender("ERR_LOG", RollingFileAppender) {
  file = "${logback.log.dir}/%d{yyyy-MM-dd}_${logback.hostname}_${logback.appname}_error.log"
  filter(ThresholdFilter) {
    level = ERROR
  }
  rollingPolicy(TimeBasedRollingPolicy) {
    fileNamePattern = "${logback.log.dir}/%d{yyyy-MM-dd}_${logback.hostname}_${logback.appname}_error.log"
    maxHistory = 10
  }
  encoder(PatternLayoutEncoder) {
    pattern = "%d{yyyy-MM-dd HH:mm:ss} %-5level [%thread] %logger{0}: %msg%n"
  }
}

logger("systemLogger", DEBUG, ["INFO_LOG", "ERR_LOG"])
root(${ROOT.LEVEL}, ["STDOUT"])