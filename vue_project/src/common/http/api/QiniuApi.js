/**
 * 公钥
 */
export const ACCESS_KEY = "Kj3qMNNio-ig2uYQZUJiWFjZflDOKw41OEBfx8q2";
/**
 * 私钥
 */
export const BUCKET_KEY = "C_6vbbt90jokhY2PJGLgoWZDriTPA8syOc_Od3LF";
/**
 * 存储命名空间
 */
export const BUCKET_SPACE_NAME = "cp-images";
/**
 * token 有效时间（十年）
 */
export const TOKEN_EFFECTIVE_TIME = 10 * 365 * 24 * 60 * 60 * 1000;
/**
 * 请求地址前缀
 */
export const BUCKET_HTTP_PRE = "http://pflfreg6m.bkt.clouddn.com/";
export default {
  getQiniuToken: function (accessKey) {
    return {
      method: 'get',
      url: '/getQiniuToken',
      data: {
        'accessKey': accessKey
      }
    }
  }
}
