package com.github.edouardswiac.zerotier;


import com.github.edouardswiac.zerotier.api.ZTNetwork;
import com.github.edouardswiac.zerotier.api.ZTNetworkMember;
import com.github.edouardswiac.zerotier.api.ZTStatus;
import com.github.edouardswiac.zerotier.interceptors.AuthenticationInterceptor;
import com.github.edouardswiac.zerotier.interceptors.LoggingInterceptor;
import com.github.edouardswiac.zerotier.interceptors.UserAgentInterceptor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;


public class ZTServiceImpl implements ZTService {
  private final OkHttpClient client;
  private final Gson gson;
  private final HttpUrl baseApiUrl;

  public static Logger LOG = LoggerFactory.getLogger("HTTP Client");
  public static String USER_AGENT = String.format("ZeroTier API Java Client v%s <https://github.com/edouardswiac/zerotier-api-java>", API_VERSION);

  public ZTServiceImpl(String apiAccessToken) {
    this(ZT_COM_CENTRAL_URL, apiAccessToken);
  }

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
  public List<ZTNetwork> getNetworks() {
    return Arrays.asList(call("network", ZTNetwork[].class));
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
    return call("status", ZTStatus.class);
  }

  @Override
  public Map<String, Integer> getNetworkMembers(String networkId) {
    HttpUrl httpUrl = baseApiUrl.newBuilder()
            .addPathSegment("network")
            .addPathSegment(networkId)
            .addPathSegment("member")
            .build();

    final Type type = new TypeToken<HashMap<String, Integer>>(){}.getType();
    Map<String, Integer> membersWithVersion = call(httpUrl, type);
    return Collections.unmodifiableMap(membersWithVersion);
  }

  @Override
  public void getNetworkMember() {

  }

  @Override
  public void updateNetworkMember() {

  }

  <T> T call(String path, Class<T> entityClass) {
    return call(baseApiUrl.newBuilder().addPathSegment(path).build(), entityClass);
  }

  // TODO refactor
  <T> T call(HttpUrl httpUrl, Class<T> entityClass) {
    Request request = new Request.Builder()
            .url(httpUrl)
            .build();

    try {
      Response response = client.newCall(request).execute();
      if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
      return gson.fromJson(response.body().charStream(), entityClass);
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }

  <T> T call(HttpUrl httpUrl, Type t) {
    Request request = new Request.Builder()
            .url(httpUrl)
            .build();

    try {
      Response response = client.newCall(request).execute();
      if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
      return gson.fromJson(response.body().charStream(), t);
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }


}
