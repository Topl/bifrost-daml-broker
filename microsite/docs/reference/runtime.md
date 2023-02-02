---
sidebar_position: 2
---

# Command Line Reference

We intruduce the list of parameters available to start the broker.

## Usage

We need the following parameters to launch the broker.

### Example

```bash
bifrost-daml-broker 0.2
Usage: bifrost-daml-broker [options]

  -n, --topl-network <value>
                           the Topl network to connect to, one of: main, valhalla, and private
  -u, --topl-uri <value>   the URI of the Topl network to connect to, for example https://127.0.0.1/
  -a, --topl-api-key <value>
                           the API key for the Topl network
  -h, --daml-host <value>  the host of the ledger, for example localhost
  -p, --daml-port <value>  the port where the ledger is listening, for example 6865
  -s, --daml-security-enabled <value>
                           whether to use TLS for the connection to the ledger
  -t, --daml-access-token <value>
                           the access token for the ledger
  -l, --daml-application-id <value>
                           the application id for the ledger, for DAML Hub hosted application the right value is 'damlhub', which is the default value when omitted
  -o, --daml-operator-party <value>
                           the party that will be used to submit transactions to the ledger
  -k, --keyfile <value>    the file that contains the operator key, for example keyfile.json
  -w, --password <value>   the password for the keyfile
```

### -n, --topl-network <value\>

The Topl network to connect to, potential values are: main, valhalla, and private.

### -u, --topl-uri <value\>

The URI of the Topl network to connect to, for example https://127.0.0.1/.

### -a, --topl-api-key <value\>

The API key for the Topl network.

### -h, --daml-host <value\>

The host of the ledger, for example localhost.

### -p, --daml-port <value\>

The port where the ledger is listening, for example 6865.


### -s, --daml-security-enabled <value\>

Whether to use TLS for the connection to the ledger. Possible values are `true` and `false`.

### -t, --daml-access-token <value\>

When the ledger is secured, this is the access token to access the Ledger API.

### -t, --daml-application-id <value\>

The application id for the ledger, for DAML Hub hosted application the right value is 'damlhub', which is the default value when this parameter is omitted.

### -o, --daml-operator-party <value\>

The party that will be used to submit and read transactions from the ledger.

### -k, --keyfile <value\>

The file that contains the operator key, for example keyfile.json. This parameter is optional. When we do not include this parameter the broker will not sign transactions as operator.

### -w, --password <value\>

The password for the key file. This is only required when a key file is present.