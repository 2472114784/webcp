export const KEY_SET_USER = 'UserStoreModule/setUser';
// initial state
const state = {
  user: null
};

// getters
const getters = {};

// actions
const actions = {
  // getAllProducts ({ commit }) {
  //   shop.getProducts(products => {
  //     commit('setProducts', products)
  //   })
  // }
};

// mutations
const mutations = {
  setUser(state, user) {
    console.log("state_setUser", user);
    state.user = user;
  },
  // setProducts (state, products) {
  //   state.all = products
  // },
  //
  // decrementProductInventory (state, { id }) {
  //   const product = state.all.find(product => product.id === id)
  //   product.inventory--
  // }
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
