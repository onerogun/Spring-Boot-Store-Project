package og.prj.adminservice.util;

import og.prj.adminservice.jpafiles.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordConfig passwordConfig;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    public SecurityConfiguration(PasswordConfig passwordConfig) {

        this.passwordConfig = passwordConfig;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .antMatchers("/admin", "/admin/*","/adduser/**","/edituser/**","/edituser").hasRole("ADMIN")
                .antMatchers("/manager", "/products","/addproduct/**","/editproduct/**","/editproduct").hasAnyRole("ADMIN","MANAGER")
                .antMatchers("/order", "/orders","/orderlist").hasRole("CUSTOMER")
                .antMatchers("/","/css/**","/css/*","/css","css/**").permitAll()
                .and().exceptionHandling().accessDeniedPage("/accessdenied")
                .and().formLogin().loginProcessingUrl("/signin").loginPage("/login").permitAll()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logoutsuccessful")
                .and()
                .rememberMe();
    }



 /*   @Bean
    public PasswordEncoder getPassWordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }*/
}
