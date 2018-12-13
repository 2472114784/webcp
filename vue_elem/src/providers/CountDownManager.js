Array.prototype.indexOf = function(val) {
    for (var i = 0; i < this.length; i++) {
    if (this[i] == val) return i;
    }
    return -1;
    };

    Array.prototype.remove = function(val) {
        var index = this.indexOf(val);
        if (index > -1) {
        this.splice(index, 1);
        }
        };

function createIterator(items) {
    var i = 0;
    return {
        hasNext:function(){
            console.log("i="+i+"   length="+items.length)
            return i < items.length;
        },
        next: function() {
            var value = this.hasNext() ? items[i++] : undefined;
            return  value;
        }
    };
}
//倒计时注册管理者
var CountDownManager = function() {
    this.countDownListeners=[];

    this.register=function(countDownListener){
        if(countDownListener&&this.countDownListeners.indexOf(countDownListener)==-1){
            //添加监听者
            this.countDownListeners.push(countDownListener);
            console.log('注册监听者'+this.countDownListeners.length)
            IntervalManager.getInstance().startCountDown(this.doEvent);
        }
    }

    this.unRegister=function(countDownListener){
        // if(countDownListener&&this.countDownListeners.indexOf(countDownListener)>=0){
            //注销监听者
            this.countDownListeners.remove(countDownListener);
            if(this.countDownListeners.length<=0){
                IntervalManager.getInstance().stopCountDown();
            }
        // }
    }

    this.doEvent=function(){
        console.log('准备开始doEvent'+CountDownManager.getInstance().countDownListeners.length)
        if(CountDownManager.getInstance().countDownListeners){
            var iterator=createIterator(CountDownManager.getInstance().countDownListeners)
            console.log('准备开始doEvent遍历')
            while(iterator.hasNext()){
                let item=iterator.next();
                console.log('准备开始doEvent执行对象item='+item);
                if(item){
                    console.log('准备开始doEvent执行')
                    item();
                }
            }
        }
    }
    //一个标记，用来判断是否已将创建了该类的实例
    this.instance = null;
}


// 提供了一个静态方法，用户可以直接在类上调用
CountDownManager.getInstance = function() {
    // 没有实例化的时候创建一个该类的实例
    if(!this.instance) {
        this.instance = new CountDownManager();
    }
    // 已经实例化了，返回第一次实例化对象的引用
    return this.instance;
}
// 定时器管理者
var IntervalManager=function(){
    this.instance=null;
    this.timer;
    this.startCountDown=function(intervalEvent){
        console.log('准备开启倒计时')
        if(!this.timer){
            console.log('开始倒计时')
            this.timer=setInterval(intervalEvent,1000);
        }
    };
    this.stopCountDown=function(){
        if(this.timer){
            clearInterval(this.timer);
            this.timer=null;
        }
    }
}

// 提供了一个静态方法，用户可以直接在类上调用
IntervalManager.getInstance = function() {
    // 没有实例化的时候创建一个该类的实例
    if(!this.instance) {
        this.instance = new IntervalManager();
    }
    // 已经实例化了，返回第一次实例化对象的引用
    return this.instance;
}
const countDownManager = {
    register:function(registerListener){
        return CountDownManager.getInstance().register(registerListener);
    },
    unRegister:function(registerListener){
        return CountDownManager.getInstance().unRegister(registerListener);
    },
  }
  export {countDownManager};