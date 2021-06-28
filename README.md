# edge-pt-example

A demo utilizing [Protocol Translation](https://developer.pelion.com/docs/device-management-edge/latest/protocol-translator/index.html) feature of [Pelion Edge](https://developer.pelion.com/docs/device-management-edge/latest/introduction/index.html) to model a building heating control system. 

![edge-pt-example](https://i.ibb.co/NT9zkkc/pelion-edge-pt-diagram.png "edge-pt-example")


## Getting Started

You will need Pelion Edge running to run the protocol translation example code. Check our [Try Pelion Edge](https://try.pelion.com/edge/) page for information on how you can run Edge locally in your machine under a VirtualBox machine.

Once you have setup Pelion Edge, clone the repository and switch to the edge folder:

```
git clone https://github.com/PelionIoT/edge-pt-example
cd ./edge-pt-example/edge/
```

Install NodeJS dependencies required by the example:

```
npm install
```

We are now ready to run. 
The protocol translator example is split into two parts: the `‘file-pt-example.js’` and `‘file-pt-example-ui.js’`. The former contains the main protocol translation logic which registers to “Edge Core” and exposes the devices while the latter contains a user interface able to visualize the current state as well as controlling the logic when this state changes.  

Both scripts process a file `‘device-values.json’` which contains the description of the devices (thermostat, fan, heating/cooling control) as well as their last saved state. When toggling the user interface widgets, the file gets updated and those changes are picked up by the main protocol logic script to update ”Edge-Core” and in-turn Pelion device management.

First let's start the main prototocol translation unit:

```
sudo node file-pt-example.js
```

The program will start and register to `'Edge Core'` all the devices described in the `‘device-values.json’` file.


```
~/edge$ sudo node file-pt-example.js 
[EdgePTExample] Connecting to " ws+unix:///tmp/edge.sock:/1/pt "
[EdgePTExample] Connected to Edge
[EdgePTExample] Registered as protocol translator. Response: ok
processing reception-thermostat
processing reception-blower
processing hall-thermostat
processing hall-blower
[EdgePTExample] Devices registered 4
[EdgePTExample] Kill this protocol translator with Ctrl+C
```

Those devices would then be shown as connected to the gateway in Pelion Device Management portal:

![edge-pt-portal](https://i.ibb.co/cNVbgKd/pelion-edge-pt-portal-gateway.png "edge-pt-portal")


On a separate terminal start the user interface part to visualize the devices and control their state:

```
sudo node file-pt-example-ui.js
```

You will be presented with the following screen:

![edge ui](https://i.ibb.co/NW9vqxb/pelion-edge-pt-current-scaled.png "edge ui")

> Use the keyboard shortcuts displayed on screen to toggle the monitoring room, the ambient temperature and the thermostat set point.

The UI script monitors when you toggle the ambient temperature and thermostat set-point and upon detecting a change, it updates the device LwM2M resources in Pelion Device Management.

In particular, it applies the following rules:

- If **current temperature < thermostat-set-point** then we turn ON fan+heater​
- If **current temperature = thermostat-set-point** then we turn OFF fan+heater+a/c
- If **current temperature > thermostat-set-point** by 2 degrees then we turn ON fan
- If **current temperature > thermostat-set-point** by 3 degrees then we turn the A/C ON
- If **current temperature to thermostat-set-point** less than 3 degrees difference then we set the fan speed 1​
- If **current temperature to thermostat-set-point** larger than 3 degrees then we set the fan speed 2​
- If **current temperature to thermostat-set-point** larger than 5 degrees then we set the fan speed 3​

## Analytics
To complement this protocol-translator example running on the edge gatway, we've also written a cloud analytic part where an application connected to Pelion Device Management monitors the changes and applies analytics to determine whether the building heating control system is operating efficiently. Please visit ['cloud-java-quarkus/README.MD'](https://github.com/PelionIoT/edge-pt-example/blob/master/cloud-java-quarkus/README.md) for more information.



​


