package com.proyect.jef.seguridad;

 // @author Kharon estudio Web
import com.proyect.jef.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Order(1)
public class ConfiguracionesSeguridad extends WebSecurityConfigurerAdapter{

 @Autowired
 private UsuarioServicio usuarioServicio;

 @Override
 protected void configure(HttpSecurity http) throws Exception {
 http
 .authorizeRequests()
 .antMatchers("/css/*", "/js/*","/img/*").permitAll()
         .antMatchers("/perfiles").authenticated()
         .antMatchers("/admin").hasRole("ADMIN")

 .and().formLogin()
 .loginPage("/login") // Que formulario esta mi login
 .loginProcessingUrl("/logincheck")
 .usernameParameter("username") // Como viajan los datos del logueo
 .passwordParameter("password")// Como viajan los datos del logueo
 .defaultSuccessUrl("/inicio") // A que URL viaja
 .permitAll()
 .and().logout() // Aca configuro la salida
 .logoutUrl("/logout")
 .logoutSuccessUrl("/login?logout")
 .permitAll().and().csrf().disable();
 }

 @Autowired
 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
  auth.userDetailsService(usuarioServicio).passwordEncoder(new BCryptPasswordEncoder());
 }

}

