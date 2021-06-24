var fs = require('fs')
    , blessed = require('blessed')
    , contrib = require('blessed-contrib')
    , screen = blessed.screen()
    , data = require('./device-values.json')

screen.title = 'Pelion Protocol Translation Demo';

// current room
let room = {};
// current visible widget
let pointWidget
    , ambientWidget
    , fanWidget
    , heatWidget
    , coolWidget;

function page(screen, index) {
    // current room
    room = data[index];

    var grid = new contrib.grid({ rows: 12, cols: 12, screen: screen })

    var nameWidget = blessed.text({ content: room.name, align: "right" });
    screen.append(nameWidget);

    pointWidget = grid.set(0, 2, 4, 2, contrib.donut,
        {
            label: 'Thermostat',
            radius: 16,
            arcWidth: 4,
            yPadding: 2,
            data: [{ label: 'Set Point', percent: room.sensors.thermostat.setpoint }]
        });

    ambientWidget = grid.set(0, 4, 2, 3, contrib.lcd,
        {
            label: 'Ambient',
            segmentWidth: 0.06,
            segmentInterval: 0.11,
            strokeWidth: 0.5,
            elements: 5,
            display: 3210,
            elementSpacing: 4,
            elementPadding: 2,
            color: 'green'
        }
    );
    ambientWidget.setDisplay(room.sensors.thermostat.temp);

    fanWidget = grid.set(0, 8, 2, 3, contrib.lcd,
        {
            label: 'Fan Speed',
            segmentWidth: 0.06,
            segmentInterval: 0.11,
            strokeWidth: 0.5,
            elements: 5,
            display: 3210,
            elementSpacing: 4,
            elementPadding: 2,
            color: 'white'
        }
    );
    fanWidget.setDisplay((room.sensors.blower.fan == 0? "OFF": room.sensors.blower.fan));

    heatWidget = grid.set(2, 8, 2, 3, contrib.lcd,
        {
            label: 'Heat',
            segmentWidth: 0.06,
            segmentInterval: 0.11,
            strokeWidth: 0.5,
            elements: 5,
            display: 3210,
            elementSpacing: 4,
            elementPadding: 2,
            color: 'green'
        }
    );
    heatWidget.setDisplay(room.sensors.blower.heat === true ? "ON" : "OFF");

    coolWidget = grid.set(4, 8, 2, 3, contrib.lcd,
        {
            label: 'Cool',
            segmentWidth: 0.06,
            segmentInterval: 0.11,
            strokeWidth: 0.5,
            elements: 5,
            display: 3210,
            elementSpacing: 4,
            elementPadding: 2,
            color: 'red'
        }
    );
    coolWidget.setDisplay(room.sensors.blower.cool === true ? "ON" : "OFF");

    var box = blessed.box({ content: 'use right-left arrows to switch rooms, \'w\',\'s\' to adjust thermostat set point, \'p\',\'l\' to adjust ambient temperature', top: '80%', left: '12%' })
    screen.append(box)
}

function calculate() {
    // reset for calculation
    room.sensors.blower.fan = 0;
    room.sensors.blower.heat = false;
    room.sensors.blower.cool = false;

    var diff;

    if (room.sensors.thermostat.temp < room.sensors.thermostat.setpoint) {
        room.sensors.blower.heat = true;
        diff = room.sensors.thermostat.setpoint - room.sensors.thermostat.temp;
    } else {
        room.sensors.blower.cool = true;
        diff = room.sensors.thermostat.temp - room.sensors.thermostat.setpoint;
    }

    if (diff < 3)
        room.sensors.blower.fan = 1;
    else if (diff < 5)
        room.sensors.blower.fan = 2;
    else
        room.sensors.blower.fan = 3;

    // update db
    fs.writeFileSync('device-values.json', JSON.stringify(data, null /* null transformer */, 4 /* spacing level 4*/));

    // update widgets
    fanWidget.setDisplay((room.sensors.blower.fan == 0? "OFF": room.sensors.blower.fan));
    heatWidget.setDisplay(room.sensors.blower.heat === true ? "ON" : "OFF");
    coolWidget.setDisplay(room.sensors.blower.cool === true ? "ON" : "OFF");

    // render
    screen.render();
}

// handle heystrokes
screen.key(['escape', 'q', 'C-c'], function (ch, key) {
    return process.exit(0);
});

screen.key(['w', 's'], function (ch, key) {
    if (key.name === "w")
        room.sensors.thermostat.setpoint += 1;
    else
        room.sensors.thermostat.setpoint -= 1;

    pointWidget.setData([{ label: 'Set Point', percent: room.sensors.thermostat.setpoint.toFixed(1) }]);
    calculate();
});

screen.key(['p', 'l'], function (ch, key) {
    if (key.name === "p")
        room.sensors.thermostat.temp = ((room.sensors.thermostat.temp * 10) + (0.1 * 10)) / 10  // cater for js float precision
    else
        room.sensors.thermostat.temp = ((room.sensors.thermostat.temp * 10) - (0.1 * 10)) / 10 // cater for js float precision

    // update ui
    ambientWidget.setDisplay(room.sensors.thermostat.temp);
    calculate();
});

// display carousel of rooms
var carousel = new contrib.carousel([page, page]
    , {
        screen: screen
        , controlKeys: true
    });

carousel.start()
