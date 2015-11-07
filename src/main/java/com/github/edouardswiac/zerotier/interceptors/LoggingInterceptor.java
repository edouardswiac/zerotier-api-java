package com.github.edouardswiac.zerotier.interceptors;


import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;

import java.io.IOException;

public class LoggingInterceptor implements Interceptor {
  public final Logger log;

  public LoggingInterceptor(Logger logger) {
    log = logger;
  }
  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();

    long t1 = System.nanoTime();
    log.info(String.format("Sending request %s on %s%n%s",
        request.url(), chain.connection(), request.headers()));

    Response response = chain.proceed(request);

    long t2 = System.nanoTime();
    log.info(String.format("Received response for %s in %.1fms%n%s",
        response.request().url(), (t2 - t1) / 1e6d, response.headers()));

    return response;
  }
}