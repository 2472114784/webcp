/**
 * vuex store 插件办法
 * */
let globalStore;
const setStore = store =>{
  globalStore = store;
};
export const getStore  = () => globalStore;

export default [setStore];
