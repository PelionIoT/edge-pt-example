/*
 * ----------------------------------------------------------------------------
 * Copyright 2018 ARM Ltd.
 *
 * SPDX-License-Identifier: Apache-2.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ----------------------------------------------------------------------------
 */

const util = require('util')
const fs = require('fs');

const JsonRpcWs = require('json-rpc-ws');
const promisify = require('es6-promisify');
const { SSL_OP_EPHEMERAL_RSA } = require('constants');
const { stringify } = require('querystring');
const { exit } = require('process');
const { captureRejectionSymbol } = require('events');

const RED = '\x1b[31m[EdgePTExample]\x1b[0m';
const GREEN = '\x1b[32m[EdgePTExample]\x1b[0m';
const YELLOW = '\x1b[33m[EdgePTExample]\x1b[0m';

// Timeout time in milliseconds
const TIMEOUT = 10000;

const OPERATIONS = {
    READ: 0x01,
    WRITE: 0x02,
    EXECUTE: 0x04,
    DELETE: 0x08
};

var globalTemp;
var globalSetP;

var knownListOfDevices;

const valuesFileName = "device-values.json";

function EdgePTExample() {
    this.name = 'file-pt-example';
    this.api_path = '/1/pt';
    this.socket_path = '/tmp/edge.sock';

    this.client = JsonRpcWs.createClient();
}

EdgePTExample.prototype.connect = async function () {
    let self = this;
    return new Promise((resolve, reject) => {
        let url = util.format('ws+unix://%s:%s',
            this.socket_path,
            this.api_path);
        console.log(GREEN, 'Connecting to "', url, '"');
        self.client.connect(url,
            function connected(error, reply) {
                if (!error) {
                    resolve(self);
                } else {
                    reject(error);
                }
            });
    });
};

EdgePTExample.prototype.disconnect = async function () {
    let self = this;
    return new Promise((resolve, reject) => {
        console.log(GREEN, 'Disconnecting from Edge.');
        self.client.disconnect((error, response) => {
            if (!error) {
                resolve(response);
            } else {
                reject(error);
            }
        });
    });
};

EdgePTExample.prototype.registerProtocolTranslator = async function () {
    let self = this;
    return new Promise((resolve, reject) => {
        let timeout = setTimeout(() => {
            reject('Timeout');
        }, TIMEOUT);

        self.client.send('protocol_translator_register', { 'name': self.name },
            function (error, response) {
                clearTimeout(timeout);
                if (!error) {
                    // Connection ok. Set up to listen for write calls
                    // from Edge Core.
                    self.exposeWriteMethod();
                    resolve(response);
                } else {
                    reject(error);
                }
            });
    });
};

