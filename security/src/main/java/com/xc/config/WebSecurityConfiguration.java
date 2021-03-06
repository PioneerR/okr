package com.xc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth)throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).
			withUser("root").password(new BCryptPasswordEncoder().encode("enjoy")).roles("USER").
			and().
			withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
		security.httpBasic().and().authorizeRequests().anyRequest().fullyAuthenticated();
		security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		security.csrf().disable();
	}

    /**
     * 以下请求，不需要做权限控制
     *
     * @author shaper 2020/11/11 0:04
     */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/actuator/hystrix.stream", "/turbine.stream") ;
	}


}