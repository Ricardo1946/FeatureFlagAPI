package FeatureFlagAPI.ApiFlag.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    /**
   * Called when a user tries to access a secured resource without valid credentials. Responds with
   * a structured JSON error message containing HTTP status and request metadata.
   *
   * @param request the incoming HTTP request
   * @param response the HTTP response to write to
   * @param authException the exception indicating the authentication failure
   * @throws IOException if an I/O error occurs while writing the response
   */
  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException {

    final HttpStatus status = HttpStatus.UNAUTHORIZED;

    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .code(status.value())
            .name(status.getReasonPhrase())
            .description(authException.getMessage())
            .build();

    mapper.writeValue(response.getOutputStream(), errorResponse);
  }
}