EdgePTExample.prototype._createDeviceParams = function (type, sensor) {
    let params;

    if (type == "thermostat") {

        // float object values are sent as base64 strings of the value
        let temperature = Buffer.allocUnsafe(8);
        temperature.writeDoubleBE(sensor.temp)
        temperature = temperature.toString('base64');

        let setPoint = Buffer.allocUnsafe(8);
        setPoint.writeDoubleBE(sensor.setpoint)
        setPoint = setPoint.toString('base64');

        // An IPSO/LwM2M temperature sensor and set point sensor (thermostat)
        params = {
            deviceId: sensor.name,
            objects: [{
                objectId: 3303,
                objectInstances: [{
                    objectInstanceId: 0,
                    resources: [{
                        resourceId: 5700,
                        operations: OPERATIONS.READ,
                        type: 'float',
                        value: temperature
                    }]
                }]
            }, {
                objectId: 3308,
                objectInstances: [{
                    objectInstanceId: 0,
                    resources: [{
                        resourceId: 5900,
                        operations: OPERATIONS.READ,
                        type: 'float',
                        value: setPoint
                    }]
                }]
            }]
        };

        // uncomment to debug
        //console.log(util.inspect(params, { depth: null, colors: true }));
    }
    else if (type == "blower") {
        // Values are always Base64 encoded strings.

        // static strings that we'll use in the application label field of power resources instances
        let fanLabel = Buffer.from('Fan motor')
        fanLabel = fanLabel.toString('base64');
        let heatLabel = Buffer.from('Heater coil')
        heatLabel = heatLabel.toString('base64');
        let coolLabel = Buffer.from('AC compressor')
        coolLabel = coolLabel.toString('base64');

        // boolean object values are sent as 0 or 1
        let heatState = Buffer.allocUnsafe(1);
        heatState.writeUIntBE(sensor.heat ? 1 : 0)
        heatState = heatState.toString('base64');
        let coolState = Buffer.allocUnsafe(1);
        coolState.writeUIntBE(sensor.cool ? 1 : 0)
        coolState = coolState.toString('base64');
        let fanState = Buffer.allocUnsafe(1);
        fanState.writeUIntBE(sensor.fan > 0 ? 1 : 0)
        fanState = fanState.toString('base64');
        let fanSpeed = Buffer.allocUnsafe(1);
        fanSpeed.writeUIntBE(sensor.fan);
        fanSpeed = fanSpeed.toString('base64');

        // An IPSO/LwM2M heating unit comprising a multi speed fan and discrete heating and cooling functions
        params = {
            deviceId: sensor.name,
            objects: [{
                objectId: 3312,
                objectInstances: [{
                    objectInstanceId: 0, // fan
                    resources: [{
                        resourceId: 5850, // fan on/off power state
                        operations: OPERATIONS.READ,
                        type: 'bool',
                        value: fanState
                    }, {
                        resourceId: 5851, // speed
                        operations: OPERATIONS.READ,
                        type: 'integer',
                        value: fanSpeed
                    }, {
                        resourceId: 5750, // application description
                        operations: OPERATIONS.READ,
                        type: 'string',
                        value: fanLabel
                    }]
                }, {
                    objectInstanceId: 1, // heat
                    resources: [{
                        resourceId: 5850, // heater on/off power state
                        operations: OPERATIONS.READ,
                        type: 'bool',
                        value: heatState
                    }, {
                        resourceId: 5750, // application description
                        operations: OPERATIONS.READ,
                        type: 'string',
                        value: heatLabel
                    }]
                }, {
                    objectInstanceId: 2, // cool
                    resources: [{
                        resourceId: 5850, // ac on/off power state
                        operations: OPERATIONS.READ,
                        type: 'bool',
                        value: coolState
                    }, {
                        resourceId: 5750, // application description
                        operations: OPERATIONS.READ,
                        type: 'string',
                        value: coolLabel

                    }]
                }]
            }]
        };
    }
    return params;
}

EdgePTExample.prototype.registerDevice = async function (type, sensor) {
    let self = this;
    return new Promise((resolve, reject) => {
        params = self._createDeviceParams(type, sensor);

        let timeout = setTimeout(() => {
            reject('Timeout');
        }, TIMEOUT);

        self.client.send('device_register', params,
            function (error, response) {
                clearTimeout(timeout);
                if (!error) {
                    resolve(response);
                } else {
                    reject(error);
                }
            });
    });
}

EdgePTExample.prototype.unregisterDevices = async function () {
    let self = this;
    return new Promise((resolve, reject) => {
        let timeout = setTimeout(() => {
            reject('Timeout');
        }, TIMEOUT);
        
        for (i = 0; i < knownListOfDevices.length; i++) {
            for (var type in devicesReadFromFile[i].sensors) {
                self.client.send('device_unregister', { deviceId: devicesReadFromFile[i].sensors[type].name },
                    function (error, response) {
                        clearTimeout(timeout);
                        if (!error) {
                            resolve(response);
                        } else {
                            reject(error);
                        }
                    });
            }
        }
    });
}


