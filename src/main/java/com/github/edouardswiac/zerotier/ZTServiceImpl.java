package com.github.edouardswiac.zerotier;


import com.github.edouardswiac.zerotier.api.ZTCMember;
import com.github.edouardswiac.zerotier.api.ZTCNetwork;
import com.github.edouardswiac.zerotier.api.ZTController;
import com.github.edouardswiac.zerotier.api.ZTNetwork;
import com.github.edouardswiac.zerotier.api.ZTNetworkMember;
import com.github.edouardswiac.zerotier.api.ZTStatus;
import com.github.edouardswiac.zerotier.exceptions.ZTClientException;
import com.github.edouardswiac.zerotier.exceptions.ZTServiceException;
import com.github.edouardswiac.zerotier.interceptors.AuthenticationInterceptor;
import com.github.edouardswiac.zerotier.interceptors.LoggingInterceptor;
import com.github.edouardswiac.zerotier.interceptors.UserAgentInterceptor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;


public final class ZTServiceImpl implements ZTService {
  private final OkHttpClient client;
  private final Gson gson;
  private final HttpUrl baseApiUrl;

  private final static Logger LOG = LoggerFactory.getLogger("HTTP Client");
  private final static String USER_AGENT = String.format("ZeroTier API Java Client v%s <https://github.com/edouardswiac/zerotier-api-java>", API_VERSION);
  private final static MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

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
  public void createNetwork(ZTNetwork network) {
    final HttpUrl targetUrl = baseApiUrl.newBuilder()
            .addPathSegment("network")
            .build();

    final Request request = new Request.Builder()
            .url(targetUrl)
            .post(RequestBody.create(MEDIA_TYPE_JSON, gson.toJson(network)))
            .build();
    execute(request);
  }

  @Override
  public List<ZTNetwork> getNetworks() {
    final HttpUrl targetUrl = baseApiUrl.newBuilder().addPathSegment("network").build();
    final Request request = new Request.Builder().url(targetUrl).build();
    return Arrays.asList(get(request, ZTNetwork[].class));
  }

  @Override
  public ZTNetwork getNetwork(@NotNull String networkId) {
    final HttpUrl targetUrl = baseApiUrl.newBuilder()
            .addPathSegment("network")
            .addPathSegment(networkId)
            .build();
    final Request request = new Request.Builder().url(targetUrl).build();
    return get(request, ZTNetwork.class);
  }

  @Override
  public void updateNetwork(@NotNull ZTNetwork network) {
    if (network.getNwid() == null)
      throw new IllegalArgumentException("network ID must not be null");

    final HttpUrl targetUrl = baseApiUrl.newBuilder()
            .addPathSegment("network")
            .addPathSegment(network.getNwid())
            .build();

    final Request request = new Request.Builder()
            .url(targetUrl)
            .post(RequestBody.create(MEDIA_TYPE_JSON, gson.toJson(network)))
            .build();
    execute(request);
  }

  @Override
  public void deleteNetwork(@NotNull String networkId) {
    final HttpUrl targetUrl = baseApiUrl.newBuilder()
            .addPathSegment("network")
            .addPathSegment(networkId)
            .build();

    final Request request = new Request.Builder()
            .url(targetUrl)
            .delete()
            .build();
    try {
      execute(request);
    } catch (ZTServiceException ex) {
      // catch the 500 that's returned when deleting :/
      if (!ex.getCause().getLocalizedMessage().contains("code=500")) {
        throw ex;
      }
    }
  }

  @Override
  public Map<String, Integer> getNetworkMembers(@NotNull String networkId) {
    final HttpUrl targetUrl = baseApiUrl.newBuilder()
            .addPathSegment("network")
            .addPathSegment(networkId)
            .addPathSegment("member")
            .build();
    final Type entityType = new TypeToken<HashMap<String, Integer>>(){}.getType();
    final Request request = new Request.Builder().url(targetUrl).build();
    final Map<String, Integer> membersWithVersion = get(request, entityType);
    return Collections.unmodifiableMap(membersWithVersion);
  }

  @Override
  public void createNetworkMember(@NotNull ZTNetworkMember networkMember) {
    // zt api treats it the same
    updateNetworkMember(networkMember);
  }

