package com.tm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AspectJの自動プロキシ設定クラスです.
 */
@Configuration
@EnableAspectJAutoProxy
public class AspectJAutoProxyConfig {

	/**
	 * 業務プロセスアスペクトのBeanを返却します。
	 * @return BeanCommonAspectAdviser
	 */
//	@Bean
//	public BusinessProcessAspect businessProcessAspect() {
//		return new BusinessProcessAspect();
//	}
}
