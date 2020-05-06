# What is this?

A filebeat setup for Ants?

It needs to be at least

## THREE TIMES

this size

---

## Step 1. Install Filebeat

The following command should do the trick you Macarena Man

    curl -L -O https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-7.6.2-darwin-x86_64.tar.gztar xzvf filebeat-7.6.2-darwin-x86_64.tar.gz

The version we're on is 7.6.2

---

## Step 2. Configure Filebeat

The default config file should be `filebeat.yml`, which should be included in the home directory of wherever you extracted the binary file

### 2.1 Inputs

This is where we specify the path to our local test result files. The following are needed

    - type: log

      enabled: true

      paths:
        - path/to/results/folder/*.json

      json.keys_under_root: true
      json.add_error_key: true
      json.message_key: msg

- `type` specifies what type of file Filebeat is expecting. Other options include Stdin, 

- `enabled` allows/disallows this input config
- `paths` include path(s) to files we want. Mine is `c:\Users\Kevin Cleary\CASE4\CA400\2020-ca400-clearyk6-oneilj54\res\results\*.json` . Did not need to wrap in quotes.
- `json.keys_under_root`, `json.add_error_key` and `json.message_key` are all configuration enabling json parsing, copying keys to top level in the output, adding error messaging and specifying a message key to allow the breakup of the json object within the doc respecitively

### 2.2 Filebeat Modules

Left this one pretty much alone, default should be as follows

    filebeat.config.modules:
   
      path: ${path.config}/modules.d/*.yml
      
      reload.enabled: false

- Has an option to increase period on which results folder should be checked too tbh

### 2.3 ElasticSearch Template Setting

Have left this one at the following

    setup.template.settings:
      index.number_of_shards: 1

- Incresing no. of shards could be good for memory management, but for now it should be ok

### 2.4 Outputs

This is where we specify the AWS cluster endpoint

    output.elasticsearch:
      
      hosts: ["https://search-ca400-2020-aco-xkszrndwe4qr4owjodgsabhday.eu-west-1.es.amazonaws.com:443"]
      # index: "index-name"

    setup.ilm.enabled: false
    setup.ilm.overwrite: true
    ilm.enabled: false

- can change index field to point at a given index
- ilm settings are used to overwrite Filebeat's Index Lifecycle Management defaults, which are not supported by AWS

### 2.5 Processors

Configure processors to extract json object from log, and try to stop unneccesary fields being created and used

    processors:
      - decode_json_fields:
        fields: "msg"
        process_array: true
        max_depth: 2
        overwrite_keys: true
        target: ""
        add_error_key: true

      - drop_fields:
        fields: ["agent.ephemeral_id", "agent.hostnme", "agent.hostname", "agent.id", "agent.type", "agent.version", "ecs.version", "error.message", "host.name", "log.file.path", "log.offset"]

- `fields` is "msg" to match `json.message_key` in inputs above
- `max_depth` specifies how deep into the json object we create ES fields for. At 2, we can create fields out of the iteration json objects nested within the attempt json objects.

- `drop_fields` allows us to specify a list of fields we do not wish to be sent to ES. Doesn't work on my machine as of 05052020

All other lines in the .yml file are commented out. Be careful with indentation, try to use single spaces over tabs.

### 2.6 run setup

Following mac attacc in the FIlebeat directory

    ./filebeat setup --pipelines --index-management -c filebeat.yml

---

## Running  Filebeat

Following mac commands

    sudo chown root filebeat.yml
    sudo ./filebeat -e

> Note; will need to change ownership of config file or else run Filebeat with --strict.perms=false specified. 


Find a full guide to installing and setting up a general Filebeat service [here](https://www.elastic.co/guide/en/beats/filebeat/current/filebeat-installation.html)