import demo from './demo_module'

export default function(store){
  store.registerModule('demoState',demo);
}

