import axios from 'axios'
import qs from 'qs'
import {Message, Loading} from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// 请求拦截
axios.interceptors.request.use(config => {

  return config;
}, error => {
  return Promise.reject(error);
});

// 响应拦截
axios.interceptors.response.use(response => {
  return response;
}, error => {
  return Promise.reject(error.response);
});


function errorState(response) {

  // 状态码正确直接返回数据
  if (response && (response.status == 200 || response.status == 304 || response.status == 400)) {
    return response;
  } else {
    Message.error('网络异常');
  }
}

function successState(res) {
  // 统一判断后端返回的错误码
  if (res.data.recode == '0001') {
    Message.error('网络异常')
  } else if (res.data.recode != '0002' && res.data.recode != '0003') {
    // Message.error('网络异常22')
  }
}

const heepServer = (opts, data) => {
  let Public = {// 公用参数 --默认会加到请求中
    // 'srAppid':""
  };
  opts.method = opts.method || 'post';
  let httpDefaultOpts = { //http默认配置
    method: opts.method,
    baseURL: '',  //默认的头地址
    url: opts.url,
    timeout: 10000,
    params: Object.assign(Public, data),
    data: qs.stringify(Object.assign(Public, data)),
    headers: opts.method == 'get' ? {
      'X-Requested-With': 'XMLHttpRequest',
      "Accept": "application/json",
      "Content-Type": "application/json; charset=UTF-8"
    } : {
      'X-Requested-With': 'XMLHttpRequest',
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    }
  };

  if (opts.method == 'get') {
    delete httpDefaultOpts.data
  } else {
    delete httpDefaultOpts.params
  }

  let promise = new Promise(function (resolve, reject) {
    axios(httpDefaultOpts).then(
      (res) => {
        successState(res)
        resolve(res)
      }
    ).catch(
      (response) => {
        errorState(response)
        reject(response)
      }
    )

  });

  return promise;
};

export default heepServer;