  @Override
  public ZTNetworkMember getNetworkMember(@NotNull String networkId, @NotNull String address) {
    final HttpUrl targetUrl = baseApiUrl.newBuilder()
            .addPathSegment("network")
            .addPathSegment(networkId)
            .addPathSegment("member")
            .addPathSegment(address)
            .build();
    final Request request = new Request.Builder().url(targetUrl).build();
    return get(request, ZTNetworkMember.class);
  }

  @Override
  public void updateNetworkMember(@NotNull ZTNetworkMember networkMember) {
    if (networkMember.getNetworkId() == null)
      throw new IllegalArgumentException("network ID must not be null");
    if (networkMember.getNodeId() == null)
      throw new IllegalArgumentException("node ID must not be null");

    final HttpUrl targetUrl = baseApiUrl.newBuilder()
            .addPathSegment("network")
            .addPathSegment(networkMember.getNetworkId())
            .addPathSegment("member")
            .addPathSegment(networkMember.getNodeId())
            .build();

    final Request request = new Request.Builder()
            .url(targetUrl)
            .post(RequestBody.create(MEDIA_TYPE_JSON, gson.toJson(networkMember)))
            .build();
    execute(request);
  }

  @Override
  public void deleteNetworkMember(@NotNull String networkId, String address) {
    final HttpUrl targetUrl = baseApiUrl.newBuilder()
            .addPathSegment("network")
            .addPathSegment(networkId)
            .addPathSegment("member")
            .addPathSegment(address)
            .build();

    final Request request = new Request.Builder()
            .url(targetUrl)
            .delete()
            .build();
    execute(request);
  }

  //=======================================

  @Override
  public ZTStatus status() {
    final HttpUrl targetUrl = baseApiUrl.newBuilder().addPathSegment("status").build();
    final Request request = new Request.Builder().url(targetUrl).build();
    return get(request, ZTStatus.class);
  }

  @Override
  public List<ZTNetwork> getNets() {
    final HttpUrl targetUrl = baseApiUrl.newBuilder().addPathSegment("network").build();
    final Request request = new Request.Builder().url(targetUrl).build();
    return Arrays.asList(get(request, ZTNetwork[].class));
  }

  @Override
  public ZTNetwork joinNet(String nwid) {
    final HttpUrl targetUrl = baseApiUrl.newBuilder()
        .addPathSegment("network")
        .addPathSegment(nwid)
        .build();
    final Request request = new Request.Builder()
        .url(targetUrl)
        .post(RequestBody.create(MEDIA_TYPE_JSON, "{}"))
        .build();
    return get(request, ZTNetwork.class);
  }

  @Override
  public ZTController controller() {
    final HttpUrl targetUrl = baseApiUrl.newBuilder().addPathSegment("controller").build();
    final Request request = new Request.Builder().url(targetUrl).build();
    return get(request, ZTController.class);
  }

  @Override
  public List<String> getCNetworks() {
    final HttpUrl targetUrl = baseApiUrl.newBuilder()
        .addPathSegment("controller")
        .addPathSegment("network").build();
    final Request request = new Request.Builder().url(targetUrl).build();
    return Arrays.asList(get(request, String[].class));
  }

  @Override
  public ZTCNetwork getCNetwork(@NotNull String networkId) {
    final HttpUrl targetUrl = baseApiUrl.newBuilder()
        .addPathSegment("controller")
        .addPathSegment("network")
        .addPathSegment(networkId)
        .build();
    final Request request = new Request.Builder().url(targetUrl).build();
    return get(request, ZTCNetwork.class);
  }

  @Override
  public ZTCNetwork updateCNetwork(@NotNull ZTCNetwork network) {
    if (network.getNwid() == null)
      throw new IllegalArgumentException("network ID must not be null");

    final HttpUrl targetUrl = baseApiUrl.newBuilder()
        .addPathSegment("controller")
        .addPathSegment("network")
        .addPathSegment(network.getNwid())
        .build();

    final Request request = new Request.Builder()
        .url(targetUrl)
        .post(RequestBody.create(MEDIA_TYPE_JSON, gson.toJson(network)))
        .build();
    return get(request, ZTCNetwork.class);
  }

