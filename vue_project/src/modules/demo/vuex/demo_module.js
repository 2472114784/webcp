import * as types from './demo_types'
import * as actions from './demo_actions'

const state = {
  count: 1
};

const mutations = {
  [types.ADD_COUNT](state,params){
    state.count += params;
  },
  [types.DEL_COUNT](state,params){
    state.count -= params;
  },
  [types.INIT_COUNT](state){
    state.count = 1;
  }
};

const getters = {
  getCount: state => state.count
};

export default {
  state,
  actions,
  getters,
  mutations
}
