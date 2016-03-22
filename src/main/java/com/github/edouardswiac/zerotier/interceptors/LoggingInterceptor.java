package com.github.edouardswiac.zerotier.interceptors;


import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import okio.Buffer;
import org.slf4j.Logger;

import java.io.IOException;
//import java.util.Arrays;

public final class LoggingInterceptor implements Interceptor {
  public final Logger log;

  public LoggingInterceptor(Logger logger) {
    log = logger;
  }
  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();

    long t1 = System.nanoTime();
    log.info(String.format("Sending %s %s", request.method(), request.url()));

    if (request.body() != null) {
      final Request reqCopy = request.newBuilder().build();
      final Buffer reqCopyBuffer = new Buffer();
      reqCopy.body().writeTo(reqCopyBuffer);
      log.info("body: {}", reqCopyBuffer.readUtf8());
    }

    Response response = chain.proceed(request);

    long t2 = System.nanoTime();
    log.info(String.format("Received HTTP %s for %s in %.1fms%n", response.code(),
        response.request().url(), (t2 - t1) / 1e6d));

    return response;
  }
}
