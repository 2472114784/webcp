export const ConfigApi = {
  updateApkInfo: function (type) {
    return {
      method: 'get',
      url: '/updateApkInfo',
      data: {
        'type': type
      }
    }
  }
}
