const os=require('os');
var ip=os.networkInterfaces();
var address;
for (var x in ip) {
    var x2 = ip[x].filter(function(i) {
        return i.family === 'IPv4' && i.internal === false;
    });
    if(x2.length > 0) address = x2[0].address;
}
console.log(address);