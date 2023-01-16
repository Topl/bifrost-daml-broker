---
sidebar_position: 2
---

# Command Line Reference

Shows the list of parameters available to start the broker.

## Usage

The following parameters are needed to launch the broker.

### Example

```bash
bifrost-daml-broker <HOST> <PORT> <KEYFILENAME> <KEYFILEPASSWORD>
```

### HOST

The host where the DAML participant node is hosted, for example, `localhost`.

### PORT

The port of the host where the DAML partticipant node is hosted, for example `6865`.

### KEYFILENAME

The filename that contains the key that the broker uses for signing, for example `keyfile.json`.

### KEYFILEPASSWORD

The password for the key file used for signing, for example `test`.