const fs = require("fs");
fs.readFile(process.argv[2], function(err, data) {
    if (err) throw err;
    const users = JSON.parse(data);
    console.log(users);
});