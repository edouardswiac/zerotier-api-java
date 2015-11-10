# zerotier-api-java [![Build Status](https://travis-ci.org/edouardswiac/zerotier-api-java.svg?branch=master)](https://travis-ci.org/edouardswiac/zerotier-api-java)
A Java library that helps you manage ZeroTier networks (https://www.zerotier.com/). The library requires an API Access Token and lets you query the status of ZeroTier and create/update/delete networks and members.


## Requirements
- Java 7+
- A ZeroTier API access token (get it from https://my.zerotier.com/ > Account)


## Installation
The library is published to maven central. If you use Maven, you can add a depency on it in your pom.xml:
```xml
<dependency>
  <groupId>com.github.edouardswiac</groupId>
  <artifactId>zerotier-api-java</artifactId>
  <version>0.6.0</version>
</dependency>
```
The library uses [OkHttp](http://square.github.io/okhttp/) for HTTP'ing and [Gson](https://github.com/google/gson) for JSON'ing.


## Features
The [`ZTService`](src/main/java/com/github/edouardswiac/zerotier/ZTService.java) interface describes the service and its available methods. The provided implementation, [`ZTServiceImpl`](src/main/java/com/github/edouardswiac/zerotier/ZTServiceImpl.java), makes HTTP requests to ZeroTier's Central REST API.

A quick list of what you can achieve with zerotier-api-java:
- ***status*** query the status of ZeroTier, see if it's online and the API version (used in the regression test)
- ***network*** create a network, update a network, delete a network
- ***member*** create a member, update a member, delete a member

Note that all the corresponding fields are not implemented. If you need more fields and want to contribute to the project, please submit a pull request with your changes along some integration testing.

## Examples
```java
ZTService ztService = new ZTServiceImpl("ZT_AUTH_TOKEN");

// create a network 
s.createNetwork(new ZTNetwork("my cool network"));

// create a member in networkId
ZTNetworkMember ztNetworkMember = new ZTNetworkMember(networkId, newMemberAddress);
s.createNetworkMember(ztNetworkMember);

// update a member
ztNetworkMember.getConfig().setAuthorized(true);
s.updateNetworkMember(ztNetworkMember);
```

## Testing
An integration test suite, [`ZTServiceTest`](src/test/java/com/github/edouardswiac/zerotier/ZTServiceTest.java), is available and run by TravisCI to make sure there are no regressions. You need to set the `ZT_AUTH_TOKEN` system property ex: `ZT_AUTH_TOKEN=...token... mvn verify` to run the integration test suite.
