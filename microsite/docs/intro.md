---
sidebar_position: 1
---

# Getting Started

Let's discover **bifrost-daml-broker in less than 5 minutes**.

## Development Usage

If you are using the bifrost-daml-broker for development purposes in conjunction with the DAML toolset,
for example, the `daml sandbox` or `daml start` command please refer to this section.

### System Requirements

Your system needs to have the following software installed.

- A Java Virtual Machine (JVM).- The bifrost-daml-broker is a Java application, and thus Java is needed.
- [Coursier](https://get-coursier.io/docs/cli-installation).- A simple command line tool (CLI) to
to run Java applications without any setup. It is very easy to install.
- [DAML SDK](https://docs.daml.com/getting-started/installation.html).- We need the DAML SDK to integrate with DAML
- [Docker](https://www.docker.com/products/docker-desktop/).- We are using Docker to launch the Bifrost node easily. 

### Broker Requirements

To launch the broker you need to provide it with a `keyfile.json` file and the password for that file.

###  Initializing the DAML Ledger

Before launching the broker you need to initialize the DAML ledger. For this, your ledger initialization script
needs to allocate a party for the broker. The broker will use this party to interact with the ledger.

This can be done by adding the following line to your DAML initialization script:

```daml
import Daml.Script
import DA.Optional

initialize : Script [Party]
initialize = do
    broker <- allocateParty "broker"
    brokerId <- validateUserId "broker"
    createUser (Daml.Script.User brokerId (Some broker)) [CanActAs broker]
    debug ("broker party: " <> partyToText broker)
    pure []
```

The debug statement will print the party allocated for the broker. You will need this party to launch the broker.

### Launch the Prerequirements

You need to launch the DAML sandbox from your DAML project using the following command:

```bash
daml start
```

You also need to launch a Bifrost node using the following command:

```bash
docker run -p 9085:9085 toplprotocol/bifrost-node:1.10.2 --forge --disableAuth --seed test --debug
```

### Launch the broker

Assuming your `keyfile.json` in the current directory and its password is `test` you can launch the broker using the following command (this uses coursier to launch it):

```bash
cs launch co.topl:bifrost-daml-broker_2.13:0.2.0 --  -n private -u http://127.0.0.1:9085 -h localhost -p 6865 -s false -o <broker party> -k keyfile.json -w test
```

