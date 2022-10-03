const redis = require('redis');
var nodemailer = require('nodemailer');
const client = redis.createClient();

client.on('connect', function() {
  console.log('Connected!');
});

client.get('E-mail', function(err, reply) {
  console.log(reply);
  var transporter = nodemailer.createTransport({
  service: 'gmail',
  auth: {
    user: 'user@gmail.com',
    pass: ''
  }
});

var mailOptions = {
  from: 'user@gmail.com',
  to: reply,
  subject: 'Hello User',
  text: 'Welcome User thank you for using our services good luck'
};

transporter.sendMail(mailOptions, function(error, info){
  if (error) {
    console.log(error);
  } else {
    console.log('Email sent: ' + info.response);
  }
});
});
