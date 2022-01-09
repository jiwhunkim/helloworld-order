package com.helloworld.config

import com.helloworld.config.filter.AuthenticatedAccountFilter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {
    override fun configure(web: WebSecurity?) {
//        web!!.ignoring().antMatchers("/v2/api-docs", "/v3/api-docs", "/swagger-resources/**", "/swagger-ui/**")
        super.configure(web)
    }

    override fun configure(http: HttpSecurity?) {
        val authenticatedAccountFilter = AuthenticatedAccountFilter()

        authenticatedAccountFilter.setAuthenticationManager { authentication ->
            val accountId = authentication.principal as String
            accountId.toLongOrNull()?.let { authentication.isAuthenticated = true }
                ?: run { authentication.isAuthenticated = false }
            authentication
        }
        http!!.authorizeRequests().and().antMatcher("/**").authorizeRequests().anyRequest().permitAll()
//        http!!.authorizeRequests()
//            .and()
//            .antMatcher("/**")
//            .csrf().disable()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .addFilter(authenticatedAccountFilter)
//            .authorizeRequests()
//            .anyRequest()
//            .authenticated()
    }
}