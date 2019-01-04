import cloneDeep from 'clone-deep';

//数据处理utils
class DataUtils {
  /**
   *
   * @param lotteryChildEntity
   */
  createLotteryChildChooseData(lotteryChildEntity) {
    let duplexNum = lotteryChildEntity.lotteryChildDuplexNum;
    let result = []
    for (let i = 0; i < duplexNum; i++) {
      result[i] = cloneDeep(lotteryChildEntity.lotteryItemEntityList)
    }
    ;
    console.log("result", result)
    return result;
  }
}

const dataUtils = new DataUtils();
export default dataUtils;
