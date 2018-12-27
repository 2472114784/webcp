export const api = {
  /**
   * banner数据
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
  }
}
