---
sidebar_position: 2
---

# Using with DAML Hub

DamlHub is a cloud-based DAML development environment. It provides a DAML sandbox and a DAML ledger as a service. The broker can be used with DamlHub to connect to the Topl network.

## Prerequisites

- You need to have your application contracts, triggers and UI deployed on a ledger on DamlHub. To launch the broker we will need the ledger ID which we refer to as `<ledger id>`.
- You need to have the ledger host on DamlHub. This is usually the ledger ID followed by `.daml.app`.
- You need to create a Service User and use the login WS to obtain its access token (see [DamlHub documentation](https://hub.daml.com/docs/api/#operation/saLogin) for more details). To launch the broker we need this token, which we refer to as `<access token>`.
- You need to extract the party for the UserAdmin party from DAML Hub UI. To launch the broker we need this party, which we refer to as `<user admin party>`.
- For this documentation we assume that you are running your bifrost node locally on port 9085. To use another network, the parameters need to be adapted accordingly.
- DAML Hub is secured using TLS, thus we need to enable the TLS flag when launching the broker `-s true`.


### Launch the broker

Assuming your `keyfile.json` in the current directory and its password is `test` you can launch the broker using the following command (this uses coursier to launch it):

```bash
cs launch co.topl:bifrost-daml-broker_2.13:0.2.0 --  -n private -u http://127.0.0.1:9085 -h <ledger id>.daml.app -p 443 -s true -t "<access token>" -o "<user admin party>" -k keyfile.json -w test
```