  @Override
  public void deleteCNetwork(@NotNull String networkId) {
    final HttpUrl targetUrl = baseApiUrl.newBuilder()
        .addPathSegment("controller")
        .addPathSegment("network")
        .addPathSegment(networkId)
        .build();

    final Request request = new Request.Builder()
        .url(targetUrl)
        .delete()
        .build();
    try {
      execute(request);
    } catch (ZTServiceException ex) {
      // catch the 500 that's returned when deleting :/
      if (!ex.getCause().getLocalizedMessage().contains("code=500")) {
        throw ex;
      }
    }
  }

  @Override
  public Map<String, Integer> getCMembers(@NotNull String networkId) {
    final HttpUrl targetUrl = baseApiUrl.newBuilder()
        .addPathSegment("controller")
        .addPathSegment("network")
        .addPathSegment(networkId)
        .addPathSegment("member")
        .build();
    final Type entityType = new TypeToken<HashMap<String, Integer>>(){}.getType();
    final Request request = new Request.Builder().url(targetUrl).build();
    final Map<String, Integer> membersWithVersion = get(request, entityType);
    return Collections.unmodifiableMap(membersWithVersion);
  }

  @Override
  public ZTCMember getCMember(@NotNull String networkId, @NotNull String memberId) {
    final HttpUrl targetUrl = baseApiUrl.newBuilder()
        .addPathSegment("controller")
        .addPathSegment("network")
        .addPathSegment(networkId)
        .addPathSegment("member")
        .addPathSegment(memberId)
        .build();
    final Request request = new Request.Builder().url(targetUrl).build();
    return get(request, ZTCMember.class);
  }

  @Override
  public ZTCMember updateCMember(@NotNull ZTCMember member) {
    if (member.getNwid() == null)
      throw new IllegalArgumentException("network ID must not be null");
    if (member.getAddress() == null)
      throw new IllegalArgumentException("address must not be null");

    final HttpUrl targetUrl = baseApiUrl.newBuilder()
        .addPathSegment("controller")
        .addPathSegment("network")
        .addPathSegment(member.getNwid())
        .addPathSegment("member")
        .addPathSegment(member.getAddress())
        .build();

    final Request request = new Request.Builder()
        .url(targetUrl)
        .post(RequestBody.create(MEDIA_TYPE_JSON, gson.toJson(member)))
        .build();
    return get(request, ZTCMember.class);
  }

  @Override
  public void deleteCMember(@NotNull ZTCMember member) {
    if (member.getNwid() == null)
      throw new IllegalArgumentException("network ID must not be null");
    if (member.getAddress() == null)
      throw new IllegalArgumentException("address must not be null");
    deleteCMember(member.getNwid(), member.getAddress());
  }
  @Override
  public void deleteCMember(@NotNull String networkId, @NotNull String memberId) {
    final HttpUrl targetUrl = baseApiUrl.newBuilder()
        .addPathSegment("controller")
        .addPathSegment("network")
        .addPathSegment(networkId)
        .addPathSegment("member")
        .addPathSegment(memberId)
        .build();

    final Request request = new Request.Builder()
        .url(targetUrl)
        .delete()
        .build();
    try {
      execute(request);
    } catch (ZTServiceException ex) {
      // catch the 500 that's returned when deleting :/
      if (!ex.getCause().getLocalizedMessage().contains("code=500")) {
        throw ex;
      }
    }
  }


  //=======================================

  Response execute(Request request) {
    try {
      final Response response = client.newCall(request).execute();
      if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
      return response;
    } catch(IOException e) {
      throw new ZTServiceException(e);
    }
  }

  <T> T get(Request request, Class<T> entityClass) {
    final Response response = execute(request);
    try {
      return gson.fromJson(response.body().charStream(), entityClass);
    } catch (IOException e) {
      throw new ZTClientException("cannot deserialize resource into its corresponding bean", e);
    }
  }

  <T> T get(Request request, Type entityClass) {
    final Response response = execute(request);
    try {
      return gson.fromJson(response.body().charStream(), entityClass);
    } catch (IOException e) {
      throw new ZTClientException("cannot deserialize resource into its corresponding bean", e);
    }
  }

}
