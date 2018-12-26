import * as types from './demo_types'
import axios from '../../../common/http'
import api from '../demo_api'

// 测试 postAPI
export const deleteCount = ({commit},option={}) =>{
  return new Promise((resolve, reject)=>{

    axios(api.testPost,option).then((res)=>{
      if(res.data.recode == '0000'){
        commit(types.DEL_COUNT,res.data.count);
        resolve(res);
      }else {
        reject(res);
      }
    },err =>{
      reject(err);
    });

  })
};


// 测试 getAPI
export const addCount = ({commit},option={}) =>{
  return new Promise((resolve, reject)=>{

    console.log('action 传递的参数：',option);
    axios(api.testGet,option).then((res)=>{
      if(res.data.recode  == '0000'){
        commit(types.ADD_COUNT,res.data.count);
        resolve(res);
      }else {
        reject();
      }
    },err =>{
      reject();
    });
  })
};

// 测试errorAPI
export const errorTest = ({commit},option={}) =>{
  return new Promise((resolve, reject)=>{
    axios(api.testErr).then((res)=>{
      if(res.data.recode  = '0000'){
        resolve(res);
      }else {
        reject(res);
      }
    },err =>{
      reject(err);
    });
  })
};