EdgePTExample.prototype.updateDeviceResources = async function (type, sensor) {
    let self = this;
    return new Promise((resolve, reject) => {
        params = self._createDeviceParams(type, sensor);

        let timeout = setTimeout(() => {
            reject('Timeout');
        }, TIMEOUT);

        self.client.send('write', params,
            function (error, response) {
                clearTimeout(timeout);
                if (!error) {
                    console.log('Device update sent:', sensor);
                    resolve(response);
                } else {
                    reject(error);
                }
            });

    });
}

EdgePTExample.prototype.exposeWriteMethod = function () {
    let self = this;
    self.client.expose('write', (params, response) => {
        let valueBuf = new Buffer.from(params.value, 'base64'); // hold as a buffer because we don't yet know the data type yet
        let resourcePath = params.uri.objectId + '/' + params.uri.objectInstanceId
            + '/' + params.uri.resourceId;
        let deviceId = params.uri.deviceId;

        let operation = '';
        if (params.operation === OPERATIONS.WRITE) {
            operation = 'write';
        } else if (params.operation === OPERATIONS.EXECUTE) {
            operation = 'execute';
        } else {
            operation = 'unknown';
        }

        received = {
            deviceId: deviceId,
            resourcePath: resourcePath,
            operation: operation,
            valueBuf: valueBuf
        }
        console.log(GREEN, 'Received a write method with data:');
        console.log(received.valueBuf);

        // look through our known list of rooms and devices for the matching device id
        // when the matching device is found update our local copy of the device and then update the file
        for (var room = 0; room < knownListOfDevices.length; room++) {
            for (var type in knownListOfDevices[room].sensors) {

                if (received.deviceId == knownListOfDevices[room].sensors[type].name) {
                    console.log(GREEN, `updating ${knownListOfDevices[room].sensors[type].name}`);
                    
                    if (type == "thermostat") {
                        knownListOfDevices[room].sensors[type].setpoint = (received.valueBuf).readDoubleBE();
                    }
                    else if (type == "blower") {
                        if (received.resourcePath == "3312/0/5850") {
                            // fan on/off state
                            // the fan could be turned on to a speed with 3312/0/5851, if 5850 is used then set to max speed
                            knownListOfDevices[room].sensors[type].fan = ((received.valueBuf).readInt8() == 0 ? 0: 3);
                            // call the updateDeviceResources function to tidy up any fan on/off to fan speed relationship changes to resource values
                            self.updateDeviceResources(type, knownListOfDevices[room].sensors[type]);                            
                        }
                        else if (received.resourcePath == "3312/0/5851") {
                            // fan speed
                            swapped = ((received.valueBuf).swap64()).readInt8();
                            knownListOfDevices[room].sensors[type].fan = swapped;
                            // call the updateDeviceResources function to tidy up any fan on/off to fan speed relationship changes to resource values
                            self.updateDeviceResources(type, knownListOfDevices[room].sensors[type]);
                        }
                        else if (received.resourcePath == "3312/1/5850") {
                            // heater on/off state
                            knownListOfDevices[room].sensors[type].heat = ((received.valueBuf).readInt8() == 0 ? false: true);
                        }
                        else if (received.resourcePath == "3312/2/5850") {
                            // ac compressor on/off state
                            knownListOfDevices[room].sensors[type].cool = ((received.valueBuf).readInt8() == 0 ? false: true);
                        }
                    }

                    // update JSON file
                    fs.writeFileSync(valuesFileName, JSON.stringify(knownListOfDevices, null, 4), (err) => {
                        if (err) console.log(err);
                    });
                }
            }
        }

        /* Always respond back to Edge, it is expecting
         * a success response to finish the write/execute action.
         * If an error is returned the value write is discarded
         * also in the Edge Core.
         */
        response(/* no error */ null, /* success */ 'ok');
    });
};

