package br.macc.teamone.auth.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import br.macc.teamone.auth.service.UserDetailsServiceImpl;

public class AuthTokenFilter extends OncePerRequestFilter {

	private static final int TOKEN_VALUE_INDEX = 7;
	private static final String TOKEN_PREFIX = "Bearer ";
	private static final String AUTH_TOKEN = "Authorization";

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private JwtUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String token = parseJwt(request);

		if (token != null) {
			String username = jwtUtils.getUserNameFromJwtToken(token);

			final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}

	private String parseJwt(final HttpServletRequest req) {
		final String hAuth = req.getHeader(AUTH_TOKEN);
		String res = null;

		if (StringUtils.hasText(hAuth) && hAuth.startsWith(TOKEN_PREFIX)) {
			res = hAuth.substring(TOKEN_VALUE_INDEX, hAuth.length());
		}

		return res;
	}

}
