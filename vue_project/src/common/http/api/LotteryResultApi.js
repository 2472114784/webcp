//TODO
export default {
  getListLotteryResultByLotteryId: function (lotteryId, page) {
    return {
      method: 'get',
      url: '/getListLotteryResultByLotteryId',
      data: {
        'lotteryId': lotteryId,
        'page': page,
      }
    }
  }
}
