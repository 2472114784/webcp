export const QiniuApi = {
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
