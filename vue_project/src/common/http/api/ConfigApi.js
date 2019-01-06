export const APK_UPDATE_TYPE_ANDROID = 1;
export const APK_UPDATE_TYPE_IOS = 2;
export default {
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