const holdProgress = async (message) => {
    process.stdin.setRawMode(true)
    console.log(YELLOW, util.format('\x1b[1m%s\x1b[0m', message));
    return new Promise(resolve => process.stdin.once('data', () => {
        process.stdin.setRawMode(false);
        resolve();
    }));
}

EdgePTExample.prototype.initValuesFile = function () {
    let self = this;

    var fileBuf = fs.readFileSync(valuesFileName, "utf-8")
    if (fileBuf == "") {
        reject(0);
    }
    var devicesReadFromFile = JSON.parse(fileBuf);
    var countSensors = 0;
    // walk through devices found in the json file registering a device instance with edge for each device    
    for (var i=0; i < devicesReadFromFile.length; i++) {
        for (var type in devicesReadFromFile[i].sensors) {
            console.log("processing " + devicesReadFromFile[i].sensors[type].name);
            self.registerDevice(type, devicesReadFromFile[i].sensors[type]);
            countSensors++;
        }
    }

    // grab our list of read devices and keep in our known list for future compare
    knownListOfDevices = devicesReadFromFile;

    return countSensors;
}

EdgePTExample.prototype.pollValuesFile = function () {
    let self = this;

    var fileBuf = fs.readFileSync(valuesFileName, "utf-8")
    if (fileBuf == "") {
        reject(0);
    }
    var devicesReadFromFile = JSON.parse(fileBuf);

    // walk through knownListOfDevices comparing with each device read from the file
    // if no changes have been detected then no action is required
    // if a change is found then trigger the update resources function with the new values
    for (var i = 0; i < devicesReadFromFile.length; i++) {
        for (var type in devicesReadFromFile[i].sensors) {
            var sensor = devicesReadFromFile[i].sensors[type];

            if (sensor.name == knownListOfDevices[i].sensors[type].name) {
                // when we're comparing the same device between the file contents and our last read version
                // if there are any changes then update our last read version and send these new values to edge
                if (JSON.stringify(knownListOfDevices[i].sensors[type]) != JSON.stringify(devicesReadFromFile[i].sensors[type])) {
                    console.log(util.inspect(devicesReadFromFile[i].sensors[type], {showHidden: false, depth: null}));
                    // update device
                    knownListOfDevices[i].sensors[type] = devicesReadFromFile[i].sensors[type];
                    self.updateDeviceResources(type, knownListOfDevices[i].sensors[type]);
                }
            }
        }
    }
}


function sleep(ms) {
    return new Promise((resolve) => {
        setTimeout(resolve, ms);
    })
}

(async function () {
    try {
        edge = new EdgePTExample();

        // Set SIGINT handle
        let quitImmediately = false;
        let sigintHandler;
        process.on('SIGINT', sigintHandler = async function () {
            if (quitImmediately) process.exit(1);
            try {
                await edge.disconnect();
            } catch (ex) { }
            process.exit(1);
        });

        // connect to edge, register the translator, register the device
        await edge.connect();
        console.log(GREEN, 'Connected to Edge');

        let response = await edge.registerProtocolTranslator();
        console.log(GREEN, 'Registered as protocol translator. Response:', response);

        await sleep(1000);
        response = await edge.initValuesFile();
        console.log(GREEN, 'Devices registered', response);

        console.log(GREEN, 'Kill this protocol translator with Ctrl+C');

        // loop the update resources function which includes file open/read/parse/update/send to edge socket
        while (1) {
            await sleep(1000);
            try {
                edge.pollValuesFile();
            } catch (ex) {
                console.log(ex);
                process.exit(1);
            }
        }

        //response = await edge.unregisterDevices();
        //console.log(GREEN, 'Example device unregistered. Response:', response);

    } catch (ex) {
        try {
            console.error(RED, 'Error...', ex);
            await edge.disconnect();
            process.exit(1);
        } catch (err) {
            console.error(RED, 'Error on closing the Edge Core connection.', err);
            process.exit(1);
        }
    }
})();




