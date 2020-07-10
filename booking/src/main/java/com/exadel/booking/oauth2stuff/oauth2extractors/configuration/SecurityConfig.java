package com.exadel.booking.oauth2stuff.oauth2extractors.configuration;

import com.exadel.booking.oauth2stuff.oauth2extractors.extractor.CustomAuthoritiesExtractor;
import com.exadel.booking.oauth2stuff.oauth2extractors.extractor.CustomPrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/login**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().disable();
    }

    @Bean
    @Profile("oauth2-extractors-custom")
    public PrincipalExtractor customPrincipalExtractor() {
        return new CustomPrincipalExtractor();
    }

    @Bean
    @Profile("oauth2-extractors-custom")
    public AuthoritiesExtractor customAuthoritiesExtractor() {
        return new CustomAuthoritiesExtractor();
    }

}