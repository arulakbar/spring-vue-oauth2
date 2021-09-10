import { createStore } from "vuex";

const baseUrl = "http://localhost:8080/";

export default createStore({
  state: {
    // auth: null,
    currentUser: null,
  },
  mutations: {
    setUser(state, payload) {
      state.currentUser = payload;
    },
  },
  actions: {
    async fetchUser(context) {
      await fetch("http://localhost:8080/profile", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      })
        .then((res) => res.json())
        .then((data) => {
          context.commit("setUser", data.name);
          // console.log(data);
        });
    },
    setToken(context, payload) {
      localStorage.setItem("token", payload.token);
    },
    logout(context) {
      localStorage.removeItem("token");
      context.commit("setUser", null);
    },
  },
  getters: {
    isAuthenticated: (state) => {
      return state.currentUser;
    },
  },
  modules: {},
});
