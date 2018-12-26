var express = require('express');
var app = express();

app.get('/testAPI/get', function (req, res) {
  res.json({
    recode: '0000', // 状态码表示正确
    msg: 'you test get API +1',
    count: 1
  });
});

app.post('/testAPI/post', function (req, res) {
  res.json({
    recode: '0000', // 状态码表示正确
    msg: 'you test post API -1',
    count: 1
  });
});

app.post('/err',function (req, res) {
  res.json({
    recode: '0001', // 状态码表示错误
    msg: 'you test err API'
  });
});

var server = app.listen(3000, function () {
  var host = server.address().address;
  var port = server.address().port;

  console.log('Example app listening at http://%s:%s', host, port);
});
