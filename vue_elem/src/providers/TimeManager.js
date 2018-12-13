 var TimeManager = function() {
    this.offerServerTime=0;

    this.getServerTime=function(){
        var now=new Date().getTime();
        return this.offerServerTime+now;
    }

    this.setServerTime=function(serverTime){
        if(serverTime){
            var now=new Date().getTime();
            this.offerServerTime=serverTime-now;
        }
    }
    //一个标记，用来判断是否已将创建了该类的实例
    this.instance = null;
}
// 提供了一个静态方法，用户可以直接在类上调用
 TimeManager.getInstance = function() {
    // 没有实例化的时候创建一个该类的实例
    if(!this.instance) {
        this.instance = new TimeManager(name);
    }
    // 已经实例化了，返回第一次实例化对象的引用
    return this.instance;
}

const timeManager = {
    getServerTime:function(){
        return TimeManager.getInstance().getServerTime();
    },
    setServerTime:function(serverTime){
        TimeManager.getInstance().setServerTime(serverTime);
    }
  }
  export {timeManager};