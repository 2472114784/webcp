import axios from 'axios';
import {Message, Loading} from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import TimeManager from '../serverTime/TimeManager'

axios.defaults.timeout = 5000;
axios.defaults.baseURL = 'http://47.75.241.94:8080/cpapi'; //填写域名

//http request 拦截器
axios.interceptors.request.use(
  config => {
    config.data = JSON.stringify(config.data);
    config.headers = {
      'Content-Type': 'application/x-www-form-urlencoded',
      // "Content-Type": "application/json",
      'token': '22E1CEDCD53248ABB79CB734C5EC9405',
    }
    return config;
  },
  error => {
    return Promise.reject(err);
  }
);

//响应拦截器即异常处理
axios.interceptors.response.use(response => {
  return response
}, err => {
  if (err && err.response) {
    switch (err.response.status) {
      case 400:
        Message.error('错误请求');
        break;
      case 401:
        Message.error('未授权，请重新登录')
        break;
      case 403:
        Message.error('拒绝访问')
        break;
      case 404:
        Message.error('请求错误,未找到该资源')
        break;
      case 405:
        Message.error('请求方法未允许')
        break;
      case 408:
        Message.error('请求超时')
        break;
      case 500:
        Message.error('服务器端出错')
        break;
      case 501:
        Message.error('网络未实现')
        break;
      case 502:
        Message.error('网络错误')
        break;
      case 503:
        Message.error('服务不可用')
        break;
      case 504:
        Message.error('网络超时')
        break;
      case 505:
        Message.error('http版本不支持该请求')
        break;
      default:
        Message.error(`连接错误${err.response.status}`)
    }
  } else {
    Message.error('连接到服务器失败')
  }
  return Promise.resolve(err.response)
})


/**
 * 封装get方法
 * @param url
 * @param data
 * @returns {Promise}
 */

export function fetch(url, params = {}) {
  return new Promise((resolve, reject) => {
    axios.get(url, {
      params: params
    })
      .then(response => {
        handlerResponse(resolve, reject, response.data);
      })
      .catch(err => {
        reject(err)
      })
  })
}


/**
 * 封装post请求
 * @param url
 * @param data
 * @returns {Promise}
 */

export function post(url, data = {}) {
  return new Promise((resolve, reject) => {
    axios.post(url, data)
      .then(response => {
        handlerResponse(resolve, reject, response.data);
      }, err => {
        reject(err)
      })
  })
}

/**
 * 封装patch请求
 * @param url
 * @param data
 * @returns {Promise}
 */

export function patch(url, data = {}) {
  return new Promise((resolve, reject) => {
    axios.patch(url, data)
      .then(response => {
        handlerResponse(resolve, reject, response.data);
      }, err => {
        reject(err)
      })
  })
}

/**
 * 封装put请求
 * @param url
 * @param data
 * @returns {Promise}
 */

export function put(url, data = {}) {
  return new Promise((resolve, reject) => {
    axios.put(url, data)
      .then(response => {
        handlerResponse(resolve, reject, response.data);
      }, err => {
        reject(err)
      })
  })
}

/**
 * 处理服务器数据
 * @param resolve
 * @param reject
 * @param response
 */
function handlerResponse(resolve, reject, result) {
  if (result.code == 200) {
    //成功
    TimeManager.setServerTime(result.serverTime);
    resolve(result.data);
  } else {
    //失败
    reject(result)
  }
}

export function http(params) {
  if (checkParams(params)) {
    let httpObj;
    switch (params.method) {
      case 'get':
        httpObj = fetch(params.url, params.data);
        break;
      case 'put':
        httpObj = put(params.url, params.data);
        break;
      case 'patch':
        httpObj = patch(params.url, params.data);
        break;
      case 'post':
        httpObj = post(params.url, params.data);
        break;
    }
    return httpObj;
  }
}

/***
 * 检查请求数据
 * @param params
 * @returns {boolean}
 */
function checkParams(params) {
  if (!params) {
    return false;
  }
  if (['get', 'put', 'patch', 'post'].indexOf(params.method) == -1) {
    return false;
  }
  if (!params.url) {
    return false;
  }
  return true;
}

/**
 * 下面是获取数据的接口
 */
/**
 * 测试接口
 * 名称：exam
 * 参数：paramObj/null
 * 方式：fetch/post/patch/put
 */
export const server = {
  banner: function (a, b) {
    return {
      method: 'get',
      url: '/banner',
      data: {
        'a': a,
        'b': b
      }
    }
  },
  lottery: function () {
    return {
      method: 'get',
      url: '/getLotteryById',
      data: {
        'lottery_id': 30000,
      }
    }
  },
  exam: function () {
    return http(this.banner());
  },
  examLottery: function () {
    return http(this.lottery());
  }
}
