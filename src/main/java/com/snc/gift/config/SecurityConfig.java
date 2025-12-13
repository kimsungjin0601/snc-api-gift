package com.snc.gift.config;

import com.snc.gift.security.CustomAccessDeniedHandler;
import com.snc.gift.security.CustomAuthEntryPoint;
import com.cstify.common.provider.TokenProvider;
import com.cstify.common.service.SecurityUserDetailsService;
import com.cstify.common.service.TokenBlockService;
import com.snc.gift.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	@Autowired
	private CorsConfigurationSource corsConfigurationSource;

	private final SecurityUserDetailsService userDetailsService;
	private final TokenBlockService tokenBlockService;
	private final TokenProvider tokenProvider;

	private final CustomAccessDeniedHandler customAccessDeniedHandler;
	private final CustomAuthEntryPoint customAuthEntryPoint;

	private static final String[] permitAllArray = {
			"/", "/api-docs/**", "/swagger-ui/**",
			"/api/auth/member/sign-in","/api/auth/admin/sign-in","/api/auth/purchase/sign-in",
			"/api/auth/sign-in/**","/api/system/admin",
			"/api/front/member/sign-up","/api/front/main","/api/front/gift/**","/api/auth/token"
	};

	@Bean
	@Order(1)
	public SecurityFilterChain publicChain(HttpSecurity http) throws Exception {
		http.cors(cors -> cors.configurationSource(corsConfigurationSource))
			.securityMatcher(permitAllArray)
			.csrf(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
			.exceptionHandling(handler -> handler
				.authenticationEntryPoint(customAuthEntryPoint)
				.accessDeniedHandler(customAccessDeniedHandler));
		return http.build();
	}

	@Bean
	@Order(2)
	public SecurityFilterChain securityChain(HttpSecurity http) throws Exception {
		http.cors(cors -> cors.configurationSource(corsConfigurationSource))
			.csrf(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/api/front/**").hasRole("MEMBER")
				.requestMatchers("/api/admin/**").hasAnyRole("AGENT_ADMIN","HQ_ADMIN","SUPER_HQ_ADMIN","SYSTEM_ADMIN")
				.requestMatchers("/api/purchase/**").hasRole("HQ_ORDER")
				.anyRequest().authenticated()
			)
			.exceptionHandling(handler -> handler
				.authenticationEntryPoint(customAuthEntryPoint)
				.accessDeniedHandler(customAccessDeniedHandler))
			.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	private JwtAuthFilter jwtAuthFilter(){
		return new JwtAuthFilter(userDetailsService, tokenBlockService, tokenProvider);
	}
}
