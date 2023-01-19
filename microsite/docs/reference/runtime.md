---
sidebar_position: 2
---

# Command Line Reference

We intruduce the list of parameters available to start the broker.

## Usage

We need the following parameters to launch the broker.

### Example

```bash
Usage: bifrost-daml-broker [options]

  -n, --topl-network <value>
                           the Topl network to connect to, one of: main, valhalla, and private
  -u, --topl-uri <value>   the URI of the Topl network to connect to, for example https://127.0.0.1/
  -a, --topl-api-key <value>
                           the API key for the Topl network
  -h, --daml-host <value>  the host of the ledger, for example localhost
  -p, --daml-port <value>  the port where the ledger is listening, for example 6865
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

### -k, --keyfile <value\>

The file that contains the operator key, for example keyfile.json. This parameter is optional. When we do not include this parameter the broker will not sign transactions as operator.

### -w, --password <value\>

The password for the key file. This is only required when a key file is present.