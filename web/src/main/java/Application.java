import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.annotation.Aspect;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import util.JDKRedisTemplate;

import javax.servlet.Filter;
import javax.sql.DataSource;

@SpringBootApplication
@EnableAsync
//@EnableScheduling
@MapperScan("persistence")
@ComponentScan(basePackages = "controller", includeFilters = {@ComponentScan.Filter(Aspect.class)})
@PropertySource({"classpath:application.yml"})
//@ImportResource({"classpath:dubbo.xml"})
//@ImportResource({"classpath:dubbo.xml", "classpath:rabbitmq.xml"})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    /**
     * 模板
     *
     * @param properties
     * @return
     */
    @Bean(name = "freeMarkerProperties")
    public FreeMarkerViewResolver velocityViewResolver(FreeMarkerProperties properties) {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(true);
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html; charset=UTF-8");
        return resolver;
    }

    // 用于处理编码问题
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    @Bean
    public JDKRedisTemplate jdkRedisTemplate(RedisConnectionFactory connectionFactory){
        JDKRedisTemplate jdkRedisTemplate = new JDKRedisTemplate();
        jdkRedisTemplate.setConnectionFactory(connectionFactory);
        return jdkRedisTemplate ;
    }

    @Bean(name = "syncZkClient")
    public ZkClient client(@Value("${zookeeper.host}")String zookeeperConnectionString){
        ZkClient zkClient = new ZkClientImpl(zookeeperConnectionString);
        zkClient.connect();
        return zkClient;
    }

    //DataSource配置
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }

    //提供SqlSeesion
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/persistence/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }


}
