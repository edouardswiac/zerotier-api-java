package com.github.edouardswiac.zerotier.interceptors;


import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class AuthenticationInterceptor implements Interceptor {
  private final String apiAccessToken;

  public AuthenticationInterceptor(String apiAccessToken) {
    this.apiAccessToken = apiAccessToken;
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request originalRequest = chain.request();
    if (null == apiAccessToken || apiAccessToken.isEmpty()) {
      return chain.proceed(originalRequest);
    }

    Request requestWithUserAgent = originalRequest.newBuilder()
        .addHeader("Authorization", String.format("Bearer %s", apiAccessToken))
        .build();
    return chain.proceed(requestWithUserAgent);
  }
}
