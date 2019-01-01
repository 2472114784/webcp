export const bannerApi = {
  /**
   * 获取banner数据
   * @param a
   * @param b
   * @returns {{method: string, data: {}, url: string}}
   */
  banner: function () {
    return {
      method: 'get',
      url: '/banner',
      data: {}
    }
  },
  /**
   * 更新Banner数据
   * @RequestParam long id, @RequestParam String bannerTitle, @RequestParam String bannerContent, @RequestParam String bannerUrl, @RequestParam int bannerType
   * @returns {{method: string, data: {}, url: string}}
   */
  updateBanner: function (id, bannerTitle, bannerContent, bannerUrl, bannerType) {
    return {
      method: 'get',
      url: '/updateBanner',
      data: {
        'id': id,
        'bannerTitle': bannerTitle,
        'bannerContent': bannerContent,
        'bannerUrl': bannerUrl,
        'bannerType': bannerType,
      }
    }
  },
  /**
   * 添加banner
   * @param bannerTitle
   * @param bannerContent
   * @param bannerUrl
   * @param bannerType
   * @returns {{method: string, data: {bannerTitle: *, bannerType: *, bannerUrl: *, bannerContent: *}, url: string}}
   */
  addBanner: function (bannerTitle, bannerContent, bannerUrl, bannerType) {
    return {
      method: 'get',
      url: '/addBanner',
      data: {
        'bannerTitle': bannerTitle,
        'bannerContent': bannerContent,
        'bannerUrl': bannerUrl,
        'bannerType': bannerType,
      }
    }
  }
}
