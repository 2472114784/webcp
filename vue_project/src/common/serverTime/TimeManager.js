class TimeManager {
  getServerTime() {
    var now = new Date().getTime();
    return this.offerServerTime + now;
  }

  setServerTime(serverTime) {
    if (serverTime) {
      var now = new Date().getTime();
      this.offerServerTime = serverTime - now;
    }
  }

}

export default new TimeManager();
