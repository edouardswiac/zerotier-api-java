package com.github.edouardswiac.zerotier;


import com.github.edouardswiac.zerotier.api.ZTStatus;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * http://stackoverflow.com/questions/26509107/how-to-specify-a-default-user-agent-for-okhttp-2-x-requests
 */
public class ZTServiceImpl implements ZTService {
  OkHttpClient client;
  Gson gson;
  String ztCentralUrl;
  public static String DEFAULT_URL = "https://my.zerotier.com/api/";
  public ZTServiceImpl(String ztCentralUrl, String apiAccessToken) {
    client = new OkHttpClient();
    gson = new Gson();
    this.ztCentralUrl = ztCentralUrl;
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
            .url(ztCentralUrl + "status")
            .build();

    try {
      Response response = client.newCall(request).execute();
      if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
      return gson.fromJson(response.body().charStream(), ZTStatus.class);
    } catch(IOException e) {
      throw new RuntimeException(e);
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
