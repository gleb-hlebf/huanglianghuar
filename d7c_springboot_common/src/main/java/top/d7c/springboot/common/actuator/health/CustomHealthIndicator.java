package top.d7c.springboot.common.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import top.d7c.plugins.core.PageResult;

/**
 * @Title: CustomHealthIndicator
 * @Package: top.d7c.springboot.common.actuator.health
 * @author: 吴佳隆
 * @date: 2021年1月4日 下午12:54:56
 * @Description: 自定义 actuator 健康检查，访问 /actuator/health 接口会多出 custom-health 节点的健康信息。
 */
@Component("custom-health")
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        Health.Builder builder = new Health.Builder();
        PageResult result = null;
        try {
            result = check();
            if (result.isOk()) {
                return builder.withDetail("details", result.toString()).up().build();
            }
        } catch (Exception e) {
            return builder.withDetail("error", e).down().build();
        }
        return builder.withDetail("details", result.toString()).down().build();
    }

    /**
     * 健康检查逻辑
     */
    private PageResult check() {
        // TODO perform some specific health check
        return PageResult.ok();
    }

}