package covidstatics.covidstats.SecurityConfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class Databasesecurity extends WebSecurityConfigurerAdapter {
    @Autowired 
    DataSource dataSource;

     //Enable jdbc authentication
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
            .dataSource(dataSource)
            .usersByUsernameQuery("select username, password, enabled from users where username=?")
            .authoritiesByUsernameQuery("select username, authority from authorities where username=?")
        ;
      
      
    }
    @Override
	protected void configure(HttpSecurity http) throws Exception {
     
        http.sessionManagement().maximumSessions(1);


        http.authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/admin/statedata").hasAnyRole( "ADMIN")
        .antMatchers("/admin/statedata/{id}").hasAnyRole("ADMIN")
        .and()
        .formLogin().and()  
        .httpBasic()  
        .and()
        .logout().logoutSuccessUrl("/") ; 
      }

}
