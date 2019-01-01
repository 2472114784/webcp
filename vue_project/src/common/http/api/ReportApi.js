//TODO
export const ReportApi = {
  getListReportByUserId: function (page) {
    return {
      method: 'get',
      url: '/getListReportByUserId',
      data: {
        'page': page,
      }
    }
  },
  adminGetListReportByUserId: function (userId, page) {
    return {
      method: 'get',
      url: '/getListReportByUserId',
      data: {
        'userId': userId,
        'page': page,
      }
    }
  }
}
