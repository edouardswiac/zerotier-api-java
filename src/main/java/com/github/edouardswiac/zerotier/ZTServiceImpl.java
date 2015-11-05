package com.github.edouardswiac.zerotier;


import com.github.edouardswiac.zerotier.api.ZTStatus;
import com.github.edouardswiac.zerotier.exceptions.ZTServiceException;
import com.github.edouardswiac.zerotier.interceptors.AuthenticationInterceptor;
import com.github.edouardswiac.zerotier.interceptors.LoggingInterceptor;
import com.github.edouardswiac.zerotier.interceptors.UserAgentInterceptor;
import com.google.gson.Gson;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class ZTServiceImpl implements ZTService {
  private final OkHttpClient client;
  private final Gson gson;
  private final HttpUrl baseApiUrl;

  public static Logger LOG = LoggerFactory.getLogger("HTTP Client");
  public static String USER_AGENT = String.format("ZeroTier API Java Client v%s <https://github.com/edouardswiac/zerotier-api-java>", API_VERSION);

  public ZTServiceImpl(String ztCentralUrl, String apiAccessToken) {
    this.gson = new Gson();

    this.client = new OkHttpClient();
    // user agent
    client.interceptors().add(new UserAgentInterceptor(USER_AGENT));
    // auth
    client.interceptors().add(new AuthenticationInterceptor(apiAccessToken));
    // logging
    client.interceptors().add(new LoggingInterceptor(LOG));

    this.baseApiUrl = HttpUrl.parse(ztCentralUrl);
  }

  @Override
  public void createNetwork() {

  }

  @Override
  public void getNetwork() {

  }

  @Override
  public void updateNetowk() {

  }

  @Override
  public void deleteNetwork() {

  }

  @Override
  public void listNetworks() {

  }

  @Override
  public ZTStatus status() {
    Request request = new Request.Builder()
            .url(baseApiUrl.newBuilder().addPathSegment("status").build())
            .build();

    try {
      Response response = client.newCall(request).execute();
      if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
      return gson.fromJson(response.body().charStream(), ZTStatus.class);
    } catch(IOException e) {
      throw new ZTServiceException(e);
    }
  }

  @Override
  public void getNetworkMembers() {

  }

  @Override
  public void getNetworkMember() {

  }

  @Override
  public void updateNetworkMember() {

  }
}
