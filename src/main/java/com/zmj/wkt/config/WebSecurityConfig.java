package com.zmj.wkt.config;

import com.zmj.wkt.jwt.JWTAuthenticationFilter;
import com.zmj.wkt.jwt.JWTLoginFilter;
import com.zmj.wkt.security.*;
import com.zmj.wkt.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

/**
 * spring security 配置文件
 * @author zmj
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Autowired
    private DataSource dataSource;

    @Value("${matcher}")
    String matcher;

    @Bean
    UserDetailsService customUserService(){ //注册UserDetailsService 的bean
        return new CustomUserServiceImpl();
    }

/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()); //user Details Service验证(无MD5加密)
    }*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login2","/login","/home","/loginError","/register","/authImage","/checkImageCode","/authImageBase64","/sendSmsCode"
                        ,"/verifyCode","/showSlideshowInfo","/noRoot/**","/css/**","/img/**","/js/**","loginSuccess",matcher
                        ,"/getGoodsType","/getGoods","/getQQGoods","/getGoodsByID","/clearGoods","/favoritesItemGet","/favoritesGet").permitAll()
                .anyRequest().authenticated() //任何请求,登录后才可以访问
                .and()
                .sessionManagement()
                .invalidSessionUrl("/login")
                .maximumSessions(1)
                .expiredUrl("/login")
                .and()
                .and()
                //登录后记住用户，下次自动登录
                //数据库中必须存在名为persistent_logins的表
                // 这里是核心
                .rememberMe()
                .tokenValiditySeconds(1209600)
                //指定记住登录信息所使用的数据源
                .tokenRepository(persistentTokenRepository())//code4
                //整合JWT
                .and()
                .addFilter(new JWTLoginFilter(authenticationManager()))
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                ;
        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);

        http.headers()
                .addHeaderWriter(
                        new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                .and().csrf().disable()
                .formLogin()
                //设置登录成功行为
                .successHandler(authenticationSuccessHandler())
                //设置登录失败行为
                .failureHandler(authenticationFailureHandler())
                .loginProcessingUrl("/login")
                .loginPage("/login").permitAll()
                //设置注销行为
                .and().logout().logoutSuccessHandler(authenticationLogoutSuccessHandler()).logoutUrl("/logout").logoutSuccessUrl("/notLogin").invalidateHttpSession(true) .clearAuthentication(true).permitAll()
                .and().authorizeRequests().anyRequest().authenticated()
                //设置未登录状态行为
                .and().exceptionHandling().authenticationEntryPoint(new AjaxAwareAuthenticationEntryPoint());
                //.and().exceptionHandling().authenticationEntryPoint(new AjaxAwareAuthenticationEntryPoint("/index.html"))
                /*.and().sessionManagement().invalidSessionUrl("/timeout").maximumSessions(1).maxSessionsPreventsLogin(false)
                .expiredUrl("/timeout");*/
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler authenticationFailureHandler() {
        AuthenticationFailureHandler authenticationFailureHandler = new AuthenticationFailureHandler();
        return authenticationFailureHandler;
    }

    @Bean
    public SimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler() {
        AuthenticationSuccessHandler authenticationSuccessHandler = new AuthenticationSuccessHandler();
        return authenticationSuccessHandler;
    }

    @Bean
    public SimpleUrlLogoutSuccessHandler authenticationLogoutSuccessHandler() {
        AuthenticationLogoutSuccessHandler authenticationLogoutSuccessHandler = new AuthenticationLogoutSuccessHandler();
        return authenticationLogoutSuccessHandler;
    }

    public JWTLoginFilter jwtLoginFilter() throws Exception {
        JWTLoginFilter jwtLoginFilter = new JWTLoginFilter(authenticationManager());
        jwtLoginFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        return jwtLoginFilter;
    }


    @Bean
    PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/img/**","/js/**","/vue/**","/static/**");
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
        //可以仿照上面一句忽略静态资源
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{//MD5加密验证密码
        auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder(){

            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String)rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                //return encodedPassword.equals(MD5Util.encode((String)rawPassword));
                return encodedPassword.equals(rawPassword.toString());
            }}); //user Details Service验证
    }

    /**
     * allow cross origin requests
     * 跨域相关，请百度
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
