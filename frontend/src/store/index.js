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
      fetch("http://localhost:8080/v1/home", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      })
        .then((res) => res.json())
        .then((data) => context.commit("setUser", data.name));
    },
    async setToken(context, payload) {
      await fetch(
        baseUrl +
          "login/oauth2/code/github?code=" +
          payload.code +
          "&state=" +
          payload.state
      )
        .then((res) => res.json())
        .then((data) => {
          localStorage.setItem("token", data.accessToken);
          console.log(data.accessToken);
        });
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
